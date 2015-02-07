package com.transparent.politics.dao.data;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.transparent.politics.services.data.GovMember;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class OpenKnessetGovMember implements GovMember {

    private Integer id;
    private String name;
    private String place_of_birth;
    private Date end_date;
    private String resource_uri;
    private List<OpenKnessetUrl> links;
//    private List<String> video_about;
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
//    private List<String> videos_related;
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

//    public List<String> getVideo_about() {
//        return this.video_about;
//    }
//
//    public void setVideo_about(List<String> value) {
//        this.video_about = value;
//    }

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

    @Override
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

//    public List<String> getVideos_related() {
//        return this.videos_related;
//    }
//
//    public void setVideos_related(List<String> value) {
//        this.videos_related = value;
//    }

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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((absolute_url == null) ? 0 : absolute_url.hashCode());
        result = prime * result + ((agendas_uri == null) ? 0 : agendas_uri.hashCode());
        result = prime * result + ((average_monthly_committee_presence == null) ? 0 : average_monthly_committee_presence.hashCode());
        result = prime * result + ((average_weekly_presence_hours == null) ? 0 : average_weekly_presence_hours.hashCode());
        result = prime * result + ((average_weekly_presence_rank == null) ? 0 : average_weekly_presence_rank.hashCode());
        result = prime * result + ((bills_stats_approved == null) ? 0 : bills_stats_approved.hashCode());
        result = prime * result + ((bills_stats_first == null) ? 0 : bills_stats_first.hashCode());
        result = prime * result + ((bills_stats_pre == null) ? 0 : bills_stats_pre.hashCode());
        result = prime * result + ((bills_stats_proposed == null) ? 0 : bills_stats_proposed.hashCode());
        result = prime * result + ((bills_uri == null) ? 0 : bills_uri.hashCode());
        result = prime * result + ((committees == null) ? 0 : committees.hashCode());
        result = prime * result + ((current_position == null) ? 0 : current_position.hashCode());
        result = prime * result + ((current_role_descriptions == null) ? 0 : current_role_descriptions.hashCode());
        result = prime * result + ((date_of_birth == null) ? 0 : date_of_birth.hashCode());
        result = prime * result + ((date_of_death == null) ? 0 : date_of_death.hashCode());
        result = prime * result + ((detailed_roles == null) ? 0 : detailed_roles.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((end_date == null) ? 0 : end_date.hashCode());
        result = prime * result + ((family_status == null) ? 0 : family_status.hashCode());
        result = prime * result + ((fax == null) ? 0 : fax.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((img_url == null) ? 0 : img_url.hashCode());
        result = prime * result + ((is_current == null) ? 0 : is_current.hashCode());
        result = prime * result + ((links == null) ? 0 : links.hashCode());
        result = prime * result + ((mmms_count == null) ? 0 : mmms_count.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((number_of_children == null) ? 0 : number_of_children.hashCode());
        result = prime * result + ((party_name == null) ? 0 : party_name.hashCode());
        result = prime * result + ((party_url == null) ? 0 : party_url.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((place_of_birth == null) ? 0 : place_of_birth.hashCode());
        result = prime * result + ((place_of_residence == null) ? 0 : place_of_residence.hashCode());
        result = prime * result + ((place_of_residence_lat == null) ? 0 : place_of_residence_lat.hashCode());
        result = prime * result + ((place_of_residence_lon == null) ? 0 : place_of_residence_lon.hashCode());
        result = prime * result + ((residence_centrality == null) ? 0 : residence_centrality.hashCode());
        result = prime * result + ((residence_economy == null) ? 0 : residence_economy.hashCode());
        result = prime * result + ((resource_uri == null) ? 0 : resource_uri.hashCode());
        result = prime * result + ((start_date == null) ? 0 : start_date.hashCode());
        result = prime * result + ((votes_count == null) ? 0 : votes_count.hashCode());
        result = prime * result + ((year_of_aliyah == null) ? 0 : year_of_aliyah.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OpenKnessetGovMember other = (OpenKnessetGovMember) obj;
        if (absolute_url == null) {
            if (other.absolute_url != null)
                return false;
        } else if (!absolute_url.equals(other.absolute_url))
            return false;
        if (agendas_uri == null) {
            if (other.agendas_uri != null)
                return false;
        } else if (!agendas_uri.equals(other.agendas_uri))
            return false;
        if (average_monthly_committee_presence == null) {
            if (other.average_monthly_committee_presence != null)
                return false;
        } else if (!average_monthly_committee_presence.equals(other.average_monthly_committee_presence))
            return false;
        if (average_weekly_presence_hours == null) {
            if (other.average_weekly_presence_hours != null)
                return false;
        } else if (!average_weekly_presence_hours.equals(other.average_weekly_presence_hours))
            return false;
        if (average_weekly_presence_rank == null) {
            if (other.average_weekly_presence_rank != null)
                return false;
        } else if (!average_weekly_presence_rank.equals(other.average_weekly_presence_rank))
            return false;
        if (bills_stats_approved == null) {
            if (other.bills_stats_approved != null)
                return false;
        } else if (!bills_stats_approved.equals(other.bills_stats_approved))
            return false;
        if (bills_stats_first == null) {
            if (other.bills_stats_first != null)
                return false;
        } else if (!bills_stats_first.equals(other.bills_stats_first))
            return false;
        if (bills_stats_pre == null) {
            if (other.bills_stats_pre != null)
                return false;
        } else if (!bills_stats_pre.equals(other.bills_stats_pre))
            return false;
        if (bills_stats_proposed == null) {
            if (other.bills_stats_proposed != null)
                return false;
        } else if (!bills_stats_proposed.equals(other.bills_stats_proposed))
            return false;
        if (bills_uri == null) {
            if (other.bills_uri != null)
                return false;
        } else if (!bills_uri.equals(other.bills_uri))
            return false;
        if (committees == null) {
            if (other.committees != null)
                return false;
        } else if (!committees.equals(other.committees))
            return false;
        if (current_position == null) {
            if (other.current_position != null)
                return false;
        } else if (!current_position.equals(other.current_position))
            return false;
        if (current_role_descriptions == null) {
            if (other.current_role_descriptions != null)
                return false;
        } else if (!current_role_descriptions.equals(other.current_role_descriptions))
            return false;
        if (date_of_birth == null) {
            if (other.date_of_birth != null)
                return false;
        } else if (!date_of_birth.equals(other.date_of_birth))
            return false;
        if (date_of_death == null) {
            if (other.date_of_death != null)
                return false;
        } else if (!date_of_death.equals(other.date_of_death))
            return false;
        if (detailed_roles == null) {
            if (other.detailed_roles != null)
                return false;
        } else if (!detailed_roles.equals(other.detailed_roles))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (end_date == null) {
            if (other.end_date != null)
                return false;
        } else if (!end_date.equals(other.end_date))
            return false;
        if (family_status == null) {
            if (other.family_status != null)
                return false;
        } else if (!family_status.equals(other.family_status))
            return false;
        if (fax == null) {
            if (other.fax != null)
                return false;
        } else if (!fax.equals(other.fax))
            return false;
        if (gender == null) {
            if (other.gender != null)
                return false;
        } else if (!gender.equals(other.gender))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (img_url == null) {
            if (other.img_url != null)
                return false;
        } else if (!img_url.equals(other.img_url))
            return false;
        if (is_current == null) {
            if (other.is_current != null)
                return false;
        } else if (!is_current.equals(other.is_current))
            return false;
        if (links == null) {
            if (other.links != null)
                return false;
        } else if (!links.equals(other.links))
            return false;
        if (mmms_count == null) {
            if (other.mmms_count != null)
                return false;
        } else if (!mmms_count.equals(other.mmms_count))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (number_of_children == null) {
            if (other.number_of_children != null)
                return false;
        } else if (!number_of_children.equals(other.number_of_children))
            return false;
        if (party_name == null) {
            if (other.party_name != null)
                return false;
        } else if (!party_name.equals(other.party_name))
            return false;
        if (party_url == null) {
            if (other.party_url != null)
                return false;
        } else if (!party_url.equals(other.party_url))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (place_of_birth == null) {
            if (other.place_of_birth != null)
                return false;
        } else if (!place_of_birth.equals(other.place_of_birth))
            return false;
        if (place_of_residence == null) {
            if (other.place_of_residence != null)
                return false;
        } else if (!place_of_residence.equals(other.place_of_residence))
            return false;
        if (place_of_residence_lat == null) {
            if (other.place_of_residence_lat != null)
                return false;
        } else if (!place_of_residence_lat.equals(other.place_of_residence_lat))
            return false;
        if (place_of_residence_lon == null) {
            if (other.place_of_residence_lon != null)
                return false;
        } else if (!place_of_residence_lon.equals(other.place_of_residence_lon))
            return false;
        if (residence_centrality == null) {
            if (other.residence_centrality != null)
                return false;
        } else if (!residence_centrality.equals(other.residence_centrality))
            return false;
        if (residence_economy == null) {
            if (other.residence_economy != null)
                return false;
        } else if (!residence_economy.equals(other.residence_economy))
            return false;
        if (resource_uri == null) {
            if (other.resource_uri != null)
                return false;
        } else if (!resource_uri.equals(other.resource_uri))
            return false;
        if (start_date == null) {
            if (other.start_date != null)
                return false;
        } else if (!start_date.equals(other.start_date))
            return false;
        if (votes_count == null) {
            if (other.votes_count != null)
                return false;
        } else if (!votes_count.equals(other.votes_count))
            return false;
        if (year_of_aliyah == null) {
            if (other.year_of_aliyah != null)
                return false;
        } else if (!year_of_aliyah.equals(other.year_of_aliyah))
            return false;
        return true;
    }
    
}