package com.alphabank.controller;

import java.util.List;
import java.util.Scanner;

import com.alphabank.dao.AccountDAO;
import com.alphabank.model.Account;
import com.alphabank.model.Customer;
import com.alphabank.service.BankImp;

public class AccountController {

	public Account createAccount() {
		BankImp bank = new BankImp();
		Customer customer = new Customer();
		Account account = new Account();
		if (bank.add(account, customer.getId())) {
		} else {
			System.out.println("Failed to add account.");
		}
		return account;
	}

	public boolean updateAccount() {
		return false;

	}

	public boolean removeAccount() {
		AccountDAO accountDao = new AccountDAO();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Account ID: ");
		int accountId = sc.nextInt();
		if (accountDao.removeAccountDao(accountId)) {
			System.out.println("Account removed successfully.");
			return true;
		} else {
			System.out.println("Failed to remove the account.");
		}
		return false;
	}

	public List<Account> getAccountsOfCustomer() {
		return null;
	}

	public String getAccountDetails() {
		return null;
	}
}
