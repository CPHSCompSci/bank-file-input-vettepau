package app;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) 
	{
		example1();
	}
	
	public static void example1()
	{
		Bank bank = new Bank("la banca superiore di paul");
		String files = "bankingDataBase";
		bank.loadAccounts(files);
		Scanner scan = new Scanner(System.in);
		String again = "yes";
		String trash;
		
		while(again.equals("yes")) {
		
		System.out.println("....... MENU ....... ");
		System.out.println();
		System.out.println("Deposit				1");
		System.out.println("Withdraw			2");
		System.out.println("Check Balance 			3");
		System.out.println("Create Account			4");
		System.out.println("Close Account 			5");
		System.out.println("Transfer Funds 			6");
		System.out.println();
		System.out.println();
		
		int menu = scan.nextInt();
		
		switch(menu)
		{
		case 1: //deposit
			System.out.println("Please enter your account number");
			System.out.println();
			int d1 = scan.nextInt();
			System.out.println("Please enter the amount you would like to deposit");
			System.out.println();
			int d2 = scan.nextInt();
			bank.deposit(d1, d2);
		break;
		
		case 2: //withdraw
			System.out.println("Please enter your account number");
			System.out.println();
			int w1 = scan.nextInt();
			System.out.println("Please enter the amount you would like to withdraw");
			System.out.println();
			int w2 = scan.nextInt();
			bank.withdraw(w1, w2);
		break;
		
		case 3: //check balance
			System.out.println("Please enter your account number");
			System.out.println();
			int c1 = scan.nextInt();
			System.out.println(bank.checkBalance(c1));
		break;
		
		case 4: //create account
			System.out.println("Please enter your first and last name");
			System.out.println();
			String cta1 = scan.next();
			System.out.println("Your new account number is: " + bank.createAccount(cta1));
			System.out.println("Please save this number for future transactions");
			System.out.println();
		break;
		
		case 5: //close account
			System.out.println("Please enter your account number");
			System.out.println();
			int cla1 = scan.nextInt();
			bank.closeAccount(cla1);
		break;
		
		case 6: //transfer funds
			System.out.println("Please enter your account number");
			System.out.println();
			int tf1 = scan.nextInt();
			System.out.println("Please enter the account number of the account you would like to transfer money");
			System.out.println();
			trash = scan.nextLine();
			int tf2 = scan.nextInt();
			System.out.println("Please enter the amount you would like to transfer");
			System.out.println();
			trash = scan.nextLine();
			int tf3 = scan.nextInt();
			bank.transferFunds(tf1, tf2, tf3);
		break;
		}
		bank.saveAccounts(files);
		System.out.println("Would you like to preform any additional banking functions");
		System.out.println("yes or no");
		trash = scan.nextLine();
		again = scan.next();
		}
	}
}
