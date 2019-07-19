package com.Providers;

import java.io.FileInputStream;
import java.util.Properties;

import com.DAOServiceLayer.DaoLoginInterface;


public class LoginInstanceProvider 
{
	public static DaoLoginInterface getInstance()
	{
		DaoLoginInterface acc=null;
		
		
		
		try 
		{
			FileInputStream fis=new FileInputStream(".//resources//info.properties");
			Properties p=new Properties();
			p.load(fis);
			String Login=null;
			
			Login=p.getProperty("LoginInterface");
			
			Class c =Class.forName(Login);
			acc=(DaoLoginInterface) c.newInstance();
		}
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InstantiationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return acc;
		
	}

}
