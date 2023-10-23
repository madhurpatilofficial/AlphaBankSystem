package com.alphabank.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.alphabank.model.Transaction;
import com.jdbc.connection.JDBCConnect;

public class TransactionDAO {

	public BigDecimal getBalanceByCustomerId(int customerId) {
		JDBCConnect connection = new JDBCConnect();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		BigDecimal totalBalance = BigDecimal.ZERO;

		try {
			con = connection.getConnection();
			String query = "SELECT SUM(balance) AS total_balance FROM Accounts WHERE customer_id = ?";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				totalBalance = resultSet.getBigDecimal("total_balance");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return totalBalance;
	}

}
