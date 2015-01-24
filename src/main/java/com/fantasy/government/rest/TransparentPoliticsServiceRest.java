package com.fantasy.government.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.government.dao.GovMemberDAO;
import com.fantasy.government.rest.data.GovMembersListRdt;
import com.fantasy.government.services.data.GovMember;

@RestController
public class TransparentPoliticsServiceRest {
	
    @Autowired
    private GovMemberDAO govMemberDAO;
    
	@Autowired
	private RestTypeConverter restTypeConverter;
	
	@RequestMapping("/members")
	public GovMembersListRdt getMembers() {
	    List<? extends GovMember> allGovMembers = govMemberDAO.getCurrentGovMembers();
	    GovMembersListRdt govMemberListView = restTypeConverter.toGovMemberListRdt(allGovMembers);
        return govMemberListView;
	}
}