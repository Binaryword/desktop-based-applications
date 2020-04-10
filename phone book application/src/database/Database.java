package database;

import java.sql.Connection ; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet ; 
import java.util.ArrayList;

import phonebook.Contact;

public class Database {
	
	private String db_url = "jdbc:mysql://localhost:3306/mysqldata?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";  
	private String db_userName = "root" ; 
    private String db_password = "kinshat1995@gmail.com"; 
    
    Connection connection ; 
    Statement statement ; 
    PreparedStatement preparedStatement  ; 
    ResultSet resultSet ; 
    ArrayList<Contact> contact; 

	public Database(){

		contact = new ArrayList<>(); 
	}
	
	
	public void getConnection() throws SQLException{
		
		connection = DriverManager.getConnection(db_url , db_userName , db_password); 
		connection.setAutoCommit(false); 
		contact.clear();
	}
	
	
	public void createStatement() throws SQLException{
		
		statement = connection.createStatement();
	}
	
	public void executeQuery(String command) throws SQLException{
		
		resultSet = statement.executeQuery(command);
		
	}
	
	public void createPreparedStatement(String string) throws SQLException{
		
		preparedStatement = connection.prepareStatement(string);  
	}
	
	public void setInsetPreparedStatement(String name , String number) throws SQLException{
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, number); 
	}
	
	public void setDeletePreparedStatement(String name) throws SQLException{
		preparedStatement.setString(1, name); 
	}
	
	public void executePreparedStatement() throws SQLException{
		
		preparedStatement.executeUpdate() ; 
	}
	
	public void saveOperation() throws SQLException{
		connection.commit();
	}
	
	public void discardOperation() throws SQLException{
		connection.rollback();
	}
	
	public ArrayList<Contact> getDatabaseContent(){
		
		//contact.clear(); 
		
		try {
			
			while(resultSet.next()){
					
			String name  = resultSet.getString("contactName"); 
			String number = resultSet.getString("contactNumber"); 
			
			contact.add(new Contact(name , number)); 
			
				
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return contact ; 
	}
	
	public void closeConnection() throws SQLException{
		
		if(connection != null)
		connection.close();
	}
}




