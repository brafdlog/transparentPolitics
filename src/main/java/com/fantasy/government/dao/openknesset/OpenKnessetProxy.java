package com.fantasy.government.dao.openknesset;

import java.io.IOException;

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

import com.fantasy.government.common.HttpUtils;
import com.fantasy.government.dao.data.OpenKnessetGovMember;
import com.fantasy.government.dao.data.OpenKnessetGovParty;

@Path("/proxy/openknesset")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class OpenKnessetProxy {
	
	private static final String API_KEY = "special-key";
    private static final int NO_PAGING_OF_RESULTS = 0;

    interface OpenKnesset {
		@GET
		@Path("api/v2/member/")
		@Produces(MediaType.APPLICATION_JSON)
		OpenKnessetGovMemberList getMembers(@QueryParam("api_key") String key, @QueryParam("limit") Integer limit, @QueryParam("is_current") Boolean isCurrent);
		
		@GET
        @Path("api/v2/member/")
        @Produces(MediaType.APPLICATION_JSON)
        OpenKnessetGovMemberList getAllMembers(@QueryParam("api_key") String key, @QueryParam("limit") Integer limit);
		
        @GET
        @Path("api/v2/party/")
        @Produces(MediaType.APPLICATION_JSON)
        OpenKnessetGovPartyList getAllParties(@QueryParam("api_key") String key, @QueryParam("limit") Integer limit);
	}
	
	public OpenKnessetGovMemberList getAllMembers() {
	    return getMembers(null);
	}

	public OpenKnessetGovMemberList getMembers(Boolean onlyCurrent) {
		OpenKnesset proxy = getOpenKnessetProxy();
		try {
		    OpenKnessetGovMemberList res;
		    if (onlyCurrent != null) {
		        res = proxy.getMembers(API_KEY, NO_PAGING_OF_RESULTS, onlyCurrent);
		    } else {
		        res = proxy.getAllMembers(API_KEY, NO_PAGING_OF_RESULTS);
		    }
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public OpenKnessetGovMember getMember(Integer memberId) {
	    try {
	        // Can't use rest easy's client because the url that doesn't end with / causes redirect and it can't follow redirects
            return HttpUtils.getJsonObjectFromUrl("https://oknesset.org/api/v2/member/" + memberId, OpenKnessetGovMember.class);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return null;
	}
	
	public OpenKnessetGovPartyList getAllParties() {
        OpenKnesset proxy = getOpenKnessetProxy();
        try {
            OpenKnessetGovPartyList partyList = proxy.getAllParties(API_KEY, NO_PAGING_OF_RESULTS);
            return partyList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public OpenKnessetGovParty getParty(Integer partyId) {
        try {
            // Can't use rest easy's client because the url that doesn't end with / causes redirect and it can't follow redirects
            return HttpUtils.getJsonObjectFromUrl("https://oknesset.org/api/v2/party/" + partyId, OpenKnessetGovParty.class);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return null;
    }

    private OpenKnesset getOpenKnessetProxy() {
        
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://oknesset.org/");
        ResteasyWebTarget rtarget = (ResteasyWebTarget)target;
        OpenKnesset proxy = rtarget.proxy(OpenKnesset.class);
        return proxy;
    }
    
}