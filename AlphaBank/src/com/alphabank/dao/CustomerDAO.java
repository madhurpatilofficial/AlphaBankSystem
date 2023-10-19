package com.alphabank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.alphabank.model.Customer;
import com.jdbc.connection.JDBCConnect;
public class CustomerDAO {
	
	public boolean addCustomerDao(String login, String password, String name, String phone, String email, String dateStr) {
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
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (con != null) {
	                con.close();
	                System.out.println("Closing Connection......");
	            } else {
	                System.out.println("Can't close the connection.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
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
             System.out.println("Customer not found");	        }
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	    }
		return null;
	}
	}
