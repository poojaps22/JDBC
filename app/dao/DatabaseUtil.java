package com.fis.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
	
	private static Connection con;
	
	static
	{
		try
		{
			String host = "localhost:3306";
		    String database = "fisapp";
		    String url = "jdbc:mysql://"+ host+"/"+database;

			Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection(url,"root","Cloud@123$");
	       
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	public static Connection getConnection()
	{
		return con;
	}

}
