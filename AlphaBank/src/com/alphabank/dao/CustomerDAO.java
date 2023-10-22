package com.alphabank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.alphabank.model.Customer;
import com.alphabank.model.Employee;
import com.jdbc.connection.JDBCConnect;

public class CustomerDAO {

	public boolean addCustomerDao(String login, String password, String name, String phone, String email,
			String dateStr) {
		// Database Connection
		JDBCConnect connection = new JDBCConnect();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = connection.getConnection();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date utilDate = sdf.parse(dateStr);
			java.sql.Date registrationDate = new java.sql.Date(utilDate.getTime());

			// Create a Customer object
			Customer newCustomer = new Customer();
			newCustomer.setLogin(login);
			newCustomer.setPassword(password);
			newCustomer.setName(name);
			newCustomer.setPhone(phone);
			newCustomer.setEmail(email);
			newCustomer.setRegistrationDate(registrationDate);

			// Insert the customer into the database
			String insertQuery = "INSERT INTO customers (login, password, name, phone, email, registrationDate) VALUES (?, ?, ?, ?, ?, ?)";
			preparedStatement = con.prepareStatement(insertQuery);
			preparedStatement.setString(1, newCustomer.getLogin());
			preparedStatement.setString(2, newCustomer.getPassword());
			preparedStatement.setString(3, newCustomer.getName());
			preparedStatement.setString(4, newCustomer.getPhone());
			preparedStatement.setString(5, newCustomer.getEmail());
			preparedStatement.setDate(6, newCustomer.getRegistrationDate());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteCustomerDao(int customerId) {

		try {
			JDBCConnect connection = new JDBCConnect();
			Connection con = connection.getConnection();
			PreparedStatement preparedStatement = null;

			String deleteQuery = "DELETE FROM Customers WHERE id = ?";
			preparedStatement = con.prepareStatement(deleteQuery);
			preparedStatement.setInt(1, customerId);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;

	}

	public Customer findByCustomerIdDao(int customerId) {
		try {
			JDBCConnect connection = new JDBCConnect();
			Connection con = connection.getConnection();
			PreparedStatement preparedStatement = null;

			String selectQuery = "SELECT * FROM Customers WHERE id = ?";
			preparedStatement = con.prepareStatement(selectQuery);
			preparedStatement.setInt(1, customerId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				Customer customer = new Customer();
				customer.setId(resultSet.getInt("id"));
				customer.setLogin(resultSet.getString("login"));
				customer.setPassword(resultSet.getString("password"));
				customer.setName(resultSet.getString("name"));
				customer.setPhone(resultSet.getString("phone"));
				customer.setEmail(resultSet.getString("email"));
				customer.setRegistrationDate(resultSet.getDate("registrationDate"));
				return customer; // Return the found customer
			} else {
				System.out.println("Customer not found");
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	public List<Customer> findCustomersByNameDao(String name) {
		try {
			JDBCConnect connection = new JDBCConnect();
			Connection con = connection.getConnection();
			PreparedStatement preparedStatement = null;

			String selectQuery = "SELECT * FROM Customers WHERE name = ?";
			preparedStatement = con.prepareStatement(selectQuery);
			preparedStatement.setString(1, name);

			ResultSet resultSet = preparedStatement.executeQuery();

			List<Customer> foundCustomers = new ArrayList<>();

			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setId(resultSet.getInt("id"));
				customer.setLogin(resultSet.getString("login"));
				customer.setPassword(resultSet.getString("password"));
				customer.setName(resultSet.getString("name"));
				customer.setPhone(resultSet.getString("phone"));
				customer.setEmail(resultSet.getString("email"));
				customer.setRegistrationDate(resultSet.getDate("registrationDate"));
				foundCustomers.add(customer);
			}

			return foundCustomers;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return new ArrayList<Customer>();
	}

	public boolean updateCustomerDao(Customer customer) {
		try {
			JDBCConnect connection = new JDBCConnect();
			Connection con = connection.getConnection();
			PreparedStatement preparedStatement = null;

			// Check if the name and phone number and email are not empty or null
			if (customer.getName() != null && !customer.getName().isEmpty() && customer.getPhone() != null
					&& !customer.getPhone().isEmpty() && customer.getEmail() != null
					&& !customer.getEmail().isEmpty()) {
				// Define the SQL query to update the name and phone number
				String updateQuery = "UPDATE Customers SET name = ?, phone = ?, email = ? WHERE id = ?";
				preparedStatement = con.prepareStatement(updateQuery);

				// Set the updated values using the Employee object
				preparedStatement.setString(1, customer.getName());
				preparedStatement.setString(2, customer.getPhone());
				preparedStatement.setString(3, customer.getEmail());
				preparedStatement.setInt(4, customer.getId());

				int rowsAffected = preparedStatement.executeUpdate();

				return rowsAffected > 0;
			} else {
				// Handle the case where the name or phone number is empty
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
