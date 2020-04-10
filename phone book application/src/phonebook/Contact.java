package phonebook;

import javafx.beans.property.SimpleStringProperty;

public class Contact {

	
		private SimpleStringProperty contactName ; 
		private SimpleStringProperty phoneNumber ; 
		
		public Contact(String name , String number){
			
			this.contactName = new SimpleStringProperty(name) ; 
			this.phoneNumber = new SimpleStringProperty(number);
		}

		
		public void setContactName(String name){
			this.contactName.set(name); 
		}
		
		public void setPhoneNumber(String number){
			this.phoneNumber.set(number); 
		}
		
		public String getContactName(){
			return this.contactName.get() ; 
		}
		
		public String getPhoneNumber(){
			return this.phoneNumber.get() ; 
		}
		
		public String toString(){
			return String.format("Name: %s   Phone.No: %s",  this.getContactName() , this.getPhoneNumber()); 
		}
}
