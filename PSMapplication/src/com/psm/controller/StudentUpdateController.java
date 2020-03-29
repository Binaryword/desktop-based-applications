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
import com.psm.database.StudentDao;
import com.psm.model.ChangeDirectory;
import com.psm.model.Parent;
import com.psm.model.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class StudentUpdateController implements Initializable{

	    @FXML private AnchorPane root ;

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
	    private JFXComboBox<String> combo_performance;

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
	    private StudentDao studDao = new StudentDao() ;

	    @Override
		public void initialize(URL location, ResourceBundle resources) {

	    	//set up necessary widget..
	    	ObservableList<String> classList = FXCollections.observableArrayList("NRY1" , "NRY2" , "NRY3" , "PRY1" , "PRY2" , "PRY3" , "PRY4" , "PRY5") ;
	    	combo_class.getItems().addAll(classList);


	    	ObservableList<String>  paymentList= FXCollections.observableArrayList("PAID" , "HALF_PAID"  , "NOT_PAID") ;
	    	combo_payment_status.getItems().addAll(paymentList);

	    	ObservableList<String>  performanceList= FXCollections.observableArrayList("EXCELLENT" , "GOOD"  , "POOR") ;
	    	combo_performance.getItems().addAll(performanceList);

	    	dob_calender.setValue(LocalDate.parse("2000-01-01"));

	    	//setting student value to the gui widget
	    	initWidget();


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
	    	System.out.println(path.toString() + "\\src\\com\\psm\\passports\\new_image.png");
	    	String passport_path = path.toString() + "\\src\\com\\psm\\passports\\" + student.getFirstName()+"_"+student.getId()+".png" ;
	    	System.out.println(passport_path);


	    	try {

				ChangeDirectory.copy(file , new File(passport_path));
				student.setPassport_location(passport_path);

			} catch (IOException e) {

				e.printStackTrace();
			}



	    }// end of method....


	    @FXML
	    void activate_update(ActionEvent event) {




	    	int id = student.getId() ;
	    	String name = txt_name.getText() ;
	    	String othername = txt_otherName.getText();
	    	String address = txt_address.getText();
	    	int age = Integer.parseInt(txt_age.getText().trim());
	    	String dob = dob_calender.getValue().toString() ;
	    	String stud_class = combo_class.getValue() ;
	    	String pay_status = combo_payment_status.getValue() ;
	    	String stud_performance = combo_performance.getValue() ;

	    	boolean sex  ;

	    	if(radio_male.isSelected())
	    		sex = true ;
	    	else
	    		sex = false ;

	    	String famName = txt_family_name.getText() ;
	    	String famAddress = txt_parent_address.getText();
	    	String famContact = txt_parent_contact.getText() ;

	    	String pass_loc = "/com/psm/passports/"+name+"_"+id+".png";

	    student = new Student(id, age, name, othername, address, dob , pay_status.toUpperCase() , stud_class.toUpperCase() , sex , new Parent(famName , famContact , famAddress));
	    student.setPassport_location(pass_loc);
	    student.setStud_performance(stud_performance);
	    updateStudentRecord(student);

	    }

	    @FXML
	    void active_close_window(ActionEvent event) {

	    		Stage stage = (Stage)root.getScene().getWindow() ;
	    		stage.close();

	    }



	    public static void initStudent(Student stud){

	    	student = stud ;
	    	System.out.println("Student from update form " + student.toString() ) ;
	    }



	    public void initWidget(){

	    	txt_name.setText(student.getFirstName());
	    	txt_otherName.setText(student.getOtherName());
	    	txt_address.setText(student.getAddress());
	    	txt_age.setText(String.valueOf(student.getAge()));
	    	dob_calender.setValue(LocalDate.parse(student.getDOB()));
	    	combo_class.setValue(student.getStud_class().toUpperCase());
	    	combo_payment_status.setValue(student.getPaymentStatus().toUpperCase());
	    	combo_performance.setValue(student.getStud_performance().toUpperCase());

	    	if(student.getSex() == true )
	    		radio_male.setSelected(true);
	    	else
	    		radio_female.setSelected(true);


	    	txt_family_name.setText(student.getParent().getFamily_name());
	    	txt_parent_address.setText(student.getParent().getFamily_address());
	    	txt_parent_contact.setText(student.getParent().getFamily_contact());

	    }


	    public void updateStudentRecord(Student student){

	    	// updating database
	    	boolean success = studDao.updateStudent(student);

	    	if(success)
	    	{

	    		System.out.println("Update successfull");
	    		Stage stage = (Stage)root.getScene().getWindow() ;
	    		stage.close();

	    	}

	    	else
	    		System.out.println("Error");

	    }





}
