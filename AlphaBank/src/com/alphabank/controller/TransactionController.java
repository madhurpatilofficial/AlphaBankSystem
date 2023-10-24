package com.alphabank.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.sql.Timestamp;

import com.alphabank.dao.TransactionDAO;
import com.alphabank.model.Account;
import com.alphabank.model.Transaction;

public class TransactionController {

	public List<Transaction> getTransactionsOfAccount(Account account) {

		return null;

	}

	public String getTransactionDetails(Transaction transaction) {

		return null;
	}

	public boolean withdraw(Account account, BigDecimal amount) {

		return false;

	}

//	
//	
//	
//	public boolean performDeposit() {
//	    Scanner scanner = new Scanner(System.in);
//
//	    System.out.print("Enter account ID for deposit: ");
//	    int accountId = scanner.nextInt();
//
//	    System.out.print("Enter the deposit amount: ");
//	    BigDecimal depositAmount = scanner.nextBigDecimal();
//	    
//
//	    // Assuming that you have a method to retrieve the account by ID
//	    Account account = getAccount(accountId);
//	    
//	    if (account == null) {
//	        System.out.println("Account not found.");
//	        return false;
//	    }
//
//	    TransactionDAO transactionDAO = new TransactionDAO();
//	    Transaction transaction = new Transaction();
//	    
//	    transaction.setAmount(depositAmount);
//	    transaction.setAccount(account);
//	    
//	    boolean success = deposit(transaction);
//	    
//	    if (success) {
//	        System.out.println("Deposit was successful!");
//	        // Update the account balance
//	        BigDecimal newBalance = account.getBalance().add(depositAmount);
//	        account.setBalance(newBalance);
//	    } else {
//	        System.out.println("Failed to deposit.");
//	    }
//	    
//	    return success;
//	}
//	
//	 public void depositMoney(int customer_id) {
//	        Scanner scanner = new Scanner(System.in);
//
//	        // Prompt the user to enter the deposit amount
//	        System.out.print("Enter ID to deposit: ");
//	        int customer_id = scanner.nextInt();
//
//	        // Prompt the user to enter the deposit amount
//	        System.out.print("Enter the deposit amount: ");
//	        BigDecimal depositAmount = scanner.nextBigDecimal();
//
//	        // Fetch the customer's account based on the customer_id
//	        Account customerAccount = getAccountByCustomerId(customer_id);
//
//	        // Check if the customerAccount is not null (i.e., the account exists)
//	        if (customerAccount != null) {
//	            // Perform the deposit operation
//	            boolean depositSuccess = deposit(customerAccount, depositAmount);
//
//	            if (depositSuccess) {
//	                System.out.println("Money deposited successfully. New balance: " + customerAccount.getBalance());
//	            } else {
//	                System.out.println("Failed to deposit.");
//	            }
//	        } else {
//	            System.out.println("Customer has no account.");
//	        }
//	    }

	public boolean transfer(Account from, Account to, BigDecimal amount) {

		return false;

	}

	public BigDecimal viewBalance() {
		Scanner scanner = new Scanner(System.in);

		// Prompt the user to enter the customer ID
		System.out.print("Enter Account ID: ");
		int customerId = scanner.nextInt();

		TransactionDAO transactionDAO = new TransactionDAO();
		BigDecimal balance = transactionDAO.getBalanceByCustomerId(customerId);

		// Print the balance
		System.out.println("Balance: " + balance);
		if (balance == null) {
			System.out.println("Invalid Account ID!!!!");
		}

		return balance; // Return the balance if needed
	}

}
