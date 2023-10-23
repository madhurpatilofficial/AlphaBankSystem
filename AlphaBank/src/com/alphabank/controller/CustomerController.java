package com.alphabank.controller;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.alphabank.dao.CustomerDAO;
import com.alphabank.model.Account;
import com.alphabank.model.Customer;
import com.alphabank.model.Employee;
import com.alphabank.service.BankImp;
import com.jdbc.connection.JDBCConnect;

public class CustomerController {
	Scanner sc = new Scanner(System.in);

	public Customer createCustomer() throws Exception {
		BankImp bank = new BankImp();
		Customer customer = new Customer();
		if (bank.add(customer)) {
		} else {
			System.out.println("Failed to add customer.");
		}
		return customer;
	}

	public boolean removeCustomer() {
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

	public Customer getCustomerOfAccount(Account account) {

		return null;

	}

	public Customer findCustomerByID() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the customer's ID to find: ");
		int customerId = scanner.nextInt();
		CustomerDAO custdao = new CustomerDAO();

		try {
			Customer foundCustomer = custdao.findByCustomerIdDao(customerId);

			if (foundCustomer != null) {
				System.out.println("Customer ID: " + foundCustomer.getId());
				System.out.println("Customer Login: " + foundCustomer.getLogin());
				System.out.println("Customer Name: " + foundCustomer.getName());
				System.out.println("Customer Email: " + foundCustomer.getEmail());

				return foundCustomer;
			} else {
				System.out.println("Customer with ID " + customerId + " not found.");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

		return null;
	}

	public List<Customer> findCustomersByName() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the customer's name to find: ");
		String customerName = scanner.nextLine();
		CustomerDAO custdao = new CustomerDAO();

		try {
			List<Customer> foundCustomers = custdao.findCustomersByNameDao(customerName);

			if (foundCustomers != null && !foundCustomers.isEmpty()) {
				for (Customer customer : foundCustomers) {
					System.out.println("Customer ID: " + customer.getId());
					System.out.println("Customer Login: " + customer.getLogin());
					System.out.println("Customer Name: " + customer.getName());
					System.out.println("Customer Email: " + customer.getEmail());
					System.out.println("----------------");
				}
				return foundCustomers;
			} else {
				System.out.println("No customers found with the name: " + customerName);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

		return new ArrayList<Customer>();
	}

	public boolean updateCustomer() {
		BankImp bank = new BankImp();
		Customer customer = new Customer();

		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the Customer ID to update: ");
		int customerId = scanner.nextInt();
		customer.setId(customerId);

		System.out.print("Enter new name: ");
		String newName = scanner.next();
		customer.setName(newName);

		System.out.print("Enter new phone number: ");
		String newPhone = scanner.next();
		customer.setPhone(newPhone);

		System.out.print("Enter new Email: ");
		String newEmail = scanner.next();
		customer.setEmail(newEmail);

		// Call the update method from BankImp to update the employee's information
		if (bank.update(customer)) {
			System.out.println("Customer information updated successfully!");
			return true;
		} else {
			System.out.println("Failed to update customer information.");
			return false;
		}
	}
	
	public static void changePasswordCustomer() throws SQLException {
	    Scanner sc = new Scanner(System.in);

	    System.out.println("*********** Change Password for Customer by ID ****************");

	    System.out.print("Enter customer ID: ");
	    int customerId = sc.nextInt();
	    sc.nextLine(); // Consume the newline character left by nextInt()

	    System.out.print("Enter the new password: ");
	    String newPassword = sc.nextLine();

	    JDBCConnect connection = new JDBCConnect();
	    Connection con = null;

	    // Update the password in the database
	    try {
	        con = connection.getConnection();
	        String query = "UPDATE Customers SET password = ? WHERE id = ?";
	        PreparedStatement preparedStatement = con.prepareStatement(query);
	        preparedStatement.setString(1, newPassword);
	        preparedStatement.setInt(2, customerId);

	        int updatedRows = preparedStatement.executeUpdate();

	        if (updatedRows > 0) {
	            System.out.println("Password updated successfully.");
	        } else {
	            System.out.println("Failed to update password.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


}
