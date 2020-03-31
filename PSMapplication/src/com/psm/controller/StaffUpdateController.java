package com.psm.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.psm.model.Staff;
import com.psm.model.Student;

import javafx.fxml.Initializable;

public class StaffUpdateController implements Initializable{

	private static Staff staff ;
	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}

	public static void initStudent(Staff staf) {


		    	staff = staf ;
		    	System.out.println("Student from update form " + staf.toString() ) ;

	}

}
