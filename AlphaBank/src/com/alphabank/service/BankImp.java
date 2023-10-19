package com.alphabank.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.alphabank.dao.CustomerDAO;
import com.alphabank.model.Account;
import com.alphabank.model.Branch;
import com.alphabank.model.Customer;
import com.alphabank.model.Employee;
import com.alphabank.model.Loan;
import com.alphabank.model.Transaction;
import com.jdbc.connection.JDBCConnect;

public class BankImp implements Bank{

	@Override
	public boolean add(Customer customer) throws Exception {
	    CustomerDAO custdao = new CustomerDAO();
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("*********** Enter Customer Details to Create Customer ****************");

	    // Gather customer information from the user
	    System.out.print("Login: ");
	    String login = scanner.next();

	    System.out.print("Password: ");
	    String password = scanner.next();

	    System.out.print("Name: ");
	    String name = scanner.next();

	    System.out.print("Phone: ");
	    String phone = scanner.next();

	    System.out.print("Email: ");
	    String email = scanner.next();

	    System.out.print("Registration Date (dd/MM/yyyy): ");
	    String dateStr = scanner.next();

	    boolean success = custdao.addCustomerDao(login, password, name, phone, email, dateStr);
		return success;
	}


		// TODO Auto-generated method stub
	

	@Override
	public boolean add(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(Loan loan) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(Branch branch) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(Transaction transaction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Customer> findAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findCustomer(String param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findEmployee(String param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findAccountsOfCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Loan> findLoansOfCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> findTransactionsOfAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> findTransactionsOfCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTransactionDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Loan loan) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Branch branch) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Customer customer) {
	    CustomerDAO custdao = new CustomerDAO();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter ID to delete: ");
		int customerId = scanner.nextInt();
		        boolean success = custdao.deleteCustomerDao(customerId);
		        return success;
		
	}
	@Override
	public boolean delete(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Account account) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean delete(Loan loan) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Branch branch) {
		// TODO Auto-generated method stub
		return false;
	}
	// Implementation of Bank methods
	}
