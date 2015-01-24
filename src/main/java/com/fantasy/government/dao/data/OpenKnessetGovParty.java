package com.fantasy.government.dao.data;

import com.fantasy.government.services.data.GovParty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class OpenKnessetGovParty implements GovParty {

    private Integer id;
    private String name;
    private String logo;
    private Boolean is_coalition;
    private Integer number_of_seats;
    private Integer knesset_id;
    private Integer number_of_members;
    private String absolute_url;
    private String resource_uri;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Boolean getIs_coalition() {
        return is_coalition;
    }

    public void setIs_coalition(Boolean is_coalition) {
        this.is_coalition = is_coalition;
    }

    public Integer getNumber_of_seats() {
        return number_of_seats;
    }

    public void setNumber_of_seats(Integer number_of_seats) {
        this.number_of_seats = number_of_seats;
    }

    public Integer getKnesset_id() {
        return knesset_id;
    }

    public void setKnesset_id(Integer knesset_id) {
        this.knesset_id = knesset_id;
    }

    public Integer getNumber_of_members() {
        return number_of_members;
    }

    public void setNumber_of_members(Integer number_of_members) {
        this.number_of_members = number_of_members;
    }

    public String getAbsolute_url() {
        return absolute_url;
    }

    public void setAbsolute_url(String absolute_url) {
        this.absolute_url = absolute_url;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public void setResource_uri(String resource_uri) {
        this.resource_uri = resource_uri;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    @Override
    public String getImageUrl() {
        return logo;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }

}