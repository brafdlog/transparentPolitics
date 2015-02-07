package com.transparent.politics.rest.data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class GovPartyListRdt {

    private List<GovPartyRdt> parties;

    public GovPartyListRdt() {
        parties = new ArrayList<>();
    }

    public List<GovPartyRdt> getParties() {
        return parties;
    }

    public void setParties(List<GovPartyRdt> parties) {
        this.parties = parties;
    }

}