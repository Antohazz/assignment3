package com.meritamerica.assignment3;

import java.lang.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class MeritBank {
	
	public static long accountNumber = 1;
	public static AccountHolder[] accountHolders = new AccountHolder[1];
	public static CDOffering[] cdOfferings = new CDOffering[0];
	public static CDOffering offering;

	
// Read DATABASE from FILE
	static boolean readFromFile(String fileName) {
		
		accountHolders = new AccountHolder[1];
		cdOfferings = new CDOffering[0];
		
		try {
			BufferedReader rd = new BufferedReader(new FileReader(fileName));
				
				MeritBank.setNextAccountNumber(Long.parseLong(rd.readLine()));
				

// Read CDOffers
				int numOfCDOfferings = Integer.parseInt(rd.readLine());
				int n = 0;
				cdOfferings = new CDOffering[numOfCDOfferings];
				
				while (numOfCDOfferings > 0) {
					cdOfferings[n] = CDOffering.readFromString(rd.readLine());
					n++;
					numOfCDOfferings--;
				}
				
//Read Account Holders
				int numOfAccountHolders = Integer.parseInt(rd.readLine());
				
					while (numOfAccountHolders > 0) {
					AccountHolder ac = AccountHolder.readFromString(rd.readLine());
					addAccountHolder(ac);

//read checking accounts
					int numOfCheckAcc = Integer.parseInt(rd.readLine());
					while (numOfCheckAcc > 0){
						ac.addCheckingAccount(CheckingAccount.readFromString(rd.readLine()));	
						numOfCheckAcc--;
					}
					
//read savings accounts	
					int numOfSaveAcc = Integer.parseInt(rd.readLine());
					while (numOfSaveAcc > 0) {
						ac.addSavingsAccount(SavingsAccount.readFromString(rd.readLine()));
						numOfSaveAcc--;
					}
//read CD accounts						
					int numOfCDAcc = Integer.parseInt(rd.readLine());
					while (numOfCDAcc > 0){
						ac.addCDAccount(CDAccount.readFromString(rd.readLine()));
						numOfCDAcc--;
					}
					
				numOfAccountHolders--;
				}//Read Account Holders

		
			rd.close();
			
			sortAccountHolders();
			
			System.out.println(AccountHolder.writeToString());
						
			return true;
			
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}//catch
		
		
	}//readFromFile
	
	
	
// Write DATABASE to FILE
	static boolean writeToFile(String fileName) {
		
		String outp = getNextAccountNumber() + "\n";
		
		for (int i = 0; i < cdOfferings.length; i++) {
			if (cdOfferings[i] != null) {
				outp += cdOfferings[i].toString();
			}
		}
		
		outp += accountHolders.length + "\n";
		for (int i = 0; i < accountHolders.length; i++) {
			if (accountHolders[i] != null) {
				outp += accountHolders[i].toStringForFile();
			}
		}
		
		
		System.out.println(outp);
		PrintWriter out;
		try {
			out = new PrintWriter("BankOutput.txt");
			out.println(outp);
			out.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	static AccountHolder[] sortAccountHolders() {
		int l = 0;
		while (accountHolders[l] != null) {
			l++;			
		}
		Arrays.sort(accountHolders, 0, l);

		return accountHolders;
	}
	
	static void setNextAccountNumber(long nextAccountNumber) {
		accountNumber = nextAccountNumber;
	}
	
	public static long getNextAccountNumber() {
		return accountNumber++;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void addAccountHolder(AccountHolder accountHolder) {

		
		for (int i = 0; i < accountHolders.length; i++) {
			if (accountHolders[i] == null) {
				accountHolders[i] = accountHolder;
				
				//Extending Array if full
				if(i == accountHolders.length - 1) {
					AccountHolder[] temp = new AccountHolder[accountHolders.length * 2];
					for (int j = 0; j < accountHolders.length; j++) {
						temp[j] = accountHolders[j];
					}
					accountHolders = temp;		
				}
								
				break;
			}
		}

	}
	
	public static AccountHolder[] getAccountHolders() {
		return accountHolders;
	}
	
	public static CDOffering[] getCDOfferings() {
		return cdOfferings;
	}


	public static CDOffering getBestCDOffering(double depositAmount) {
		if (cdOfferings != null) {
		double val = 0.0;
		int j = 0;
		for (int i = 0; i < cdOfferings.length; i++) {
			if((double)futureValue(depositAmount, cdOfferings[i].getInterestRate(), cdOfferings[i].getTerm()) > val) {
				val = (double)futureValue(depositAmount, cdOfferings[i].getInterestRate(), cdOfferings[i].getTerm());
				j = i;
				
			}
		
		}
		return cdOfferings[j];
		}else return null;
	}

	public static CDOffering getSecondBestCDOffering(double depositAmount) {
		if (cdOfferings != null) {
		double val = 0.0;
		int j = 0;
		int k = 0;
		for (int i = 0; i < cdOfferings.length; i++) {
			if((double)futureValue(depositAmount, cdOfferings[i].getInterestRate(), cdOfferings[i].getTerm()) > val) {
				val = (double)futureValue(depositAmount, cdOfferings[i].getInterestRate(), cdOfferings[i].getTerm());
				j = i;
				k = j;
			}
		
		}
		
		return cdOfferings[k];
		}else return null;
	}
	
	public static void clearCDOfferings() {

		cdOfferings = null;
	}
	
	public static void setCDOfferings(CDOffering[] offerings) {
		
		cdOfferings = offerings;
	}
	
	
	public static double totalBalances() {
		double total = 0.0;
		for (int i = 0; i < accountHolders.length; i++) {
			if (accountHolders[i] != null)
			total += accountHolders[i].getCombinedBalance();
			else break;
		}
		
		return total;
	}
	
	public static double futureValue(double presentValue, double interestRate, int term) {
		double futureValue = presentValue * Math.pow(1 + interestRate, term);
		return futureValue;
	}



}



