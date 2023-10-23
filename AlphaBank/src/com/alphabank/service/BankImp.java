package com.alphabank.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Scanner;

import com.alphabank.dao.AccountDAO;
import com.alphabank.dao.BranchDAO;
import com.alphabank.dao.CustomerDAO;
import com.alphabank.dao.EmployeeDAO;
import com.alphabank.model.Account;
import com.alphabank.model.Branch;
import com.alphabank.model.Customer;
import com.alphabank.model.Employee;
import com.alphabank.model.Loan;
import com.alphabank.model.Transaction;
import com.jdbc.connection.JDBCConnect;

public class BankImp implements Bank {

	public boolean add(Customer customer) throws Exception {
		CustomerDAO custdao = new CustomerDAO();
		Account account = new Account();
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********** Enter Customer Details to Create Customer ****************");

		// Gather customer information from the user
		System.out.print("Enter Username: ");
		String login = scanner.next();

		System.out.print("Enter Password: ");
		String password = scanner.next();

		System.out.print("Enter Name: ");
		String name = scanner.next();

		System.out.print("Enter Phone: ");
		String phone = scanner.next();

		System.out.print("Enter Email: ");
		String email = scanner.next();

		System.out.print("Enter Registration Date (dd/MM/yyyy): ");
		String dateStr = scanner.next();

		try {
			// Add the customer to the database
			customer = custdao.addCustomerDao(login, password, name, phone, email, dateStr);

			// Check if the customer was added successfully
			if (customer != null) {
				// Now, add an account for the customer
				addAccount(account, customer.getId());
				System.out.println("Customer and Account added successfully!");
				return true;
			} else {
				System.out.println("Failed to add customer.");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addAccount(Account account, int customerId) throws SQLException {
		AccountDAO accountdao = new AccountDAO();
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n*********** Enter Account Details ****************");

		// Gather account information from the user
		System.out.print("Account ID: ");
		int accountId = scanner.nextInt();
		account.setId(accountId);

		// Set the customer ID for the account
		account.setCustomerId(customerId);

		System.out.print("Enter Opening Date (dd/MM/yyyy): ");
		String openingDate = scanner.next();

		System.out.print("Enter Balance: ");
		BigDecimal balance = scanner.nextBigDecimal();

		// Add the account to the database
		if (accountdao.addAccountDao(accountId, openingDate, balance, customerId)) {
			return true;
		} else {
			System.out.println("Failed to add account.");
			return false;
		}
	}

//	@Override
//	public boolean add(Customer customer) throws Exception {
//		CustomerDAO custdao = new CustomerDAO();
//		Account account = new Account();
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("*********** Enter Customer Details to Create Customer ****************");
//
//		// Gather customer information from the user
//		System.out.print("Enter Username: ");
//		String login = scanner.next();
//
//		System.out.print("Enter Password: ");
//		String password = scanner.next();
//
//		System.out.print("Enter Name: ");
//		String name = scanner.next();
//
//		System.out.print("Enter Phone: ");
//		String phone = scanner.next();
//
//		System.out.print("Enter Email: ");
//		String email = scanner.next();
//
//		System.out.print("Enter Registration Date (dd/MM/yyyy): ");
//		String dateStr = scanner.next();
//		
//        boolean success = custdao.addCustomerDao(login, password, name, phone, email, dateStr);
//        if (success) {
//            add(account, customer.getId());
//        }
//        return success;
//
//	}

	@Override
	public boolean add(Account account, int customerId) {
		AccountDAO accountdao = new AccountDAO();
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n*********** Enter Account Details ****************");

		System.out.print("Account ID: ");
		int accountId = scanner.nextInt();
		account.setId(accountId);

		account.setCustomerId(customerId);

		System.out.print("Enter Opening Date (dd/MM/yyyy): ");
		String openingDate = scanner.next();

		System.out.print("Enter Balance: ");
		BigDecimal balance = scanner.nextBigDecimal();

		// Pass the customer ID to the AccountDAO method
		boolean success = accountdao.addAccountDao(accountId, openingDate, balance, customerId);
		return success;
	}

	// TODO Auto-generated method stub

	@Override
	public boolean add(Employee employee) {
		EmployeeDAO empdao = new EmployeeDAO();
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n************** Enter Employee Details to Create Employee ****************");

		// Gather customer information from the user
		System.out.print("Enter Name: ");
		String name = scanner.next();

		System.out.print("Enter Phone: ");
		String phone = scanner.next();

		System.out.print("Enter Username: ");
		String login = scanner.next();

		System.out.print("Enter Password: ");
		String password = scanner.next();

		System.out.println("Choose Position \n1. Admin \n2. Teller");
		int positionChoice = scanner.nextInt();
		String position = null; // Initialize position as null

		if (positionChoice == 1) {
			position = "Admin";
		} else if (positionChoice == 2) {
			position = "Teller";
		} else {
			System.out.println("Invalid position choice. Employee details will not be added.");
			return false; // Exit the method, no employee details will be added
		}

		System.out.println(
				"Select Branch from Given Options: \nB001 (Hadapsar) \nB002 (Kharadi) \nB003 (Hinjewadi)  \nB009 (Kothrud)");
		String bchoice = scanner.next();
		String branchId = null; // Initialize branchId as null

		if ("B001".equals(bchoice) || "B002".equals(bchoice) || "B003".equals(bchoice) || "B009".equals(bchoice)) {
			branchId = bchoice;
		} else {
			System.out.println("Invalid branch choice. Employee details will not be added.");
			return false; // Exit the method, no employee details will be added
		}

		boolean success = empdao.addEmployeeDao(name, phone, position, login, password, branchId);
		return success;
	}

	@Override
	public boolean update(Employee employee) {
		EmployeeDAO employeeDAO = new EmployeeDAO();

		// Update the employee's information using the provided Employee object
		boolean success = employeeDAO.updateEmployeeDao(employee);

		return success;
	}

//	public boolean add(Account account,int customerId) {
//		AccountDAO accountdao = new AccountDAO();
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("\n*********** Enter Account Details ****************");
//
//		// Gather account information from the user
////
//     System.out.print("Account ID: ");
//	    int accountId = scanner.nextInt();
//	    account.setId(accountId);
//
//		System.out.print("Enter Opening Date (dd/MM/yyyy): ");
//		String openingDate = scanner.next();
//
//		System.out.print("Enter Balance: ");
//		BigDecimal balance = scanner.nextBigDecimal();
//
//        boolean success = accountdao.addAccountDao(account.getId(), openingDate, balance, customerId);
//        return success;
//	}

	@Override
	public boolean add(Loan loan) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(Branch branch) {
		BranchDAO branchdao = new BranchDAO();
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n*********** Enter Branch Details to Create Branch ****************");

		// Gather customer information from the user

		System.out.print("Enter Branch_id: ");
		String id = scanner.next();

		System.out.print("Enter Address: ");
		String address = scanner.next();

		System.out.print("Enter Phone: ");
		String phone = scanner.next();

		boolean success = branchdao.addBranchDao(id, address, phone);
		return success;
	}

	@Override
	public boolean add(Transaction transaction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	// implemented in CustomerController
	public List<Customer> findAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	// implemented in CustomerController
	public Customer findCustomer(String param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	// implemented in CustomerController
	public List<Employee> findAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	// Implemented in Employee Controller
	public Employee findEmployee(String param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findAccountsOfCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Loan> findLoansOfCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> findTransactionsOfAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> findTransactionsOfCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTransactionDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Customer customer) {
		CustomerDAO customerDAO = new CustomerDAO();

		// Update the employee's information using the provided Employee object
		boolean success = customerDAO.updateCustomerDao(customer);

		return success;
	}

	public boolean setManagerImp(Employee employee, int managerId) {
		EmployeeDAO employeeDAO = new EmployeeDAO();

		// Update the employee's manager using the provided Employee object and
		// managerId
		boolean success = employeeDAO.setManagerDao(employee.getId(), managerId);
		return success;
	}

	@Override
	public boolean update(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Loan loan) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Branch branch) {
		BranchDAO branchDAO = new BranchDAO();

		// Update the employee's information using the provided Employee object
		boolean success = branchDAO.updateBranchDao(branch);

		return success;
	}

	@Override
	public boolean delete(Customer customer) {
		CustomerDAO custdao = new CustomerDAO();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter ID to delete: ");
		int customerId = scanner.nextInt();
		boolean success = custdao.deleteCustomerDao(customerId);
		return success;

	}

	@Override
	public boolean delete(Employee employee) {
		EmployeeDAO empdao = new EmployeeDAO();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter ID to delete: ");
		int employeeId = scanner.nextInt();
		boolean success = empdao.deleteEmployeeDao(employeeId);
		return success;

	}

	@Override
	public boolean delete(Account account) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public boolean delete(Loan loan) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Branch branch) {
		BranchDAO branchdao = new BranchDAO();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter ID to delete: ");
		String branchId = scanner.next();
		boolean success = branchdao.deleteBranchDao(branchId);
		return success;
	}
	// Implementation of Bank methods

//    public BigDecimal viewBalance(int customerId) {
//        TransactionDAO transactionDAO = new TransactionDAO();
//        return transactionDAO.getBalanceByCustomerId(customerId);
//    }

//	public boolean addDeposit() {
//		
//	}

	public boolean addDeposit() throws SQLException {
		// Take user-defined inputs
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Customer ID: ");
		int customerId = scanner.nextInt();
		System.out.println("Enter the amount to deposit: ");
		BigDecimal amount = scanner.nextBigDecimal();

		String transactionType = "deposit";

		// Database Connection
		JDBCConnect connection = new JDBCConnect();
		Connection con = null;

		try {
			con = connection.getConnection();
			// Retrieve the current balance for the customer's account
			String selectQuery = "SELECT balance FROM accounts WHERE id = ?";
			PreparedStatement selectStatement = con.prepareStatement(selectQuery);
			selectStatement.setInt(1, customerId);

			ResultSet resultSet = selectStatement.executeQuery();

			if (resultSet.next()) {
				BigDecimal currentBalance = resultSet.getBigDecimal("balance");

				// Update the current balance
				currentBalance = currentBalance.add(amount);

				// Update the current_balance in the account table
				String updateQuery = "UPDATE accounts SET balance = ? WHERE id = ?";
				PreparedStatement updateStatement = con.prepareStatement(updateQuery);
				updateStatement.setBigDecimal(1, currentBalance);
				updateStatement.setInt(2, customerId);

				int rowsAffected = updateStatement.executeUpdate();

				if (rowsAffected > 0) {
					// Insert a new transaction record
					String insertTransactionQuery = "INSERT INTO transactions (datetime, amount, id, type) VALUES (NOW(), ?, ?, ?)";
					PreparedStatement insertTransactionStatement = con.prepareStatement(insertTransactionQuery);
					insertTransactionStatement.setBigDecimal(1, amount);
					insertTransactionStatement.setInt(2, customerId);
					insertTransactionStatement.setString(3, transactionType);

					int success = insertTransactionStatement.executeUpdate();
					if (success > 0) {
						System.out.println("Amount Added Successfully");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
}
