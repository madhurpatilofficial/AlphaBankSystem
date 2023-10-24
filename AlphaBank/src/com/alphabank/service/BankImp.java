package com.alphabank.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
			customer = custdao.addCustomerDao(login, password, name, phone, email, dateStr);

			if (customer != null) {
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
	
	
	public List<String> findAllmanager() throws SQLException {
	    ArrayList<String> data = new ArrayList<>();

	    JDBCConnect connection = new JDBCConnect();
	    Connection con = null;

	    try {
	        con = connection.getConnection();
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM employees WHERE manager_id IS NOT NULL");

	        while (rs.next()) {
	            int id = rs.getInt(1);
	            String name = rs.getString(2);
	            String phone = rs.getString(3);
	            String title = rs.getString(4);
	            String branchId = rs.getString(5);
	            int managerId = rs.getInt(6);

	            String managerInfo = "Manager ID: " + id + "\nName: " + name + "\nPhone: " + phone + "\nTitle: " + title
	                + "\nBranch ID: " + branchId + "\nManager ID: " + managerId;

	            data.add(managerInfo);
	        }

	        // Iterate and print the manager information
	        for (String managerInfo : data) {
	            System.out.println(managerInfo);
	            System.out.println(); // Add an empty line for separation
	        }
	    } catch (Exception e) {
	        System.out.println("ERROR: " + e.getMessage());
	    } finally {
	        if (con != null) {
	            con.close();
	            System.out.println("Closing Connection......");
	        } else {
	            System.out.println("Can't able to close the connection.....");
	        }
	    }

	    return data;
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

	public boolean deposit() throws SQLException {
		// Take user-defined inputs
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Account ID: ");
		int accountId = scanner.nextInt();
		System.out.println("Enter the amount to deposit: ");
		BigDecimal amount = scanner.nextBigDecimal();

		String transactionType = "Deposit";

		// Database Connection
		JDBCConnect connection = new JDBCConnect();
		Connection con = null;

		try {
			con = connection.getConnection();

			// Check if the account exists
			if (!isAccountExists(accountId)) {
				System.out.println("Account does not exist.");
				return false;
			}

			// Retrieve the current balance for the customer's account
			String selectQuery = "SELECT balance FROM accounts WHERE id = ?";
			PreparedStatement selectStatement = con.prepareStatement(selectQuery);
			selectStatement.setInt(1, accountId);

			ResultSet resultSet = selectStatement.executeQuery();

			if (resultSet.next()) {
				BigDecimal currentBalance = resultSet.getBigDecimal("balance");

				// Update the current balance
				currentBalance = currentBalance.add(amount);

				// Update the current_balance in the account table
				String updateQuery = "UPDATE accounts SET balance = ? WHERE id = ?";
				PreparedStatement updateStatement = con.prepareStatement(updateQuery);
				updateStatement.setBigDecimal(1, currentBalance);
				updateStatement.setInt(2, accountId);

				int rowsAffected = updateStatement.executeUpdate();

				if (rowsAffected > 0) {

					String insertTransactionQuery = "INSERT INTO transactions (datetime, amount, account_id, type) VALUES (NOW(), ?, ?, ?)";
					PreparedStatement insertTransactionStatement = con.prepareStatement(insertTransactionQuery);
					insertTransactionStatement.setBigDecimal(1, amount);
					insertTransactionStatement.setInt(2, accountId);
					insertTransactionStatement.setString(3, transactionType);

					int success = insertTransactionStatement.executeUpdate();
					if (success > 0) {
						System.out.println("Amount Added Successfully");
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Deposit is Successfull!!!");
		}
		return true;
	}

	public boolean withdraw() throws SQLException {
		// Take user-defined inputs
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Account ID: ");
		int accountId = scanner.nextInt();
		System.out.println("Enter Amount to Withdraw: ");
		BigDecimal withdrawAmount = scanner.nextBigDecimal();

		String transactionType = "WithDraw";

		// Database Connection
		JDBCConnect connection = new JDBCConnect();
		Connection con = null;

		try {
			con = connection.getConnection();

			// Check if the account exists
			if (!isAccountExists(accountId)) {
				System.out.println("Account does not exist.");
				return false;
			}

			// Retrieve the current balance for the customer's account
			String selectQuery = "SELECT balance FROM accounts WHERE id = ?";
			PreparedStatement selectStatement = con.prepareStatement(selectQuery);
			selectStatement.setInt(1, accountId);

			ResultSet resultSet = selectStatement.executeQuery();

			if (resultSet.next()) {
				BigDecimal currentBalance = resultSet.getBigDecimal("balance");

				// Ensure sufficient balance for withdrawal
				if (currentBalance.compareTo(withdrawAmount) >= 0) {
					// Update the current balance
					currentBalance = currentBalance.subtract(withdrawAmount);

					// Update the current_balance in the account table
					String updateQuery = "UPDATE accounts SET balance = ? WHERE id = ?";
					PreparedStatement updateStatement = con.prepareStatement(updateQuery);
					updateStatement.setBigDecimal(1, currentBalance);
					updateStatement.setInt(2, accountId);

					int rowsAffected = updateStatement.executeUpdate();

					if (rowsAffected > 0) {

						String insertTransactionQuery = "INSERT INTO transactions (datetime, amount, account_id, type) VALUES (NOW(), ?, ?, ?)";
						PreparedStatement insertTransactionStatement = con.prepareStatement(insertTransactionQuery);
						insertTransactionStatement.setBigDecimal(1, withdrawAmount);
						insertTransactionStatement.setInt(2, accountId);
						insertTransactionStatement.setString(3, transactionType);

						int success = insertTransactionStatement.executeUpdate();
						if (success > 0) {
							System.out.println("Amount Withdrawn Successfully");
						}
					}
				} else {
					System.out.println("Insufficient balance for withdrawal.");
				}
			}
		} catch (SQLException e) {
			System.out.println("Withdraw Successfull!!!");
		}
		return true;
	}

	private boolean isAccountExists(int accountId) {
		JDBCConnect connection = new JDBCConnect();
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			con = connection.getConnection();
			String query = "SELECT COUNT(*) FROM accounts WHERE id = ?";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, accountId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				return count > 0;
			}
		} catch (SQLException e) {
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
	
	
	public boolean transfer() throws SQLException {
	    // Take user-defined inputs
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Enter Source Account ID: ");
	    int sourceAccountId = scanner.nextInt();
	    System.out.println("Enter Destination Account ID: ");
	    int destinationAccountId = scanner.nextInt();
	    System.out.println("Enter the amount to transfer: ");
	    BigDecimal transferAmount = scanner.nextBigDecimal();

	    String transactionType = "transfer";

	    // Database Connection
	    JDBCConnect connection = new JDBCConnect();
	    Connection con = null;

	    con = connection.getConnection();

	    // Check if both source and destination accounts exist
	    if (!isAccountExists(sourceAccountId) || !isAccountExists(destinationAccountId)) {
	        System.out.println("Source or destination account does not exist.");
	        return false;
	    }

	    // Retrieve the current balance for the source account
	    String selectQuerySource = "SELECT balance FROM accounts WHERE id = ?";
	    PreparedStatement selectStatementSource = con.prepareStatement(selectQuerySource);
	    selectStatementSource.setInt(1, sourceAccountId);

	    ResultSet sourceResultSet = selectStatementSource.executeQuery();

	    // Retrieve the current balance for the destination account
	    String selectQueryDestination = "SELECT balance FROM accounts WHERE id = ?";
	    PreparedStatement selectStatementDestination = con.prepareStatement(selectQueryDestination);
	    selectStatementDestination.setInt(1, destinationAccountId);

	    ResultSet destinationResultSet = selectStatementDestination.executeQuery();

	    if (sourceResultSet.next() && destinationResultSet.next()) {
	        BigDecimal sourceBalance = sourceResultSet.getBigDecimal("balance");
	        BigDecimal destinationBalance = destinationResultSet.getBigDecimal("balance");

	        // Ensure sufficient balance for transfer
	        if (sourceBalance.compareTo(transferAmount) >= 0) {
	            // Update the source account's balance
	            sourceBalance = sourceBalance.subtract(transferAmount);

	            // Update the destination account's balance
	            destinationBalance = destinationBalance.add(transferAmount);

	            // Update the balances in the account table
	            String updateQuerySource = "UPDATE accounts SET balance = ? WHERE id = ?";
	            PreparedStatement updateStatementSource = con.prepareStatement(updateQuerySource);
	            updateStatementSource.setBigDecimal(1, sourceBalance);
	            updateStatementSource.setInt(2, sourceAccountId);

	            String updateQueryDestination = "UPDATE accounts SET balance = ? WHERE id = ?";
	            PreparedStatement updateStatementDestination = con.prepareStatement(updateQueryDestination);
	            updateStatementDestination.setBigDecimal(1, destinationBalance);
	            updateStatementDestination.setInt(2, destinationAccountId);

	            int sourceRowsAffected = updateStatementSource.executeUpdate();
	            int destinationRowsAffected = updateStatementDestination.executeUpdate();

	            if (sourceRowsAffected > 0 && destinationRowsAffected > 0) {
	                // Insert a new transaction record for the source account
	                String insertTransactionQuerySource = "INSERT INTO transactions (datetime, amount, account_id, type) VALUES (NOW(), ?, ?, ?)";
	                PreparedStatement insertTransactionStatementSource = con.prepareStatement(insertTransactionQuerySource);
	                insertTransactionStatementSource.setBigDecimal(1, transferAmount);
	                insertTransactionStatementSource.setInt(2, sourceAccountId);
	                insertTransactionStatementSource.setString(3, transactionType);

	                int sourceTransactionSuccess = insertTransactionStatementSource.executeUpdate();

	                // Insert a new transaction record for the destination account
	                String insertTransactionQueryDestination = "INSERT INTO transactions (datetime, amount, account_id, type) VALUES (NOW(), ?, ?, ?)";
	                PreparedStatement insertTransactionStatementDestination = con.prepareStatement(insertTransactionQueryDestination);
	                insertTransactionStatementDestination.setBigDecimal(1, transferAmount);
	                insertTransactionStatementDestination.setInt(2, destinationAccountId);
	                insertTransactionStatementDestination.setString(3, transactionType);
	                int destinationTransactionSuccess = insertTransactionStatementDestination.executeUpdate();

	                if (sourceTransactionSuccess > 0 && destinationTransactionSuccess > 0) {
	                    System.out.println("Amount Transferred Successfully");
	                } else {
	                    System.out.println("Transfer Failed");
	                }
	            }
	        } else {
	            System.out.println("Insufficient balance for transfer.");
	        }
	    }

	    return true;
	}


	

//	public boolean transfer() throws SQLException {
//		// Take user-defined inputs
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Enter Source Account ID: ");
//		int sourceAccountId = scanner.nextInt();
//		System.out.println("Enter Destination Account ID: ");
//		int destinationAccountId = scanner.nextInt();
//		System.out.println("Enter the amount to transfer: ");
//		BigDecimal transferAmount = scanner.nextBigDecimal();
//
//		String transactionType = "transfer";
//
//		// Database Connection
//		JDBCConnect connection = new JDBCConnect();
//		Connection con = null;
//
//		try {
//			con = connection.getConnection();
//
//			// Check if both source and destination accounts exist
//			if (!isAccountExists(sourceAccountId) || !isAccountExists(destinationAccountId)) {
//				System.out.println("Source or destination account does not exist.");
//				return false;
//			}
//
//			// Retrieve the current balance for the source account
//			String selectQuerySource = "SELECT balance FROM accounts WHERE id = ?";
//			PreparedStatement selectStatementSource = con.prepareStatement(selectQuerySource);
//			selectStatementSource.setInt(1, sourceAccountId);
//
//			ResultSet sourceResultSet = selectStatementSource.executeQuery();
//
//			// Retrieve the current balance for the destination account
//			String selectQueryDestination = "SELECT balance FROM accounts WHERE id = ?";
//			PreparedStatement selectStatementDestination = con.prepareStatement(selectQueryDestination);
//			selectStatementDestination.setInt(1, destinationAccountId);
//
//			ResultSet destinationResultSet = selectStatementDestination.executeQuery();
//
//			// Begin a transaction
//			con.setAutoCommit(false);
//
//			if (sourceResultSet.next() && destinationResultSet.next()) {
//				BigDecimal sourceBalance = sourceResultSet.getBigDecimal("balance");
//				BigDecimal destinationBalance = destinationResultSet.getBigDecimal("balance");
//
//				// Ensure sufficient balance for transfer
//				if (sourceBalance.compareTo(transferAmount) >= 0) {
//					// Update the source account's balance
//					sourceBalance = sourceBalance.subtract(transferAmount);
//
//					// Update the destination account's balance
//					destinationBalance = destinationBalance.add(transferAmount);
//
//					// Update the balances in the account table
//					String updateQuerySource = "UPDATE accounts SET balance = ? WHERE id = ?";
//					PreparedStatement updateStatementSource = con.prepareStatement(updateQuerySource);
//					updateStatementSource.setBigDecimal(1, sourceBalance);
//					updateStatementSource.setInt(2, sourceAccountId);
//
//					String updateQueryDestination = "UPDATE accounts SET balance = ? WHERE id = ?";
//					PreparedStatement updateStatementDestination = con.prepareStatement(updateQueryDestination);
//					updateStatementDestination.setBigDecimal(1, destinationBalance);
//					updateStatementDestination.setInt(2, destinationAccountId);
//
//					int sourceRowsAffected = updateStatementSource.executeUpdate();
//					int destinationRowsAffected = updateStatementDestination.executeUpdate();
//
//					if (sourceRowsAffected > 0 && destinationRowsAffected > 0) {
//						// Insert a new transaction record for the source account
//						String insertTransactionQuerySource = "INSERT INTO transactions (datetime, amount, account_id, type) VALUES (NOW(), ?, ?, ?)";
//						PreparedStatement insertTransactionStatementSource = con
//								.prepareStatement(insertTransactionQuerySource);
//						insertTransactionStatementSource.setBigDecimal(1, transferAmount);
//						insertTransactionStatementSource.setInt(2, sourceAccountId);
//						insertTransactionStatementSource.setString(3, transactionType);
//
//						int sourceTransactionSuccess = insertTransactionStatementSource.executeUpdate();
//
//						// Insert a new transaction record for the destination account
//						String insertTransactionQueryDestination = "INSERT INTO transactions (datetime, amount, account_id, type) VALUES (NOW(), ?, ?, ?)";
//						PreparedStatement insertTransactionStatementDestination = con
//								.prepareStatement(insertTransactionQueryDestination);
//						insertTransactionStatementDestination.setBigDecimal(1, transferAmount);
//						insertTransactionStatementDestination.setInt(2, destinationAccountId);
//						insertTransactionStatementDestination.setString(3, transactionType);
//						int destinationTransactionSuccess = insertTransactionStatementDestination.executeUpdate();
//
//						if (sourceTransactionSuccess > 0 && destinationTransactionSuccess > 0) {
//							// Commit the transaction if all steps are successful
//							con.commit();
//							System.out.println("Amount Transferred Successfully");
//						} else {
//							// Roll back the transaction if any step fails
//							con.rollback();
//						}
//					}
//				} else {
//					System.out.println("Insufficient balance for transfer.");
//				}
//			}
//		} catch (SQLException e) {
//			// Roll back the transaction in case of any exception
//			con.rollback();
//			System.out.println("Transfer Failed");
//			e.printStackTrace();
//		} finally {
//			// Set auto-commit back to true to resume normal behavior
//			con.setAutoCommit(true);
//		}
//
//		return true;
//	}

	public void showTransactionDetailsByAccountId() {
		// Take user-defined input for the account ID
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Account ID: ");
		int accountId = scanner.nextInt();
		System.out.println("\n");

		// Database Connection
		JDBCConnect connection = new JDBCConnect();
		Connection con = null;

		try {
			con = connection.getConnection();

			// Check if the account exists
			if (!isAccountExists(accountId)) {
				System.out.println("Account does not exist.");
				return;
			}

			// Retrieve transaction details for the specified account ID
			String query = "SELECT id, datetime, amount, type FROM transactions WHERE account_id = ?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, accountId);

			ResultSet resultSet = statement.executeQuery();

			// Display the transaction details
			while (resultSet.next()) {
				int transactionId = resultSet.getInt("id");
				String datetime = resultSet.getString("datetime");
				BigDecimal amount = resultSet.getBigDecimal("amount");
				String type = resultSet.getString("type");

				System.out.println("Transaction ID: " + transactionId);
				System.out.println("Datetime: " + datetime);
				System.out.println("Amount: " + amount);
				System.out.println("Type: " + type);
				System.out.println("-------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
