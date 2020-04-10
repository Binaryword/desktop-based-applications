package phonebook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.Reflection;

public class ContactTable extends TableView<Contact>{
 
		
		@SuppressWarnings("unchecked")
		public ContactTable(){
			
			this.setPadding(new Insets(2, 0 , 0 , 0));
			this.getColumns().addAll(nameColumn() , numberColumn()) ; 
			
			this.setEditable(false); 
			
		
		}
		
		
		
		public TableColumn<Contact , String> nameColumn(){
			
			TableColumn<Contact , String>  nameCol = new TableColumn<>("Contact Name");
			nameCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
			nameCol.setMinWidth(198);
			nameCol.setMaxWidth(198); 
			nameCol.setEditable(true);
			
			nameCol.setCellFactory(TextFieldTableCell.<Contact>forTableColumn()); 
			nameCol.setOnEditCommit((CellEditEvent<Contact , String> t)->{
				
				 ((Contact)t.getTableView().getItems().get(t.getTablePosition().getRow())).setContactName(t.getNewValue());
			});
			
			return nameCol ; 
		}
		
		
		public TableColumn<Contact , String> numberColumn(){
			
			TableColumn<Contact , String> numberCol = new TableColumn<>("Phone No.");
			numberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
			numberCol.setMinWidth(197); 
			numberCol.setMaxWidth(197); 
			
			numberCol.setCellFactory(TextFieldTableCell.<Contact>forTableColumn());
			numberCol.setOnEditCommit((CellEditEvent<Contact , String> t)->{
				((Contact)t.getTableView().getItems().get(t.getTablePosition().getRow())).setPhoneNumber(t.getNewValue()); 
			});
			
			
			
			return numberCol ; 
			
		}
		
		
		public ObservableList<Contact> addTableItems(){
			
			Contact c1 = new Contact("samuel" , "07023242756");
			Contact c2 = new Contact("bolaji" , "08134543453");
			Contact c3 = new Contact("kamaldeen" ,"09034328793");
			Contact c4 = new Contact("salamat" , "08145338923");
			Contact c5 = new Contact("muideen" , "07089883245");
			
		ObservableList<Contact> list = FXCollections.observableArrayList(c1 , c2 , c3 , c4 , c5 ) ; 	
			
		return list ; 
		
		}
		
		
		
		
}
