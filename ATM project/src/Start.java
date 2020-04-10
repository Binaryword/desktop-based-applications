import java.util.Scanner; 

public class Start{
	
	public static void main(String[] args){
		AccountList acc = new AccountList(); 
		Transaction withdraw = new Withdraw(); 
		Scanner input = new Scanner(System.in);
		
		
		System.out.println("Enter account Number");
		int accNum  = input.nextInt() ; 
		
	 
		     acc.setAccountNumber(accNum);
			
			
			Account account = acc.getAccount();
		
	if(account != null){
		System.out.println("Account found........................... ");
		System.out.println(acc.getAccount()); 
		
		System.out.println(account.getPin());
		System.out.println(account.getAcountName());
		
		System.out.println("\n\n\n");
		
		System.out.println("Enter Pin");
		int pin = input.nextInt();
		
		
		acc.setPin(pin);
		System.out.printf(" Pin match account number   ? %b " , acc.validatePIn()); 
		
			}else{
				System.out.println("Error Account not found ");
			}
	
	//===================================================================
	//TO PERFORM TRANSACTION ===============//
	
		System.out.println("Transaction in progress......");
		System.out.println("Enter amount to withdraw");
		int amount = input.nextInt(); 

		withdraw.setAmount(amount);

		//set the balance from data base ; 
		System.out.println("your current account balance is === " + account.getAccountBalance());
		withdraw.setBalance(account.getAccountBalance());
		System.out.println(" ==" + withdraw.getBalance() + " - " + withdraw.getAmount());
		withdraw.activateTransaction();

		System.out.println("your new account balance is === " + withdraw.getNewBalance());
	
		
	}
}