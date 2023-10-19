package com.alphabank.controller;

import java.math.BigDecimal;
import java.util.List;

import com.alphabank.model.Account;
import com.alphabank.model.Transaction;

public class TransactionController {

	public List<Transaction> getTransactionsOfAccount(Account account){
	
		return null;
		
	}
	
	public String getTransactionDetails(Transaction transaction) {
		
		return null;
	}	

	public boolean withdraw(Account account, BigDecimal amount)  {
		
		return false;
		
	}
	

	public boolean deposit(Account account, BigDecimal amount) {
		
		return false;
		
	}


	public boolean transfer(Account from, Account to, BigDecimal amount)  {
		
		return false;
		
	}
}

