

public class Account{
	
	private int accountNumber ; 
	private double accountBalance ; 
	private int PIN ;
	private String accountName ; 
	
	
	 
	public Account(int accNum , double accBal , int pin , String name ){
		
		accountNumber = accNum ; 
		accountBalance = accBal ; 
		PIN = pin ; 
		accountName = name ; 
		
	}
	
	public void setAccountNumber(int accNum){
		accountNumber = accNum;
	}
	public void setAccountBalance(double accBal){
		accountBalance = accBal;
	}
	public void setPin(int pin){
		PIN = pin;
	}
	public void setAcountName(String name){
		accountName = name ; 
	}
	
	
	public int getAccountNumber(){
		return accountNumber  ; 
	}
	public double getAccountBalance(){
		return accountBalance; 
	}
	public int getPin(){
		return PIN ; 
	}
	public String getAcountName(){
		return accountName  ; 
	}
	
	
	public String toString(){
		return String.format("[ %s %d  %d %.0f ]" , getAcountName() , getAccountNumber() , getPin() , getAccountBalance());
	}
	
	
}