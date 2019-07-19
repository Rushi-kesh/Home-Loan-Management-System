package com.Exceptions;

public class ExceedPeriodException extends Exception
{
	String msg;

	public ExceedPeriodException(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ExceedPeriodException [msg=" + msg + "]";
	}
	
}
