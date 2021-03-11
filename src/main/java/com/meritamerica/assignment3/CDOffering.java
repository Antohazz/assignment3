package com.meritamerica.assignment3;

public class CDOffering {

	private double interestRate;
	private int term;


	public CDOffering(int term, double interestRate) {
		this.term = term;
		this.interestRate = interestRate;

	}
	
	public static CDOffering readFromString(String cdOfferingDataString) throws NumberFormatException {
		try {
		int firstCh = 0;
		int lastCh = cdOfferingDataString.indexOf(",");
		int t = Integer.parseInt(cdOfferingDataString.substring(firstCh, lastCh));
		firstCh = lastCh + 1;
		double ir = Double.parseDouble(cdOfferingDataString.substring(firstCh));
		CDOffering offering = new CDOffering(t, ir);
		return offering;
		}catch(Exception e){
			throw new NumberFormatException();
		}
	}
	
	
	public int getTerm() {
		return term;
	}
	
	

	public double getInterestRate() {
		return interestRate;
	}
	
//	// Outputs account info
	public String toString() {
		String info = getTerm()+ "," + getInterestRate() + "\n";
			
		return info;
	}
	
	
}


