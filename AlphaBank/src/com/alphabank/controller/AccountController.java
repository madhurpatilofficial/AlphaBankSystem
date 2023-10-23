package com.alphabank.controller;

import java.util.List;

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
		return false;

	}

	public List<Account> getAccountsOfCustomer() {
		return null;
	}

	public String getAccountDetails() {
		return null;
	}
}
