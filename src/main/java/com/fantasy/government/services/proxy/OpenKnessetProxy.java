package com.fantasy.government.services.proxy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.stereotype.Component;

import com.fantasy.government.rest.data.GovMembersListView;

@Path("/proxy/openknesset")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class OpenKnessetProxy {
	
	interface OpenKnesset {
		@GET
		@Path("api/v2/member/")
		@Produces(MediaType.APPLICATION_JSON)
		GovMembersListView getMembers(@QueryParam("api_key") String key);
	}

	public GovMembersListView getMembers() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://oknesset.org/");
		ResteasyWebTarget rtarget = (ResteasyWebTarget)target;
		
		OpenKnesset proxy = rtarget.proxy(OpenKnesset.class);
		try {
			GovMembersListView res = proxy.getMembers("special-key");
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}