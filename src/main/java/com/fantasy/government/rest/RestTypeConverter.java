package com.fantasy.government.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.government.rest.data.GovMemberRdt;
import com.fantasy.government.rest.data.GovMembersListRdt;
import com.fantasy.government.rest.data.GovPartyRdt;
import com.fantasy.government.services.GovMemberService;
import com.fantasy.government.services.data.GovMember;
import com.fantasy.government.services.data.GovParty;

@Component
public class RestTypeConverter {
    
    @Autowired
    private GovMemberService memberService;

    public GovMembersListRdt toGovMemberListRdt(List<? extends GovMember> govMembers) {
        GovMembersListRdt membersListRdt = new GovMembersListRdt();
        for (GovMember govMember : govMembers) {
            Integer memberGrade = memberService.getMemberGrade(govMember);
            GovMemberRdt govMemberRdt = toGovMemberRdt(govMember, memberGrade);
            membersListRdt.getMembers().add(govMemberRdt);
        }
        return membersListRdt;
    }
    
    public GovMemberRdt toGovMemberRdt(GovMember govMember, Integer memberGrade) {
        GovMemberRdt govMemberRdt = new GovMemberRdt();
        govMemberRdt.setId(govMember.getId());
        govMemberRdt.setImageUrl(govMember.getImageUrl());
        govMemberRdt.setName(govMember.getName());
        govMemberRdt.setGrade(memberGrade);
        return govMemberRdt;
    }
    
    public GovPartyRdt toGovPartyRdt(GovParty govParty, Integer partyGrade) {
        GovPartyRdt govPartyRdt = new GovPartyRdt();
        govPartyRdt.setId(govParty.getId());
        govPartyRdt.setImageUrl(govParty.getImageUrl());
        govPartyRdt.setName(govParty.getName());
        govPartyRdt.setGrade(partyGrade);
        return govPartyRdt;
    }
    
}
