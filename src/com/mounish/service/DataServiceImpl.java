package com.mounish.service;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.mounish.db.DBConnection;
import com.mounish.util.HttpUtil;

/**
 * Class for method implementation related to data service
 * 
 * @author Mounish Savier
 *
 */

public class DataServiceImpl implements DataService {
	
	@Override
	public String retrieveCompaigns(String compaignURL) {
		return HttpUtil.readRestData(compaignURL);
	}

	@Override
	public String retrieveActivities(String creativeURL) {
		return HttpUtil.readRestData(creativeURL);
	}

	@Override
	public void exportData() {
		String query = "select ca.id Campaign_ID ,ca.name Campaign_Name," + " sum(cr.clicks) Total_Clicks," + " sum(cr.views) Total_Views,"
				+ "round(sum(cr.views)*ca.cpm/1000) revenue " + "from sm_campaign ca, sm_creatives cr "
				+ "where ca.id=cr.parentId  " + "group by ca.id, ca.name, ca.cpm";
 
		
		Connection conn = null;
		Statement statement = null;

		try {
			try {
				conn = DBConnection.getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			statement = conn.createStatement();

			ResultSet rs = statement.executeQuery(query);

			try {
				convertToCsv(rs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			while (rs.next()) {

				String campaignId = rs.getString("campaign_id");
				String campainName = rs.getString("campaign_name");
				long clicks = rs.getLong("clicks");
				long views = rs.getLong("viewSum");
				double revenue = rs.getDouble("revenue");

				System.out.println("userid : " + campaignId);
				System.out.println("username : " + campainName);
				System.out.println("clicks : " + clicks);
				System.out.println("views : " + views);
				System.out.println("revenue : " + revenue);

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	/**
	 * Method to convert Result set to CSV file
	 * @param rs
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
	private static void convertToCsv(ResultSet resultSet) throws SQLException, FileNotFoundException {
		PrintWriter csvWriter = new PrintWriter(new File("Campaign.csv"));
		ResultSetMetaData meta = resultSet.getMetaData();
		int numberOfColumns = meta.getColumnCount();
		String dataHeaders = "\"" + meta.getColumnName(1) + "\"";
		for (int i = 2; i < numberOfColumns + 1; i++) {		
			dataHeaders += ",\"" + meta.getColumnName(i).replaceAll("\"", "\\\"") + "\"";
			System.out.println(dataHeaders);
		}
		csvWriter.println(dataHeaders);
		while (resultSet.next()) {
			String row = "\"" + resultSet.getString(1).replaceAll("\"", "\\\"") + "\"";
			System.out.println(row);
			for (int i = 2; i < numberOfColumns + 1; i++) {			
				
				if(i==5){
					row += ",\""+"$" + resultSet.getString(i).replaceAll("\"", "\\\"") + "\"";
				}else{
				    row += ",\"" + resultSet.getString(i).replaceAll("\"", "\\\"") + "\"";
				}
				System.out.println(row);
			}
			csvWriter.println(row);
		}
		csvWriter.close();
	}

	
}
