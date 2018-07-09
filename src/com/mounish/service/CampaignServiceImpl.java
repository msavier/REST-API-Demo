package com.mounish.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import com.mounish.db.DBConnection;
import com.mounish.service.model.Campaign;

/**
 * Class for the Campaign related DB activities
 * 
 * @author Mounish Savier
 *
 */

public class CampaignServiceImpl implements CampaignService {

	@Override
	public boolean addCampaigns(List<Campaign> campaigns) {
		boolean status = false;

		Connection conn = null;
		try {
		  conn = DBConnection.getConnection();
		} catch (Exception e1) {
			
		}

		String insertQuery = "insert into SM_CAMPAIGN (id, cpm, name, start_date)" + " values (?, ?, ?, ?)";
		String deleteQuery = "delete from sm_campaign";
		
		PreparedStatement preparedStmt = null;
		PreparedStatement preparedStmtDel = null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			preparedStmt = conn.prepareStatement(insertQuery);
			preparedStmtDel = conn.prepareStatement(deleteQuery);	
			preparedStmtDel.execute();
			for (Campaign camp : campaigns) {
				preparedStmt.setInt(1, camp.getId());
				preparedStmt.setDouble(2, camp.getCpm());
				preparedStmt.setString(3, camp.getName());
				preparedStmt.setString(4, camp.getStartDate());
				
				preparedStmt.execute();
	
			}
			
			conn.commit();
			conn.close();
			status = true;
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;

	}
}
