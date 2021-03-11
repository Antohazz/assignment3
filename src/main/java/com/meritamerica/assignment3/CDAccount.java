package com.meritamerica.assignment3;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CDAccount extends BankAccount{
	
	int term;

	
	public CDAccount(CDOffering offering, double balance) {
		super.offering = offering;
		super.balance = balance;
		super.accountNumber = MeritBank.getNextAccountNumber();
	}
	
	public CDAccount(double balance, double interestRate, Date date, int term) {
		super.balance = balance;
		super.interestRate = interestRate;
		this.term = term;
		super.openDate = date;
		
	}
	
	public int getTerm() {
		return offering.getTerm();
	}
	public double getInterestRate() {
		return offering.getInterestRate();
	}

	public Date getStartDate(){
		Date date = new Date();
		return date;
	}
	
	public boolean withdraw(double amount) {

			return false;
			
	}
	
	public boolean deposit (double amount) {

			return false;
		
	}
	

	public static CDAccount readFromString(String accountData) throws NumberFormatException, ParseException{

		
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
		lastCh = accountData.indexOf(",", firstCh);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date openDate = df.parse(accountData.substring(firstCh, lastCh));
		
		firstCh = lastCh+1;
		int term = Integer.parseInt(accountData.substring(firstCh));
		
		CDAccount cdAccount = new CDAccount(balance, iRate, openDate, term);
		
		return cdAccount;
	}
	
	
	public double futureValue() {
		double futureBalance = getBalance() * Math.pow(1 + getInterestRate(), getTerm());
		return futureBalance;
	}
	
	
	
//	// Outputs account info
	public String toString() {
		String cdAccInfo = "CD Account Balance: $" + getBalance() + "/n"+
				"CD Account Interest Rate: " + getInterestRate() + "/n"+
				"CD Account Balance in 3 years: $" + futureValue(3) + "/n"+
				"CD Account term years: "+ getTerm();
			
		return cdAccInfo;
	}
	
}

