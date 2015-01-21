package com.fantasy.government.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.government.rest.data.GovMembersListView;
import com.fantasy.government.services.proxy.OpenKnessetProxy;

@Component
@Path("/proxy/openknesset")
@Produces(MediaType.APPLICATION_JSON)
public class TransparentPoliticsServiceRest {
	
	@Autowired
	OpenKnessetProxy proxy;
	
	@GET
	@Path("/members")
	public GovMembersListView getMembers() {
		return proxy.getMembers();
	}
}