package com.psm.database;

public class DBFactory {
	
	private static StaffDao staff_dao ; 
	private static StudentDao stud_dao ; 
	
	public DBFactory(){
			
	}	
	
	
	public static void connect_database(){
		staff_dao = new StaffDao(); 
		stud_dao = new StudentDao(); 
		
	}
	
	public static StaffDao access_staff(){
		
		return staff_dao ; 
	}
	
	
	public static StudentDao access_student(){
		
		return stud_dao ; 
	}
	
	
	

}
