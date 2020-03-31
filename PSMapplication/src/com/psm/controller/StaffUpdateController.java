package com.psm.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.psm.model.Staff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class StaffUpdateController implements Initializable{


	@FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txt_firstname;

    @FXML
    private JFXTextField txt_address;

    @FXML
    private JFXTextField txt_age;

    @FXML
    private JFXDatePicker txt_dob;

    @FXML
    private JFXDatePicker txt_doe;

    @FXML
    private JFXComboBox<String> combo_staff_group;

    @FXML
    private JFXComboBox<String> combo_class_taking;

    @FXML
    private JFXRadioButton radio_male;

    @FXML
    private ToggleGroup sex_group;

    @FXML
    private JFXRadioButton radio_female;

    @FXML
    private JFXTextField txt_kin_name;

    @FXML
    private JFXTextField txt_kin_address;

    @FXML
    private JFXTextField txt_kin_contact;

    @FXML
    private JFXButton btn_select_image;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXButton btn_cancel;


	private static Staff staff ;
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//set up necessary widget..
    	ObservableList<String> classList = FXCollections.observableArrayList("NRY1" , "NRY2" , "NRY3" , "PRY1" , "PRY2" , "PRY3" , "PRY4" , "PRY5") ;
    	combo_class_taking.getItems().addAll(classList);


    	ObservableList<String>  staffGroupList= FXCollections.observableArrayList("TEACHING_STAFF" , "NON_TEACHING_STAFF") ;
    	combo_staff_group.getItems().addAll(staffGroupList);



    	txt_dob.setValue(LocalDate.parse("2000-01-01"));
    	txt_doe.setValue(LocalDate.parse("2000-01-01"));
	}

	public static void initStudent(Staff staf) {


		  staff = staf ;
		  System.out.println("Student from update form " + staf.toString() ) ;

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

	    @FXML
	    void txt_othername(ActionEvent event) {

	    }


}
