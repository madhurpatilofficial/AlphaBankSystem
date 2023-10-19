package com.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnect {
	
	Connection con = null;
	public Connection getConnection() throws SQLException{
	
	String url = "jdbc:mysql://localhost:3306/banksystem";
	String username = "root";
	String password = "root";
	
	
	try {
		//setting connection object
		con = DriverManager.getConnection(url, username, password);
//		System.out.println(con); //connection object
		
		if(con!=null) {
			System.out.println("Connection is successful....");
		}
		else {
			System.out.println("Not able to connect....");
		}
	}
	
	//if any error comes in try block, catch block will execute
	catch(Exception e) {
		System.out.println("Error " + e.getMessage());
	}
	return con;

	}
}
