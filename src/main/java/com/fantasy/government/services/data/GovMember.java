package com.fantasy.government.services.data;

public interface GovMember {

    Integer getId();

    String getName();

    String getGender();

    Boolean isCurrent();

    String getImageUrl();

	Float getAverage_weekly_presence_hours();

	Integer getBills_stats_proposed();

	Float getAverage_monthly_committee_presence();

	Integer getBills_stats_approved();

	Integer getBills_stats_pre();

}