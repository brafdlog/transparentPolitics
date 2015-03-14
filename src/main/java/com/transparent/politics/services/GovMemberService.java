package com.transparent.politics.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.PostConstruct;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.transparent.politics.common.Config;
import com.transparent.politics.dao.GovMemberDAO;
import com.transparent.politics.dao.GovPartyDAO;
import com.transparent.politics.dao.cache.CacheManager;
import com.transparent.politics.services.data.GovMember;
import com.transparent.politics.services.data.GovMemberAverages;
import com.transparent.politics.services.data.GovMembersDataStore;
import com.transparent.politics.services.data.GovParty;

@Component
public class GovMemberService {

    private static final String GOV_MEMBER_DATA_STORE_CACHE_KEY = "govMemberDataStore";

    private static final int MAX_SECONDS_TO_WAIT = 60;

    @Qualifier(Config.CACHE_PROVIDER_BEAN_QUALIFIER)
    @Autowired
    private CacheManager cacheManager;
    
    @Autowired
    private GovMemberDAO govMemberDAO;
    
    @Autowired
    private GovPartyDAO govPartyDAO;
    
    private AtomicBoolean isCalculating = new AtomicBoolean(false);
    
    @PostConstruct
    public void init() {
        try {
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        getGovMemberDataStore();
                    } catch (Exception e) {
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        } catch (Exception e) {
            System.out.println("Failed calculating members on init " + e);
            e.printStackTrace();
        }
    }
    
    public GovMembersDataStore getGovMemberDataStore() throws Exception {
        return getGovMemberDataStore(true);
    }
    
