package com.alphabank.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.alphabank.model.Account;
import com.alphabank.model.Customer;
import com.alphabank.service.BankImp;
import com.jdbc.connection.JDBCConnect;
import java.sql.Connection;

public class AccountDAO {
	
	
	public boolean addAccountDao(int accountId, String openingDate, BigDecimal balance, int customerId) {
        // Database Connection
        JDBCConnect connection = new JDBCConnect();
        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = connection.getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilDate = sdf.parse(openingDate);
            java.sql.Date sqlOpeningDate = new java.sql.Date(utilDate.getTime());

            // Set the existing customer's ID
            String insertQuery = "INSERT INTO accounts (customer_id, id, openingDate, balance) VALUES (?, ?, ?, ?)";

            // Initialize the PreparedStatement
            preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setInt(1, customerId); // Set the customer ID
            preparedStatement.setInt(2, accountId);
            preparedStatement.setDate(3, sqlOpeningDate);
            preparedStatement.setBigDecimal(4, balance);

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
	
	

//	public boolean addAccountDao(int accountId, String openingDate, BigDecimal balance, int customerId) {
//	    // Database Connection
//	    JDBCConnect connection = new JDBCConnect();
//	    Connection con = null;
//	    PreparedStatement preparedStatement = null;
//
//	    try {
//	        con = connection.getConnection();
//	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//	        java.util.Date utilDate = sdf.parse(openingDate);
//	        java.sql.Date sqlOpeningDate = new java.sql.Date(utilDate.getTime());
//
//	        // Set the existing customer's ID
//	        String insertQuery = "INSERT INTO accounts (customer_id, id, openingDate, balance) VALUES (?, ?, ?, ?)";
//
//	        // Initialize the PreparedStatement
//	        preparedStatement = con.prepareStatement(insertQuery);
//	        preparedStatement.setInt(1, customerId); // Set the customer ID
//	        preparedStatement.setInt(2, accountId);
//	        preparedStatement.setDate(3, sqlOpeningDate);
//	        preparedStatement.setBigDecimal(4, balance);
//
//	        // Execute the insert statement
//	        int rowsAffected = preparedStatement.executeUpdate();
//	        return rowsAffected > 0;
//	    } catch (SQLException | ParseException e) {
//	        e.printStackTrace();
//	    } finally {
//	        try {
//	            if (preparedStatement != null) {
//	                preparedStatement.close();
//	            }
//	            if (con != null) {
//	                con.close();
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	    return false;
//	}
}

//	public boolean addAccountDao(int accountId, String openingdate, BigDecimal balance) {
//		// Database Connection
//		JDBCConnect connection = new JDBCConnect();
//		Connection con = null;
//		PreparedStatement preparedStatement = null;
//
//		try {
//			con = connection.getConnection();
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			java.util.Date utilDate = sdf.parse(openingdate);
//			java.sql.Date openingDate = new java.sql.Date(utilDate.getTime());
//
//			Account newAccount = new Account();
//			Customer newCustomer = new Customer();
//			
//			newAccount.setId(accountId);
//			newAccount.setOpeningDate(openingDate);
//			newAccount.setCurrentBalance(balance);
//
//			// Set the existing customer's ID
//			// newAccount.setCustomerId(customerId);
//
//			preparedStatement.executeUpdate();
//
//			String insertQuery = "INSERT INTO accounts (customer_id, id, openingDate, balance) VALUES (?, ?, ?, ?)";
//			
//            preparedStatement = con.prepareStatement(insertQuery);
//            preparedStatement.setInt(1, newAccount.getCustomerId());
//            preparedStatement.setInt(2, newAccount.getId());
//            preparedStatement.setDate(3, newAccount.getOpeningDate());
//            preparedStatement.setBigDecimal(4, newAccount.getCurrentBalance());
//            
//			preparedStatement.executeUpdate();
//
//			// Retrieve the customer Id from the customer table
////			String getcustomerid = "SELECT id FROM customers where login = ?";
////			preparedStatement = con.prepareStatement(getcustomerid);
////			preparedStatement.setString(1, newCustomer.getLogin());
//
//
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return true;
//	}

//	public boolean addAccountDao(int accountId, String openingdate, BigDecimal balance, String customerLogin) {
//	    // Database Connection
//	    JDBCConnect connection = new JDBCConnect();
//	    Connection con = null;
//	    PreparedStatement preparedStatement = null;
//
//	    try {
//	        con = connection.getConnection();
//	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//	        java.util.Date utilDate = sdf.parse(openingdate);
//	        java.sql.Date openingDate = new java.sql.Date(utilDate.getTime());
//
//	        Account newAccount = new Account();
//
//	        // Retrieve the customer's ID based on their login (username)
//	        int customerID = getCustomerIDByLogin(con, customerLogin);
//
//	        // Check if a valid customer ID was retrieved
//	        if (customerID != -1) {
//	            newAccount.setId(accountId);
//	            newAccount.setOpeningDate(openingDate);
//	            newAccount.setCurrentBalance(balance);
//	            newAccount.setCustomerId(customerID); // Set the customer's ID
//
//	            // Insert the account into the database
//	            String insertQuery = "INSERT INTO accounts (customer_id, id, openingDate, balance) VALUES (?, ?, ?, ?)";
//	            preparedStatement = con.prepareStatement(insertQuery);
//	            preparedStatement.setInt(1, newAccount.getCustomerId());
//	            preparedStatement.setInt(2, newAccount.getId());
//	            preparedStatement.setDate(3, newAccount.getOpeningDate());
//	            preparedStatement.setBigDecimal(4, newAccount.getCurrentBalance());
//
//	            int rowAffected = preparedStatement.executeUpdate();
//
//	            if (rowAffected > 0) {
//	                // Account inserted successfully
//	                return true;
//	            } else {
//	                // Handle insertion failure
//	                return false;
//	            }
//	        } else {
//	            // Handle the case where a valid customer ID was not retrieved
//	            return false;
//	        }
//
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    } catch (ParseException e) {
//	        e.printStackTrace();
//	    }
//	    return false;
//	}
//
//	private int getCustomerIDByLogin(Connection con, String customerLogin) throws SQLException {
//	    // Query to retrieve the customer's ID based on their login (username)
//	    String query = "SELECT id FROM customers WHERE login = ?";
//	    PreparedStatement preparedStatement = con.prepareStatement(query);
//	    preparedStatement.setString(1, customerLogin);
//	    ResultSet resultSet = preparedStatement.executeQuery();
//
//	    int customerID = -1; // Default value if not found
//	    if (resultSet.next()) {
//	        customerID = resultSet.getInt("id");
//	    }
//
//	    return customerID;
//	}
