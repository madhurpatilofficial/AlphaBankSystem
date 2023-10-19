package com.alphabank.service;

import java.util.Scanner;

import com.alphabank.model.Customer;
import com.alphabank.model.Employee;

public class LoginImpl implements Login {

	
	public  boolean LoginAsCustomer() {
		
		Customer cus = new Customer();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Username:");
		String inputUser = sc.next();
		
		System.out.println("Enter Password:");
		int pass = sc.nextInt();
		
		
		String username = "Megh" ;
		int password  = 123456;
		
		
		if(!(String.valueOf(username).isEmpty() && String.valueOf(password).isEmpty())) {
			
			if(inputUser.equals(username) && pass==password) {
				System.out.println("Hello Customer..");
				System.out.println("Welcome to Alpha Bank");
			}
			else {
				System.out.println("Enter valid credentials..");
			}
			
			return true;
		}
		else {
			System.out.println("Enter Username and password");
		}	
		sc.close();
		return false;
	
	}

	@Override
	public boolean LoginAsEmployee() {
		
		Employee cus = new Employee();
		Scanner sc = new Scanner(System.in);
		
		String inputUser = sc.next();
		int pass = sc.nextInt();
		
		String username = "Varun" ;
		int password  = 123456;
		
		if(String.valueOf(username).isEmpty() && String.valueOf(password).isEmpty()) {
			
			if(inputUser.equals(username) && pass==password) {
				System.out.println("Hello Employee..");
				System.out.println("Welcome to Alpha Bank");
			}
			else {
				System.out.println("Enter valid credentials..");
			}
			
			return true;
		}
		else {
			System.out.println("Enter Username and password");
		}	
		sc.close();
		return false;
	}
	
	
	
	

}
