package com.mounish.client;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mounish.service.CampaignService;
import com.mounish.service.CampaignServiceImpl;
import com.mounish.service.CreativeService;
import com.mounish.service.CreativeServiceImpl;
import com.mounish.service.DataService;
import com.mounish.service.DataServiceImpl;
import com.mounish.service.model.Campaign;
import com.mounish.service.model.Creative;
import com.mounish.util.JSONUtil;

/** 
 * Helper class to have methods called by client 
 * 
 * @author Mounish Savier
 *
 */
public class ClientHelper {
    //Dependent Service Objects
	private DataService dataService;
	private CampaignService campaignService;
	private CreativeService creativeService;
	
    /**
     * Constructor to create dependent service Objects
     */
	public ClientHelper() {
		dataService = new DataServiceImpl();
		campaignService = new CampaignServiceImpl();
		creativeService = new CreativeServiceImpl();
	}

	/**
	 * Method to retrieve the campaign data by calling REST End point and convert JSON into Java Object		
	 * 
	 * @param capaignURI
	 * @throws IOException
	 */
	public void createCompaigns(String campaignURI) throws IOException {
		String campaignsJSON = dataService.retrieveCompaigns(campaignURI);
		JSONArray jsonArray = JSONUtil.getJSONArray(campaignsJSON);
		List<Campaign> compaigns = prepareCampaignsList(jsonArray);
		campaignService.addCampaigns(compaigns);
	}
	/**
	 *  Method to retrieve the creative data by calling REST End point and convert JSON into Java Object	
	 *  
	 * @param creativeURL
	 * @throws IOException
	 */
	public void createActivities(String creativeURL) throws IOException {
		String creativesJSON = dataService.retrieveActivities(creativeURL);
		JSONArray jsonArray = JSONUtil.getJSONArray(creativesJSON);
		List<Creative> activities = prepareCeativesList(jsonArray);
		creativeService.addCreatives(activities);
	}
	
    /**
     * Method to prepare Campaign List as java object
     * @param jsonArray
     * @return
     */
	private List<Campaign> prepareCampaignsList(JSONArray jsonArray) {
		List<Campaign> campaigns = new ArrayList<Campaign>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject json = (JSONObject) jsonArray.get(i);
			Campaign camp = new Campaign();
			String cpm = (String) json.get("cpm");
			camp.setCpm(Double.parseDouble(cpm.substring(1)));
			String id =  json.get("id").toString();
			camp.setId(Integer.parseInt(id));
			camp.setName((String) json.get("name"));
			camp.setStartDate((String) json.get("startDate"));
			campaigns.add(camp);
		}
		return campaigns;
	}
	
	 /**
     * Method to prepare Creative List as java object
     * 
     * @param jsonArray
     * @return
     */

	private List<Creative> prepareCeativesList(JSONArray jsonArray) {
		List<Creative> creatives = new ArrayList<Creative>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject json = (JSONObject) jsonArray.get(i);
			Creative crt = new Creative();
			String id = json.get("id").toString();
			crt.setId(Integer.parseInt(id));
			String parentId =  json.get("parentId").toString();
			crt.setParentId(Integer.parseInt(parentId));
			String clicks =  json.get("clicks").toString();
			crt.setClicks(Long.parseLong(clicks));
			String views = json.get("views").toString();
			crt.setViews(Long.parseLong(views));
			creatives.add(crt);
		}
		return creatives;
	}
	/**
	 * Read from Database and export into CSV
	 */
	public void exportCSVData() {
		dataService.exportData();
	}

}
