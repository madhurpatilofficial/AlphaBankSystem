package com.alphabank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.alphabank.model.Branch;
import com.alphabank.model.Customer;
import com.alphabank.service.BankImp;
import com.jdbc.connection.JDBCConnect;

public class BranchDAO {
	public boolean addBranchDao(String id, String address, String phone) {
		// Database Connection
		JDBCConnect connection = new JDBCConnect();
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			con = connection.getConnection();
			Branch newbranch = new Branch();
			newbranch.setId(id);
			newbranch.setAddress(address);
			newbranch.setPhone(phone);

			String insertQuery = "INSERT INTO Branches (id,address, phone) VALUES (?, ?, ?)";
			preparedStatement = con.prepareStatement(insertQuery);
			preparedStatement.setString(1, newbranch.getId());
			preparedStatement.setString(2, newbranch.getAddress());
			preparedStatement.setString(3, newbranch.getPhone());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean updateBranchDao(Branch branch) {
		JDBCConnect connection = new JDBCConnect();
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			con = connection.getConnection();

			String updateQuery = "UPDATE Branches SET address = ?, phone = ? WHERE id = ?";
			preparedStatement = con.prepareStatement(updateQuery);
			preparedStatement.setString(1, branch.getAddress());
			preparedStatement.setString(2, branch.getPhone());
			preparedStatement.setString(3, branch.getId());

			int rowsAffected = preparedStatement.executeUpdate();

			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteBranchDao(String branch_id) {
		try {
			JDBCConnect connection = new JDBCConnect();
			Connection con = connection.getConnection();
			PreparedStatement preparedStatement = null;

			String deleteQuery = "DELETE FROM Branches WHERE id = ?";
			preparedStatement = con.prepareStatement(deleteQuery);
			preparedStatement.setString(1, branch_id);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;

	}

}
