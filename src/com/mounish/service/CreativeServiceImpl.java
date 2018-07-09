package com.mounish.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.mounish.db.DBConnection;
import com.mounish.service.model.Creative;
import com.mounish.service.model.Campaign;

/**
 * Class for the Creative related DB activities
 * 
 * @author Mounish Savier
 *
 */
public class CreativeServiceImpl implements CreativeService {

	@Override
	public boolean addCreatives(List<Creative> creatives) {
		boolean status = false;

		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
		} catch (Exception e1) {
		}
		String insertQuery = "insert into SM_CREATIVES (id, parentId, clicks, views)" + " values (?, ?, ?, ?)";
		String deleteQuery = "delete from sm_creatives";
		
		PreparedStatement preparedStmt = null;
		PreparedStatement preparedStmtDel = null;
		
		try {

			preparedStmt = conn.prepareStatement(insertQuery);
			preparedStmtDel = conn.prepareStatement(deleteQuery);
			preparedStmtDel.execute();

			for (Creative creative : creatives) {
				preparedStmt.setInt(1, creative.getId());
				preparedStmt.setInt(2, creative.getParentId());
				preparedStmt.setLong(3, creative.getClicks());
				preparedStmt.setLong(4, creative.getViews());
				
				
				preparedStmt.execute();
			}
			conn.commit();
			conn.close();	
			status = true;
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		} 
		return status;
	}

}
