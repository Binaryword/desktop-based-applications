package phonebook;

import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import message.Message;
import message.MessageBox;

public class MenuController {

	menuGraphic menu ; 
	ContactTable table ; 
	Database database ; 
	 boolean isFound = false  ;
	
	public MenuController(menuGraphic menu , ContactTable table ,  Database db){
		this.table = table ; 
		this.menu =  menu ; 
		this.database = db ; 
		
		activateMenuBarAnimation();
		activateserchTextFieldHandler();
		
	}
	
	public void loadDatabaseContent(){
		try {
			database.getConnection();
			database.createStatement();
			database.executeQuery("select * from Contact"); 
		    table.getItems().addAll(FXCollections.observableArrayList(database.getDatabaseContent() ) );
		    database.closeConnection();
		
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
	}//end of method .......
	
	
	//menu controllers.......
	public void activateMenuBarAnimation(){
		
		Line line = menu.buttonLine ; 
		
			
		menu.contactButton.setOnAction((ActionEvent event)->{ 
			lineAnimation(line , 18 , menu.setContactListLayout());	activateserchTextFieldHandler();
			
		}); 
		
		menu.createButton.setOnAction((ActionEvent event)->{ 
			lineAnimation(line , 150  , menu.setAddNewContactLayout() );
			activateAddButtonHandler();
		});
		
		
		menu.editButton.setOnAction((ActionEvent event)->{ 
			
			lineAnimation(line , 280 , menu.setUpdateContactLayout() );
			activateUpdateHandler();
			activateDeleteHandler();
			
		}); 
		
	}
	
	//animation method..
	public void lineAnimation(Line line , double offsetx , Pane pane){
		
		EventHandler<ActionEvent> onFinish = e->{
			
			menu.setCenterLayout(pane); 
		};
		
		line.setStroke(Color.WHITE); 
		KeyValue value = new KeyValue(line.layoutXProperty() , offsetx , Interpolator.EASE_BOTH);
		KeyFrame frame = new KeyFrame(Duration.millis(200) , onFinish , value); 
		Timeline timeline = new Timeline(frame) ; 
		timeline.play(); 
		
		menu.setCenterLayout(new Pane());
		
	}//ends....
	
	public void activateAddButtonHandler(){
		
		menu.btn_addContact.setOnAction(e->{
			
			String name = menu.txt_contactName.getText() ; 
			String number =  menu.txt_phoneNumber.getText() ; 
			
			if(name.length() <= 14 && number.length() <= 14){
				
				try {
					
					database.getConnection();
					database.createPreparedStatement("insert into Contact(contactName , contactNumber) "
							+ "value(? , ?)" ); 
					database.setInsetPreparedStatement(name, number); 
					database.executePreparedStatement();
					database.saveOperation();
					database.closeConnection();
					
					MessageBox.showAlertBox(new Message("Add Successfully" , "Alert")); 
				} catch (Exception e1) {
					
					MessageBox.showAlertBox(new Message(e.toString() , "Error"));
				}
				
			}
			
		}); 
	}
	
	public void activateserchTextFieldHandler(){
		
		TextField search = menu.searchField ;
		table.getItems().clear();
		loadDatabaseContent(); 
	    table.setEditable(false); 
		
		search.setOnAction(e->{
			
			ObservableList<Contact> lists =  table.getItems() ; 
			System.out.println( lists.size() );
			 
			int i = 0 ; 
			
			 for(Contact list : lists){
	
				 if( ( search.getText().toLowerCase().equals(list.getContactName().toLowerCase()) ) 
						 || ( search.getText().toLowerCase().equals(list.getPhoneNumber()) )   ){
				
				 	isFound = true  ; 
				 	 table.requestFocus();
				 	
				 	break ;
				 }
				 
	
			}//for loops...

				if(isFound){
					 System.out.println("Found");
					 isFound = false ; 
					
					 
				}else{
					
					MessageBox.showAlertBox(new Message("Contact not found" , "Alert")); 
					isFound = false ; 
					
				}//end of if / else
			 
		}); //end of handler
	
		
	}//end of method
	
	
	
	//          UPDATE HANDLER 
	public void activateUpdateHandler(){
		
		table.setEditable(true); 
		
		table.getItems().clear();
		loadDatabaseContent(); 
		System.out.println("program get here");
		
		Button updateButton = this.menu.btn_update  ; 
		
		updateButton.setOnAction(e->{
			System.out.println("hello world");
			
			boolean userOption = MessageBox.showMessageBox(new Message("Save Change ? " , "Update")); 
			
			if(userOption){
				System.out.println("Contact Updated");
				
				for(int i = 0 ; i < table.getItems().size() ; i++)
				System.out.println(table.getItems().get(i).getContactName());
				
				
			}else {
				
				System.out.println("Not updated");
				
			
			}
			
			
		}); 
		
	}//end of method 
	
	
	public void activateDeleteHandler(){
		
		menu.btn_delete.setOnAction(e->{
			
			ObservableList<Contact> allItems , selectedItem ; 
			
			allItems = table.getItems() ; 
			selectedItem = table.getSelectionModel().getSelectedItems(); 
		
			
		
			if(!(selectedItem.isEmpty())){
				
				boolean isYes = MessageBox.showMessageBox(new Message("Are you sure ?" , "Delete"));
				
				if(isYes){
					
					//database   remove from database
				
					try {
						
						database.getConnection();
						database.createPreparedStatement("delete from Contact where contactName = ? ") ;
						
						for(Contact c : selectedItem){
							
							database.setDeletePreparedStatement(c.getContactName());
							System.out.println(c.getContactName());
							database.executePreparedStatement();
							database.saveOperation();
						}
						
					
						database.closeConnection();
						//remove from table also
						selectedItem.forEach(allItems::remove);
						MessageBox.showAlertBox(new Message("Delete Successfully" , "Alert"));
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						MessageBox.showAlertBox(new Message(e1.getMessage() , "Error") );
					} 
					
					
					//looping through selected item.....
					
						
						
					
				}//inner if statement if selection is not empty
				
			}else{
				
				MessageBox.showAlertBox(new Message("Please Select to Delete!" , "Alert")); 
				
			}
			
		});
		
		
		
		
	}//end of methods.......
	
	
}//end of class....
