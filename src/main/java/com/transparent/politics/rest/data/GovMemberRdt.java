package com.transparent.politics.rest.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class GovMemberRdt {

    private Integer id;
    private String name;
    private String imageUrl;
    private Integer grade;
    private Float averageWeeklyPresenceHours;
    private GovPartyRdt party;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public GovPartyRdt getParty() {
        return party;
    }

    public void setParty(GovPartyRdt party) {
        this.party = party;
    }

    public Float getAverageWeeklyPresenceHours() {
        return averageWeeklyPresenceHours;
    }

    public void setAverageWeeklyPresenceHours(Float averageWeeklyPresenceHours) {
        this.averageWeeklyPresenceHours = averageWeeklyPresenceHours;
    }
    
}
