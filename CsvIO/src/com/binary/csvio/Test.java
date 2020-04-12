package com.binary.csvio;

import java.util.ArrayList;
import java.util.List;

public class Test {

	
	public static void main(String[] args) {
		
		List<Student> student = new ArrayList<>(); 
		student.add(new Student(1 , "akin" , true , 13));
		student.add(new Student(2 , "bola" , false , 13));
		student.add(new Student(3 , "oyin" , true , 13));
		student.add(new Student(4 , "segun" , true , 13));
		student.add(new Student(5 , "ade" , true , 13));
		
		for(Student s: student){
			
			boolean success = WriteToCSV.writeToCSV(s.toCSV(), "student.csv"); 
			
			if(success)
				System.out.println("Succefully.... inserted" + s.toCSV());
		}
		
	}
	
}
