package com.transparent.politics.services.data;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class GovMembersDataStore {

    private final Date calculationDate;
    private final Map<Integer, GovMember> memberIdToMember;
    private final Map<Integer, Integer> memberIdToGrade;
    private final Map<String, GovParty> partyNameToParty;
    private final Map<String, Integer> partyNameToGrade;

    public GovMembersDataStore(Map<Integer, GovMember> memberIdToMember, Map<Integer, Integer> memberIdToGrade, Map<String, GovParty> partyNameToParty,
            Map<String, Integer> partyNameToGrade) {
        this.memberIdToMember = ImmutableMap.copyOf(memberIdToMember);
        this.memberIdToGrade = ImmutableMap.copyOf(memberIdToGrade);
        this.partyNameToParty = ImmutableMap.copyOf(partyNameToParty);
        this.partyNameToGrade = ImmutableMap.copyOf(partyNameToGrade);
        this.calculationDate = new Date();
    }

    public Integer getGovMemberGrade(Integer memeberId) {
        return memberIdToGrade.get(memeberId);
    }

    public GovMember getGovMember(Integer memeberId) {
        return memberIdToMember.get(memeberId);
    }
    
    public Collection<GovMember> getAllMembers() {
        return memberIdToMember.values();
    }
    
    public GovParty getPartyByName(String partyName) {
        return partyNameToParty.get(partyName);
    }
    
    public Integer getPartyGrade(String partyName) {
        return partyNameToGrade.get(partyName);
    }

    public Date getCalculationDate() {
        return calculationDate;
    }
    
}
