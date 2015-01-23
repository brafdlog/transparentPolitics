package com.fantasy.government.rest.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_EMPTY)
public class GovMembersListView {
	
	protected List<GovMember> objects;
	protected ResultMeta meta;
	
	public List<GovMember> getObjects() {
		return this.objects;
	}
	public void setObjects(List<GovMember> value) {
		this.objects = value;
	}
	public ResultMeta getMeta() {
		return this.meta;
	}
	public void setMeta(ResultMeta value) {
		this.meta = value;
	}

}