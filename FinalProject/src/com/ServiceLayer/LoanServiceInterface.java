package com.ServiceLayer;

import java.sql.SQLException;

import com.Beans.LoanData;
import com.Beans.UserDetails;
import com.Exceptions.InvalidAmountException;

public interface LoanServiceInterface 
{
	boolean registerLoanDetails(double loanAmount,int loanPeriod,UserDetails user);
	void loanDetails();
	boolean payInstallment();
	boolean payPartialInstallment(double amount)throws  InvalidAmountException;
	boolean payPartialInstallment(double amount,int period)throws InvalidAmountException;
	boolean payFullInstallment();
	double emiRate();
	void transactionHistory();
	boolean findLoan(String loanType,int loanAccount); 
	public LoanData getHL();
	
	
	
	
}
