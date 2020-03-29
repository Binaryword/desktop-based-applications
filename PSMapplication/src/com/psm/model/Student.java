package com.psm.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {


	private SimpleIntegerProperty id ;
	private SimpleIntegerProperty age ;
	private SimpleStringProperty  firstName ;
	private SimpleStringProperty  otherName ;
	private SimpleStringProperty  address ;
	private SimpleStringProperty  DOB ;
	private SimpleStringProperty   paymentStatus;
	private SimpleStringProperty  stud_class ;
	private SimpleBooleanProperty sex   ;
	private String passport_location ;
	private String stud_performance ;
	private Parent parent  ;



	public Student(int id , int age , String firstName , String otherName , String address , String DOB , String pay_status, String stud_class ,  boolean sex , Parent parent){


		this.id = new SimpleIntegerProperty(id);
		this.age = new SimpleIntegerProperty(age);
		this.firstName = new SimpleStringProperty(firstName);
		this.otherName = new SimpleStringProperty(otherName);
		this.address = new SimpleStringProperty(address);
		this.DOB = new SimpleStringProperty(DOB);
		this.paymentStatus = new SimpleStringProperty(pay_status);
		this.stud_class = new SimpleStringProperty(stud_class);
		this.sex = new SimpleBooleanProperty(sex);
		this.parent = parent ;


	}




	public String getStud_performance() {
		return stud_performance;
	}




	public void setStud_performance(String stud_performance) {
		this.stud_performance = stud_performance;
	}




	public Parent getParent() {
		return parent;
	}




	public void setParent(Parent parent) {
		this.parent = parent;
	}




	public int getId() {
		return id.get();
	}




	public int getAge() {
		return age.get();
	}




	public String getFirstName() {
		return firstName.get();
	}



	public String getOtherName() {
		return otherName.get();
	}




	public String getAddress() {
		return address.get();
	}




	public String getDOB() {
		return DOB.get();
	}




	public String getPaymentStatus() {
		return paymentStatus.get();
	}




	public String getStud_class() {
		return stud_class.get();
	}




	public boolean getSex() {
		return sex.get();
	}




	public void setId(int id) {
		this.id.set(id);
	}




	public void setAge(int age) {
		this.age.set(age);
	}



	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}




	public void setOtherName(String otherName) {
		this.otherName.set(otherName);
	}




	public void setAddress(String address) {
		this.address.set(address);
	}




	public void setDOB(String dOB) {
		this.DOB.set(dOB);
	}




	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus.set(paymentStatus);
	}




	public void setStud_class(String stud_class) {
		this.stud_class.set(stud_class);
	}




	public void setSex(boolean sex) {
		this.sex.set(sex);
	}




	public String getPassport_location() {
		return passport_location;
	}




	public void setPassport_location(String passport_location) {
		this.passport_location = passport_location;
	}




	@Override
	public String toString() {
		return "Student [id=" + id + ", age=" + age + ", firstName=" + firstName + ", otherName=" + otherName
				+ ", address=" + address + ", DOB=" + DOB + ", paymentStatus=" + paymentStatus + ", stud_class="
				+ stud_class + ", sex=" + sex + ", parent=" + parent + "]";
	}











}// end of class....
