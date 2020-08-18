package com.eq.model;

import java.util.Comparator;
import java.util.List;

public class Relevance implements Comparator<Relevance>{
	
	private double digit = 0 ; 
	private List<String> document  ; 
	
	public Relevance() {}

	public Relevance(double digit, List<String> document) {
		super();
		this.digit = digit;
		this.document = document;
	}

	public double getDigit() {
		return digit;
	}
	
	public double getDigitPercent(){
		
		return (getDigit()*100); 
	}

	public void setDigit(double digit) {
		this.digit = digit;
	}
	

	public List<String> getDocument() {
		return document;
	}

	public void setDocument(List<String> document) {
		this.document = document;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(digit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Relevance other = (Relevance) obj;
		if (Double.doubleToLongBits(digit) != Double.doubleToLongBits(other.digit))
			return false;
		return true;
	}

	public String toString() {
		
		return String.format("USER QUERY IS (%f percent) RELEVANCE TO %s",  getDigit()*100 , getDocument()) ; 
	}
	
	public String combineDocument() {
		
		StringBuilder build = new StringBuilder();
		for(String string : getDocument()) {
			build.append(string); 
		}
		
		return build.toString(); 
	}


	@Override
	public int compare(Relevance o1, Relevance o2) {
		// TODO Auto-generated method stub
		
		if(o1.getDigitPercent() > o2.getDigitPercent())
			return  (int)o1.getDigitPercent() ; 
		else 
			return (int)o2.getDigitPercent();
	}

}
