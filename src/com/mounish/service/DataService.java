package com.mounish.service;

/**
 * Interface for methods related to Data service like read from JSON and write into CSV
 * 
 * @author Mounish Savier
 *
 */
public interface DataService {
	/**
	 * Method to get the value from REST API for list of Campaigns
	 * @return String
	 */
	public String retrieveCompaigns(String campaignURL);
	
	/**
	 * Method to get the value from REST API for list of Activities
	 * @return String
	 */
	public String retrieveActivities(String creativeURL);
	
	/**
	 * Query the DB and Export to CSV file
	 */
	public void exportData();

}
