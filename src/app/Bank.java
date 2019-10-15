package app;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
	// Variable for logging/not logging
	private static final boolean LOG = false;

	private static int accountCounter = 1;
	private String name;
	private ArrayList<Account> accounts;

	public Bank() {
		this("Bank Name");
	}

	public Bank(String name) {
		this.name = name;
		accounts = new ArrayList<>();
		log("Bank Created");
	}

	public int createAccount(String name) {
		Account newAccount = new Account(name);
		accounts.add(newAccount);

		System.out.println("Successful Transaction");
		return newAccount.accountNumber;
	}

	public boolean closeAccount(int accountNumber) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			System.out.println("Could not close account, please check if that was the right number");
			Main.example1();
			return false;
		}
		accounts.remove(account);
		System.out.println("Successfully closed, hopefully our service has been satisfactory");
		return true;
	}

	public boolean deposit(int accountNumber, int amount) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			System.out.println("Could not deposit to account , please check if that was the right number");
			return false;
		}
		account.balance += amount;
		System.out.println("Successfully deposited $" + amount + " to your account");
		return true;
	}

	public boolean withdraw(int accountNumber, int amount) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			System.out.println("Could not withdraw from account, please check if that was the right number ");
			return false;
		}
		if (account.balance < amount) {
			System.out.println("Insufficient funds in ");
			return false;
		}
		account.balance -= amount;
		System.out.println("Successfully withdrew $" + amount);
		return true;
	}

	public int checkBalance(int accountNumber) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			System.out.println("Could not check balance of account, please check if that was the right number");
			return -1;
		}
		System.out.println("Your balance is: " + account.balance);
		return account.balance;
	}

	public void transferFunds(int accountnum, int accountnum2, int amount) {
		Account account = findAccount(accountnum);
		Account account2 = findAccount(accountnum2);
		if (account.equals(null) && account2.equals(null))
		{
			System.out.println("Could not transfer funds, please check if that was the right number");
		}
		else {
			account2.balance += amount;
			System.out.println("Successfully transfered $" + amount);
		}
		
	}
	
	public static void saveAccounts(String filename) {
		try {
			FileWriter fw = new FileWriter ("bankingDataBase");
			fw.append(filename);
			fw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//log("Save not yet implemented.");
	}

	public static void loadAccounts(String filename) {
		try {
			File inputfile = new File("info");
			Scanner fileIn = new Scanner(inputfile);
			
			String line1 = fileIn.nextLine();
			//String[] nums = line1.split(" ");
			
			String word = null;
			int wordCount = indexRepeat(' ', filename) + 1;		
			for (int a = 0; a < wordCount; a++)
			{
				int pos = filename.indexOf(' ');
				if(filename.indexOf(' ') == -1)			
				{
					String Account = filename;
					//Account a = new Account(a,)
					
				}
				else {
				String account = filename.substring(0, pos);
				} 
			}
			fileIn.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		//log("Load not yet implemented.");
	}

	public static int indexRepeat(char letter, String word)		
	  {
		  int a;
		  int num = 0;
		  for(a = 0; a < word.length()-1; a++)
		  {
			  if(word.charAt(a) == letter)
			  {
				  num++;
			  }
		  }	  
		return num;
	  }
	
	private Account findAccount(int accountNumber) {
		for (int i = accounts.size() - 1; i >= 0; i--) {
			if (accounts.get(i).accountNumber == accountNumber)
				return accounts.get(i);
		}
		return null;
	}

	private void log(String message) {
		if (LOG)
			System.out.println(name + " ::: " + message + ".");
	}

	/**
	 * Private Inner Class Account
	 * Deals with Account information
	 */
	private class Account {
		int accountNumber;
		String name;
		int balance;

		private Account(String name) {
			this.name = name;
			balance = 0;
			accountNumber = accountCounter++;
		}

		public String toString() {
			return "{" + accountNumber + "::" + name + "::$" + balance + "}";
		}

	}
}
