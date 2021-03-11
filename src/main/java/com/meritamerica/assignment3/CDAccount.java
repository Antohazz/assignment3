package com.meritamerica.assignment3;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CDAccount extends BankAccount{
	
	
	public CDAccount(CDOffering offering, double balance) {
		super.offering = offering;
		super.balance = balance;
		super.accountNumber = MeritBank.getNextAccountNumber();
	}
	
	
	public CDAccount(long accNum, double balance, CDOffering offering, Date date) {
		super.balance = balance;
		super.offering = offering;
		super.openDate = date;
		super.accountNumber = accNum;
		
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
	

	public static CDAccount readFromString(String accountData) throws NumberFormatException{

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
		lastCh = accountData.indexOf(",", firstCh);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date openDate = df.parse(accountData.substring(firstCh, lastCh));
		
		firstCh = lastCh+1;
		int term = Integer.parseInt(accountData.substring(firstCh));
		
		CDOffering of = new CDOffering(term, iRate);
		
		CDAccount cdAccount = new CDAccount(accNum, balance, of, openDate);
		
		return cdAccount;
	}catch(Exception e){
		throw new NumberFormatException();
	}
	}
	
	
	public double futureValue() {
		double futureBalance = getBalance() * Math.pow(1 + getInterestRate(), getTerm());
		return futureBalance;
	}
	
	
	
//	// Outputs account info
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String cdAccInfo = getAccountNumber() + "," + getBalance() + "," + getInterestRate() + "," + df.format(getOpenedOn()) + "," + getTerm();
			
		return cdAccInfo;
	}
	
}

