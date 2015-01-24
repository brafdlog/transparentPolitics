package com.fantasy.government.dao.data;

import java.util.Date;
import java.util.List;

import com.fantasy.government.services.data.GovMember;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class OpenKnessetGovMember implements GovMember {

    private Integer id;
    private String name;
    private String place_of_birth;
    private Date end_date;
    private String resource_uri;
    private List<OpenKnessetUrl> links;
    private List<String> video_about;
    private Integer year_of_aliyah;
    private String absolute_url;
    private String gender;
    private String bills_uri;
    private String fax;
    private List<OpenKnessetRole> detailed_roles;
    private Integer mmms_count;
    private Float average_weekly_presence_hours;
    private String email;
    private Integer residence_economy;
    private String party_name;
    private List<String> videos_related;
    private Date date_of_death;
    private Integer average_weekly_presence_rank;
    private Integer bills_stats_proposed;
    private Date start_date;
    private String current_role_descriptions;
    private Integer votes_count;
    private Float average_monthly_committee_presence;
    private String place_of_residence_lat;
    private String agendas_uri;
    private String party_url;
    private Integer bills_stats_approved;
    private Boolean is_current;
    private List<List<String>> committees;
    private Integer bills_stats_first;
    private Integer residence_centrality;
    private String place_of_residence_lon;
    private Integer number_of_children;
    private String place_of_residence;
    private String phone;
    private String family_status;
    private Integer current_position;
    private Date date_of_birth;
    private Integer bills_stats_pre;
    private String img_url;

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getPlace_of_birth() {
        return this.place_of_birth;
    }

    public void setPlace_of_birth(String value) {
        this.place_of_birth = value;
    }

    public Date getEnd_date() {
        return this.end_date;
    }

    public void setEnd_date(Date value) {
        this.end_date = value;
    }

    public String getResource_uri() {
        return this.resource_uri;
    }

    public void setResource_uri(String value) {
        this.resource_uri = value;
    }

    public List<OpenKnessetUrl> getLinks() {
        return this.links;
    }

    public void setLinks(List<OpenKnessetUrl> links) {
        this.links = links;
    }

    public List<String> getVideo_about() {
        return this.video_about;
    }

    public void setVideo_about(List<String> value) {
        this.video_about = value;
    }

    public Integer getYear_of_aliyah() {
        return this.year_of_aliyah;
    }

    public void setYear_of_aliyah(Integer value) {
        this.year_of_aliyah = value;
    }

    public String getAbsolute_url() {
        return this.absolute_url;
    }

    public void setAbsolute_url(String value) {
        this.absolute_url = value;
    }

    @Override
    public String getGender() {
        return this.gender;
    }

    public void setGender(String value) {
        this.gender = value;
    }

    public String getBills_uri() {
        return this.bills_uri;
    }

    public void setBills_uri(String value) {
        this.bills_uri = value;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String value) {
        this.fax = value;
    }

    public List<OpenKnessetRole> getDetailed_roles() {
        return this.detailed_roles;
    }

    public void setDetailed_roles(List<OpenKnessetRole> value) {
        this.detailed_roles = value;
    }

    public Integer getMmms_count() {
        return this.mmms_count;
    }

    public void setMmms_count(Integer value) {
        this.mmms_count = value;
    }

    public Float getAverage_weekly_presence_hours() {
        return this.average_weekly_presence_hours;
    }

    public void setAverage_weekly_presence_hours(Float value) {
        this.average_weekly_presence_hours = value;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public Integer getResidence_economy() {
        return this.residence_economy;
    }

    public void setResidence_economy(Integer value) {
        this.residence_economy = value;
    }

    public String getParty_name() {
        return this.party_name;
    }

    public void setParty_name(String value) {
        this.party_name = value;
    }

    public List<String> getVideos_related() {
        return this.videos_related;
    }

    public void setVideos_related(List<String> value) {
        this.videos_related = value;
    }

    public Date getDate_of_death() {
        return this.date_of_death;
    }

    public void setDate_of_death(Date value) {
        this.date_of_death = value;
    }

    public Integer getAverage_weekly_presence_rank() {
        return this.average_weekly_presence_rank;
    }

    public void setAverage_weekly_presence_rank(Integer value) {
        this.average_weekly_presence_rank = value;
    }

    public Integer getBills_stats_proposed() {
        return this.bills_stats_proposed;
    }

    public void setBills_stats_proposed(Integer value) {
        this.bills_stats_proposed = value;
    }

    public Date getStart_date() {
        return this.start_date;
    }

    public void setStart_date(Date value) {
        this.start_date = value;
    }

    public String getCurrent_role_descriptions() {
        return this.current_role_descriptions;
    }

    public void setCurrent_role_descriptions(String value) {
        this.current_role_descriptions = value;
    }

    public Integer getVotes_count() {
        return this.votes_count;
    }

    public void setVotes_count(Integer value) {
        this.votes_count = value;
    }

    public Float getAverage_monthly_committee_presence() {
        return this.average_monthly_committee_presence;
    }

    public void setAverage_monthly_committee_presence(Float value) {
        this.average_monthly_committee_presence = value;
    }

    public String getPlace_of_residence_lat() {
        return this.place_of_residence_lat;
    }

    public void setPlace_of_residence_lat(String value) {
        this.place_of_residence_lat = value;
    }

    public String getAgendas_uri() {
        return this.agendas_uri;
    }

    public void setAgendas_uri(String value) {
        this.agendas_uri = value;
    }

    public String getParty_url() {
        return this.party_url;
    }

    public void setParty_url(String value) {
        this.party_url = value;
    }

    public Integer getBills_stats_approved() {
        return this.bills_stats_approved;
    }

    public void setBills_stats_approved(Integer value) {
        this.bills_stats_approved = value;
    }

    @Override
    @JsonProperty("is_current")
    public Boolean isCurrent() {
        return this.is_current;
    }

    public void setIs_current(Boolean value) {
        this.is_current = value;
    }

    public List<List<String>> getCommittees() {
        return this.committees;
    }

    public void setCommittees(List<List<String>> value) {
        this.committees = value;
    }

    public Integer getBills_stats_first() {
        return this.bills_stats_first;
    }

    public void setBills_stats_first(Integer value) {
        this.bills_stats_first = value;
    }

    public Integer getResidence_centrality() {
        return this.residence_centrality;
    }

    public void setResidence_centrality(Integer value) {
        this.residence_centrality = value;
    }

    public String getPlace_of_residence_lon() {
        return this.place_of_residence_lon;
    }

    public void setPlace_of_residence_lon(String value) {
        this.place_of_residence_lon = value;
    }

    public Integer getNumber_of_children() {
        return this.number_of_children;
    }

    public void setNumber_of_children(Integer value) {
        this.number_of_children = value;
    }

    public String getPlace_of_residence() {
        return this.place_of_residence;
    }

    public void setPlace_of_residence(String value) {
        this.place_of_residence = value;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String value) {
        this.phone = value;
    }

    public String getFamily_status() {
        return this.family_status;
    }

    public void setFamily_status(String value) {
        this.family_status = value;
    }

    public Integer getCurrent_position() {
        return this.current_position;
    }

    public void setCurrent_position(Integer value) {
        this.current_position = value;
    }

    public Date getDate_of_birth() {
        return this.date_of_birth;
    }

    public void setDate_of_birth(Date value) {
        this.date_of_birth = value;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public Integer getBills_stats_pre() {
        return this.bills_stats_pre;
    }

    public void setBills_stats_pre(Integer value) {
        this.bills_stats_pre = value;
    }

    @Override
    @JsonProperty("img_url")
    public String getImageUrl() {
        return this.img_url;
    }

    public void setImg_url(String value) {
        this.img_url = value;
    }
    
    @Override
    public String toString() {
        return id + " " + name;
    }

}