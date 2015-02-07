package com.transparent.politics.services.data;

public interface GovMember {

    Integer getId();

    String getName();

    String getGender();

    Boolean isCurrent();

    String getImageUrl();

	Float getAverageWeeklyPresenceHours();

	Float getAverageMonthlyCommitteePresence();

	Integer getTromitBills();

	Integer getProposedBills();

	Integer getApprovedBills();

}