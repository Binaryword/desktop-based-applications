package com.psm.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
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

public class StaffUpdateController implements Initializable{


	@FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txt_firstname;

    @FXML
    private JFXTextField  txt_othername ;

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
	private StaffDao staffDao = new StaffDao() ;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//set up necessary widget..
     	ObservableList<String> classList = FXCollections.observableArrayList("NRY1" , "NRY2" , "NRY3" , "PRY1" , "PRY2" , "PRY3" , "PRY4" , "PRY5") ;
    	combo_class_taking.getItems().addAll(classList);


    	ObservableList<String>  staffGroupList= FXCollections.observableArrayList("TEACHING_STAFF" , "NON_TEACHING_STAFF") ;
    	combo_staff_group.getItems().addAll(staffGroupList);



    	txt_dob.setValue(LocalDate.parse("2000-01-01"));
    	txt_doe.setValue(LocalDate.parse("2000-01-01"));

    	// initialize the Gui widget....
    	initWidget();
	}

	public static void initStudent(Staff staf) {


		  staff = staf ;
		  System.out.println("Student from update form " + staf.toString() ) ;

	}


	 	@FXML
	    void activate_Image_selection(ActionEvent event) {

	 		FileChooser filechooser = new FileChooser();
	    	filechooser.setInitialDirectory((new File("C:\\")));
	    	filechooser.setInitialFileName("image");
	    	filechooser.getExtensionFilters().addAll(new ExtensionFilter("png" , "*.png")
	    			, new ExtensionFilter("jpg", "*.jpg"));

	    	File file = filechooser.showOpenDialog(new Stage());

	    	if(file == null)
	    		return ;


	    	Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
	    	System.out.println(path.toString() + "\\src\\com\\psm\\staff_passports\\new_image.png");
	    	String passport_path = path.toString() + "\\src\\com\\psm\\staff_passports\\" + staff.getFirstName()+"_"+staff.getId()+".png" ;
	    	System.out.println(passport_path);


	    	try {

				ChangeDirectory.copy(file , new File(passport_path));
				staff.setPassport_location(passport_path);

			} catch (IOException e) {

				e.printStackTrace();
			}


	    }

	    @FXML
	    void activate_update(ActionEvent event) {

	    	int id = staff.getId() ;
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

	    	String pass_loc = "/com/psm/staff_passports/"+name+"_"+id+".png";

	    staff = new Staff(id, age, name, othername, address, address , sex , doe , dob , staff_group.toUpperCase() , class_taking.toUpperCase() , new NextOfKin(kin_name , kin_contact));
	    //staff = new Staff(id, age, name, othername, address, dob , pay_status.toUpperCase() , stud_class.toUpperCase() , sex , new Parent(famName , famContact , famAddress));
	    staff.setPassport_location(pass_loc);
	    updateStaffRecord(staff);

	    }




	    public void initWidget(){

	    	txt_firstname.setText(staff.getFirstName());
	    	txt_othername.setText(staff.getOtherName());
	    	txt_address.setText(staff.getAddress());
	    	txt_age.setText(String.valueOf(staff.getAge()));
	    	txt_dob.setValue(LocalDate.parse(staff.getDOB()));
	    	txt_doe.setValue(LocalDate.parse(staff.getDOE()));
	    	combo_staff_group.setValue(staff.getStaffGroup().toUpperCase());
	    	combo_class_taking.setValue(staff.getClassTaking().toUpperCase());


	    	if(staff.getSex() == true )
	    		radio_male.setSelected(true);
	    	else
	    		radio_female.setSelected(true);


	    	txt_kin_name.setText(staff.getKin().getName());
	    	txt_kin_contact.setText(staff.getKin().getContact());

	    }




	    private void updateStaffRecord(Staff staff2) {

	    	System.out.println("STUDENT UPDATE BUTTON AS BEEN CLICKED......................");
	    	
	    	// updating database
	    	boolean success = staffDao.updateStaff(staff2);
	    	
	    	try {
				List<Staff> list = staffDao.getStaffs();
				for(Staff l : list)
					System.out.println(l.toString());
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

	    	if(success)
	    	{

	    		System.out.println("Update successfull");
	    		Stage stage = (Stage)root.getScene().getWindow() ;
	    		stage.close();

	    	}

	    	else
	    		System.out.println("Error");
		}

		@FXML
	    void active_close_window(ActionEvent event) {

	    	Stage stage = (Stage)root.getScene().getWindow() ;
    		stage.close();

	    }




}
