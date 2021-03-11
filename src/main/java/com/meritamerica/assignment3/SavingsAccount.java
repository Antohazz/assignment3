package com.meritamerica.assignment3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//SavingsAccount


public class SavingsAccount extends BankAccount{
	
	private final double INTEREST_RATE = 0.01; // Interest rate

	//Sets opening balance
	SavingsAccount(double openingBalance){
		super.balance = openingBalance;
	}
	
	SavingsAccount(double balance, double interestRate, Date openDate){
		super.balance = balance;
		super.interestRate = interestRate;
		super.openDate = openDate;
	}

	//Interest getter
	public double getInterestRate() {
		return INTEREST_RATE;
	}
		
	public static SavingsAccount readFromString(String accountData) throws NumberFormatException, ParseException{

		
		int firstCh = 0;
		int lastCh = accountData.indexOf(",");
		int accNum = Integer.parseInt(accountData.substring(firstCh, lastCh));
		
		firstCh = lastCh+1;
		lastCh = accountData.indexOf(",", firstCh);
		double balance = Double.parseDouble(accountData.substring(firstCh, lastCh));
		
		firstCh = lastCh+1;
		lastCh = accountData.indexOf(",", firstCh);
		double iRate = Double.parseDouble(accountData.substring(firstCh, lastCh));
		
		firstCh = lastCh+1;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date openDate = df.parse(accountData.substring(firstCh));
		
		SavingsAccount savingsAccount = new SavingsAccount(balance, iRate, openDate);
		
		return savingsAccount;
	}
	
//	// Outputs account info
//	public String toString() {
//		String saveAccInfo = "Savings Account Balance: $" + getBalance() + "/n"+
//				"Savings Account Interest Rate: " + getInterestRate() + "/n"+
//				"Savings Account Balance in 3 years: $" + futureValue(3);
//		return saveAccInfo;
//	}
	
	
}
