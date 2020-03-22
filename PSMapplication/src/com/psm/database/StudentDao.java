package com.psm.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import com.psm.model.Parent;
import com.psm.model.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentDao {

	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preStatement = null;
	private boolean hasTable = false;

	public StudentDao() {

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

			System.out.println("creat table called......................................");

			String query = " select name from sqlite_master where type = 'table' and name = 'studentTable' ";
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

		String sql = "create table studentTable("
				+ "id integer(10) primary key , "
				+ "age integer(15) not null , "
				+ "firstname varchar(30) not null , "
				+ "othername varchar(100) not null , "
				+ "address varchar(100) not null , "
				+ "DOB DATE , "
				+ "pay_status varchar(20) , "
				+ "stud_class varchar(20) , "
				+ "sex boolean default true, "
				+ "family_name varchar(30) not null , "
				+ "family_contact varchar(15) not null , "
				+ "family_address varchar(100) not null ) ; " ;

		try {
			statement = connection.createStatement();
			boolean isFine = statement.execute(sql);

			if (isFine) {
				System.out.println(" Student Table created successfully...");
			}

		} catch (SQLException e) {

			System.out.println("An erro as occur while creating Student table.....");
			e.printStackTrace();
		}

	}// end of method.....



	public boolean insertStudent(Student student) {

		String sql = "insert into studentTable values (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?) ";
		boolean isSuccessfull = false;

		try {
			preStatement = connection.prepareStatement(sql);
			preStatement.setInt(1, student.getId());
			preStatement.setInt(2, student.getAge());
			preStatement.setString(3, student.getFirstName().toLowerCase());
			preStatement.setString(4, student.getOtherName().toLowerCase());
			preStatement.setString(5, student.getAddress().toLowerCase());
			preStatement.setString(6, student.getDOB().toLowerCase());
			preStatement.setString(7, student.getPaymentStatus().toLowerCase());
			preStatement.setString(8, student.getStud_class().toLowerCase());
			preStatement.setBoolean(9, student.getSex());
			preStatement.setString(10, student.getParent().getFamily_name().toLowerCase());
			preStatement.setString(11, student.getParent().getFamily_address().toLowerCase());
			preStatement.setString(12, student.getParent().getFamily_contact().toLowerCase());

			boolean isFine = preStatement.execute();

			if (isFine) {
				System.out.println("student data Insertion successful...");
			}

			isSuccessfull = true;

		} catch (SQLException e) {

			isSuccessfull = false;
			System.out.println("An erro as occur while inserting into student table.....");
			e.printStackTrace();
		}

		return isSuccessfull;
	}// end of method



	public ObservableList<Student> getStudents(String Class) throws ParseException {

		ObservableList<Student> studentList = FXCollections.observableArrayList();
		Student student = null;
		String sql = "select * from studentTable where stud_class=? ; " ;

//		+ "id integer(10) primary key , "
//		+ "age integer(15) not null , "
//		+ "firstname varchar(30) not null , "
//		+ "othername varchar(100) not null , "
//		+ "address varchar(100) not null , "
//		+ "DOB date , "
//		+ "pay_status varchar(20) , "
//		+ "stud_class varchar(20) , "
//		+ "sex boolean default true, "
//		+ "family_name varchar(30) not null , "
//		+ "family_contact varchar(15) not null , "
//		+ "family_address varchar(100) not null ) ; " ;

		try {
			preStatement  = connection.prepareStatement(sql);
			preStatement.setString(1, Class);
			ResultSet resultSet = preStatement.executeQuery();

			DateFormat formatter = new SimpleDateFormat("yyy-MM-dd") ;

			while (resultSet.next()) {

				int id = resultSet.getInt("id");
				int age  = resultSet.getInt("age");
				String firstName = resultSet.getString("firstname");
				String otherName = resultSet.getString("othername");
				String address = resultSet.getString("address");
				java.util.Date DOB = formatter.parse(resultSet.getString("DOB"));
				String pay_status = resultSet.getString("pay_status");
				String stud_class = resultSet.getString("stud_class");
				boolean sex = resultSet.getBoolean("sex");
				String familyName = resultSet.getString("family_name");
				String family_contact = resultSet.getString("family_contact");
				String family_address = resultSet.getString("family_address");


				System.out.println();
				student = new Student(id, age, firstName, otherName, address, formatter.format(DOB) , pay_status , stud_class.toUpperCase() , sex , new Parent(familyName , family_contact , family_address));

				if (student != null)
					studentList.add(student);

				System.out.println(" Student data found successfully...");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return studentList;
	}// end of method


	public Student getStudent(String studentId) {

		Student student = null;
		String sql = "select  * from StudentTable where id = ?;";
		DateFormat formatter = new SimpleDateFormat("yyy-MM-dd") ;

		try {

			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, studentId);
			ResultSet resultSet = preStatement.executeQuery();

			if (resultSet.next()) {

				int id = resultSet.getInt("id");
				int age  = resultSet.getInt("age");
				String firstName = resultSet.getString("firstname");
				String otherName = resultSet.getString("othername");
				String address = resultSet.getString("address");
				java.util.Date DOB = null ;
				try {
					DOB = formatter.parse(resultSet.getString("DOB"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				String pay_status = resultSet.getString("pay_status");
				String stud_class = resultSet.getString("stud_class");
 				boolean sex = resultSet.getBoolean("sex");
				String familyName = resultSet.getString("family_name");
				String family_contact = resultSet.getString("family_contact");
				String family_address = resultSet.getString("family_address");


				System.out.println();
				student = new Student(id, age, firstName, otherName, address, formatter.format(DOB) , pay_status , stud_class.toUpperCase() , sex , new Parent(familyName , family_contact , family_address));

				System.out.println(" Student data found successfully...");

				return student;

			}else
			{
				System.out.println(" Student NOT data found successfully...");
			}

		} catch (SQLException e) {

			System.out.println("");
		}

		return student;
	}// end of method





}
