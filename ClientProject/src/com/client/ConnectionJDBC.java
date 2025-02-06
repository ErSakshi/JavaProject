package com.client;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionJDBC {
	
	Connection c ;
    Statement s ;

	public ConnectionJDBC() {
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver") ;
			c= DriverManager.getConnection("jdbc:mysql://localhost:3306/linkedIn_db","root","root") ;
			  s = c.createStatement();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

//	public Connection getConnection() {
//		 if (conn == null) {
//	            System.err.println("❌ getConnection() called but connection is NULL! Check database settings.");
//	        }
//	        return conn;
//		return null;
//	}

	
	


}
