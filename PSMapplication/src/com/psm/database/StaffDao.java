package com.psm.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException ;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.psm.model.NextOfKin;
import com.psm.model.Staff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class StaffDao {


	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preStatement = null;
	private boolean hasTable = false;


	public StaffDao() {

		System.out.println("Student constructor as been called");
		connection = Database.getInstance().getConnection();
		createStudentTable();

	}

	/*
	 * create a student table if no table existed.....
	 */

	private void createStudentTable() {

		if (!hasTable) {
			hasTable = true;

			System.out.println("create table called......................................");

			String query = "select name from sqlite_master where type = 'table' and name = 'staffTable' ";
			try {
				statement = connection.createStatement();
				ResultSet set = statement.executeQuery(query);

				if (!set.next()) {
					initiateTable();
				} else {
					System.out.println("Table existed.....");
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

		} // end of if statement....

	}// end of method

	/*
	 * using this method to initiate table
	 */
	private void initiateTable() {

		String sql = "create table staffTable ("
				+ "id integer(100) primary key , "
				+ "age integer(15) not null , "
				+ "firstname varchar(30) not null , "
				+ "othername varchar(100) not null ,  "
				+ "address varchar(100) not null , "
				+ "contact varchar(15) not null , "
				+ "DOB DATE , "
				+ "DOE DATE ,"
				+ "staff_group varchar(20) ,   "
				+ "class_taking varchar(20) , "
				+ "sex boolean default true, "
				+ "kin_name varchar(30) not null , "
				+ "kin_contact varchar(15) not null , "
				+ "pass_location varchar(200) ) ; " ;

		try {
			statement = connection.createStatement();
			boolean isFine = statement.execute(sql);

			if (isFine) {
				System.out.println(" Staff Table created successfully...");
			}

		} catch (SQLException e) {

			System.out.println("An erro as occur while creating Staff table.....");
			e.printStackTrace();
		}

	}// end of method.....


	public void delet_table(){

		String sql = "drop table staffTable" ;
		try {
			statement = connection.createStatement() ;
			statement.execute(sql) ;
			System.out.println("table deleted successfylly");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public boolean insertStaff(Staff staff) {

		String sql = "insert into staffTable values (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?  , ?) ;  ";
		boolean isSuccessfull = false;


		try {
			preStatement = connection.prepareStatement(sql);
			preStatement.setInt(1, staff.getId());
			preStatement.setInt(2, staff.getAge());
			preStatement.setString(3, staff.getFirstName().toLowerCase());
			preStatement.setString(4, staff.getOtherName().toLowerCase());
			preStatement.setString(5, staff.getAddress().toLowerCase());
			preStatement.setString(6, staff.getContact().toLowerCase());
			preStatement.setString(7, staff.getDOB());
			preStatement.setString(8, staff.getDOE());
			preStatement.setString(9, staff.getStaffGroup().toUpperCase());
			preStatement.setString(10, staff.getClassTaking().toUpperCase());
			preStatement.setBoolean(11, staff.getSex());
			preStatement.setString(12, staff.getKin().getName());
			preStatement.setString(13, staff.getKin().getContact());
			preStatement.setString(14 ,  staff.getPassport_location());
			boolean isFine = preStatement.execute();

			if (isFine) {
				System.out.println("staff data Insertion successful...");
			}

			isSuccessfull = true;

		} catch (SQLException e) {

			isSuccessfull = false;
			System.out.println("An erro as occur while inserting into staff table.....");

			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getMessage());
			alert.setHeaderText("Technical Error");
			alert.showAndWait();

			e.printStackTrace();
		}

		return isSuccessfull;
	}// end of method



	public ObservableList<Staff> getStaffs(String staffGroup) throws ParseException {

		ObservableList<Staff> staffList = FXCollections.observableArrayList();
		Staff staff = null;
		String sql = "select * from staffTable where staff_group = ? ; " ;
		String staff_g = staffGroup.toUpperCase() ;


		try {

			preStatement  = connection.prepareStatement(sql);
			preStatement.setString(1, staff_g);
			ResultSet resultSet = preStatement.executeQuery();
			java.util.Date DOB = null ;
			java.util.Date DOE = null ;
//			System.out.println("STAFF GROUP  " + staffGroup.toUpperCase() );
			DateFormat formatter = new SimpleDateFormat("yyy-MM-dd") ;



			while (resultSet.next()) {

				System.out.println("^^^^^^^^^^^^^^^^^^ STAFF GROUP ^^^^^^^^^^^^^^^^^^^^^^^^^^^     "  + staff_g);

				int id = resultSet.getInt("id");
				int age  = resultSet.getInt("age");
				String firstName = resultSet.getString("firstname");
				String otherName = resultSet.getString("othername");
				String address = resultSet.getString("address");
				String contact = resultSet.getString("contact");

				try{

					 DOB = formatter.parse(resultSet.getString("DOB"));
					 DOE = formatter.parse(resultSet.getString("DOE"));

				}catch(NullPointerException x)
				{
					System.out.println("no Date");
				}


				String staff_group = resultSet.getString("staff_group");
				String class_taking = resultSet.getString("class_taking");
				boolean sex = resultSet.getBoolean("sex");
				String kin_name = resultSet.getString("kin_name");
				String kin_contact = resultSet.getString("kin_contact");
				String pass_location = resultSet.getString("pass_location") ;

				//System.out.println("STAFF GROUP  >>>>>> " + staff_group.toUpperCase() );

				System.out.println();
				staff = new Staff(id, age, firstName, otherName, address, contact , sex , formatter.format(DOE) , formatter.format(DOB) , staff_group.toUpperCase() , class_taking.toUpperCase() , new NextOfKin(kin_name , kin_contact));
				staff.setPassport_location(pass_location);

				if (staff != null)
					staffList.add(staff);

				System.out.println(" staff data found successfully...");
			}

		} catch (SQLException e ) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getMessage());
			alert.setHeaderText("Technical Error");
			alert.showAndWait();
			e.printStackTrace();
		}

		return staffList;
	}// end of method


	public ObservableList<Staff> getStaffs() throws ParseException {

		ObservableList<Staff> staffList = FXCollections.observableArrayList();
		Staff staff = null;
		String sql = "select * from staffTable; " ;


		try {

			preStatement  = connection.prepareStatement(sql);
			ResultSet resultSet = preStatement.executeQuery();
			java.util.Date DOB = null ;
			java.util.Date DOE = null ;
//			System.out.println("STAFF GROUP  " + staffGroup.toUpperCase() );
			DateFormat formatter = new SimpleDateFormat("yyy-MM-dd") ;



			while (resultSet.next()) {

				int id = resultSet.getInt("id");
				int age  = resultSet.getInt("age");
				String firstName = resultSet.getString("firstname");
				String otherName = resultSet.getString("othername");
				String address = resultSet.getString("address");
				String contact = resultSet.getString("contact");

				try{

					 DOB = formatter.parse(resultSet.getString("DOB"));
					 DOE = formatter.parse(resultSet.getString("DOE"));

				}catch(NullPointerException x)
				{
					System.out.println("no Date");
				}


				String staff_group = resultSet.getString("staff_group");
				String class_taking = resultSet.getString("class_taking");
				boolean sex = resultSet.getBoolean("sex");
				String kin_name = resultSet.getString("kin_name");
				String kin_contact = resultSet.getString("kin_contact");
				String pass_location = resultSet.getString("pass_location") ;

				//System.out.println("STAFF GROUP  >>>>>> " + staff_group.toUpperCase() );

				System.out.println();
				staff = new Staff(id, age, firstName, otherName, address, contact , sex , formatter.format(DOE) , formatter.format(DOB) , staff_group , class_taking.toUpperCase() , new NextOfKin(kin_name , kin_contact));
				staff.setPassport_location(pass_location);
				if (staff != null)
					staffList.add(staff);

				System.out.println(" staff data found successfully...");
			}

		} catch (SQLException e ) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getMessage());
			alert.setHeaderText("Technical Error");
			alert.showAndWait();
			e.printStackTrace();
			e.printStackTrace();
		}

		return staffList;
	}// end of method


	public boolean updateStaff(Staff staff){


//		= "create table staffTable("
//				+ "id integer(10) primary key , "
//				+ "age integer(15) not null , "
//				+ "firstname varchar(30) not null , "
//				+ "othername varchar(100) not null , "
//				+ "address varchar(100) not null , "
//				+ "contact varchar(15) not null , "
//				+ "DOB DATE , "
//				+ "DOE DATE"
//				+ "staff_group varchar(20) , "
//				+ "class_taking varchar(20) , "
//				+ "sex boolean default true, "
//				+ "kin_name varchar(30) not null , "
//				+ "kin_contact (15) not null  ) ; " ;


		String sql = "update staffTable  set id=?, age=?, firstname=? , othername=? , address=?,  contact=? , DOB=? ,  DOE=? ,"
				+ "staff_group=? , class_taking=? , sex=? ,  kin_name=? , kin_contact=? ,  pass_location=? where id = ? ; ";

		boolean isSuccessfull = false;

		try {
			preStatement = connection.prepareStatement(sql);
			preStatement.setInt(1, staff.getId());
			preStatement.setInt(2, staff.getAge());
			preStatement.setString(3, staff.getFirstName().toLowerCase());
			preStatement.setString(4, staff.getOtherName().toLowerCase());
			preStatement.setString(5, staff.getAddress().toLowerCase());
			preStatement.setString(6, staff.getContact());
			preStatement.setString(7, staff.getDOB());
			preStatement.setString(8, staff.getDOE());
			preStatement.setString(9, staff.getStaffGroup().toUpperCase());
			preStatement.setString(10, staff.getClassTaking().toUpperCase());
			preStatement.setBoolean(11, staff.getSex());
			preStatement.setString(12, staff.getKin().getName().toLowerCase());
			preStatement.setString(13, staff.getKin().getContact().toLowerCase());
			preStatement.setString(14 ,  staff.getPassport_location());
			preStatement.setInt(15, staff.getId());

			boolean isFine = preStatement.execute();

			if (isFine) {
				System.out.println("staff data Updated successful...");
			}

			isSuccessfull = true;

		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getMessage());
			alert.setHeaderText("Technical Error");
			alert.showAndWait();
			isSuccessfull = false;
			System.out.println("An erro as occur while updating into staff table.....");
			e.printStackTrace();
		}

		return isSuccessfull;


	}// end of update method



	public void deleteStaff(int id){
		String query = "delete from staffTable  where id=?;" ;
		try {
			preStatement = connection.prepareStatement(query) ;
			preStatement.setInt(1, id);
			boolean b = preStatement.execute() ;

			if(b)
				System.out.print("delection completed");
			else
				System.out.print("no deletion...");
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getMessage());
			alert.setHeaderText("Technical Error");
			alert.showAndWait();
			e.printStackTrace();
		}

	}


}
