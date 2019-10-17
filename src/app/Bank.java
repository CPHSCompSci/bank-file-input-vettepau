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
		//log("Bank Created");
	}

	public int createAccount(String name) {
		Account newAccount = new Account(name);
		accounts.add(newAccount);
		return newAccount.accountNumber;
	}

	public boolean closeAccount(int accountNumber) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			return false;
		}
		accounts.remove(account);
		return true;
	}

	public boolean deposit(int accountNumber, int amount) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			return false;
		}
		account.balance += amount;
		return true;
	}

	public int withdraw(int accountNumber, int amount) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			return 0;
		}
		if (account.balance < amount) {
			return 1;
		}
		account.balance -= amount;
		return 2;
	}

	public int checkBalance(int accountNumber) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			return -1;
		}
		return account.balance;
	}

	public boolean transferFunds(int accountnum, int accountnum2, int amount) {
		Account account = findAccount(accountnum);
		Account account2 = findAccount(accountnum2);
		if (account.equals(null) || account2.equals(null))
		{
			return false;
		}
		else {
			account2.balance += amount;
			account.balance -= amount;
			return true;
		}
		
	}
	public void saveAccounts(String filename) {
		try {
			FileWriter fw = new FileWriter (filename);
			for(Account a: accounts) // enhanced for loop / for each
			{
				String message = a.toString();
				fw.append(message);
			}
			fw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("Save implemented.");
	}
	public void loadAccounts(String filename) {
		try {
		Scanner fileScanner = new Scanner(new File(filename));
		
		while(fileScanner.hasNextLine())
		{
			String line = fileScanner.nextLine();
			String[] split = line.split("::");
			int accountNumber = Integer.parseInt(split[0].substring(1));
			String name = split[1];
			int amount = Integer.parseInt(split[2].substring(1, split[2].length()-1));
			Account a = new Account(accountNumber, name, amount);
			accounts.add(a);
		}
			fileScanner.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
			accountNumber = getRandomAccountNumber();
		}

		private Account(int an, String name, int bal) {
			this.accountNumber = an;
			this.name = name;
			this.balance = bal;
		}
		
		private int getRandomAccountNumber() {
			boolean flag = false;
			int num = 0;
			do {
				num = (int)(Math.random() * 1000000);
				for(Account a: accounts)
				{
					if(a.accountNumber == num)
						flag = true;
				}
			}while(flag);
			return num;
		}
		
		public String toString() {
			return "{" + accountNumber + "::" + name + "::$" + balance + "}\n";
		}

	}
}
