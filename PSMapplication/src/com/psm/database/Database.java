package com.psm.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.psm.database.Database;

public class Database {

		private Connection connection = null;
		private static Database database = new Database();

		private Database() {

		}

		public static Database getInstance() {
			System.out.println("geting db instance.....");
			return database;
		}

		public Connection getConnection() {

			if(connection == null)
			{
				try {
					Class.forName("org.sqlite.JDBC");
					try {
						connection = DriverManager.getConnection("jdbc:sqlite:src/Database/database.db");
						System.out.println("Connecting to database.........");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}		
			
			return connection ; 
		}//end of method 
		
		
		public void closeConnection(){
			try {
				
				if(connection != null )
				connection.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		}//end of method 

}// end of class..
