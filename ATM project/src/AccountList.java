
import java.util.*; 
public class AccountList{
	
	Map<Integer , Account> accountList ;  
	Account myaccount ; 
	private int Pin = 0000 ; 
	boolean answer = true; 
	private int accountNumber ; 
	
	public AccountList(){
		accountList = new HashMap<Integer , Account>() ; 
	Account	akinAccount = new Account(33333 , 2000 , 1995 , "akin");
	Account bolaAccount = new Account(11111 , 5000 , 1998 , "bola");
	Account	segunAccount = new Account(22222 , 10000 , 1978 , "segun");
		
	accountList.put(33333 , akinAccount);	
	accountList.put(11111 , bolaAccount);	
	accountList.put(22222 , segunAccount);	
	
			
	}
	
	
	public Account getAccount(){
		
		Set<Integer>  keys = accountList.keySet() ; 
		
		for(Integer key : keys){
			
			if(key == getAccountNumber())
		    myaccount = accountList.get(key);
			
			
		}
		
		return myaccount ; 
		
	}//end of method ....................
	
	
	
	public boolean validatePIn(){
		
		Account account = getAccount(); 
		if(account.getPin() == getPin()) 
			answer = true ; 
		else 
			answer = false ; 
		
		return answer ; 
	}
	
	public void setPin(int pin){
		
		Pin = pin ; 
	}
	
	
	public int getPin(){
		return  Pin ; 
	}
	
	
	
	public void setAccountNumber(int accountNumber){
		this.accountNumber = accountNumber ; 
	}
	
	public int getAccountNumber(){
		return accountNumber ; 
	}
}