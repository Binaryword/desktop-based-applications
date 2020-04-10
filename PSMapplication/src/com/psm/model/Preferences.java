package com.psm.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class Preferences{

	private String admin_password ;
	private String admin_username ;
	static Gson gson = null ;

	private static final String FILE_LOCATION = "setting.txt" ;

	public Preferences(){

		admin_username = "admin" ;
		admin_password = "admin" ;

	}


	public String getAdmin_password() {
		return admin_password;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}

	public String getAdmin_username() {
		return admin_username;
	}

	public void setAdmin_username(String admin_username) {
		this.admin_username = admin_username ;
	}




	@Override
	public String toString() {
		return "Preferences [admin_password=" + admin_password + ", admin_username=" + admin_username + "]";
	}


	public static void initConfig(){

		FileWriter writer = null ;

		try {

			Preferences preferences = new Preferences();
			System.out.println(preferences);
			gson = new Gson();
			writer = new FileWriter(FILE_LOCATION);
			writer.write(preferences.toString());
			String data = gson.toJson(preferences);
			writeToFile(data);

		} catch (IOException e) {
			System.out.println("Error : " + e.getMessage());
			e.printStackTrace();
		}


	}


	private static void writeToFile(String data){

		Formatter formater = null ;

			System.out.println("adding content to file : " + data);

			try {

				formater = new Formatter(new File(FILE_LOCATION));
				formater.format("%s", data) ;
				System.out.println("content added to file successfully : " + data);

			} catch (FileNotFoundException e) {

				System.out.println(e.getMessage());

			}finally{

				if(formater != null)
					formater.close();


			}

	}


	public static Preferences getConfig() {

		Preferences preferences = null;

		try {

			preferences = gson.fromJson(new FileReader(FILE_LOCATION) , Preferences.class );

		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {

			System.out.println(e.getMessage());
			initConfig();
		}

		//System.out.println("FROM JSON : " + preferences.toString());

		return preferences ;

	}

}
