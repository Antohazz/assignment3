package com.meritamerica.assignment3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/* Checking account class.

 */

public class CheckingAccount extends BankAccount{
	
	private final double INTEREST_RATE = 0.0001;
	
	//Sets opening balance
	CheckingAccount(double openingBalance){
		super.balance = openingBalance;
	}
	
	CheckingAccount(double balance, double interestRate, Date openDate){
		super.balance = balance;
		super.interestRate = interestRate;
		super.openDate = openDate;
	}

	public double getInterestRate() {
		return INTEREST_RATE;
	}
	
	public static CheckingAccount readFromString(String accountData) throws NumberFormatException, ParseException{

		
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
		
		CheckingAccount checkingAccount = new CheckingAccount(balance, iRate, openDate);
		
		return checkingAccount;
	}

	
//	// Outputs account info
//	public String toString() {
//		String checkAccInfo = "Checking Account Balance: $" + getBalance() + "/n"+
//				"Checking Account Interest Rate: " + INTEREST_RATE + "/n"+
//				"Checking Account Balance in 3 years: $" + futureValue(3);
//		return checkAccInfo;
//	}
	
	
}


