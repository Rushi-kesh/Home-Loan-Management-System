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
import com.Providers.LoanInstanceProvider;
import com.ServiceLayer.LoanServiceInterface;

public class HomeLoanManagement implements LoanServiceInterface
{
	private LoanData HL;
	public static double emi=0.01f;
	private DaoLoanInterface DL;
	private DaoTransactInterface DLT;
	
	public HomeLoanManagement() 
	{
		
		DL=DaoLoanInstanceProvider.getInstnace();
		DLT=DaoTInstance.getInstance();
	
	}

	public boolean registerLoanDetails(double loanAmount,int loanPeriod,UserDetails user) 
	{
		HL=new LoanData(loanAmount, loanPeriod);
		 HL.setLoanAccNo(user.getAccountNumber());
		 calculateEmi( loanAmount,loanPeriod);
		 HL.setRemAmount(HL.getMonAmount()*loanPeriod);
		 
		 HL.setRemPeriod(loanPeriod);
		 
		 HL.setLoanType("Home");
		 
		 if(DL.createLoan(HL))
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
		HL.setMonAmount(monthlyInst);
		
	}
	public void loanDetails() 
	{
		System.out.println("\n\n\t****Home Loan Details****");
		System.out.println("\tLoan account number : "+HL.getLoanAccNo());
		System.out.println("\tLoan purchased Amount : "+HL.getLoanAmount());
		System.out.println("\tLoan repay period : "+HL.getLoanPeriod());
		System.out.println("\n\n\t****Current status of Home Loan****");
		if(HL.getRemAmount()==0)
		{
			System.out.println("\tCongrats you already paid loan");
		}
		else
		{
			System.out.println("\t Remaining Loan amount : "+HL.getRemAmount());
			System.out.println("\tRemaining Loan repay period (with interest) : "+HL.getRemPeriod());
			System.out.println("\tMonthly installment amount : "+HL.getMonAmount()+"\n\n");
			
			
		}
		
		
		
	}

	
	public boolean payInstallment() 
	{
		double monthlyAmount=HL.getMonAmount();
		
		double temp=HL.getRemAmount();
		temp=temp-monthlyAmount;
		int rem=HL.getRemPeriod();
		rem--;
		HL.setRemPeriod(rem);
		HL.setRemAmount(temp);
		DLT.doTransaction(HL.getLoanAccNo(),(monthlyAmount));
		return true;
	}

	
	public boolean payPartialInstallment(double amount) throws  InvalidAmountException
	{
		double temp=HL.getRemAmount();
		if(amount < HL.getMonAmount() && amount >= HL.getRemAmount())
		{
			throw new InvalidAmountException("Invalid Amount....\n Amount"+amount+"less than EMI"+HL.getMonAmount());
		}
		else
		{
			temp=temp-amount;
			HL.setRemAmount(temp);
			DLT.doTransaction(HL.getLoanAccNo(), (amount));
			calculateEmi(temp,HL.getRemPeriod());
		}
		
		
		return false;
	}
	public boolean payPartialInstallment(double amount,int period) throws  InvalidAmountException
	{
		double temp=HL.getRemAmount();
		if(amount < HL.getMonAmount() && amount >= HL.getRemAmount())
		{
			throw new InvalidAmountException("Invalid Amount....\n Amount"+amount+"less than EMI"+HL.getMonAmount());
		}
		else
		{
			temp=temp-amount;
			HL.setRemAmount(temp);
			DLT.doTransaction(HL.getLoanAccNo(), (amount));
			HL.setLoanPeriod(period);
			HL.setLoanPeriod(period);
			calculateEmi(temp, period);
		}
		
		
		return false;
	}

	
	public boolean payFullInstallment()  
	{
		double temp=HL.getRemAmount();
		double temp1=HL.getMonAmount();
		temp1=temp1+temp1;
		System.out.println("You have to pay penalty of"+temp1);
		System.out.println("Total amount to be paid is="+temp+"+"+temp1+"="+(temp+temp1));
		HL.setRemAmount(0);
		HL.setRemPeriod(0);
		HL.setMonAmount(0);
		DLT.doTransaction(HL.getLoanAccNo(), (temp+temp1));
		if(DL.deleteLoanDetails(HL))
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
		
		DLT.showTransactions(HL.getLoanAccNo());
		
	}


	public boolean findLoan(String loanType,int loanAccount) 
	{
		this.HL=DL.getLoan(loanAccount, loanType);
		if(this.HL==null)
		{
			return false;
		}
		return true;
	}
	public LoanData getHL() {
		return HL;
	}


	

}
