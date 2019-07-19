package com.Exceptions;

public class InvalidAmountException extends Exception 
{
	String msg;

	public InvalidAmountException(String msg) 
	{
		super();
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "InvalidAmountException [msg=" + msg + "]";
	}
	
}
