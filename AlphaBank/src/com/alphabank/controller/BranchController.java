package com.alphabank.controller;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Scanner;

import com.alphabank.model.Branch;
import com.alphabank.model.Customer;
import com.alphabank.model.Employee;
import com.alphabank.service.BankImp;

public class BranchController {
	public Branch createBranch() {
		BankImp bank = new BankImp();
		Branch branch = new Branch();
		if (bank.add(branch)) {
			System.out.println("Branch added successfully!");
		} else {
			System.out.println("Failed to add branch.");
		}
		return branch;

	}

	public boolean updateBranch() {
		BankImp bank = new BankImp();
		Branch branch = new Branch();

		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the Branch ID to update: ");
		String branchId = scanner.next();
		branch.setId(branchId);

		System.out.print("Enter new Address: ");
		String newAddress = scanner.next();
		branch.setAddress(newAddress);

		System.out.print("Enter new phone number: ");
		String newPhone = scanner.next();
		branch.setPhone(newPhone);

		// Call the update method from BankImp to update the employee's information
		if (bank.update(branch)) {
			System.out.println("Branch information updated successfully!");
			return true;
		} else {
			System.out.println("Failed to update branch information.");
			return false;
		}

	}

	public boolean removeBranch() {

		BankImp bank = new BankImp(); // Create an instance of BankIMP
		Branch branch = new Branch();
		boolean success = bank.delete(branch);

		if (success) {
			System.out.println("Branch deleted successfully!");
		} else {
			System.out.println("Failed to delete branch.");
		}
		return true;
	}

	public String getBranchDetails() {

		return null;

	}

	public List<Employee> getBranchEmployees() {

		return null;

	}

}
