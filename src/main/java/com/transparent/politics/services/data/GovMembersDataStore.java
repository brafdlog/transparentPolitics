package com.transparent.politics.services.data;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class GovMembersDataStore {

    private final Map<Integer, GovMember> memberIdToMember;
    private final Map<Integer, Integer> memberIdToGrade;

    public GovMembersDataStore(Map<Integer, GovMember> memberIdToMember, Map<Integer, Integer> memberIdToGrade) {
        this.memberIdToMember = ImmutableMap.copyOf(memberIdToMember);
        this.memberIdToGrade = ImmutableMap.copyOf(memberIdToGrade);
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

}
