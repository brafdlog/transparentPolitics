package com.fantasy.government.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.government.dao.openknesset.OpenKnessetProxy;
import com.fantasy.government.services.data.GovMember;

@Component
public class GovMemberDAO {

    private static final Boolean ONLY_CURRENT_MEMBERS = Boolean.TRUE;
    
    @Autowired
    private OpenKnessetProxy openKnessetApi;
    
    /**
     * Returns a list of all current government members.
     */
    public List<? extends GovMember> getCurrentGovMembers() {
        return openKnessetApi.getMembers(ONLY_CURRENT_MEMBERS).getObjects();
    }
    
    public GovMember getGovMember(Integer memberId) {
        return openKnessetApi.getMember(memberId);
    }
}
