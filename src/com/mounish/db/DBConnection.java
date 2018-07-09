package com.mounish.db;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
/**
 * Class to create Database connection by reading the details from the properties file
 * @author Mounish Savier
 *
 */
public class DBConnection {
    /**
     * Method to create Database connection
     * 
     * @return
     * @throws Exception
     */
	public static Connection getConnection() throws Exception {

		Properties dbProp = readDBProperties();
		String url = dbProp.getProperty("url");
		System.out.println(url);
		String username = dbProp.getProperty("username");
		String password = dbProp.getProperty("password");

		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection(url, username, password);
		
		return connection;
	}
    /**
     * Method to read Database connection details
     * @return
     * @throws IOException
     */
	private static Properties readDBProperties() throws IOException {
		Properties dbProp = new Properties();
		InputStream inStream = null;
		try {
			inStream = new FileInputStream("db.properties");
			dbProp.load(inStream);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (inStream != null) {
				inStream.close();
			} 
		}
		return dbProp;

	}
}
