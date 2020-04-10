
public class Withdraw extends Transaction{
	
		private double amount  ; 
		private double balance; 
		private double newBalance ;  
		
		public Withdraw(){
		
			this.amount = 0.00 ; 
		}	
		public void setAmount(double amount){
			
				this.amount = amount ; 

			
		}
		
		public double getAmount(){
			return amount ; 
		}
		
		public void setBalance(double balance){
			this.balance = balance;
			System.out.println("balance set successfully");
		}
		
		public double getBalance(){
			return balance ; 
		}
		
		public double getNewBalance(){
			return newBalance; 
		}
		
		@Override
		public void activateTransaction(){
			System.out.println(balance-amount);
			newBalance = balance - amount ; 
			
			System.out.println("Transaction successfully");
			System.out.println(newBalance);
		}
		
		

}