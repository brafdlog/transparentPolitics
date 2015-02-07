package com.transparent.politics.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.transparent.politics.common.Config;
import com.transparent.politics.dao.cache.CacheManager;
import com.transparent.politics.dao.data.OpenKnessetGovParty;
import com.transparent.politics.dao.openknesset.OpenKnessetProxy;
import com.transparent.politics.services.data.GovParty;

@Component
public class GovPartyDAO {

    private static final String PARTY_CACHE_KEY_PREFIX = "party_";
    private static final String ALL_PARTIES_CACHE_KEY = "allParties";

    @Autowired
    private OpenKnessetProxy openKnessetApi;
    
    @Qualifier(Config.CACHE_PROVIDER_BEAN_QUALIFIER)
    @Autowired
    private CacheManager cacheManager;
    
    public List<? extends GovParty> getAllParties() throws IOException {
        List<OpenKnessetGovParty> allParties = cacheManager.get(ALL_PARTIES_CACHE_KEY, new TypeReference<List<OpenKnessetGovParty>>() {});
        if (allParties == null) {
            allParties = openKnessetApi.getAllParties().getObjects();
            cacheManager.set(ALL_PARTIES_CACHE_KEY, allParties);
        }
        return allParties;
    }
    
    public GovParty getParty(Integer partyId) throws IOException {
        String cacheKey = PARTY_CACHE_KEY_PREFIX + partyId;
        OpenKnessetGovParty party = cacheManager.get(cacheKey, OpenKnessetGovParty.class);
        if (party == null) {
            party = openKnessetApi.getParty(partyId);
            cacheManager.set(cacheKey, party);
        }
        
        return party;
    }
}
