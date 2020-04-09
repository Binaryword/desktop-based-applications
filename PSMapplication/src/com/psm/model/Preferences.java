package com.psm.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class Preferences{

	private String admin_password ;
	private String admin_username ;

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
	
	

	public static void initConfig(){

		FileWriter writer = null ;

		try {

			Preferences preferences = new Preferences();
			System.out.println(preferences);
			Gson gson = new Gson();
			writer = new FileWriter(FILE_LOCATION);
			writer.write(preferences.toString());
			gson.toJson(preferences, writer);

		} catch (IOException e) {
			System.out.println("Error : " + e.getMessage());
			e.printStackTrace();
		}


	}

}
