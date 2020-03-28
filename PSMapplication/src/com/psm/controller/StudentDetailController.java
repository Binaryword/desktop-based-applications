package com.psm.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.psm.database.StudentDao;
import com.psm.model.Student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StudentDetailController implements Initializable{


	@FXML
	private AnchorPane root ;

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

    private StudentDao stud_dao = new StudentDao();


    @Override
	public void initialize(URL location, ResourceBundle resources) {

    	System.out.println("the init method..");

    	if(!student.equals(null))
			initWidget();
	}

    @FXML
    void activateDeleteStudent(ActionEvent event) {


    	stud_dao.deleteStudentRecord(student.getId());
    }

    @FXML
    void activateUpdateStudent(ActionEvent event) {

    	if(student == null)
    		return ;


    	try {

    		StudentUpdateController.initStudent(student);
    		Parent root =  FXMLLoader.load(getClass().getResource("/com/psm/front_design/Update_Student.fxml"));
    		Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Update Form");
			stage.showAndWait();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



    }


	public static void initStudent(Student stud){

		student = stud ;
		System.out.println(student.toString());


	}



	public void initWidget(){

		passport_holder.setImage(new Image(getClass().getResourceAsStream("/com/psm/passports/defaultImage.png")));
		System.out.println("Password location :  " + student.getPassport_location());

		if(student.getPassport_location() != null){

			try{
				
				String pass_loc = "/com/psm/passports/"+student.getFirstName().trim()+"_"+student.getId()+".png"; 
				passport_holder.setImage(new Image(getClass().getResourceAsStream(pass_loc)));
				student.setPassport_location(pass_loc);

			}catch(Exception e){

				System.out.println("Error : " + e.getMessage() ) ;
			}


		}



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


	public void reloadWidget(Student student){


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
