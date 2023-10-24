
create database banksystem;
use banksystem;
-- ------------------------------------------------------------ 

CREATE TABLE Loan_Types (
    id VARCHAR(20) PRIMARY KEY,
    description VARCHAR(50)
);

CREATE TABLE Customers (
    id INT PRIMARY KEY auto_increment,
    login VARCHAR(20),
    password VARCHAR(50),
    name VARCHAR(20),
    phone VARCHAR(15),
    email VARCHAR(50),
    registrationDate DATE
);
desc customers;

select * from Customers;
select * from accounts;
select * from transactions;
select * from employees;
select * from branches;
desc accounts;
desc transactions;

-- ALTER TABLE customers
-- CHANGE COLUMN password hashedPassword VARCHAR(255);

-- ALTER TABLE accounts DROP FOREIGN KEY accounts_ibfk_1;
-- ALTER TABLE Customers MODIFY COLUMN id INT AUTO_INCREMENT;
-- ALTER TABLE loan DROP FOREIGN KEY loan_ibfk_1;
-- ALTER TABLE Customers MODIFY COLUMN id INT AUTO_INCREMENT;
-- ALTER TABLE accounts ADD FOREIGN KEY (customer_id) REFERENCES Customers(id);
-- ALTER TABLE loan ADD FOREIGN KEY (customer_id) REFERENCES Customers(id);


CREATE TABLE Branches (
    id VARCHAR(20) PRIMARY KEY,
    address VARCHAR(30),
    phone VARCHAR(15)
);


-- ALTER TABLE transactions
-- MODIFY COLUMN id INT AUTO_INCREMENT;


-- Alter the table to change the data type of the 'id' column to INT and set it as AUTO_INCREMENT
-- ALTER TABLE Branches
-- MODIFY id varchar(20) AUTO_INCREMENT;

-- -- Set 'id' as the primary key
-- ALTER TABLE Branches
-- ADD PRIMARY KEY (id);


-- ALTER TABLE Accounts
-- DROP FOREIGN KEY accounts_ibfk_2;

-- ALTER TABLE employees
-- DROP FOREIGN KEY employees_ibfk_1;

-- ALTER TABLE employees
-- DROP FOREIGN KEY employees_ibfk_1;
-- Alter table employees add foreign key(branch_id) references Branches(id);

-- Alter table Accounts add foreign key(branch_id) references Branches(id);

-- Alter table loan add foreign key(branch_id) references Branches(id);

CREATE TABLE Loan (
    id INT PRIMARY KEY,
    customer_id INT,
    type VARCHAR(20),
    amount DECIMAL,
    branch_id VARCHAR(11),
    FOREIGN KEY (customer_id) REFERENCES Customers(id),
    FOREIGN KEY (type) REFERENCES Loan_Types(id),
    FOREIGN KEY (branch_id) REFERENCES Branches(id)
);

CREATE TABLE Account_Types (
    id VARCHAR(20) PRIMARY KEY,
    description VARCHAR(40),
    interestRate DECIMAL(10,2)
);

CREATE TABLE Accounts (
    id INT PRIMARY KEY auto_increment,
    customer_id INT,
    branch_id VARCHAR(11),
    openingDate DATE,
    balance DECIMAL,
    type VARCHAR(20),
    FOREIGN KEY (customer_id) REFERENCES Customers(id),
    FOREIGN KEY (branch_id) REFERENCES Branches(id),
    FOREIGN KEY (type) REFERENCES Account_Types(id))



-- CREATE TABLE Accounts (
--     id INT PRIMARY KEY,
--     customer_id INT,
--     branch_id VARCHAR(11),
--     openingDate DATE,
--     balance DECIMAL,
--     type VARCHAR(20),
--     FOREIGN KEY (id) REFERENCES Customers(id),
--     FOREIGN KEY (branch_id) REFERENCES Branches(id),
--     FOREIGN KEY (type) REFERENCES Account_Types(id))


-- ALTER TABLE Accounts
-- ADD 
-- FOREIGN KEY (customer_id)
-- REFERENCES Customers(id);

-- ALTER TABLE Accounts
-- DROP FOREIGN KEY accounts_ibfk_5; -- First, drop the existing foreign key constraint

CREATE TABLE Employees (
    id INT PRIMARY KEY,
    name VARCHAR(20),
    phone VARCHAR(15),
    position VARCHAR(15),
    branch_id VARCHAR(11),
    manager_id INT,
    login VARCHAR(20),
    password VARCHAR(30),
    FOREIGN KEY (branch_id) REFERENCES Branches(id),
    FOREIGN KEY (manager_id) REFERENCES Employees(id)
);

-- ALTER TABLE accounts
-- ADD CONSTRAINT FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE;

-- ALTER TABLE accounts
-- MODIFY customer_id INT NULL;

CREATE TABLE OverdraftLog (
    id INT PRIMARY KEY,
    account_id INT,
    description VARCHAR(50),
    FOREIGN KEY (id) REFERENCES Accounts(id)
);

CREATE TABLE Transaction_Types (
    id VARCHAR(20) PRIMARY KEY,
    description VARCHAR(40)
);


