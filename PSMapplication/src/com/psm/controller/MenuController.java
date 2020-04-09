package com.psm.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.psm.database.StaffDao;
import com.psm.database.StudentDao;
import com.psm.model.DynamicTime;
import com.psm.model.NextOfKin;
import com.psm.model.Parent;
//import com.psm.model.Preferences;
import com.psm.model.Staff;
import com.psm.model.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController implements Initializable{

	
	@FXML
	private AnchorPane root ; 
	
	@FXML
    private JFXButton nusery_one_button;
	
	@FXML
    private JFXButton nursery_two_button ; 

    @FXML
    private JFXButton logout_button;
    
    @FXML
    private JFXButton nursery_3_button;

    @FXML
    private JFXButton pry_1_button;

    @FXML
    private JFXButton pry_2_button;

    @FXML
    private JFXButton pry_3_button;

    @FXML
    private JFXButton pry_4_button;

    @FXML
    private JFXButton pry_5_button;

    @FXML
    private TableView<Student> student_table;

    @FXML
    private TableColumn<Student, Integer> student_id_column;

    @FXML
    private TableColumn<Student, String> student_name_column;

    @FXML
    private TableColumn<Student, String> student_surname_column;

    @FXML
    private TableColumn<Student, String> stud_DOB_column;

    @FXML
    private TableColumn<Student, String> stud_payStatus_col;

    @FXML
    private TableColumn<Student, String> stud_class_col;

    @FXML
    private TableColumn<Student, Integer> stud_age_col;

    @FXML
    private Text student_current_name ;


//    staff related instance variable

    @FXML
    private JFXButton btn_aced_staff;

    @FXML
    private JFXButton btn_non_aced_staff;

    @FXML
    private JFXButton btn_staff_info;

    @FXML
    private TableView<Staff> staff_table_view;

    @FXML
    private TableColumn<Staff, Integer> staff_id_col;

    @FXML
    private TableColumn<Staff, String> staff_othername_col;

    @FXML
    private TableColumn<Staff, String> staff_firstname_col;

    @FXML
    private TableColumn<Staff, String> staff_doe_col;

    @FXML
    private TableColumn<Staff, String> staff_classtaking_col;

    @FXML
    private TableColumn<Staff, String> staff_contact_col;

    @FXML
    private TableColumn<Staff, String> staff_sex_col;

    @FXML
    private TableColumn<Staff, Integer> staff_age_col;

    @FXML
    private Text staff_current_name ;
    
    @FXML 
    private Text text_dashboard_date ; 
    
    @FXML 
    private Text txt_day_greeting ; 


    @FXML
    private JFXButton btn_show_student;
    
    private JFXPopup popup ; 

    private StudentDao stud_dao  ;
    
    private StaffDao staff_dao ;

    private Student selected_student  ;
    

    private Staff selected_staff ;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//getting db connection
		stud_dao = new StudentDao();
		staff_dao = new StaffDao();
		

		// initializing the staff table and columns

		staff_id_col.setCellValueFactory(new PropertyValueFactory<>("id"));

		staff_othername_col.setCellValueFactory(new PropertyValueFactory<>("otherName"));

		staff_firstname_col.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		staff_doe_col.setCellValueFactory(new PropertyValueFactory<>("DOE"));

		staff_classtaking_col.setCellValueFactory(new PropertyValueFactory<>("classTaking"));

		staff_contact_col.setCellValueFactory(new PropertyValueFactory<>("contact"));

		staff_sex_col.setCellValueFactory(new PropertyValueFactory<>("sex"));

		staff_age_col.setCellValueFactory(new PropertyValueFactory<>("age"));


		//inserting  staff data into table
		staff_table_view.getSelectionModel().selectedItemProperty().addListener((v , s_old , s_new)->{

			selected_staff = s_new ;
			staff_current_name.setText("");

			staff_current_name.setText(" Name : " + s_new.getFirstName().toUpperCase() + " " + s_new.getOtherName().toUpperCase());
			System.out.println("Current Student name : " + s_new.getFirstName());

				

		});






		// initializing the staff table and columns

		student_id_column.setCellValueFactory(new PropertyValueFactory<>("id"));

		student_name_column.setCellValueFactory(new PropertyValueFactory<>("otherName"));

		student_surname_column.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		stud_DOB_column.setCellValueFactory(new PropertyValueFactory<>("DOB"));

		stud_payStatus_col.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));

		stud_class_col.setCellValueFactory(new PropertyValueFactory<>("stud_class"));

		stud_age_col.setCellValueFactory(new PropertyValueFactory<>("age"));

		//inserting data into table
		student_table.getSelectionModel().selectedItemProperty().addListener((v , s_old , s_new)->{

			selected_student = s_new ;
			student_current_name.setText(" Name : " + s_new.getFirstName().toUpperCase() + " " + s_new.getOtherName().toUpperCase());
			System.out.println("Current Student name : " + s_new.getFirstName());

				
		});


		 initTable();
		 initPopup();
		 
		 
		 //initializing the dashboard time
		 DynamicTime.startTime(text_dashboard_date , txt_day_greeting);

		// staff_dao.deletAllStaff();
		// adding staff to the database.
		// addStaff();
		//addStudent();
	}


	public void initTable() {

		System.out.println(" ***************************************    THE INIT METHOD IS CALLED  *************************************");
		student_table.getItems().removeAll() ;
		student_table.setItems(getStudents("nry1"));

	}


	@FXML
    void activateAddStudentButton(MouseEvent event) {

		Stage stage = null ;

		try {

			javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("/com/psm/front_design/Add_Student.fxml"));
			Scene scene = new Scene(root);

			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setScene(scene);
			stage.setTitle("Add Student");
			stage.showAndWait();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


//		Staff staff1 = new Staff(01 , 23 , "Jhon" , "Rudmimant" , "street 1" , "08056453745" , false , "2019-02-07" , "2019-44-30" , "TEACHING_STAFF" , "NRY1" , new NextOfKin("adam1", "01233435456")) ;
//		staff_dao.insertStaff(staff1);
    }


	@FXML
    void activateAddStaffButton(MouseEvent event) {

		Stage stage = null ;

		try {

			javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("/com/psm/front_design/Add_Staff.fxml"));
			Scene scene = new Scene(root);

			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setScene(scene);
			stage.setTitle("Add Student");
			stage.showAndWait();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


//		Staff staff1 = new Staff(01 , 23 , "Jhon" , "Rudmimant" , "street 1" , "08056453745" , false , "2019-02-07" , "2019-44-30" , "TEACHING_STAFF" , "NRY1" , new NextOfKin("adam1", "01233435456")) ;
//		staff_dao.insertStaff(staff1);
    }

	public void addStaff(){

		//staff = new Staff(id, age, firstName, otherName, address, contact , sex , formatter.format(DOE) , formatter.format(DOB) , staff_group , class_taking.toUpperCase() , new NextOfKin(kin_name , kin_contact));


		Staff staff1 = new Staff(11 , 23 , "Jhon" , "Rudmimant" , "street 1" , "08056453745" , false , "2019-02-07" , "2019-44-30" , "TEACHING_STAFF" , "NRY1" , new NextOfKin("adam1", "01233435456")) ;
		Staff staff2 = new Staff(22 , 45 , "Anna" , "lum" , "street 2" , "08056453745" , false ,       "2019-02-07" , "2019-44-30" , "NON_TEACHING_STAFF" , "NRY1" , new NextOfKin("adam2", "01233435456")) ;
		Staff staff3 = new Staff(33 , 34 , "Bobs" , "kals" , "street 3" , "08056453745" , true ,       "2019-02-07" , "2019-44-30" , "TEACHING_STAFF" , "PRY1" , new NextOfKin("adam3", "01233435456")) ;
		Staff staff4 = new Staff(44 , 23 , "luth" , "gigs" , "street 4" , "08056453745" , false ,      "2019-02-07" , "2019-44-30" , "NON_TEACHING_STAFF" , "PRY3" , new NextOfKin("adam4", "01233435456")) ;
		Staff staff5 = new Staff(55 , 45 , "amma" , "Bumm" , "street 5" , "08056453745" , true ,       "2019-02-07" , "2019-44-30" , "TEACHING_STAFF" , "PRY2" , new NextOfKin("adam5", "01233435456")) ;
		Staff staff6 = new Staff(66 , 55 , "Cipher" , "Samp" , "street 6" , "08056453745" , false ,    "2019-02-07" , "2019-44-30" , "TEACHING_STAFF" , "PRY3" , new NextOfKin("adam6", "01233435456")) ;
		Staff staff7 = new Staff(77 , 24 , "Bobs" , "micheal" , "street 7" , "08056453745" , true ,    "2019-02-07" , "2019-44-30" , "TEACHING_STAFF" , "PRY5" , new NextOfKin("adam7", "01233435456")) ;
	//	Staff staff8 = new Staff(10 , 27 , "Kamals" , "put" , "street 8" , "08056453745" , false ,     "2019-02-07" , "2019-44-30" , "TEACHING_STAFF" , "PRY5" , new NextOfKin("adam8", "01233435456")) ;

		//return items ;



		staff_dao.insertStaff(staff1);
		staff_dao.insertStaff(staff2);
		staff_dao.insertStaff(staff3);
		staff_dao.insertStaff(staff4);
		staff_dao.insertStaff(staff5);
		staff_dao.insertStaff(staff6);
		staff_dao.insertStaff(staff7);
		//staff_dao.insertStaff(staff8);

		System.out.println("staff insertion completed");

	}


	 @FXML void activateStaffButton(ActionEvent event) {

		 	JFXButton b = (JFXButton) event.getSource() ;
	    	System.out.println("Staff button selected");
	    	
	    	
			switch(b.getId())
			{

			case "btn_aced_staff" :
				staff_table_view.getItems().removeAll() ;
				staff_table_view.setItems(getStaff("TEACHING_STAFF"));
				break ;

			case "btn_non_aced_staff" :
				staff_table_view.getItems().removeAll() ;
				staff_table_view.setItems(getStaff("NON_TEACHING_STAFF"));
				break ;

				default :
					staff_table_view.getItems().removeAll() ;
					staff_table_view.setItems(getStaff("TEACHING_STAFF"));

			}// end of switch case statement....

	    }


	public ObservableList<Student> setTableData(){

		ObservableList<Student> items = FXCollections.observableArrayList() ;
		items.add(new Student(01 , 12 , "adam" , "john oweri" , "third Street" , "2003/1/3" , "PAID" , "Primary1" , true , new Parent("Mr Adam" , "Stree 2" , "07035342754"))) ;
		items.add(new Student(02 , 12 , "adam" , "john oweri" , "third Street" , "2003/1/3" , "PAID" , "Primary1" , true , new Parent("Mr Adam" , "Stree 2" , "07035342754"))) ;
		items.add(new Student(03 , 12 , "adam" , "john oweri" , "third Street" , "2003/1/3" , "PAID" , "Primary1" , true , new Parent("Mr Adam" , "Stree 2" , "07035342754"))) ;
		items.add(new Student(04 , 12 , "adam" , "john oweri" , "third Street" , "2003/1/3" , "PAID" , "Primary1" , true , new Parent("Mr Adam" , "Stree 2" , "07035342754"))) ;


		return items ;

	}


	public void addStudent(){


		Student stud1 = new Student(05 , 12 , "boob" , "john oweri" , "third Street" , "2003-16-13" , "PAID" , "PRY2" , true , new Parent("Mr Adam" , "Stree 2" , "07035342754")) ;
		Student stud2 = new Student(06 , 12 , "salam" , "john oweri" , "third Street" , "3030-17-12" , "PAID" , "NRY1" , true , new Parent("Mr Adam" , "Stree 2" , "07035342754")) ;
		Student stud3 = new Student(07 , 12 , "goods" , "john oweri" , "third Street" , "2320-19-20" , "PAID" , "PRY1" , true , new Parent("Mr Adam" , "Stree 2" , "07035342754")) ;
		Student stud4 = new Student(11 , 12 , "bado" , "john oweri" , "third Street" , "2030-10-12" , "PAID" , "PRY2" , true , new Parent("Mr Adam" , "Stree 2" , "07035342754")) ;

		stud_dao.insertStudent(stud1);
		stud_dao.insertStudent(stud2);
		stud_dao.insertStudent(stud3);
		stud_dao.insertStudent(stud4);

	}

	//helper method to student form database.....
		public ObservableList<Staff> getStaff(String staff_group){

			try {

				return staff_dao.getStaffs(staff_group) ;

			} catch (ParseException e) {

				e.printStackTrace();

				return null ;
			}

		}

	//helper method to student form database.....
	public ObservableList<Student> getStudents(String Class){

		try {

			return stud_dao.getStudents(Class) ;

		} catch (ParseException e) {

			e.printStackTrace();

			return null ;
		}

	}


	@FXML void activateClassButton(ActionEvent event) {



		JFXButton b = (JFXButton) event.getSource() ;

		switch(b.getId())
		{

		case "nusery_one_button" :
			student_table.getItems().removeAll() ;
			student_table.setItems(getStudents("nry1"));
			break ;
		case "nursery_two_button" :
			student_table.getItems().removeAll() ;
			student_table.setItems(getStudents("nry2"));
			break ;
		case "nursery_3_button" :
			student_table.getItems().removeAll() ;
			student_table.setItems(getStudents("nry3"));
			break ;
		case "pry_1_button" :
			student_table.getItems().removeAll() ;
			student_table.setItems(getStudents("pry1"));
			break ;
		case "pry_2_button" :
			student_table.getItems().removeAll() ;
			student_table.setItems(getStudents("pry2"));
			break ;
		case "pry_3_button" :
			student_table.getItems().removeAll() ;
			student_table.setItems(getStudents("pry3"));
			break ;
		case "pry_4_button" :
			student_table.getItems().removeAll() ;
			student_table.setItems(getStudents("pry4"));
			break ;
		case "pry_5_button" :
			student_table.getItems().removeAll() ;
			student_table.setItems(getStudents("pry5"));
			break ;
		default :
			student_table.getItems().removeAll() ;
			student_table.setItems(getStudents("nry1"));
			//return ;


		}

	 }


    @FXML
    void activateLoadStudentWindow(ActionEvent event) {

    	//loadWindow("Student Panel" , "/com/psm/front_design/menu_design.fxml") ;
    	if(selected_student==null)
			return  ;

    	Stage stage = null ;

		try {

			StudentDetailController.initStudent(selected_student);
			FXMLLoader loader = new  FXMLLoader(getClass().getResource("/com/psm/front_design/Student_detail.fxml"));
			javafx.scene.Parent root = loader.load();
			Scene scene = new Scene(root);
			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setTitle("Student Panel");
			stage.showAndWait();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }


    @FXML
    void activateLoadStaffWindow(ActionEvent event) {

    	//loadWindow("Student Panel" , "/com/psm/front_design/menu_design.fxml") ;
    	if(selected_staff==null)
			return  ;

    	Stage stage = null ;

		try {

			StaffDetailController.initStaff(selected_staff);
			FXMLLoader loader = new  FXMLLoader(getClass().getResource("/com/psm/front_design/Staff_detail.fxml"));
			javafx.scene.Parent root = loader.load();
			Scene scene = new Scene(root);
			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setTitle("Staff Panel");
			stage.showAndWait();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    public void initPopup(){
    	
    	JFXButton refreshButton = new JFXButton("Refresh"); 
    	JFXButton deleteButton = new JFXButton("Delete"); 
    	JFXButton updateButton = new JFXButton("Update"); 
    	
    	VBox vbox =  new VBox();
    	vbox.getChildren().addAll(refreshButton , deleteButton , updateButton) ; 
    	
    	popup = new JFXPopup() ;
    	popup.setPopupContent(vbox);
    	//popup.getWindowOwner().set
 
    }




    @FXML
    void activateLogout(ActionEvent event) throws IOException {
    	
    	Stage stage = (Stage)root.getScene().getWindow() ; 
    	stage.close(); 
    	
    	javafx.scene.Parent parent =  FXMLLoader.load(getClass().getResource("/com/psm/front_design/login_design.fxml"));
		Scene scene = new Scene(parent);
		Stage windows = new Stage();
		windows.setTitle("Praise Fountain Login");
		windows.setScene(scene);
		windows.show();
		
		DynamicTime.stopTime();
		
		//Preferences.initConfig();
    	
    }

	public void reloadPanels(String Class){



	}//end of method....



}
