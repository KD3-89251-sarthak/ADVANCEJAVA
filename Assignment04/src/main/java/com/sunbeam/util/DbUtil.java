package com.sunbeam.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjava";
	private static final String DB_USER = "KD3_89243_Akshay";
	private static final String DB_PASSWD = "manager";
	
	static {
		
		try {
			Class.forName(DB_DRIVER);
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	public static Connection getConnection() throws Exception{
		
		Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
		return con;
		
	}
	

}
