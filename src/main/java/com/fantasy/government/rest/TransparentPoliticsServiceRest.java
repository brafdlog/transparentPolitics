package com.fantasy.government.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.government.rest.data.GovEmbersListView;
import com.fantasy.government.services.proxy.OpenKnessetProxy;

@RestController
public class TransparentPoliticsServiceRest {
	
	@Autowired
	OpenKnessetProxy proxy;
	
	@RequestMapping("/members")
	public GovEmbersListView getMembers() {
		return new GovEmbersListView(proxy.getMembers());
	}
}