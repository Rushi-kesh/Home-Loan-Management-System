package com.Beans;
public class LoanData 
{
	
	private int loanAccNo;
	private double loanAmount;
	private int loanPeriod;
	private double remAmount;
	private int remPeriod;
	private double monAmount;
	private String loanType;
	public LoanData(double d, int loanPeriod) 
	{
		
		this.loanAmount = d;
		this.loanPeriod = loanPeriod;
	}
	
	
	@Override
	public String toString() {
		return "LoanData [loanAccNo=" + loanAccNo + ", loanAmount=" + loanAmount + ", loanPeriod=" + loanPeriod
				+ ", remAmount=" + remAmount + ", remPeriod=" + remPeriod + ", monAmount=" + monAmount + ", loanType="
				+ loanType + "]";
	}


	public double getRemAmount() 
	{
		return remAmount;
	}
	public void setRemAmount(double remAmount) 
	{
		this.remAmount = remAmount;
	}
	public int getRemPeriod() 
	{
		return remPeriod;
	}
	public void setRemPeriod(int remPeriod) 
	{
		this.remPeriod = remPeriod;
	}
	public double getMonAmount() 
	{
		return monAmount;
	}
	public void setMonAmount(double monAmount) 
	{
		this.monAmount = monAmount;
	}
	public int getLoanAccNo() 
	{
		return loanAccNo;
	}
	
	public void setLoanAccNo(int loanAccNo) {
		this.loanAccNo = loanAccNo;
	}

	public double getLoanAmount() 
	{
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) 
	{
		this.loanAmount = loanAmount;
	}
	public int getLoanPeriod() 
	{
		return loanPeriod;
	}
	public void setLoanPeriod(int loanPeriod) 
	{
		this.loanPeriod = loanPeriod;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	
	

}
