package com.ServiceImpleamentation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.Beans.LoanData;
import com.Beans.UserDetails;
import com.DAOServiceLayer.DaoLoanInterface;
import com.DAOServiceLayer.DaoTransactInterface;
import com.Exceptions.InvalidAmountException;
import com.Providers.DaoLoanInstanceProvider;
import com.Providers.DaoTInstance;
import com.ServiceLayer.LoanServiceInterface;

public class CarLoanManagement implements LoanServiceInterface
{
	private LoanData CL;
	public static float emi=0.01f;
	private DaoLoanInterface DL;
	private DaoTransactInterface DLT;
	
	public CarLoanManagement() 
	{
		
		DL=DaoLoanInstanceProvider.getInstnace();
		DLT=DaoTInstance.getInstance();
	
	}

	public boolean registerLoanDetails(double loanAmount,int loanPeriod,UserDetails user) 
	{
		CL=new LoanData(loanAmount, loanPeriod);
		CL.setLoanAccNo(user.getAccountNumber());
		 calculateEmi( loanAmount,loanPeriod);
		 CL.setRemAmount(CL.getMonAmount()*loanPeriod);
		 
		 CL.setRemPeriod(loanPeriod);
		 
		 CL.setLoanType("Car");
		 
		 if(DL.createLoan(CL))
		 {
			 return true;
		 }
		
		return false;
	}
	public void calculateEmi(double remAmt,int noInstallment)
	{
		double emiRate=emiRate();
		double temp=1.0f;
		for(int i =1;i<noInstallment;i++)
		{
			temp=temp*(1+emiRate);
		}
		double monthlyInst= ((remAmt)*((emiRate*temp)/(temp-1)));
		CL.setMonAmount(monthlyInst);
		
	}
	public void loanDetails() 
	{
		System.out.println("\n\n\t****Car Loan Details****");
		System.out.println("\tLoan account number : "+CL.getLoanAccNo());
		System.out.println("\tLoan purchased Amount : "+CL.getLoanAmount());
		System.out.println("\tLoan repay period : "+CL.getLoanPeriod());
		System.out.println("\n\n\t****Current status of Car Loan****");
		if(CL.getRemAmount()==0)
		{
			System.out.println("\tCongrats you already paid loan");
		}
		else
		{
			System.out.println("\t Remaining Loan amount : "+CL.getRemAmount());
			System.out.println("\tRemaining Loan repay period (with interest) : "+CL.getRemPeriod());
			System.out.println("\tMonthly installment amount : "+CL.getMonAmount()+"\n\n");
			
			
		}
		
		
		
	}

	
	public boolean payInstallment() 
	{
		double monthlyAmount=CL.getMonAmount();
		
		double temp=CL.getRemAmount();
		temp=temp-monthlyAmount;
		int rem=CL.getRemPeriod();
		rem--;
		CL.setRemPeriod(rem);
		CL.setRemAmount(temp);
		DLT.doTransaction(CL.getLoanAccNo(),(monthlyAmount));
		return true;
	}

	
	public boolean payPartialInstallment(double amount) throws  InvalidAmountException
	{
		double temp=CL.getRemAmount();
		if(amount < CL.getMonAmount() && amount >= CL.getRemAmount())
		{
			throw new InvalidAmountException("Invalid Amount....\n Amount"+amount+"less than EMI"+CL.getMonAmount());
		}
		else
		{
			temp=temp-amount;
			CL.setRemAmount(temp);
			DLT.doTransaction(CL.getLoanAccNo(), (amount));
			calculateEmi(temp,CL.getRemPeriod());
		}
		
		
		return false;
	}
	public boolean payPartialInstallment(double amount,int period) throws  InvalidAmountException
	{
		double temp=CL.getRemAmount();
		if(amount < CL.getMonAmount() && amount >= CL.getRemAmount())
		{
			throw new InvalidAmountException("Invalid Amount....\n Amount"+amount+"less than EMI"+CL.getMonAmount());
		}
		else
		{
			temp=temp-amount;
			CL.setRemAmount(temp);
			DLT.doTransaction(CL.getLoanAccNo(), (amount));
			CL.setLoanPeriod(period);
			CL.setLoanPeriod(period);
			calculateEmi(temp, period);
		}
		
		
		return false;
	}

	
	public boolean payFullInstallment()  
	{
		double temp=CL.getRemAmount();
		double temp1=CL.getMonAmount();
		temp1=temp1+temp1;
		System.out.println("You have to pay penalty of"+temp1);
		System.out.println("Total amount to be paid is="+temp+"+"+temp1+"="+(temp+temp1));
		CL.setRemAmount(0);
		CL.setRemPeriod(0);
		CL.setMonAmount(0);
		DLT.doTransaction(CL.getLoanAccNo(), (temp+temp1));
		if(DL.deleteLoanDetails(CL))
		{
			System.out.println("Successfully paid full loan");
		}
		
		
		return true;
	}

	
	public double emiRate() 
	{
		
		return emi;
	}

	
	public void transactionHistory() 
	{
		
		DLT.showTransactions(CL.getLoanAccNo());
		
	}


	public boolean findLoan(String loanType,int loanAccount) 
	{
		this.CL=DL.getLoan(loanAccount, loanType);
		if(this.CL==null)
		{
			return false;
		}
		return true;
	}
	public LoanData getHL() {
		return CL;
	}


	

}
