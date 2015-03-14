package com.transparent.politics.rest.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class GovMemberRdt implements Comparable<GovMemberRdt> {

    private Integer id;
    private String name;
    private String imageUrl;
    private Integer grade;
    private Float averageWeeklyPresenceHours;
    private Float averageMonthlyCommitteePresence;
    private Integer tromitBills;
    private Integer proposedBills;
    private Integer approvedBills;
    private Integer partyId;
    
    //TODO these should not be repeated in every member
    private double allMembersAverageWeeklyPresenceHours;
    private double allMembersAverageMonthlyCommitteePresence;
    private double allMembersAverageProposedBills;
    private double allMembersAverageApprovedBills;
    private double allMembersAverageGrade;

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

    public Integer getParty() {
        return partyId;
    }

    public void setParty(Integer partyId) {
        this.partyId = partyId;
    }

    public Float getAverageWeeklyPresenceHours() {
        return averageWeeklyPresenceHours;
    }

    public void setAverageWeeklyPresenceHours(Float averageWeeklyPresenceHours) {
        this.averageWeeklyPresenceHours = averageWeeklyPresenceHours;
    }

    public Float getAverageMonthlyCommitteePresence() {
        return averageMonthlyCommitteePresence;
    }

    public void setAverageMonthlyCommitteePresence(Float averageMonthlyCommitteePresence) {
        this.averageMonthlyCommitteePresence = averageMonthlyCommitteePresence;
    }

    public Integer getTromitBills() {
        return tromitBills;
    }

    public void setTromitBills(Integer tromitBills) {
        this.tromitBills = tromitBills;
    }

    public Integer getProposedBills() {
        return proposedBills;
    }

    public void setProposedBills(Integer proposedBills) {
        this.proposedBills = proposedBills;
    }

    public Integer getApprovedBills() {
        return approvedBills;
    }

    public void setApprovedBills(Integer approvedBills) {
        this.approvedBills = approvedBills;
    }
    
    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    public double getAllMembersAverageWeeklyPresenceHours() {
        return allMembersAverageWeeklyPresenceHours;
    }

    public void setAllMembersAverageWeeklyPresenceHours(double allMembersAverageWeeklyPresenceHours) {
        this.allMembersAverageWeeklyPresenceHours = allMembersAverageWeeklyPresenceHours;
    }

    public double getAllMembersAverageMonthlyCommitteePresence() {
        return allMembersAverageMonthlyCommitteePresence;
    }

    public void setAllMembersAverageMonthlyCommitteePresence(double allMembersAverageMonthlyCommitteePresence) {
        this.allMembersAverageMonthlyCommitteePresence = allMembersAverageMonthlyCommitteePresence;
    }

    public double getAllMembersAverageProposedBills() {
        return allMembersAverageProposedBills;
    }

    public void setAllMembersAverageProposedBills(double allMembersAverageProposedBills) {
        this.allMembersAverageProposedBills = allMembersAverageProposedBills;
    }

    public double getAllMembersAverageApprovedBills() {
        return allMembersAverageApprovedBills;
    }

    public void setAllMembersAverageApprovedBills(double allMembersAverageApprovedBills) {
        this.allMembersAverageApprovedBills = allMembersAverageApprovedBills;
    }
    
    public double getAllMembersAverageGrade() {
        return allMembersAverageGrade;
    }

    public void setAllMembersAverageGrade(double allMembersAverageGrade) {
        this.allMembersAverageGrade = allMembersAverageGrade;
    }

    @Override
    public int compareTo(GovMemberRdt other) {
        return grade.compareTo(other.grade);
    }
    
}
