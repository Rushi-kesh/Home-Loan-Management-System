package com.DAOServiceLayer;


import com.Beans.LoanData;

public interface DaoLoanInterface
{
	boolean createLoan(LoanData L) ;
	LoanData getLoan(int loanAccount,String loanType);
	boolean updateLoanDetails(LoanData L);
	boolean deleteLoanDetails(LoanData L) ;
}
