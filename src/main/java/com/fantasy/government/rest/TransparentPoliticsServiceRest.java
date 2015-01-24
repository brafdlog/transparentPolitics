package com.fantasy.government.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.government.dao.GovMemberDAO;
import com.fantasy.government.dao.GovPartyDAO;
import com.fantasy.government.rest.data.GovMemberRdt;
import com.fantasy.government.rest.data.GovMembersListRdt;
import com.fantasy.government.rest.data.GovPartyListRdt;
import com.fantasy.government.rest.data.GovPartyRdt;
import com.fantasy.government.services.data.GovMember;
import com.fantasy.government.services.data.GovParty;

@RestController
public class TransparentPoliticsServiceRest {
	
    @Autowired
    private GovMemberDAO govMemberDAO;
    
    @Autowired
    private GovPartyDAO govPartyDAO;
    
	@Autowired
	private RestTypeConverter restTypeConverter;
	
	@RequestMapping("/members")
	public GovMembersListRdt getMembers() {
	    List<? extends GovMember> allGovMembers = govMemberDAO.getCurrentGovMembers();
	    GovMembersListRdt govMemberListView = restTypeConverter.toGovMemberListRdt(allGovMembers);
        return govMemberListView;
	}
	
    @RequestMapping("/members/{memberId}")
    public GovMemberRdt getMember(@PathVariable Integer memberId) {
        GovMember govMember = govMemberDAO.getGovMember(memberId);
        GovMemberRdt govMemberRdt = restTypeConverter.toGovMemberRdt(govMember);
        return govMemberRdt;
    }
	
	@RequestMapping("/parties")
	public GovPartyListRdt getParties() {
	    List<? extends GovParty> allParties = govPartyDAO.getAllParties();
	    GovPartyListRdt govPartyListRdt = restTypeConverter.toGovPartyListRdt(allParties);
	    return govPartyListRdt;
	}
	
    @RequestMapping("/parties/{partyId}")
    public GovPartyRdt getParty(@PathVariable Integer partyId) {
        GovParty govParty = govPartyDAO.getParty(partyId);
        GovPartyRdt govPartyRdt = restTypeConverter.toGovPartyRdt(govParty);
        return govPartyRdt;
    }
}