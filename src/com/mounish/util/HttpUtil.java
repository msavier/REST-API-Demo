package com.mounish.util;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Utility class to read data using HTTP connection by invoking REST End point
 * @author Mounish Savier
 *
 */
public class HttpUtil {
    /**
     * Method to read data from REST End point
     * 
     * @param restURI
     * @return
     */
	public static String readRestData(String restURI) {

		StringBuffer responseJSON = new StringBuffer();
		HttpURLConnection conn = null;
		InputStream error = null;
		
		try {

			URL url = new URL(restURI);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String line = "";
			while ((line = br.readLine()) != null) {
				responseJSON.append(line);
			}			

		}  catch (IOException ioe) {
			if (ioe instanceof java.net.MalformedURLException) {
				System.out.println("Invalid URL provided");
			}
			if (conn instanceof HttpURLConnection) {
				int statusCode = 500;
				try {
					statusCode = conn.getResponseCode();
				} catch (Exception e) {
					System.out.println("Connection timed out");
					System.exit(1);
				}
				if (statusCode != 200) {
					error = conn.getErrorStream();
				}
				System.out.println("\nResponse Status " + statusCode);
			}
			
		}
		return responseJSON.toString();
	}

}
