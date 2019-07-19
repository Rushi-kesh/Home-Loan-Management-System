package com.DAOServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Beans.LoanData;
import com.DAOServiceLayer.DaoLoanInterface;
import com.Providers.DatabaseConnectionProvider;

public class DaoLoan implements DaoLoanInterface
{
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	int updateCount;
	
	public DaoLoan()
	{
		conn=DatabaseConnectionProvider.getConnection();
	}
	
	public boolean createLoan(LoanData L)
	{
		try
		{
			ps=conn.prepareStatement("insert into loandetails values(?,?,?,?,?,?,?);");
			ps.setInt(1, L.getLoanAccNo());
			ps.setDouble(2, L.getLoanAmount());
			ps.setInt(3, L.getLoanPeriod());
			ps.setDouble(4, L.getRemAmount());
			ps.setInt(5, L.getRemPeriod());
			ps.setDouble(6, L.getMonAmount());
			ps.setString(7, L.getLoanType());
			
			
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

	public LoanData getLoan(int loanAccount,String loanType)
	{
		try
		{
			LoanData L=null;
			ps=conn.prepareStatement("Select * from loandetails;");
			rs=ps.executeQuery();
			while(rs.next())
			{
				
				
				if(rs.getInt(1)==(loanAccount) && rs.getString(7).equals(loanType) )
				{
					L=new LoanData(rs.getDouble(2), rs.getInt(3));
					L.setLoanAccNo(rs.getInt(1));
					L.setRemAmount(rs.getDouble(4));
					L.setRemPeriod(rs.getInt(5));
					L.setMonAmount(rs.getDouble(6));
					L.setLoanType(rs.getString(7));
				}
				
			}
			return L;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
		
	}

	
	public boolean updateLoanDetails(LoanData L) 
	{
		
		if(deleteLoanDetails(L))
		{
			if(createLoan(L))
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean deleteLoanDetails(LoanData L)
	{
		try
		{
			ps=conn.prepareStatement("DELETE FROM loandetails WHERE accountnumber = ?");
			ps.setInt(1, L.getLoanAccNo());
			updateCount=ps.executeUpdate();
			
			if(updateCount==1)
			{
				return true;
			}
		}
		catch(SQLException  e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

}
