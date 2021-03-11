package com.meritamerica.assignment3;




public class CDOffering {

	private double interestRate;
	private int term;


	public CDOffering(int term, double interestRate) {
		this.term = term;
		this.interestRate = interestRate;

	}
	
	public static CDOffering readFromString(String cdOfferingDataString) throws NumberFormatException {
		int firstCh = 0;
		int lastCh = cdOfferingDataString.indexOf(",");
		int t = Integer.parseInt(cdOfferingDataString.substring(firstCh, lastCh));
		firstCh = lastCh + 1;
		double ir = Double.parseDouble(cdOfferingDataString.substring(firstCh));
		CDOffering offering = new CDOffering(t, ir);
		return offering;
	}
	
	
	public int getTerm() {
		return term;
	}
	
	

	public double getInterestRate() {
		return interestRate;
	}
	
//	// Outputs account info
	public String toString() {
		String info = "CD offer Interest Rate: " + getInterestRate() + "/n"+
					"CD offer term years: "+ getTerm();
			
		return info;
	}
	
	
}