    public GovMembersDataStore getGovMemberDataStore(boolean calculateIfNotInCache) throws Exception {
        GovMembersDataStore govMembersDataStore = cacheManager.get(GOV_MEMBER_DATA_STORE_CACHE_KEY, GovMembersDataStore.class);
        if (govMembersDataStore == null) {
            if (!calculateIfNotInCache) {
                return null;
            }
            recalculateMemberAndPartyGrades();
            govMembersDataStore = cacheManager.get(GOV_MEMBER_DATA_STORE_CACHE_KEY, GovMembersDataStore.class);
        } else {
            int daysSinceLastCalculation = Days.daysBetween(new LocalDate(govMembersDataStore.getCalculationDate()), new LocalDate()).getDays();
            if (daysSinceLastCalculation > Config.DAYS_UNTIL_RECALCULATE && !isCalculating.get()) {
                new Thread(new Runnable() {
                    
                    @Override
                    public void run() {
                        try {
                            recalculateMemberAndPartyGrades();
                        } catch (Exception e) {
                            System.out.println("Error recalculating. " + e.getStackTrace());
                        }
                    }
                }).start();
            }
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

        GovMemberAverages govMemberAverages = getGovMemberAverages(allGovMembers);
        
        Map<Integer, GovMember> memberIdToMember = new HashMap<>(allGovMembers.size());
        Map<Integer, Integer> memberIdToGrade = new HashMap<>(allGovMembers.size());
        
        for (GovMember govMember : weeklyPresenceGradeMap.keySet()) {
            memberIdToMember.put(govMember.getId(), govMember);
            // the weekly presence is sometimes null so we don't want to calculate it in that case
            Integer weeklyPresenceGrade = govMember.getAverageWeeklyPresenceHours() == null ? -1 : weeklyPresenceGradeMap.get(govMember);
            Integer monthlyCommitteePresenceGrade = monthlyCommitteePresenceGradeMap.get(govMember);
            Integer billsPreGrade = billsPreGradeMap.get(govMember);
            Integer billsProposedGrade = billsProposedGradeMap.get(govMember);
            Integer billsApprovedGrade = billsApprovedGradeMap.get(govMember);
            
            double memberOverallGrade = getMemberGrade(govMember, weeklyPresenceGrade, monthlyCommitteePresenceGrade, billsPreGrade, billsProposedGrade, billsApprovedGrade);
            memberIdToGrade.put(govMember.getId(), (int) memberOverallGrade);
        }
        
        List<? extends GovParty> allParties = govPartyDAO.getAllParties();
        Map<String, GovParty> partyNameToPartyMap = buildPartyNameToPartyMap(allParties);
        
        Map<String, Integer> partyNameToGrade = calculatePartyGrades(memberIdToMember, memberIdToGrade);
        
        GovMembersDataStore govMemberDataStore = new GovMembersDataStore(memberIdToMember, memberIdToGrade, partyNameToPartyMap, partyNameToGrade, govMemberAverages);
        cacheManager.set(GOV_MEMBER_DATA_STORE_CACHE_KEY, govMemberDataStore);
        
        isCalculating.set(false);
        long endTime = new Date().getTime();
        double durationInSeconds = (endTime - startTime)/1000.0;
        System.out.println("Recalculating grades: finished. Took " + durationInSeconds + " seconds.");
    }

    private GovMemberAverages getGovMemberAverages(List<GovMember> allGovMembers) {
        List<Integer> weeklyPresenceHours = new ArrayList<>();
        List<Integer> monthlyCommitteePresenceHours = new ArrayList<>();
        List<Integer> billsProposed = new ArrayList<>();
        List<Integer> billsApproved = new ArrayList<>();
        for (GovMember member : allGovMembers) {
            if (member.getAverageWeeklyPresenceHours() != null) {
                weeklyPresenceHours.add(member.getAverageWeeklyPresenceHours().intValue());
            }
            if (member.getAverageMonthlyCommitteePresence() != null) {
                monthlyCommitteePresenceHours.add(member.getAverageMonthlyCommitteePresence().intValue());
            }
            billsProposed.add(member.getProposedBills());
            billsApproved.add(member.getApprovedBills());
        }
        
        GovMemberAverages govMemberAverages = new GovMemberAverages(
                getAverage(weeklyPresenceHours),
                getAverage(monthlyCommitteePresenceHours),
                getAverage(billsProposed),
                getAverage(billsApproved));
        return govMemberAverages;
    }

    private Map<String, Integer> calculatePartyGrades(Map<Integer, GovMember> memberIdToMember, Map<Integer, Integer> memberIdToGrade) {
        Map<String, Integer> partyNameToGrade = new HashMap<>();
        Map<String, List<Integer>> partyNameToMemberGrades = new HashMap<>();
        
        for (Integer memberId : memberIdToGrade.keySet()) {
            GovMember member = memberIdToMember.get(memberId);
            Integer memberGrade = memberIdToGrade.get(memberId);
            if (memberGrade.equals(0)) {
                continue;
            }
            String partyName = member.getPartyName();
            if (!partyNameToMemberGrades.containsKey(partyName)) {
                partyNameToMemberGrades.put(partyName, new ArrayList<Integer>());
            }
            partyNameToMemberGrades.get(partyName).add(memberGrade);
        }
        
        for (String partyName : partyNameToMemberGrades.keySet()) {
            List<Integer> memberGrades = partyNameToMemberGrades.get(partyName);
            double averageGrade = getAverage(memberGrades);
            partyNameToGrade.put(partyName, (int) averageGrade);
        }
        return partyNameToGrade;
    }

    private double getMemberGrade(GovMember member, int memberWeeklyPresenceGrade, int monthlyCommitteePresenceGrade, int billsPreGrade, int billsProposedGrade, int billsApprovedGrade) throws IOException {
        
    	double overallGrade = getAverage(Arrays.asList(memberWeeklyPresenceGrade, monthlyCommitteePresenceGrade, billsPreGrade, billsProposedGrade, billsApprovedGrade));
        
        // The grade is between 0 and 119. We want to get a grade in the range [0,100]
        return overallGrade/1.2;
    }
    
    private double getAverage(Collection<Integer> numbers) {
        Integer sum = 0;
        int numValidFactors = 0;
        for (Integer number : numbers) {
//            if (number > 0) {
                sum += number;
                numValidFactors++;
//            }
        }
        if(numValidFactors == 0) {
            return 0;
        }
        return sum.doubleValue() / numValidFactors;
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
    
    private Map<String, GovParty> buildPartyNameToPartyMap(List<? extends GovParty> allParties) {
        Map<String, GovParty> partyNameToPartyMap = new HashMap<>(allParties.size());
        
        for (GovParty party : allParties) {
            partyNameToPartyMap.put(party.getName(), party);
        }
        
        return partyNameToPartyMap;
    }
    
}
