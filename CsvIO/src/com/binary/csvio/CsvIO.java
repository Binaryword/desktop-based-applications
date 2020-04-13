package com.binary.csvio;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

public class CsvIO {


	public static boolean writeToCSV(String[] record, String FILE_LOCATION, boolean append){

	     CSVWriter writer = null ;
		try {

			writer = new CSVWriter(new FileWriter(FILE_LOCATION , append));
		   // String [] data = record
		    writer.writeNext(record);

		    //close the writer
		      writer.close();
		      return true ;
		} catch (IOException e) {

			e.printStackTrace();
			return false ;
		}


	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	public static List<Student> readFromCSV(String[] colums , String location){

		 CsvToBean csv = new CsvToBean();
		 List<Student> list = new ArrayList<>() ;

	   //   String csvFilename = "data.csv";
	      CSVReader csvReader = null;
		try {

			csvReader = new CSVReader(new FileReader(location));
			//Set column mapping strategy
		   List<Student> l = csv.parse(setColumMapping(colums), csvReader);


		      for (Student object : l) {
		          Student student = object;
		          list.add(student);
		          System.out.println(student);
		      }

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	      return list ;


	}





	 @SuppressWarnings({"rawtypes", "unchecked"})
	   private static ColumnPositionMappingStrategy setColumMapping(String[] colum)
	   {
		 ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
	      strategy.setType(Student.class);
	      String[] columns = new String[] {"id", "name" , "sex" , "age" }; 
	      strategy.setColumnMapping(columns);
	      return strategy;
	   }

}
