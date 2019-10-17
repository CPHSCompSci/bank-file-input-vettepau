package app;

import java.text.DecimalFormat;
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
			boolean d3 = bank.deposit(d1, d2);
			if(d3) {
				System.out.println("Successfully deposited $" + d2 + " to your account");
			}
			else {
				System.out.println("Could not deposit to account , please check if that was the right number");
			}
		break;
		
		case 2: //withdraw
			System.out.println("Please enter your account number");
			System.out.println();
			int w1 = scan.nextInt();
			System.out.println("Please enter the amount you would like to withdraw");
			System.out.println();
			int w2 = scan.nextInt();
			int w3 = bank.withdraw(w1, w2);
			if(w3 == 2) {
				System.out.println("Successfully withdrew $" + w2);
			}
			else if(w3 == 1){
				System.out.println("Insufficient funds in account");
			}
			else {
				System.out.println("Could not withdraw from account, please check if that was the right number ");
			}
		break;
		
		case 3: //check balance
			DecimalFormat decimalZero = new DecimalFormat("0.00");
			System.out.println("Please enter your account number");
			System.out.println();
			int c1 = scan.nextInt();
			int c2 = bank.checkBalance(c1);
			if(c2 >= 0) {
				System.out.println("Your balance is: $" + decimalZero.format(c2));
			}
			else {
				System.out.println("Could not check balance of account, please check if that was the right number");
			}
		break;
		
		case 4: //create account
			DecimalFormat sixMin = new DecimalFormat("000000");
			System.out.println("Please enter your first and last name");
			System.out.println();
			String cta1 = scan.next();
			String cta2 = scan.next();
			cta1 = cta1 + " " +cta2;
			System.out.println("Your new account number is: " + sixMin.format(bank.createAccount(cta1)));
			System.out.println("Please save this number for future transactions");
			System.out.println();
		break;
		
		case 5: //close account
			System.out.println("Please enter your account number");
			System.out.println();
			int cla1 = scan.nextInt();
			boolean cla2 = bank.closeAccount(cla1);
			if(cla2) {
				System.out.println("Successfully closed, hopefully our service has been satisfactory");
			}
			else {
				System.out.println("Could not close account, please check if that was the right number");
			}
		break;
		
		case 6: //transfer funds
			DecimalFormat decimalZero1 = new DecimalFormat("0.00");
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
			boolean tf4 = bank.transferFunds(tf1, tf2, tf3);
			if(tf4) {
				System.out.println("Successfully transfered $" + decimalZero1.format(tf3));
			}
			else {
				System.out.println("Could not transfer funds, please check if that was the right number");
			}
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
