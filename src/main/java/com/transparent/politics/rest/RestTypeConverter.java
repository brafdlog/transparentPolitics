package com.transparent.politics.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transparent.politics.rest.data.GovMemberRdt;
import com.transparent.politics.rest.data.GovMembersListRdt;
import com.transparent.politics.rest.data.GovPartyListRdt;
import com.transparent.politics.rest.data.GovPartyRdt;
import com.transparent.politics.services.GovMemberService;
import com.transparent.politics.services.GovPartyService;
import com.transparent.politics.services.data.GovMember;
import com.transparent.politics.services.data.GovMemberAverages;
import com.transparent.politics.services.data.GovMembersDataStore;
import com.transparent.politics.services.data.GovParty;

@Component
public class RestTypeConverter {

    @Autowired
    private GovMemberService memberService;

    @Autowired
    private GovPartyService partyService;

    public GovMembersListRdt toGovMemberListRdt(GovMembersDataStore govMemberDataStore) throws IOException {
        GovMembersListRdt membersListRdt = new GovMembersListRdt();
        for (GovMember govMember : govMemberDataStore.getAllMembers()) {
            GovMemberRdt govMemberRdt = toGovMemberRdt(govMemberDataStore, govMember.getId());
            membersListRdt.getMembers().add(govMemberRdt);
        }
        return membersListRdt;
    }

    public GovMemberRdt toGovMemberRdt(GovMembersDataStore govMemberDataStore, Integer govMemberId) throws IOException {
        GovMember govMember = govMemberDataStore.getGovMember(govMemberId);
        Integer govMemberGrade = govMemberDataStore.getGovMemberGrade(govMemberId);
        GovMemberRdt govMemberRdt = new GovMemberRdt();
        govMemberRdt.setId(govMember.getId());
        govMemberRdt.setImageUrl(govMember.getImageUrl());
        govMemberRdt.setName(govMember.getName());
        govMemberRdt.setAverageWeeklyPresenceHours(govMember.getAverageWeeklyPresenceHours());
        govMemberRdt.setAverageMonthlyCommitteePresence(govMember.getAverageMonthlyCommitteePresence());
        govMemberRdt.setTromitBills(govMember.getTromitBills());
        govMemberRdt.setProposedBills(govMember.getProposedBills());
        govMemberRdt.setApprovedBills(govMember.getApprovedBills());
        govMemberRdt.setGrade(govMemberGrade);

        GovMemberAverages govMemberAverages = govMemberDataStore.getGovMemberAverages();
        govMemberRdt.setAllMembersAverageWeeklyPresenceHours(roundNumber(govMemberAverages.getAverageWeeklyPresenceHours(), 1));
        govMemberRdt.setAllMembersAverageMonthlyCommitteePresence(roundNumber(govMemberAverages.getAverageMonthlyCommitteePresence(), 1));
        govMemberRdt.setAllMembersAverageProposedBills(roundNumber(govMemberAverages.getAverageProposedBills(), 1));
        govMemberRdt.setAllMembersAverageApprovedBills(roundNumber(govMemberAverages.getAverageApprovedBills(), 1));
        govMemberRdt.setAllMembersAverageGrade(roundNumber(govMemberAverages.getAverageGrade(), 1));

        GovParty party = govMemberDataStore.getPartyByName(govMember.getPartyName());
        govMemberRdt.setParty(party.getId());
        return govMemberRdt;
    }

    public GovPartyListRdt toGovPartyListRdt(Collection<? extends GovParty> govParties) throws Exception {
        GovPartyListRdt govPartyListRdt = new GovPartyListRdt();
        for (GovParty party : govParties) {
            String partyImageUrl = partyService.getPartyImageUrl(party);
            GovPartyRdt partyRdt = toGovPartyRdt(party);
            partyRdt.setImageUrl(partyImageUrl);
            govPartyListRdt.getParties().add(partyRdt);
        }
        return govPartyListRdt;
    }

    public GovPartyRdt toGovPartyRdt(GovParty govParty) throws Exception {
        Integer partyGrade = partyService.getPartyGrade(govParty);
        GovPartyRdt govPartyRdt = new GovPartyRdt();
        govPartyRdt.setId(govParty.getId());
        govPartyRdt.setImageUrl(govParty.getImageUrl());
        govPartyRdt.setName(govParty.getName());
        govPartyRdt.setGrade(partyGrade);
        return govPartyRdt;
    }

    private double roundNumber(double number, int numDecimalDigits) {
        return new BigDecimal(number).setScale(numDecimalDigits, RoundingMode.HALF_EVEN).doubleValue();
    }

}
