package com.alphabank.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.alphabank.dao.CustomerDAO;
import com.alphabank.model.Account;
import com.alphabank.model.Customer;
import com.alphabank.service.BankImp;


public class CustomerController {
	Scanner sc = new Scanner(System.in);
	private int id;
	private String login;
	private String password;
	private String name;
	private String phone;
	private String email;

	public Customer createCustomer() throws Exception {
//		we have added customer in BankIMP.java and called it here
         BankImp bank = new BankImp(); // Create an instance of BankIMP
		Customer customer = new Customer();
		// Call the 'add' method on the BankIMP instance
		if (bank.add(customer)) {
	        System.out.println("Customer added successfully!");
	    } else {
	        System.out.println("Failed to add customer.");
	    }
	    return customer;
	}
	public boolean removeCustomer(){
		 BankImp bank = new BankImp(); // Create an instance of BankIMP
		Customer customer = new Customer();
		boolean success = bank.delete(customer);

		if (success) {
			System.out.println("Customer deleted successfully!");
		} else {
			System.out.println("Failed to delete customer.");
		}
		return true;
	}
	
	public Customer getCustomerOfAccount(Account account){
		
		return null;
		
	}
	
	public Customer findCustomerByID() {
	    Scanner scanner = new Scanner(System.in);
	    // Prompt the user to enter the customer's ID
	    System.out.println("Enter the customer's ID to find: ");
	    int customerId = scanner.nextInt();
	    CustomerDAO custdao = new CustomerDAO();

	    try {
	        // Call the DAO method to find the customer by ID
	        Customer foundCustomer = custdao.findByCustomerIdDao(customerId);

	        if (foundCustomer != null) {
	            // Customer found, print the customer details
	            System.out.println("Customer ID: " + foundCustomer.getId());
	            System.out.println("Customer Login: " + foundCustomer.getLogin());
	            System.out.println("Customer Name: " + foundCustomer.getName());
	            System.out.println("Customer Email: " + foundCustomer.getEmail());
	            // Print other customer details as needed

	            return foundCustomer;
	        } else {
	            System.out.println("Customer with ID " + customerId + " not found.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	    }

	    return null;
	}


	
	public List<Customer> findCustomersByName(String name) {
		return null;
		
	}	
}

