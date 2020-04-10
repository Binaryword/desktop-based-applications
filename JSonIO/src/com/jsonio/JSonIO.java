package com.jsonio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;


import sun.security.action.GetLongAction;

public class JSonIO {


	private Object object ;
	private String data ;
	private Gson gson  ;
	private String file_location ;


	public JSonIO(){

	}

	public JSonIO(Object object , String data , String file_location){


			this.object = object  ;
			this.data = data ;
			this.file_location = file_location ;
	}





	public void writeToFile(Object object , String file_location){




		gson = new Gson();
		String tostring = gson.toJson(object);
		write(tostring , file_location);


	}

	private void write(String data , String location){

		Formatter formater = null ;

		System.out.println("adding content to file : " + data);

		try {

			formater = new Formatter(new File(location));
			formater.format("%s", data) ;
			System.out.println("content added to file successfully : " + data);

		} catch (FileNotFoundException e) {

			System.out.println(e.getMessage());

		}finally{

			if(formater != null)
				formater.close();


		}

	}// end of method....


	public Object readFromFile(Object object){

		Object ob = null ;
		Gson gson = new Gson() ;

		try {

			ob = gson.fromJson(new FileReader(getFile_location()) , object.getClass());
			System.out.println("file read successfully");
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {

			e.printStackTrace();
		}

		return ob ;
	}

	public Object getObject() {
		return object;
	}



	public void setObject(Object object) {
		this.object = object;
	}



	public String getData() {
		return data;
	}



	public void setData(String data) {
		this.data = data;
	}



	public Gson getGson() {
		return gson;
	}



	public void setGson(Gson gson) {
		this.gson = gson;
	}



	public String getFile_location() {
		return file_location;
	}



	public void setFile_location(String file_location) {
		this.file_location = file_location;
	}



}
