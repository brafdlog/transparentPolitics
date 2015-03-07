package com.transparent.politics.rest;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	    GovMembersListRdt govMemberListRdt = restTypeConverter.toGovMemberListRdt(govMemberDataStore);
	    Collections.sort(govMemberListRdt.getMembers());
        return govMemberListRdt;
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
	
	@SuppressWarnings("serial")
	@RequestMapping("/parties/{partyId}")
    public HashMap<String, GovPartyRdt> getParty(@PathVariable Integer partyId) throws IOException {
        GovParty govParty = govPartyDAO.getParty(partyId);
        final GovPartyRdt govPartyRdt = restTypeConverter.toGovPartyRdt(govParty);
        return new HashMap<String, GovPartyRdt>(){{
        	put("party", govPartyRdt);
        }};
    }
	
	@RequestMapping(value = "/lastCalculatedDate", method = RequestMethod.GET)
	public String getLastCalculatedDate() throws Exception {
	    GovMembersDataStore govMemberDataStore = govMemberService.getGovMemberDataStore(false);
	    if (govMemberDataStore == null) {
	        return "Never";
	    }
	    Date date = govMemberDataStore.getCalculationDate();
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:SS");
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/recalculate", method = RequestMethod.POST)
	public void recalculateGrades() throws Exception {
	    govMemberService.recalculateMemberAndPartyGrades();
	}
	
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String tempTest() throws Exception {
        return "V1";
    }
}