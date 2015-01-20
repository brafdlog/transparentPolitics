package com.fantasy.government.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.fantasy.government.rest.data.GovMembersListView;
import com.fantasy.government.services.proxy.OpenKnessetProxy;

@Component
@Path("/proxy/openknesset")
@Produces(MediaType.APPLICATION_JSON)
public class TransparentPoliticsServiceRest {
	
	@GET
	@Path("/members")
	public GovMembersListView getMembers() {
		return new OpenKnessetProxy().getMembers();
	}
}