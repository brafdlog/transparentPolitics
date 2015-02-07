package com.transparent.politics.services;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.transparent.politics.dao.PartyImageUrl;
import com.transparent.politics.services.data.GovParty;

@Component
public class GovPartyService {

    public Integer getPartyGrade(GovParty party) {
        return new Random().nextInt(100);
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
