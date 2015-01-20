package com.fantasy.government.rest.data;

import java.util.List;

public class GovMembersListView {
	
	private List<GovMember> objects;
	private ResultMeta meta;
	
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