package com.psm.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.binary.alert.Alert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.psm.database.DBFactory;
import com.psm.database.StudentDao;
import com.psm.model.ChangeDirectory;
import com.psm.model.Parent;
import com.psm.model.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class AddStudentController implements Initializable{

	    @FXML private AnchorPane root ;

	    @FXML private StackPane stackpane ;

	    @FXML
	    private JFXTextField txt_name;

	    @FXML
	    private JFXTextField txt_id;

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
	    private JFXButton btn_submit;

	    @FXML
	    private JFXButton btn_clear;

	    private Student student ;

	    private File file ;

	    private StudentDao stud_dao = DBFactory.access_student();

    @FXML
    void activate_clear(ActionEvent event) {

//    	JFXButton button1 = new JFXButton("Yes");
//    	JFXButton button2 = new JFXButton("No");
//
//    	button1.setOnAction(e->{
//
//    		System.out.println("Yes");
//    	});
//    	button2.setOnAction(e->{
//
//    		System.out.println("No");
//    		Alert.getDialog().close();
//    	});
//
//    	List<JFXButton> buttons = new ArrayList<>();
//    	buttons.add(button1);
//    	buttons.add(button2) ;
//
//    	Alert.showConfirmation(stackpane, root, buttons, "Successfully added" , "Alert");

    	Stage stage = (Stage) root.getScene().getWindow() ;
		stage.close();


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


    	JFXButton comfirmButton = new JFXButton("Ok");
		Alert.showAlert(stackpane, root , comfirmButton , file.getName() + " selected successfully", "Alert");

		comfirmButton.setOnAction(e->{
			Alert.getDialog().close();
		});

    }

    @FXML
    void activate_submit(ActionEvent event) {

    	int id = Integer.parseInt(txt_id.getText());
    	String fName = txt_name.getText() ;
    	String oName = txt_otherName.getText();
    	String address = txt_address.getText() ;
    	int age = Integer.parseInt(txt_age.getText()) ;
    	String dob = dob_calender.getValue().toString();
    	String stud_class  = combo_class.getValue();
    	String pay_status = combo_payment_status.getValue();

    	boolean sex = true ;

    	if(radio_male.isSelected())
    		sex = true ;
    		else
    			sex = false ;


    	String famName = txt_family_name.getText();
    	String famAddress = txt_parent_address.getText();
    	String famContact = txt_parent_contact.getText();


    	student = new Student(id, age, fName, oName, address, dob , pay_status.toUpperCase() , stud_class.toUpperCase() , sex , new Parent(famName , famContact , famAddress));
    	
    	if(file!=null){
    		
    		Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        	System.out.println(path.toString() + "\\src\\com\\psm\\passports\\new_image.png");
        	String passport_path = path.toString() + "\\src\\com\\psm\\passports\\" + fName+"_"+id+".png" ;
        	String pass_loc = "/com/psm/passports/"+fName+"_"+id+".png";

        	System.out.println(passport_path);


        	try {

    			ChangeDirectory.copy(file , new File(passport_path));

    		} catch (IOException e) {


    			e.printStackTrace();
    		}
        	
        	student.setPassport_location(pass_loc);
    	}
    	
 	   	student.setStud_performance("EXCELLENT");
 	   	
 	   	
 	   JFXButton yesButton = new JFXButton("Yes");
   	JFXButton noButton = new JFXButton("No");

   	// if yesButton selected perform update operation
   	yesButton.setOnAction(e->{
   		
   		Alert.getDialog().close();
   		addStudentRecord(student);
   		
   	});

   	//if noButton selected cancel operation.
   	noButton.setOnAction(e->{

   		System.out.println("No");
   		Alert.getDialog().close();
   	});

   	List<JFXButton> buttons = new ArrayList<>();
   	buttons.add(yesButton);
   	buttons.add(noButton) ;
   	Alert.showConfirmation(stackpane, root, buttons, "Are you sure to add  " + student.getFirstName() + "  " +student.getOtherName() , "Confirmation");

 	   
    }
    
    

	private void addStudentRecord(Student student) {

		boolean status = stud_dao.insertStudent(student);

		if(status)
		{

			JFXButton comfirmButton = new JFXButton("Ok");
			Alert.showAlert(stackpane, root , comfirmButton , "Student Registered Successfully !!", "Add Student Alert");

			comfirmButton.setOnAction(e->{

				Alert.getDialog().close();
				Stage stage = (Stage) root.getScene().getWindow() ;
				stage.close();

			});


		}else{

			JFXButton comfirmButton = new JFXButton("Ok");
			Alert.showAlert(stackpane, root , comfirmButton , "Registration Fail Try again !!", "Error Alert");

			comfirmButton.setOnAction(e->{
				Alert.getDialog().close();
			});

			initWidget(student);
		}
	}// end of methods.....
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//set up necessary widget..
    	ObservableList<String> classList = FXCollections.observableArrayList("NRY1" , "NRY2" , "NRY3" , "PRY1" , "PRY2" , "PRY3" , "PRY4" , "PRY5") ;
    	combo_class.getItems().addAll(classList);


    	ObservableList<String>  paymentList= FXCollections.observableArrayList("PAID" , "HALF_PAID"  , "NOT_PAID") ;
    	combo_payment_status.getItems().addAll(paymentList);

    	dob_calender.setValue(LocalDate.parse("2000-01-01"));
	}


	 public void initWidget(Student student){

	    	txt_name.setText(student.getFirstName());
	    	txt_otherName.setText(student.getOtherName());
	    	txt_address.setText(student.getAddress());
	    	txt_age.setText(String.valueOf(student.getAge()));
	    	dob_calender.setValue(LocalDate.parse(student.getDOB()));
	    	combo_class.setValue(student.getStud_class().toUpperCase());
	    	combo_payment_status.setValue(student.getPaymentStatus().toUpperCase());

	    	if(student.getSex() == true )
	    		radio_male.setSelected(true);
	    	else
	    		radio_female.setSelected(true);


	    	txt_family_name.setText(student.getParent().getFamily_name());
	    	txt_parent_address.setText(student.getParent().getFamily_address());
	    	txt_parent_contact.setText(student.getParent().getFamily_contact());

	    }


}

