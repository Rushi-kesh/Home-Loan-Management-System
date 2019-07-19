package com.DAOServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.DAOServiceLayer.DaoTransactInterface;
import com.Providers.DatabaseConnectionProvider;

public class DaoLoanTransaction implements DaoTransactInterface
{

	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	int updateCount;
	public DaoLoanTransaction()
	{
		conn=DatabaseConnectionProvider.getConnection();
	}
	public boolean doTransaction(int loanAcc, Double amount) 
	{
		try
		{
			ps=conn.prepareStatement("insert into loantransactions values(?,?);");
			ps.setInt(1, loanAcc);
			ps.setDouble(2, amount);
			updateCount=ps.executeUpdate();
			
			if(updateCount==1)
			{
				return true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public void showTransactions(int loanAcc) 
	{
		try
		{
			ps=conn.prepareStatement("Select * from loantransactions;");
			rs=ps.executeQuery();
			int i=1;
			while(rs.next())
			{
				System.out.println(i+":"+rs.getDouble(2)+"");
				i++;
				
			}
		}
		catch(SQLException  e)
		{
			e.printStackTrace();
		}
		
	}
	
}

