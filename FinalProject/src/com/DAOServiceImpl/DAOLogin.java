package com.DAOServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Beans.UserDetails;
import com.DAOServiceLayer.DaoLoginInterface;
import com.Providers.DatabaseConnectionProvider;


public class DAOLogin implements DaoLoginInterface
{
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	int updateCount;
	public DAOLogin()
	{
		conn=DatabaseConnectionProvider.getConnection();
	}
	public boolean createUser(UserDetails user) 
	{
		try
		{
			ps=conn.prepareStatement("insert into loanusers (username,password) values(?,?);");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			updateCount=ps.executeUpdate();
			ps=conn.prepareStatement("Select * from loanusers;");
			rs=ps.executeQuery();
			while(rs.next())
			{
			user.setAccountNumber(rs.getInt(3));
			}
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


	public boolean validate(UserDetails user) 
	{
		try
		{
			ps=conn.prepareStatement("Select * from loanusers;");
			rs=ps.executeQuery();
			while(rs.next())
			{
				
				if(rs.getString(1).equals(user.getUserName()) && rs.getString(2).equals(user.getPassword()))
				{
					user.setAccountNumber(rs.getInt(3));
					return true;
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public UserDetails getUserDetails(UserDetails user) 
	{
		try
		{
			ps=conn.prepareStatement("Select * from loanusers;");
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				
				if(rs.getString(1).equals(user.getUserName()) && rs.getString(2).equals(user.getPassword()))
				{
					user.setAccountNumber(rs.getInt(3));
				}
			}
		}
		catch(SQLException  e)
		{
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean verifyUserName(String userName) 
	{
		try
		{
			ps=conn.prepareStatement("Select * from loanusers;");
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				
				if(rs.getString(1).equals(userName))
				{
					return true;
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	
	
}
