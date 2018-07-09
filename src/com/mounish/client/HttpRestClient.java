package com.mounish.client;

import java.io.IOException;

/**
 * Java Client to perform the following
 * 1. read the campaign REST API and Creative REST API. 
 * 2. store the data into the corresponding Tables.
 * 3. Query the data from the tables as per the requirement
 * 4. Export the data into CSV file
 * 
 * @author Mounish Savier
 * 
 *
 */

public class HttpRestClient {
	
	public static final String CAMPAIGN_URL = "http://homework.ad-juster.com/api/campaigns"; // Campaign REST URL
	public static final String CREATIVES_URL = "http://homework.ad-juster.com/api/creatives"; // Creative REST URL

	public static void main(String[] args) throws IOException {

		ClientHelper helper = new ClientHelper();

		helper.createCompaigns(CAMPAIGN_URL);
		helper.createActivities(CREATIVES_URL);
		helper.exportCSVData();

	}

}
