package com.psm.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.psm.database.StaffDao;
import com.psm.model.ChangeDirectory;
import com.psm.model.NextOfKin;
import com.psm.model.Staff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class AddStaffController implements Initializable{

	 @FXML
	    private AnchorPane root;

	 @FXML
	    private JFXTextField txt_firstname;

	 @FXML
	    private JFXTextField txt_id;

	 @FXML
	    private JFXTextField txt_othername;

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
	    private ToggleGroup toogle_sex;

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
	    private JFXButton btn_submit;

	    @FXML
	    private JFXButton btn_clear;

	    private Staff staff ;

	    private File file ;

	    private StaffDao staff_dao = new StaffDao();

	    @Override
		public void initialize(URL location, ResourceBundle resources) {

	    	//set up necessary widget..
	     	ObservableList<String> classList = FXCollections.observableArrayList("NRY1" , "NRY2" , "NRY3" , "PRY1" , "PRY2" , "PRY3" , "PRY4" , "PRY5") ;
	    	combo_class_taking.getItems().addAll(classList);


	    	ObservableList<String>  staffGroupList= FXCollections.observableArrayList("TEACHING_STAFF" , "NON_TEACHING_STAFF") ;
	    	combo_staff_group.getItems().addAll(staffGroupList);



//	    	txt_dob.setValue(LocalDate.parse("2000-01-01"));
//	    	txt_doe.setValue(LocalDate.parse("2000-01-01"));

		}

	    @FXML
	    void activateStaffClear(ActionEvent event) {

	    }

	    @FXML
	    void activate_AddStaff(ActionEvent event) {

	    	int id = Integer.parseInt(txt_id.getText());
	    	String name = txt_firstname.getText() ;
	    	String othername = txt_othername.getText();
	    	String address = txt_address.getText();
	    	int age = Integer.parseInt(txt_age.getText().trim());
	    	String dob = txt_dob.getValue().toString() ;
	    	String doe = txt_doe.getValue().toString() ;
	    	String staff_group = combo_staff_group.getValue().toUpperCase() ;
	    	String class_taking = combo_class_taking.getValue().toUpperCase() ;


	    	boolean sex  ;

	    	if(radio_male.isSelected())
	    		sex = true ;
	    	else
	    		sex = false ;

	    	String kin_name = txt_kin_name.getText() ;
	    	String kin_contact = txt_kin_contact.getText() ;

	    	Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
	    	System.out.println(path.toString() + "\\src\\com\\psm\\passports\\new_image.png");
	    	String passport_path = path.toString() + "\\src\\com\\psm\\staff_passports\\" + name+"_"+id+".png" ;
	    	String pass_loc = "/com/psm/passports/"+name+"_"+id+".png";

	    	System.out.println(passport_path);


	    	try {

				ChangeDirectory.copy(file , new File(passport_path));

			} catch (IOException e) {

				e.printStackTrace();
			}

	    staff = new Staff(id, age, name, othername, address, address , sex , doe , dob , staff_group.toUpperCase() , class_taking.toUpperCase() , new NextOfKin(kin_name , kin_contact));
	    staff.setPassport_location(pass_loc);
	    addStaffRecord(staff);

	    }

	    private void addStaffRecord(Staff staff2) {


	    	boolean status = staff_dao.insertStaff(staff);

			if(status)
			{

				System.out.println("staff added successfully") ;
				Stage stage = (Stage) root.getScene().getWindow() ;
				stage.close();

			}else{

				initWidget(staff);
			}
		}

		private void initWidget(Staff staff2) {

			txt_id.setText(txt_id.getText());
	    	txt_firstname.setText(txt_firstname.getText()) ;
	    	txt_othername.setText(txt_othername.getText());
	    	txt_address.setText(txt_address.getText());
	    	txt_age.setText(txt_age.getText().trim());
	    	txt_dob.setValue(LocalDate.parse(txt_dob.getValue().toString())) ;
	    	txt_dob.setValue(LocalDate.parse(txt_dob.getValue().toString())) ;
	    	combo_staff_group.setValue(combo_staff_group.getValue().toUpperCase()) ;
	    	combo_class_taking.setValue(combo_class_taking.getValue().toUpperCase()) ;


	    	boolean sex = true ;

	    	if(radio_male.isSelected())
	    		sex = true ;
	    	else
	    		sex = false ;

	    	txt_kin_name.setText(txt_kin_name.getText()) ;
	    	txt_kin_contact.setText(txt_kin_contact.getText()) ;


		}

		@FXML
	    void activate_Image_selection(ActionEvent event) {

	    	FileChooser filechooser = new FileChooser();
	    	filechooser.setInitialDirectory((new File("C:\\")));
	    	filechooser.setInitialFileName("image");
	    	filechooser.getExtensionFilters().addAll(new ExtensionFilter("png" , "*.png")
	    			, new ExtensionFilter("jpg", "*.jpg"));

	    	file = filechooser.showOpenDialog(new Stage());

	    	if(file == null)
	    		return ;

	    }




}
