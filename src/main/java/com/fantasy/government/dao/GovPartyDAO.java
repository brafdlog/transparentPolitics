package com.fantasy.government.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.government.dao.openknesset.OpenKnessetProxy;
import com.fantasy.government.services.data.GovParty;

@Component
public class GovPartyDAO {

    @Autowired
    private OpenKnessetProxy openKnessetApi;
    
    public List<? extends GovParty> getAllParties() {
        return openKnessetApi.getAllParties().getObjects();
    }
    
    public GovParty getParty(Integer partyId) throws IOException {
        return openKnessetApi.getParty(partyId);
    }
}
