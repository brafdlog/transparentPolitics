package com.fantasy.government.dao.openknesset;

import java.util.List;

import com.fantasy.government.services.data.OpenKnessetGovMember;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_EMPTY)
public class OpenKnessetGovMembersList {
	
	protected List<OpenKnessetGovMember> objects;
	protected OpenKnessetResultMeta meta;
	
	public List<OpenKnessetGovMember> getObjects() {
		return this.objects;
	}
	public void setObjects(List<OpenKnessetGovMember> value) {
		this.objects = value;
	}
	public OpenKnessetResultMeta getMeta() {
		return this.meta;
	}
	public void setMeta(OpenKnessetResultMeta value) {
		this.meta = value;
	}

}