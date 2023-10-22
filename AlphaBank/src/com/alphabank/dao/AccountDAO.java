package com.alphabank.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.alphabank.model.Account;
import com.alphabank.model.Customer;
import com.jdbc.connection.JDBCConnect;
import java.sql.Connection;

public class AccountDAO {
	public boolean addAccountDao(String openingdate, BigDecimal balance) {
		// Database Connection
		JDBCConnect connection = new JDBCConnect();
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			con = connection.getConnection();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date utilDate = sdf.parse(openingdate);
			java.sql.Date openingDate = new java.sql.Date(utilDate.getTime());

			// Create a Customer object
			Account newAccount = new Account();
			Customer customer = new Customer();
			newAccount.setOpeningDate(openingDate);
			newAccount.setCurrentBalance(balance);
			// customer.setId(); // Set the customer ID, assuming you have the customer ID
			// newAccount.setCustomer(customer);

			// Insert the customer into the database
			String insertQuery = "INSERT INTO accounts (customer_id,branch_id,openingDate, balance ) VALUES (?,?,?, ?)";
			preparedStatement = con.prepareStatement(insertQuery);
			preparedStatement.setInt(1, newAccount.getCustomer().getId()); // Get the customer ID
			preparedStatement.setString(2, newAccount.getBranch().getId());
			preparedStatement.setDate(3, newAccount.getOpeningDate());
			preparedStatement.setBigDecimal(4, newAccount.getCurrentBalance());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}
}
