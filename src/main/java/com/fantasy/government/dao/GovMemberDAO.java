package com.fantasy.government.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.government.dao.openknesset.OpenKnessetProxy;
import com.fantasy.government.services.data.GovMember;

@Component
public class GovMemberDAO {

    @Autowired
    private OpenKnessetProxy openKnessetApi;
    
    public List<? extends GovMember> getAllGovMembers() {
        return openKnessetApi.getMembers().getObjects();
    }
}
