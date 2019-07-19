package com.Providers;

import java.io.FileInputStream;
import java.util.Properties;
import com.ServiceLayer.LoanServiceInterface;
public class LoanInstanceProvider 
{
	public static LoanServiceInterface getInstance(String str)
	{
		LoanServiceInterface acc=null;
		
		
		
		try 
		{
			FileInputStream fis=new FileInputStream(".//resources//info.properties");
			Properties p=new Properties();
			p.load(fis);
			String Loan=null;
			if(str=="HomeLoan")
			{
				Loan=p.getProperty("HomeLoan");
			}
			else if(str=="CarLoan")
			{
				Loan=p.getProperty("CarLoan");
			}
			Class c =Class.forName(Loan);
			acc=(LoanServiceInterface) c.newInstance();
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


