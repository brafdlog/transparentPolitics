package com.fantasy.government.rest;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.government.rest.data.GovMemberRdt;
import com.fantasy.government.rest.data.GovMembersListRdt;
import com.fantasy.government.rest.data.GovPartyListRdt;
import com.fantasy.government.rest.data.GovPartyRdt;
import com.fantasy.government.services.GovMemberService;
import com.fantasy.government.services.GovPartyService;
import com.fantasy.government.services.data.GovMember;
import com.fantasy.government.services.data.GovParty;

@Component
public class RestTypeConverter {
    
    @Autowired
    private GovMemberService memberService;
    
    @Autowired
    private GovPartyService partyService;

    public GovMembersListRdt toGovMemberListRdt(Collection<? extends GovMember> govMembers) throws IOException {
        GovMembersListRdt membersListRdt = new GovMembersListRdt();
        for (GovMember govMember : govMembers) {
            GovMemberRdt govMemberRdt = toGovMemberRdt(govMember);
            membersListRdt.getMembers().add(govMemberRdt);
        }
        return membersListRdt;
    }
    
    public GovMemberRdt toGovMemberRdt(GovMember govMember) throws IOException {
        Integer memberGrade = memberService.getMemberGrade(govMember);
        GovMemberRdt govMemberRdt = new GovMemberRdt();
        govMemberRdt.setId(govMember.getId());
        govMemberRdt.setImageUrl(govMember.getImageUrl());
        govMemberRdt.setName(govMember.getName());
        govMemberRdt.setGrade(memberGrade);
        return govMemberRdt;
    }
    
    public GovPartyListRdt toGovPartyListRdt(Collection<? extends GovParty> govParties) {
        GovPartyListRdt govPartyListRdt = new GovPartyListRdt();
        for (GovParty party : govParties) {
            String partyImageUrl = partyService.getPartyImageUrl(party);
            GovPartyRdt partyRdt = toGovPartyRdt(party);
            partyRdt.setImageUrl(partyImageUrl);
            govPartyListRdt.getParties().add(partyRdt);
        }
        return govPartyListRdt;
    }
    
    public GovPartyRdt toGovPartyRdt(GovParty govParty) {
        Integer partyGrade = partyService.getPartyGrade(govParty);
        GovPartyRdt govPartyRdt = new GovPartyRdt();
        govPartyRdt.setId(govParty.getId());
        govPartyRdt.setImageUrl(govParty.getImageUrl());
        govPartyRdt.setName(govParty.getName());
        govPartyRdt.setGrade(partyGrade);
        return govPartyRdt;
    }
    
}
