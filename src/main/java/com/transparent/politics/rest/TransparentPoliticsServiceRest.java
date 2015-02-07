package com.transparent.politics.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transparent.politics.dao.GovPartyDAO;
import com.transparent.politics.rest.data.GovMemberRdt;
import com.transparent.politics.rest.data.GovMembersListRdt;
import com.transparent.politics.rest.data.GovPartyListRdt;
import com.transparent.politics.rest.data.GovPartyRdt;
import com.transparent.politics.services.GovMemberService;
import com.transparent.politics.services.data.GovMembersDataStore;
import com.transparent.politics.services.data.GovParty;

@RestController
public class TransparentPoliticsServiceRest {
	
    @Autowired
    private GovMemberService govMemberService;
    
    @Autowired
    private GovPartyDAO govPartyDAO;
    
	@Autowired
	private RestTypeConverter restTypeConverter;
	
	@RequestMapping("/members")
	public GovMembersListRdt getMembers() throws Exception {
	    GovMembersDataStore govMemberDataStore = govMemberService.getGovMemberDataStore();
	    GovMembersListRdt govMemberListView = restTypeConverter.toGovMemberListRdt(govMemberDataStore);
        return govMemberListView;
	}
	
    @RequestMapping("/members/{memberId}")
    public GovMemberRdt getMember(@PathVariable Integer memberId) throws Exception {
        GovMembersDataStore govMemberDataStore = govMemberService.getGovMemberDataStore();
        GovMemberRdt govMemberRdt = restTypeConverter.toGovMemberRdt(govMemberDataStore, memberId);
        return govMemberRdt;
    }
	
	@RequestMapping("/parties")
	public GovPartyListRdt getParties() throws IOException {
	    List<? extends GovParty> allParties = govPartyDAO.getAllParties();
	    GovPartyListRdt govPartyListRdt = restTypeConverter.toGovPartyListRdt(allParties);
	    return govPartyListRdt;
	}
	
    @RequestMapping("/parties/{partyId}")
    public GovPartyRdt getParty(@PathVariable Integer partyId) throws IOException {
        GovParty govParty = govPartyDAO.getParty(partyId);
        GovPartyRdt govPartyRdt = restTypeConverter.toGovPartyRdt(govParty);
        return govPartyRdt;
    }
}