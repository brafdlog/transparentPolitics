package com.fantasy.government.dao.openknesset;

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

@Path("/proxy/openknesset")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class OpenKnessetProxy {
	
	interface OpenKnesset {
		@GET
		@Path("api/v2/member/")
		@Produces(MediaType.APPLICATION_JSON)
		OpenKnessetGovMembersList getMembers(@QueryParam("api_key") String key, @QueryParam("is_current") Boolean isCurrent);
		
		@GET
        @Path("api/v2/member/")
        @Produces(MediaType.APPLICATION_JSON)
        OpenKnessetGovMembersList getAllMembers(@QueryParam("api_key") String key);
	}
	
	public OpenKnessetGovMembersList getAllMembers() {
	    return getMembers(null);
	}

	public OpenKnessetGovMembersList getMembers(Boolean onlyCurrent) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://oknesset.org/");
		ResteasyWebTarget rtarget = (ResteasyWebTarget)target;
		
		OpenKnesset proxy = rtarget.proxy(OpenKnesset.class);
		try {
		    OpenKnessetGovMembersList res;
		    if (onlyCurrent != null) {
		        res = proxy.getMembers("special-key", onlyCurrent);
		    } else {
		        res = proxy.getAllMembers("special-key");
		    }
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}