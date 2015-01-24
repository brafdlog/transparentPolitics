package com.fantasy.government.services;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.fantasy.government.services.data.GovParty;

@Component
public class GovPartyService {

    public Integer getPartyGrade(GovParty party) {
        return new Random().nextInt(100);
    }
}
