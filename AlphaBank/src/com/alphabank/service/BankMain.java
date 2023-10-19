package com.alphabank.service;

import java.util.Scanner;

import com.alphabank.controller.CustomerController;

public class BankMain extends LoginImpl {

	public static void main(String[] args) throws Exception {
		CustomerController b1=new CustomerController();
		//b1.createCustomer();
        //b1.removeCustomer();
		b1.findCustomerByID();
		LoginImpl obj = new LoginImpl();
		Scanner input = new Scanner(System.in);
		System.out.println("-----Login-----");
		System.out.println("1.Login As Customer");
		System.out.println("2.Login As Employee");
		
		int choice=input.nextInt();

		switch(choice) {

		case 1:
			 obj.LoginAsCustomer();
			break;

		case 2:
			obj.LoginAsEmployee();
			break;
		}
	}		
}

