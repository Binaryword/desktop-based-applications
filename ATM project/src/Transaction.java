
public abstract class Transaction{
	
        private double amount ; 
		private double balance; 
		private double newBalance ;
		
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
		
		
		public abstract void activateTransaction();

}

