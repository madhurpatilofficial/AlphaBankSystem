package com.alphabank.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Account {

	private int id;

	private Customer customer;

	private Branch branch;

	private Date openingDate;

	private BigDecimal currentBalance;

	private BigDecimal interestRate;

	private int customerId;

	private BigDecimal newBalance;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public void setCustomerId(int customerId) {
	    this.customerId = customerId;
	}

	public int getCustomerId() {
	    return this.customerId;
	}

	public BigDecimal getBalance() {
		// TODO Auto-generated method stub
		return this.currentBalance;
	}

	public void setBalance(BigDecimal newBalance) {
		this.newBalance = newBalance;
		
	}
}