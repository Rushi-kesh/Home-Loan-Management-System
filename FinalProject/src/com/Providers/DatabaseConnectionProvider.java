package com.Providers;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;



public class DatabaseConnectionProvider 
{
	
	public static Connection getConnection()
	{
		
		
		try 
		{
			 Connection con;
			 FileInputStream fis=new FileInputStream(".//resources//database.properties");
			 Properties p=new Properties();
			 p.load(fis);
			 String driver=p.getProperty("driver");
			 String url=p.getProperty("url");
			 String userName=p.getProperty("username");
			 String pass=p.getProperty("password");
			 Class.forName(driver);
			 con=DriverManager.getConnection(url,userName,pass);
			 return con;
			 
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		return null;
		
	}

}
