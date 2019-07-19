package com.DAOServiceLayer;

import java.sql.SQLException;

public interface DaoTransactInterface 
{
	boolean doTransaction(int loanAcc ,Double monthlyAmount);
	void showTransactions(int loanAcc) ;
}
