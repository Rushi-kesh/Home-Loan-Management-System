package com.Providers;

import java.io.FileInputStream;
import java.util.Properties;

import com.DAOServiceLayer.DaoLoanInterface;
import com.DAOServiceLayer.DaoTransactInterface;

public class DaoTInstance 
{
	public static DaoTransactInterface getInstance()
	{
		DaoTransactInterface acc=null;
		
		
		
		try 
		{
			FileInputStream fis=new FileInputStream(".//resources//info.properties");
			Properties p=new Properties();
			p.load(fis);
			String Loan=null;
			
			Loan=p.getProperty("DaoTransactInterface");
			
			Class c =Class.forName(Loan);
			acc=(DaoTransactInterface) c.newInstance();
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
