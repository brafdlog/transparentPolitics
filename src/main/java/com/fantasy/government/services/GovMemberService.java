package com.fantasy.government.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.government.dao.GovMemberDAO;
import com.fantasy.government.dao.cache.CacheManager;
import com.fantasy.government.services.data.GovMember;

@Component
public class GovMemberService {

    @Autowired
    private CacheManager cacheManager;
    
    @Autowired
    private GovMemberDAO govMemberDAO;
    
    public Integer getMemberGrade(GovMember member) throws IOException {
    	List<? extends GovMember> sortedGovMembers = getSortedGovMembers();
    	for (int i=0; i<sortedGovMembers.size(); i++) {
    		if (sortedGovMembers.get(i).getId().equals(member.getId())) {
    			return (int) ((i+1)/(1.2));
    		}
    	}
    	return -1;
    }
    
    public List<? extends GovMember> getSortedGovMembers() throws IOException {
    	List<? extends GovMember> allGovMembers = govMemberDAO.getAllGovMemebers();
    	List<GovMember> weeklyPresenceHoursList = new ArrayList<>(allGovMembers);
    	Collections.sort(weeklyPresenceHoursList, new Comparator<GovMember>() {

			@Override
			public int compare(GovMember o1, GovMember o2) {
				if (o1.getAverage_weekly_presence_hours() == null && o2.getAverage_weekly_presence_hours() == null) {
					return 0;
				}
				if (o1.getAverage_weekly_presence_hours() == null) {
					return -1;
				}
				if (o2.getAverage_weekly_presence_hours() == null) {
					return 1;
				}
				return o2.getAverage_weekly_presence_hours().compareTo(o1.getAverage_weekly_presence_hours());
			}
		});
    	
    	return weeklyPresenceHoursList;
    }
    
}