CREATE TABLE Transactions (
    id INT PRIMARY KEY,  -- //this is set to auto_increment
    datetime DATETIME,
    amount DECIMAL,
    account_id INT,
    type VARCHAR(20),
    teller_emp_id INT,
    FOREIGN KEY (account_id) REFERENCES Accounts(id),
    FOREIGN KEY (type) REFERENCES Transaction_Types(id),
    FOREIGN KEY (teller_emp_id) REFERENCES Employees(id)
);
select * from transactions;
select * from transaction_types;
-- Drop foreign key constraint in OverdraftLog table
-- ALTER TABLE Transactions
-- DROP FOREIGN KEY Transactions_ibfk_1;


CREATE TABLE FailedTransactionLog (
    transaction_id INT PRIMARY KEY,
    errorType varchar(20),
    timestamp DATETIME
);


---------------------- Insert data in tables-------------------------------


-- Insert data into the Loan_Types table
-- INSERT INTO Loan_Types (id, description)
-- VALUES
--     (1, 'Personal Loan'),
--     (2, 'Home Loan'),
--     (3, 'Car Loan');
    


-- Insert data into the Loan table with the correct foreign keys
-- INSERT INTO Loan (id, customer_id, type, amount, branch_id)
-- VALUES
--     (1, 1, 1, 5000.00, 'B001'),   -- Assuming 1 corresponds to 'Personal Loan' in Loan_Types
--     (2, 2, 2, 25000.00, 'B002'),  -- Assuming 2 corresponds to 'Home Loan' in Loan_Types
--     (3, 3, 3, 10000.00, 'B003');  -- Assuming 3 corresponds to 'Car Loan' in Loan_Types
    
    
-- Insert data into the Customers table
-- INSERT INTO Customers (id, login, password, name, phone, email, registrationDate)
-- VALUES
--     (1, 'customer1', 'password1', 'Varun', '996363999', 'varun@gmail.com', '2023-10-16'),
--     (2, 'customer2', 'password2', 'Yash', '939635639', 'yash@gmail.com', '2023-10-17'),
--     (3, 'customer3', 'password3', 'Madhur', '8846383488', 'madhur@gmail.com', '2023-10-18'),
--     (4, 'customer2', 'password2', 'Yash', '939635639', 'yash@gmail.com', '2023-10-17');


-- Insert data into the Branches table
-- INSERT INTO Branches (id, address, phone)
-- VALUES
--     ('B001', 'Wagholi, Pune, Maharashtra', '9057957957'),
--     ('B002', 'Kharadi, Pune, Maharashtra', '8785845848'),
--     ('B003', 'Hadapsar, Pune', '8376367367');


-- Insert data into the Account_Types table
INSERT INTO Account_Types (id, description, interestRate)
VALUES
    (1, 'Savings Account', 5),
    (2, 'Checking Account', 4),
    (3, 'Fixed Deposit Account', 2);
    

-- Insert data into the Accounts table
-- INSERT INTO Accounts (id, customer_id, branch_id, openingDate, balance, type)
-- VALUES
--     (1, 1, 'B001', '2023-10-16', 5000.00, 1), -- Assuming '1' is a valid account type ID
--     (2, 2, 'B002', '2023-10-17', 25000.00, 2), -- Assuming '2' is a valid account type ID
--     (3, 3, 'B003', '2023-10-18', 10000.00, 3); -- Assuming '3' is a valid account type ID





-- Insert data into the Employees table with Indian names
-- INSERT INTO Employees (id, name, phone, position, branch_id, manager_id, login, password)
-- VALUES
--     (1, 'Rajesh Sharma', '555-123-4567', 'Manager', 'B001', NULL, 'rajesh.sharma', 'password123'),
--     (2, 'Priya Verma', '555-987-6543', 'Teller', 'B002', 1, 'priya.verma', 'password456'),
--     (3, 'Amit Kapoor', '555-789-0123', 'Teller', 'B003', 1, 'amit.kapoor', 'password789');



-- Insert data into the OverdraftLog table
INSERT INTO OverdraftLog (id, account_id, description)
VALUES
    (1, 1, 'Account overdrawn'),
    (2, 2, 'Account balance below the minimum threshold'),
    (3, 3, 'Account exceeded overdraft limit');



-- Insert data into the Transaction_Types table with numeric IDs
INSERT INTO Transaction_Types (id, description)
VALUES
    (1, 'Deposit'),
    (2, 'Withdrawal'),
    (3, 'Transfer');

-- Insert data into the Transactions table with the correct foreign keys
INSERT INTO Transactions (id, datetime, amount, account_id, type, teller_emp_id)
VALUES
    (1, '2023-10-16 09:00:00', 1000.00, 1, 1, 1), -- Assuming '1' corresponds to 'Deposit' in Transaction_Types
    (2, '2023-10-17 14:30:00', 500.00, 2, 2, 2), -- Assuming '2' corresponds to 'Withdrawal' in Transaction_Types
    (3, '2023-10-18 10:15:00', 1500.00, 3, 3, 3); -- Assuming '3' corresponds to 'Transfer' in Transaction_Types


-- Insert data into the FailedTransactionLog table
INSERT INTO FailedTransactionLog (transaction_id, errorType, timestamp)
VALUES
    (1, 'Insufficient Funds', '2023-10-16 15:30:00'),
    (2, 'Invalid Account', '2023-10-17 11:45:00'),
    (3, 'Network Error', '2023-10-18 08:20:00');
    

