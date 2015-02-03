package com.fantasy.government.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.government.dao.data.OpenKnessetGovMember;
import com.fantasy.government.dao.openknesset.OpenKnessetProxy;
import com.fantasy.government.services.data.GovMember;
import com.fasterxml.jackson.core.type.TypeReference;

@Component
public class GovMemberDAO {

    private static final String MEMBER_CACHE_KEY_PREFIX = "member_";
    private static final String CURRENT_MEMBERS_CACHE_KEY = "currentMembers";
    private static final Boolean ONLY_CURRENT_MEMBERS = Boolean.TRUE;
    
    @Autowired
    private OpenKnessetProxy openKnessetApi;
    
    @Autowired
    private CacheManager cacheManager;
    
    /**
     * Returns a list of all current government members.
     */
    public List<? extends GovMember> getCurrentGovMembers() throws IOException {
        List<OpenKnessetGovMember> currentMembers = cacheManager.get(CURRENT_MEMBERS_CACHE_KEY, new TypeReference<List<OpenKnessetGovMember>>() {});
        if (currentMembers == null) {
            currentMembers = openKnessetApi.getMembers(ONLY_CURRENT_MEMBERS).getObjects();
            cacheManager.set(CURRENT_MEMBERS_CACHE_KEY, currentMembers);
        }
        return currentMembers;
    }
    
    public GovMember getGovMember(Integer memberId) throws IOException {
        String cacheKey = MEMBER_CACHE_KEY_PREFIX + memberId;
        OpenKnessetGovMember member = cacheManager.get(cacheKey, OpenKnessetGovMember.class);
        if (member == null) {
            member = openKnessetApi.getMember(memberId);
            cacheManager.set(cacheKey, member);
        }
        
        return member;
    }
    
    public List<GovMember> getAllGovMemebers() throws IOException {
    	List<GovMember> allGovMembers = new ArrayList<>(120);
    	List<? extends GovMember> currentGovMembers = getCurrentGovMembers();
    	
    	for (GovMember govMember : currentGovMembers) {
    		govMember = getGovMember(govMember.getId());
    		allGovMembers.add(govMember);
    	}
    	return allGovMembers;
    }
 
}
