package com.transparent.politics.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.transparent.politics.common.Config;
import com.transparent.politics.dao.GovMemberDAO;
import com.transparent.politics.dao.cache.CacheManager;
import com.transparent.politics.services.data.GovMember;
import com.transparent.politics.services.data.GovMembersDataStore;

@Component
public class GovMemberService {

    private static final String GOV_MEMBER_DATA_STORE_CACHE_KEY = "govMemberDataStore";

    private static final int MAX_SECONDS_TO_WAIT = 60;

    @Qualifier(Config.CACHE_PROVIDER_BEAN_QUALIFIER)
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
        
        Map<GovMember, Integer> weeklyPresenceGradeMap = getWeeklyPresenceGradeMap(allGovMembers);
        Map<GovMember, Integer> monthlyCommitteePresenceGradeMap = getMonthlyCommitteePresenceGradeMap(allGovMembers);
        Map<GovMember, Integer> billsPreGradeMap = getBillsPreGradeMap(allGovMembers);
        Map<GovMember, Integer> billsProposedGradeMap = getBillsProposedGradeMap(allGovMembers);
        Map<GovMember, Integer> billsApprovedGradeMap = getBillsApprovedGradeMap(allGovMembers);
        
        Map<Integer, GovMember> memberIdToMember = new HashMap<>(allGovMembers.size());
        Map<Integer, Integer> memberIdToGrade = new HashMap<>(allGovMembers.size());
        
        for (GovMember govMember : weeklyPresenceGradeMap.keySet()) {
            memberIdToMember.put(govMember.getId(), govMember);
            Integer weeklyPresenceGrade = weeklyPresenceGradeMap.get(govMember);
            Integer monthlyCommitteePresenceGrade = monthlyCommitteePresenceGradeMap.get(govMember);
            Integer billsPreGrade = billsPreGradeMap.get(govMember);
            Integer billsProposedGrade = billsProposedGradeMap.get(govMember);
            Integer billsApprovedGrade = billsApprovedGradeMap.get(govMember);
            
            Integer memberOverallGrade = getMemberGrade(govMember, weeklyPresenceGrade, monthlyCommitteePresenceGrade, billsPreGrade, billsProposedGrade, billsApprovedGrade);
            memberIdToGrade.put(govMember.getId(), memberOverallGrade);
        }
        
        GovMembersDataStore govMemberDataStore = new GovMembersDataStore(memberIdToMember, memberIdToGrade);
        cacheManager.set(GOV_MEMBER_DATA_STORE_CACHE_KEY, govMemberDataStore);
        
        isCalculating.set(false);
        long endTime = new Date().getTime();
        double durationInSeconds = (endTime - startTime)/1000.0;
        System.out.println("Recalculating grades: finished. Took " + durationInSeconds + " seconds.");
    }

    private Integer getMemberGrade(GovMember member, int memberWeeklyPresenceGrade, int monthlyCommitteePresenceGrade, int billsPreGrade, int billsProposedGrade, int billsApprovedGrade) throws IOException {
    	int gradeSum = 0;
    	
    	// TODO num factors should take into account grades we don't have (should have -1)
    	int numFactors = 5;
    	
    	gradeSum = memberWeeklyPresenceGrade + monthlyCommitteePresenceGrade + billsPreGrade + billsProposedGrade + billsApprovedGrade;
        
        // The presence grade is between 0 and 119. We want to get a grade in the range [0,100]
        return (int) ((gradeSum)/(1.2)/numFactors);
    }
    
    private Map<GovMember, Integer> getBillsPreGradeMap(List<GovMember> allGovMembers) throws IOException {
        List<GovMember> billsPreList = new ArrayList<>(allGovMembers);
        Collections.sort(billsPreList, new GovMemberComparators.BillsPreGovMemberComparator());
        
        Map<GovMember, Integer> memberToBillsPreGradeMap = convertListToMapFromElementToIndex(billsPreList);
        
        return memberToBillsPreGradeMap;
    }

    private Map<GovMember, Integer> getBillsProposedGradeMap(List<GovMember> allGovMembers) throws IOException {
        List<GovMember> billsProposedList = new ArrayList<>(allGovMembers);
        Collections.sort(billsProposedList, new GovMemberComparators.BillsProposedGovMemberComparator());
        
        Map<GovMember, Integer> memberToBillsProposedGradeMap = convertListToMapFromElementToIndex(billsProposedList);
        
        return memberToBillsProposedGradeMap;
    }
    
    private Map<GovMember, Integer> getBillsApprovedGradeMap(List<GovMember> allGovMembers) throws IOException {
        List<GovMember> billsApprovedList = new ArrayList<>(allGovMembers);
        Collections.sort(billsApprovedList, new GovMemberComparators.BillsApprovedGovMemberComparator());
        
        Map<GovMember, Integer> memberToBillsApprovedGradeMap = convertListToMapFromElementToIndex(billsApprovedList);
        
        return memberToBillsApprovedGradeMap;
    }
    
    private Map<GovMember, Integer> getWeeklyPresenceGradeMap(List<GovMember> allGovMembers) throws IOException {
    	List<GovMember> weeklyPresenceHoursList = new ArrayList<>(allGovMembers);
    	Collections.sort(weeklyPresenceHoursList, new GovMemberComparators.WeeklyPresenceGovMemberComparator());
    	
    	Map<GovMember, Integer> memberToWeeklyPresenceGradeMap = convertListToMapFromElementToIndex(weeklyPresenceHoursList);
    	
    	return memberToWeeklyPresenceGradeMap;
    }
    
    private Map<GovMember, Integer> getMonthlyCommitteePresenceGradeMap(List<GovMember> allGovMembers) throws IOException {
        List<GovMember> monthlyCommitteePresenceList = new ArrayList<>(allGovMembers);
        Collections.sort(monthlyCommitteePresenceList, new GovMemberComparators.MonthlyCommitteePresenceGovMemberComparator());
        
        Map<GovMember, Integer> memberMonthlyCommitteePresenceGradeMap = convertListToMapFromElementToIndex(monthlyCommitteePresenceList);
        
        return memberMonthlyCommitteePresenceGradeMap;
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
