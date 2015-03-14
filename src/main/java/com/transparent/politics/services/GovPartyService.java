package com.transparent.politics.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transparent.politics.dao.PartyImageUrl;
import com.transparent.politics.services.data.GovMembersDataStore;
import com.transparent.politics.services.data.GovParty;

@Component
public class GovPartyService {

    @Autowired
    private GovMemberService govMemberService;
    
    public Integer getPartyGrade(GovParty party) throws Exception {
        GovMembersDataStore govMemberDataStore = govMemberService.getGovMemberDataStore();
        return govMemberDataStore.getPartyGrade(party.getName());
    }
    
    /**
     * Currently party logo url comes back empty from the open knesset api
     * so we get the logo url from a hardcoded map.
     */
    public String getPartyImageUrl(GovParty party) {
        if (party.getImageUrl() != null) {
            return party.getImageUrl();
        }
        return PartyImageUrl.getPartyImageUrl(party.getName());
    }
    
}
