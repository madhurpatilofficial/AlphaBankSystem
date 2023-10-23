package com.alphabank.service;

import java.math.BigDecimal;
import java.util.Scanner;

import com.alphabank.controller.AccountController;
import com.alphabank.controller.BranchController;
import com.alphabank.controller.CustomerController;
import com.alphabank.controller.EmployeeController;
import com.alphabank.controller.TransactionController;

public class BankMain extends LoginImpl {

	public static void main(String[] args) throws Exception {
		CustomerController b1 = new CustomerController();
		EmployeeController e1 = new EmployeeController();
		BranchController c1 = new BranchController();
		AccountController a1 = new AccountController();
		TransactionController t1 = new TransactionController();
		BankImp imp = new BankImp();

		Scanner scanner = new Scanner(System.in);

		while (true) {

			System.out.println("\nWelcome to AlphaBank!\n");
			System.out.println("*******************************");
			System.out.println("Main Menu");
			System.out.println("*******************************");
			System.out.println("1. Customer");
			System.out.println("2. Teller");
			System.out.println("3. Admin");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:

				System.out.println("*******************************");
				System.out.println("Customer Menu");
				System.out.println("*******************************");
				System.out.println("1. Update Customer");
				System.out.println("2. Change Password");
				System.out.println("3. View Balance");
				System.out.println("4. Deposit Money");
				System.out.print("Enter your choice: ");
				int customerChoice = scanner.nextInt();

				switch (customerChoice) {
				case 1:
					b1.updateCustomer();
					break;
				case 2:
					b1.changePasswordCustomer();
					break;

				case 3:
					t1.viewBalance();
					break;

				case 4:
					imp.addDeposit();
					break;

				default:
					System.out.println("Invalid choice.");
				}
				break;

			case 2:

				System.out.println("*******************************");
				System.out.println("Teller Menu");
				System.out.println("*******************************");
				System.out.println("1. Create Customer");
				System.out.println("2. Delete Customer");
				System.out.println("3. Find Customer by ID");
				System.out.println("4. Find Customers by Name");
				System.out.print("Enter your choice: ");
				customerChoice = scanner.nextInt();

				switch (customerChoice) {
				case 1:
					b1.createCustomer();
					break;
				case 2:
					b1.removeCustomer();
					break;
				case 3:
					b1.findCustomerByID();
					break;
				case 4:
					b1.findCustomersByName();
					break;
				default:
					System.out.println("Invalid choice.");
				}
				break;

			case 3:

				System.out.println("*******************************");
				System.out.println("Admin Menu");
				System.out.println("*******************************");
				System.out.println("1. Create Employee");
				System.out.println("2. Update Employee");
				System.out.println("3. Remove Employee");
				System.out.println("4. Find Customer By Id");
				System.out.println("5. Find Customer By Name");
				System.out.println("6. Add Branch");
				System.out.println("7. Update Branch");
				System.out.println("8. Delete Branch");
				System.out.println("9. Find Employee by ID");
				System.out.println("10. Set Manager");
				System.out.print("Enter your choice: ");
				customerChoice = scanner.nextInt();

				switch (customerChoice) {
				case 1:
					e1.createEmployee();
					break;
				case 2:
					e1.updateEmployee();
					break;
				case 3:
					e1.removeEmployee();
					break;
				case 4:
					b1.findCustomerByID();
					break;
				case 5:
					b1.findCustomersByName();
					break;
				case 6:
					c1.createBranch();
					break;
				case 7:
					c1.updateBranch();
					break;
				case 8:
					c1.removeBranch();
					break;
				case 9:
					e1.findEmployeeByID();
					break;
				case 10:
					e1.setManager();
					break;
				default:
					System.out.println("Invalid choice.");
				}
				break;

			case 4:

				System.out.println("*******\nThank you for using AlphaBank. Goodbye!*******");
				System.exit(0);
			default:
				System.out.println("Invalid choice.");
			}
		}
	}

	private static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
