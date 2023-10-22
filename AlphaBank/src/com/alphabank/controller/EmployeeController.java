package com.alphabank.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.alphabank.dao.CustomerDAO;
import com.alphabank.dao.EmployeeDAO;
import com.alphabank.model.Customer;
import com.alphabank.model.Employee;
import com.alphabank.service.BankImp;

public class EmployeeController {

	public Employee createEmployee() {
		Scanner sc = new Scanner(System.in);
		BankImp bank = new BankImp();
		Employee employee = new Employee();

		if (bank.add(employee)) {
			System.out.println("Employee added successfully!");
		} else {
			System.out.println("");
		}

		return employee;
	}

	public Employee findEmployeeByID() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the employee's ID to find: ");
		int employeeId = scanner.nextInt();
		EmployeeDAO empdao = new EmployeeDAO();

		try {
			Employee foundEmployee = empdao.findByEmployeeIdDao(employeeId);

			if (foundEmployee != null) {
				System.out.println("Employee ID: " + foundEmployee.getId());
				System.out.println("Employee Name: " + foundEmployee.getName());
				System.out.println("Employee Position: " + foundEmployee.getPosition());
				// System.out.println("Employee Branch: " + foundEmployee.getBranch());

				return foundEmployee;
			} else {
				System.out.println("Customer with ID " + employeeId + " not found.");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

		return null;
	}

	// added this
	public boolean updateEmployee() {
		BankImp bank = new BankImp();
		Employee employee = new Employee();

		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the Employee ID to update: ");
		int employeeId = scanner.nextInt();
		employee.setId(employeeId);

		System.out.print("Enter new name: ");
		String newName = scanner.next();
		employee.setName(newName);

		System.out.print("Enter new phone number: ");
		String newPhone = scanner.next();
		employee.setPhone(newPhone);

		// Call the update method from BankImp to update the employee's information
		if (bank.update(employee)) {
			System.out.println("Employee information updated successfully!");
			return true;
		} else {
			System.out.println("Failed to update employee information.");
			return false;
		}
	}

	public boolean removeEmployee() {
		BankImp bank = new BankImp(); // Create an instance of BankIMP
		Employee employee = new Employee();
		boolean success = bank.delete(employee);

		if (success) {
			System.out.println("Employee deleted successfully!");
		} else {
			System.out.println("Failed to delete Employee.");
		}
		return true;
	}

	public boolean setManager() {
		BankImp bank = new BankImp();
		Employee employee = new Employee();
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the Employee ID to update: ");
		int employeeId = scanner.nextInt();
		employee.setId(employeeId);

		System.out.print("Enter the Manager ID: ");
		int managerId = scanner.nextInt();

		boolean success = bank.setManagerImp(employee, managerId);

		if (success) {
			System.out.println("Manager set successfully!");
		} else {
			System.out.println("Failed to set Manager");
		}
		return true;
	}

}
