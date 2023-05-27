package com.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public final class DbConnection {
	private static String jdbcUrl = "jdbc:mysql://localhost:3306/bookstop?allowMultiQueries=true";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "";
	private static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	private static Connection con = null;
	
	private DbConnection() {
		// Empty constructor. Do nothing
	}
	
	public static Connection jdbcConnection() {
		try {
			if (null == con) {
				Class.forName(jdbcDriver);
				con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
				new BookDao(con).calculateFine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}


}
