package com.transparent.politics.dao.openknesset;

import java.util.List;

import com.transparent.politics.dao.data.OpenKnessetGovParty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_EMPTY)
public class OpenKnessetGovPartyList {
	
	protected List<OpenKnessetGovParty> objects;
	protected OpenKnessetResultMeta meta;
	
	public List<OpenKnessetGovParty> getObjects() {
		return this.objects;
	}
	public void setObjects(List<OpenKnessetGovParty> value) {
		this.objects = value;
	}
	public OpenKnessetResultMeta getMeta() {
		return this.meta;
	}
	public void setMeta(OpenKnessetResultMeta value) {
		this.meta = value;
	}

}