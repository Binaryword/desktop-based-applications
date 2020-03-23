package com.psm.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.psm.model.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;

public class StudentUpdateController implements Initializable{


	  @FXML
	    private JFXTextField txt_name;

	    @FXML
	    private JFXTextField txt_otherName;

	    @FXML
	    private JFXTextField txt_address;

	    @FXML
	    private JFXTextField txt_age;

	    @FXML
	    private JFXDatePicker dob_calender;

	    @FXML
	    private JFXComboBox<String> combo_class;

	    @FXML
	    private JFXComboBox<String> combo_payment_status;

	    @FXML
	    private JFXRadioButton radio_male;

	    @FXML
	    private ToggleGroup Sex;

	    @FXML
	    private JFXRadioButton radio_female;

	    @FXML
	    private JFXTextField txt_family_name;

	    @FXML
	    private JFXTextField txt_parent_address;

	    @FXML
	    private JFXTextField txt_parent_contact;

	    @FXML
	    private JFXButton btn_select_image;

	    @FXML
	    private JFXButton btn_update;

	    @FXML
	    private JFXButton btn_cancel;

	    private static Student student ;

	    @Override
		public void initialize(URL location, ResourceBundle resources) {

	    	//set up necessary widget..
	    	ObservableList<String> classList = FXCollections.observableArrayList("NRY1" , "NRY2" , "NRY3" , "PRY1" , "PRY2" , "PRY3" , "PRY4" , "PRY5") ;
	    	combo_class.getItems().addAll(classList);


		}

	    @FXML
	    void activate_Image_selection(ActionEvent event) {

	    }

	    @FXML
	    void activate_update(ActionEvent event) {

	    }

	    @FXML
	    void active_close_window(ActionEvent event) {

	    }



	    public static void initStudent(Student stud){

	    	student = stud ;
	    }

	    public void initWidget(){


	    }


}
