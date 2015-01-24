package com.fantasy.government.rest.data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class GovMembersListRdt {

    private List<GovMemberRdt> members;
    
    public GovMembersListRdt() {
        members = new ArrayList<>();
    }

    public List<GovMemberRdt> getMembers() {
        return members;
    }

    public void setMembers(List<GovMemberRdt> members) {
        this.members = members;
    }

}