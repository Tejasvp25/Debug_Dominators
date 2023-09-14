package com.debug.dominators.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {
	
	private static Connection con;
	
	private static String url="jdbc:mysql://localhost:3306/springd";
	private static String user="root";
	private static String password="xxxxx";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if(con == null) {
			 Class.forName("com.mysql.cj.jdbc.Driver"); 
			 con = DriverManager.getConnection(url,user,password);
			 
		}
		return con;
	}

}
