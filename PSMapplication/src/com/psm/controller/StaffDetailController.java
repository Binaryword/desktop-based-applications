package com.psm.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.psm.database.StaffDao;
import com.psm.model.Staff;

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

public class StaffDetailController implements Initializable{
		@FXML
	    private AnchorPane root;

	    @FXML
	    private ImageView passport_holder;

	    @FXML
	    private Text txt_othername;

	    @FXML
	    private Text txt_firstname;

	    @FXML
	    private Text txt_gender;

	    @FXML
	    private Text txt_contact;

	    @FXML
	    private Text txt_age;


	    @FXML
	    private Text txt_staff_group;

	    @FXML
	    private Text txt_class_taking;

	    @FXML
	    private Text txt_doe;

	    @FXML
	    private Text txt_dob;

	    @FXML
	    private Text txt_kin_name;

	    @FXML
	    private Text txt_address;

	    @FXML
	    private Text txt_kin_contact;

	    @FXML
	    private JFXButton btn_update;

	    @FXML
	    private JFXButton btn_delete;



	    private static Staff staff = null ;

	    private StaffDao staff_dao = new StaffDao();

	    @Override
		public void initialize(URL location, ResourceBundle resources) {


	    	if(!staff.equals(null))
				initWidget();

		}

	    @FXML
	    void activateDeleteStudent(ActionEvent event) {
	    		
	    	staff_dao.deleteStaff(staff.getId());
	    	System.out.println("Staff deleted successfull");
	    	Stage stage = (Stage)root.getScene().getWindow() ;
	    	stage.close();
	    }

	    @FXML
	    void activateUpdateStudent(ActionEvent event) {


	    	if(staff == null)
	    		return ;


	    	try {

	    		StaffUpdateController.initStudent(staff);
	    		Parent root =  FXMLLoader.load(getClass().getResource("/com/psm/front_design/Update_Staff.fxml"));
	    		Scene scene = new Scene(root);
				Stage stage = (Stage)this.root.getScene().getWindow() ;
				stage.setScene(scene);
				stage.setTitle("Update Form");
				stage.show();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }


	    public void initWidget(){

			//student = stud_dao.getStudent(String.valueOf(student.getId()));

			passport_holder.setImage(new Image(getClass().getResourceAsStream("/com/psm/staff_passports/defaultImage.png")));
			System.out.println("Passport location :  " + staff.getPassport_location());

			if(staff.getPassport_location() != null){

				try{

					String pass_loc = "/com/psm/staff_passports/"+staff.getFirstName().trim()+"_"+staff.getId()+".png";
					passport_holder.setImage(new Image(getClass().getResourceAsStream(pass_loc)));
					staff.setPassport_location(pass_loc);

				}catch(Exception e){

					System.out.println("Error : " + e.getMessage() ) ;
				}


			}



			txt_othername.setText(txt_othername.getText() + " " + staff.getOtherName());
			txt_firstname.setText(txt_firstname.getText() + " " + staff.getFirstName());
			txt_dob.setText(txt_dob.getText() + " " + staff.getDOB());
			txt_doe.setText(txt_doe.getText() + " " + staff.getDOE());
			txt_class_taking.setText(txt_class_taking.getText() + " " + staff.getClassTaking());
			txt_age.setText(txt_age.getText() + " " + staff.getAge());
			txt_address.setText(txt_address.getText() + " " + staff.getAddress());
			txt_contact.setText(txt_contact.getText() + " " + staff.getContact());
			txt_gender.setText(txt_gender.getText() + " " + ((staff.getSex()==true) ? "MALE":"FEMALE"));
			txt_staff_group.setText(txt_staff_group.getText() + " " + staff.getStaffGroup());
			txt_kin_name.setText(txt_kin_name.getText() + " " + staff.getKin().getName());
			txt_kin_contact.setText(txt_kin_contact.getText() + " " + staff.getKin().getContact());



		}

	    public static void initStaff(Staff staf){

			staff = staf ;
			System.out.println(staff.toString());


		}


}
