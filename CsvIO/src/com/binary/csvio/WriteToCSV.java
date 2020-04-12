package com.binary.csvio;

import java.io.FileWriter;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVWriter;

public class WriteToCSV {
	
	
	public static boolean writeToCSV(String record, String FILE_LOCATION){
		
	     CSVWriter writer = null ;
		try {
			
			writer = new CSVWriter(new FileWriter(FILE_LOCATION));
		    String [] data = record.split(",");
		    writer.writeNext(data);
		    
		    //close the writer
		      writer.close();
		      return true ; 
		} catch (IOException e) {

			e.printStackTrace();
			return false ; 
		}
	         
		
	}
	

}
