package com.transparent.politics.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.transparent.politics.dao.GovMemberDAO;
import com.transparent.politics.dao.cache.CacheManager;
import com.transparent.politics.dao.cache.InMemoryCacheManager;
import com.transparent.politics.services.data.GovMember;
import com.transparent.politics.services.data.GovMembersDataStore;

@Component
public class GovMemberService {

    private static final String GOV_MEMBER_DATA_STORE_CACHE_KEY = "govMemberDataStore";

    private static final int MAX_SECONDS_TO_WAIT = 60;

    @Qualifier(InMemoryCacheManager.BEAN_QUALIFIER)
    @Autowired
    private CacheManager cacheManager;
    
    @Autowired
    private GovMemberDAO govMemberDAO;
    
    private AtomicBoolean isCalculating = new AtomicBoolean(false);
    
    public GovMembersDataStore getGovMemberDataStore() throws Exception {
        GovMembersDataStore govMembersDataStore = cacheManager.get(GOV_MEMBER_DATA_STORE_CACHE_KEY, GovMembersDataStore.class);
        if (govMembersDataStore == null) {
            recalculateMemberAndPartyGrades();
            govMembersDataStore = cacheManager.get(GOV_MEMBER_DATA_STORE_CACHE_KEY, GovMembersDataStore.class);
        }
        return govMembersDataStore;
    }
    
    public void recalculateMemberAndPartyGrades() throws Exception {
        if (isCalculating.get()) {
            System.out.println("Recalculation is already running. Waiting for it to finish");
            waitForRecalculationToFinish(MAX_SECONDS_TO_WAIT);
            return;
        }
        isCalculating.set(true);
        long startTime = new Date().getTime();
        System.out.println("Recalculating grades: started");
        
        List<GovMember> allGovMembers = govMemberDAO.getAllGovMemebersDetailed();
        
        Map<GovMember, Integer> memberToWeeklyPresenceGradeMap = getMemberToWeeklyPresenceGradeMap(allGovMembers);
        
        Map<Integer, GovMember> memberIdToMember = new HashMap<>(allGovMembers.size());
        Map<Integer, Integer> memberIdToGrade = new HashMap<>(allGovMembers.size());
        
        for (GovMember govMember : memberToWeeklyPresenceGradeMap.keySet()) {
            memberIdToMember.put(govMember.getId(), govMember);
            Integer weeklyPresenceGrade = memberToWeeklyPresenceGradeMap.get(govMember);
            Integer memberOverallGrade = getMemberGrade(govMember, weeklyPresenceGrade);
            memberIdToGrade.put(govMember.getId(), memberOverallGrade);
        }
        
        try {
            cacheManager.lockCache();
            GovMembersDataStore govMemberDataStore = new GovMembersDataStore(memberIdToMember, memberIdToGrade);
            cacheManager.set(GOV_MEMBER_DATA_STORE_CACHE_KEY, govMemberDataStore);
        } finally {
            cacheManager.unlockCache();
        }
        
        isCalculating.set(false);
        long endTime = new Date().getTime();
        double durationInSeconds = (endTime - startTime)/1000.0;
        System.out.println("Recalculating grades: finished. Took " + durationInSeconds + " seconds.");
    }

    private Integer getMemberGrade(GovMember member, int memberWeeklyPresenceGrade) throws IOException {
    	// The presence grade is between 0 and 119. We want to get a grade in the range [0,100]
        return (int) ((memberWeeklyPresenceGrade+1)/(1.2));
    }
    
    private Map<GovMember, Integer> getMemberToWeeklyPresenceGradeMap(List<GovMember> allGovMembers) throws IOException {
    	List<GovMember> weeklyPresenceHoursList = new ArrayList<>(allGovMembers);
    	Collections.sort(weeklyPresenceHoursList, new Comparator<GovMember>() {

			@Override
			public int compare(GovMember o1, GovMember o2) {
				if (o1.getAverage_weekly_presence_hours() == null && o2.getAverage_weekly_presence_hours() == null) {
					return 0;
				}
				if (o1.getAverage_weekly_presence_hours() == null) {
					return -1;
				}
				if (o2.getAverage_weekly_presence_hours() == null) {
					return 1;
				}
				return o1.getAverage_weekly_presence_hours().compareTo(o2.getAverage_weekly_presence_hours());
			}
		});
    	
    	Map<GovMember, Integer> memberToWeeklyPresenceGradeMap = convertListToMapFromElementToIndex(weeklyPresenceHoursList);
    	
    	return memberToWeeklyPresenceGradeMap;
    }
    
    private <T> Map<T, Integer> convertListToMapFromElementToIndex(List<T> elementsList) {
        Map<T, Integer> indexMap = new HashMap<>(elementsList.size());
        for (int i=0; i<elementsList.size(); i++) {
            T element = elementsList.get(i);
            indexMap.put(element, i);
        }
        return indexMap;
    }
    
    private void waitForRecalculationToFinish(int maxSecondsToWait) {
        int secondsWaited = 0;
        while (isCalculating.get() && secondsWaited <= maxSecondsToWait) {
            try {
                Thread.sleep(1000);
                secondsWaited++;
            } catch (InterruptedException e) {
            }
        }
        if (secondsWaited > maxSecondsToWait) {
            throw new RuntimeException("Timeout waiting for recalculation");
        }
    }
    
}
