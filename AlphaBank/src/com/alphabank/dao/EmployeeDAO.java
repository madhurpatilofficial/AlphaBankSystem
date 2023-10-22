package com.alphabank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.alphabank.model.Employee;
import com.jdbc.connection.JDBCConnect;

public class EmployeeDAO {

	public boolean addEmployeeDao(String name, String phone, String position, String login, String password,
			String branchId) {
		JDBCConnect connection = new JDBCConnect();
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			con = connection.getConnection();

			// Create a new Employee object
			Employee employee = new Employee();
			employee.setName(name);
			employee.setPhone(phone);
			employee.setPosition(position);
			employee.setLogin(login);
			employee.setPassword(password);
			employee.setBranchId(branchId);

			// Insert the employee into the database
			String insertQuery = "INSERT INTO employees (name, phone, position, login, password, branch_id) VALUES (?, ?, ?, ?, ?, ?)";
			preparedStatement = con.prepareStatement(insertQuery);
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getPhone());
			preparedStatement.setString(3, employee.getPosition());
			preparedStatement.setString(4, employee.getLogin());
			preparedStatement.setString(5, employee.getPassword());
			preparedStatement.setString(6, employee.getBranchId());

			preparedStatement.executeUpdate();

			return true; // Success
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Failed to add Employee. Provided Branch ID does not exist.");
			return false; // Failure
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Failure
		}
	}

	public Employee findByEmployeeIdDao(int employeeId) {
		try {
			JDBCConnect connection = new JDBCConnect();
			Connection con = connection.getConnection();
			PreparedStatement preparedStatement = null;

			String selectQuery = "SELECT * FROM Employees WHERE id = ?";
			preparedStatement = con.prepareStatement(selectQuery);
			preparedStatement.setInt(1, employeeId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				Employee employee = new Employee();
				// Branch newbranch=new Branch();
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setPhone(resultSet.getString("phone"));
				employee.setPosition(resultSet.getString("position"));

				return employee; // Return the found customer
			} else {
				System.out.println("Employee not found");
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	public boolean deleteEmployeeDao(int id) {

		try {
			JDBCConnect connection = new JDBCConnect();
			Connection con = connection.getConnection();
			PreparedStatement preparedStatement = null;

			String deleteQuery = "DELETE FROM Employees WHERE id = ?";
			preparedStatement = con.prepareStatement(deleteQuery);
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;

	}

	// added recently
	public boolean updateEmployeeDao(Employee employee) {
		try {
			JDBCConnect connection = new JDBCConnect();
			Connection con = connection.getConnection();
			PreparedStatement preparedStatement = null;

			// Check if the name and phone number are not empty or null
			if (employee.getName() != null && !employee.getName().isEmpty() && employee.getPhone() != null
					&& !employee.getPhone().isEmpty()) {
				// Define the SQL query to update the name and phone number
				String updateQuery = "UPDATE Employees SET name = ?, phone = ? WHERE id = ?";
				preparedStatement = con.prepareStatement(updateQuery);

				// Set the updated values using the Employee object
				preparedStatement.setString(1, employee.getName());
				preparedStatement.setString(2, employee.getPhone());
				preparedStatement.setInt(3, employee.getId());

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

	public boolean setManagerDao(int employeeId, int managerId) {

		try {
			JDBCConnect connection = new JDBCConnect();
			Connection con = connection.getConnection();
			PreparedStatement preparedStatement = null;

			String updateQuery = "UPDATE Employees SET manager_id = ? WHERE id = ?";
			preparedStatement = con.prepareStatement(updateQuery);

			// Set the manager_id and employee_id
			preparedStatement.setInt(1, managerId);
			preparedStatement.setInt(2, employeeId);

			int rowsAffected = preparedStatement.executeUpdate();

			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return true;

		}
	}
}