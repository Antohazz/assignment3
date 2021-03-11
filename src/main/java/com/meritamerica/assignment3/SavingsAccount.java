package com.meritamerica.assignment3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//SavingsAccount


public class SavingsAccount extends BankAccount{
	
	private double interestRate = 0.01; // Interest rate

	//Sets opening balance
	SavingsAccount(double openingBalance){
		super.balance = openingBalance;
	}
	

	SavingsAccount(long accNum, double balance, double interestRate, Date openDate){
		super.balance = balance;
		this.interestRate = interestRate;
		super.openDate = openDate;
		super.accountNumber = accNum;
	}

	//Interest getter
	public double getInterestRate() {
		return interestRate;
	}
		
	public static SavingsAccount readFromString(String accountData) throws ParseException{

		try {
		int firstCh = 0;
		int lastCh = accountData.indexOf(",");
		long accNum = Integer.parseInt(accountData.substring(firstCh, lastCh));
		
		firstCh = lastCh+1;
		lastCh = accountData.indexOf(",", firstCh);
		double balance = Double.parseDouble(accountData.substring(firstCh, lastCh));
		
		firstCh = lastCh+1;
		lastCh = accountData.indexOf(",", firstCh);
		double iRate = Double.parseDouble(accountData.substring(firstCh, lastCh));
		
		firstCh = lastCh+1;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date openDate = df.parse(accountData.substring(firstCh));
		
		SavingsAccount savingsAccount = new SavingsAccount(accNum, balance, iRate, openDate);
		
		return savingsAccount;
	}catch(Exception e){
		throw new NumberFormatException();
	}
		
	}
	
//	// Outputs account info
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String saveAccInfo = getAccountNumber() + "," + getBalance() + "," + getInterestRate() + "," + df.format(getOpenedOn());
		return saveAccInfo;
	}
	
	
}
