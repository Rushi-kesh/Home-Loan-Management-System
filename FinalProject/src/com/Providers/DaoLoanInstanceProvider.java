package com.Providers;

import java.io.FileInputStream;
import java.util.Properties;

import com.DAOServiceLayer.DaoLoanInterface;


public class DaoLoanInstanceProvider 
{
	public static DaoLoanInterface getInstnace()
	{
		DaoLoanInterface acc=null;
		
		
		
		try 
		{
			FileInputStream fis=new FileInputStream(".//resources//info.properties");
			Properties p=new Properties();
			p.load(fis);
			String Loan=null;
			
			Loan=p.getProperty("DaoLoanInterface");
			
			Class c =Class.forName(Loan);
			acc=(DaoLoanInterface) c.newInstance();
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
