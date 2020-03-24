package com.psm.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.psm.database.StudentDao;
import com.psm.model.Parent;
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
import javafx.stage.Stage;

public class MenuController implements Initializable{

	@FXML
    private JFXButton nusery_one_button;

    @FXML
    private JFXButton nursery_two_button;

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
    void activateLoadStaffWindow(ActionEvent event) {
    				
    	

    }

    @FXML
    void activateStaffSelection(ActionEvent event) {

    	JFXButton b = (JFXButton) event.getSource() ;

		switch(b.getId())
		{

//		case "" :
//			student_table.getItems().removeAll() ;
//			student_table.setItems(getStudents("nry1"));
//			break ;
//		case "" :
//			student_table.getItems().removeAll() ;
//			student_table.setItems(getStudents("nry2"));
//			break ;
//		case "" :
//			student_table.getItems().removeAll() ;
//			student_table.setItems(getStudents("nry3"));
//			break ;
//			default :
		}

    }



    @FXML
    private JFXButton btn_show_student;

    private StudentDao stud_dao  ;

    private Student selected_student  ;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//getting db connection
		stud_dao = new StudentDao();


		

		staff_id_col.setCellValueFactory(new PropertyValueFactory<>("id"));

		staff_othername_col.setCellValueFactory(new PropertyValueFactory<>("otherName"));

		staff_firstname_col.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		staff_doe_col.setCellValueFactory(new PropertyValueFactory<>("DOE"));

		staff_classtaking_col.setCellValueFactory(new PropertyValueFactory<>("classTaking"));

		staff_contact_col.setCellValueFactory(new PropertyValueFactory<>("contact"));
		
		staff_sex_col.setCellValueFactory(new PropertyValueFactory<>("sex"));
		
		staff_age_col.setCellValueFactory(new PropertyValueFactory<>("age"));
		
	

		//inserting data into table
		student_table.getSelectionModel().selectedItemProperty().addListener((v , s_old , s_new)->{

			selected_student = s_new ;
		});
		

		// initializing the staff table and columns
		
		student_id_column.setCellValueFactory(new PropertyValueFactory<>("id"));

		student_name_column.setCellValueFactory(new PropertyValueFactory<>("otherName"));

		student_surname_column.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		stud_DOB_column.setCellValueFactory(new PropertyValueFactory<>("DOB"));

		stud_payStatus_col.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));

		stud_class_col.setCellValueFactory(new PropertyValueFactory<>("stud_class"));

		stud_age_col.setCellValueFactory(new PropertyValueFactory<>("age"));
		

	}

//Student(int id , int age , String firstName , String otherName , String address , String DOB , String pay_status, String stud_class ,  boolean sex){


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
			stage.setScene(scene);
			stage.setTitle("Student Panel");
			stage.showAndWait();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

	// load the detail layout
	public void loadWindow(String title , String path){



		Stage stage = null ;

		try {


			FXMLLoader loader = new  FXMLLoader(getClass().getResource(path));
			loader.load();
			StudentDetailController sd = loader.getController() ;
			//sd.initStudent(selected_student);
			javafx.scene.Parent root = FXMLLoader.load(getClass().getResource(path));
			Scene scene = new Scene(root);
			stage = new Stage();
			stage.setScene(scene);
			stage.setTitle(title);
			stage.show();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
