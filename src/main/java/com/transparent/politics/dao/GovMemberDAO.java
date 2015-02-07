package com.transparent.politics.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.transparent.politics.common.Config;
import com.transparent.politics.common.Utils;
import com.transparent.politics.dao.cache.CacheManager;
import com.transparent.politics.dao.data.OpenKnessetGovMember;
import com.transparent.politics.dao.openknesset.OpenKnessetProxy;
import com.transparent.politics.services.data.GovMember;

@Component
public class GovMemberDAO {

    private static final Boolean ONLY_CURRENT_MEMBERS = Boolean.TRUE;
    
    @Autowired
    private OpenKnessetProxy openKnessetApi;
    
    @Qualifier(Config.CACHE_PROVIDER_BEAN_QUALIFIER)
    @Autowired
    private CacheManager cacheManager;
    
    /**
     * Returns a list of all current government members.
     * Each gov member contains only basic information. To get get more detailed info call getAllGovMemebersDetailed
     */
    public List<? extends GovMember> getCurrentGovMembersNonDetailed() throws IOException {
        List<? extends GovMember> currentMembers = openKnessetApi.getMembers(ONLY_CURRENT_MEMBERS).getObjects();
        return currentMembers;
    }
    
    public GovMember getGovMember(Integer memberId) throws Exception {
        int numTries = 0;
        OpenKnessetGovMember member = null;
        while (numTries < 10 && member == null) {
            numTries++;
            try {
                member = openKnessetApi.getMember(memberId);
            } catch (Exception e) {
                System.out.println("Exception while getting member. Probably too many requests. Sleeping and trying again");
                member = null;
                // Waiting here in case exception is because of too many requests
                Utils.sleep(3000);
            }
        }
        
        if (member == null) {
            throw new Exception("Failed getting gov member with id " + memberId);
        }
        
        return member;
    }

    public List<GovMember> getAllGovMemebersDetailed() throws Exception {
    	List<GovMember> allGovMembers = new ArrayList<>(120);
    	List<? extends GovMember> currentGovMembers = getCurrentGovMembersNonDetailed();
    	
    	int memberCounter = 1;
    	for (GovMember govMember : currentGovMembers) {
    	    if (memberCounter % 5 == 0) {
    	        System.out.println("Getting member " + memberCounter);
    	    }
    		govMember = getGovMember(govMember.getId());
    		allGovMembers.add(govMember);
    		memberCounter++;
    		
    		// Need to sleep to prevent too many requsets exception
    		Utils.sleep(75);
    	}
    	return allGovMembers;
    }
    
}
