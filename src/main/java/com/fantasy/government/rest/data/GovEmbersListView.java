package com.fantasy.government.rest.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_EMPTY)
public class GovEmbersListView extends GovMembersListView {
	
	public GovEmbersListView(GovMembersListView other) {
		this.objects = other.objects;
		this.meta = other.meta;
	}
	
	@JsonIgnore
	public List<GovMember> getObjects() {
		return super.getObjects();
	}
	
	@JsonIgnore
	public void setObjects(List<GovMember> value) {
		super.setObjects(value);;
	}
	
	public List<GovMember> getMembers() {
		return super.getObjects();
	}
	
	public void setMembers(List<GovMember> value) {
		super.setObjects(value);;
	}

}