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

    private Student student = null ;



    @Override
	public void initialize(URL location, ResourceBundle resources) {


	}

    @FXML
    void activateDeleteStudent(ActionEvent event) {

    }

    @FXML
    void activateUpdateStudent(ActionEvent event) {

    }


	public void initStudent(Student stud){
		
		this.student = stud ;
		System.out.println(student.toString());
	}

}
