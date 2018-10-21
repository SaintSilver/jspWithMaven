package com.kutar.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class ConnectionManager {
	
	public static Connection getConnection() {
		
		ResourceBundle rb = ResourceBundle.getBundle("db");
		
		try {
			Class.forName(rb.getString("jdbc.driver"));
			return DriverManager.getConnection(
					rb.getString("jdbc.url"), 
					rb.getString("jdbc.username"),
					rb.getString("jdbc.password"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
