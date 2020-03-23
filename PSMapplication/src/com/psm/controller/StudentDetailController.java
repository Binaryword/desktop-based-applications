package com.psm.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.psm.model.Student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class StudentDetailController implements Initializable{

	@FXML
    private ImageView passport_holder;

    @FXML
    private Text txt_name;

    @FXML
    private Text txt_firstname;

    @FXML
    private Text txt_dob;

    @FXML
    private Text txt_class;

    @FXML
    private Text txt_age;

    @FXML
    private Text txt_address;

    @FXML
    private Text txt_paymentStatus;

    @FXML
    private Text txt_gender;

    @FXML
    private Text txt_aced_performance;

    @FXML
    private Text txt_parent_name;

    @FXML
    private Text txt_parent_contact;

    @FXML
    private Text txt_parent_address;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXButton btn_delete;

    private static Student student = null ;



    @Override
	public void initialize(URL location, ResourceBundle resources) {

    	System.out.println("the init method..");

    	if(!student.equals(null))
			initWidget();
	}

    @FXML
    void activateDeleteStudent(ActionEvent event) {

    }

    @FXML
    void activateUpdateStudent(ActionEvent event) {
    	
    	

    }


	public static void initStudent(Student stud){

		student = stud ;
		System.out.println(student.toString());
		

	}



	public void initWidget(){

		txt_name.setText(txt_name.getText() + " " + student.getOtherName());
		txt_firstname.setText(txt_firstname.getText() + " " + student.getFirstName());
		txt_dob.setText(txt_dob.getText() + " " + student.getDOB());
		txt_class.setText(txt_class.getText() + " " + student.getStud_class());
		txt_age.setText(txt_age.getText() + " " + student.getAge());
		txt_address.setText(txt_address.getText() + " " + student.getAddress());
		txt_paymentStatus.setText(txt_paymentStatus.getText() + " " + student.getPaymentStatus());
		txt_gender.setText(txt_gender.getText() + " " + ((student.getSex()==true) ? "MALE":"FEMALE"));
		txt_aced_performance.setText(txt_aced_performance.getText() + " " + "GOOD");
		txt_parent_name.setText(txt_parent_name.getText() + " " + student.getParent().getFamily_name());
		txt_parent_address.setText(txt_parent_address.getText() + " " + student.getParent().getFamily_address());
		txt_parent_contact.setText(txt_parent_contact.getText() + " " + student.getParent().getFamily_contact());


	}








}
