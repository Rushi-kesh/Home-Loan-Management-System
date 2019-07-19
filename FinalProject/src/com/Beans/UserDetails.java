package com.Beans;

public class UserDetails 
{
	private String userName;
	private String password;
	private int accountNumber;
	public UserDetails(String userName, String password) 
	{
		
		this.userName = userName;
		this.password = password;
	}
	
	@Override
	public String toString() 
	{
		return "UserDetails [userName=" + userName + ", password=" + password + "]";
	}

	public String getUserName() 
	{
		return userName;
	}
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	

}
