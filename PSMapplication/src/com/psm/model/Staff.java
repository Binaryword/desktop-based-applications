package com.psm.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Staff {
	private IntegerProperty id ;
	private StringProperty otherName ;
	private StringProperty firstName ;
	private BooleanProperty sex  ;
	private StringProperty contact ;
	private IntegerProperty age ;
	private StringProperty address ;
	private StringProperty staffGroup ;
	private StringProperty classTaking ;
	private StringProperty DOE ;
	private StringProperty DOB ;
	private  NextOfKin kin ;
	private String passport_location ; 


	public Staff(int id , int age , String fn , String on , String address , String contact ,  boolean sex , String DOE , String DOB , String staffGroup , String class_taking , NextOfKin kin ){
		this.id  = new SimpleIntegerProperty(id);
		this.otherName = new SimpleStringProperty(on);
		this.firstName = new SimpleStringProperty(fn);
		this.sex = new SimpleBooleanProperty(sex);
		this.contact = new SimpleStringProperty(contact);
		this.age = new SimpleIntegerProperty(age);
		this.address = new SimpleStringProperty(address);
		this.staffGroup = new SimpleStringProperty(staffGroup);
		this.classTaking = new SimpleStringProperty(class_taking) ;
		this.DOE = new SimpleStringProperty(DOE);
		this.DOB = new SimpleStringProperty(DOB);
		this.kin = kin  ;

	}

	
	

	public String getPassport_location() {
		return passport_location;
	}




	public void setPassport_location(String passport_location) {
		this.passport_location = passport_location;
	}




	public int getId() {
		return id.get();
	}


	public void setId(int id) {
		this.id.set(id);
	}


	public String getOtherName() {
		return otherName.get();
	}


	public void setOtherName(String otherName) {
		this.otherName.set(otherName);
	}


	public String getFirstName() {
		return firstName.get();
	}


	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}


	public boolean getSex() {
		return sex.get();
	}


	public void setSex(boolean sex) {
		this.sex.set(sex);;
	}


	public String getContact() {
		return contact.get();
	}


	public void setContact(String contact) {
		this.contact.set(contact);
	}


	public int getAge() {
		return age.get();
	}


	public void setAge(int age) {
		this.age.set(age);
	}


	public String getAddress() {
		return address.get();
	}


	public void setAddress(String address) {
		this.address.set(address);
	}


	public String getStaffGroup() {
		return staffGroup.get();
	}


	public void setStaffGroup(String staffGroup) {
		this.staffGroup.set(staffGroup);
	}


	public String getClassTaking() {
		return classTaking.get();
	}


	public void setClassTaking(String classTaking) {
		this.classTaking.set(classTaking);
	}


	public String getDOE() {
		return DOE.get();
	}


	public void setDOE(String dOE) {
		DOE.set(dOE);
	}


	public String getDOB() {
		return DOB.get();
	}


	public void setDOB(String dOB) {
		DOB.set(dOB);
	}


	public NextOfKin getKin() {
		return kin;
	}


	public void setKin(NextOfKin kin) {
		this.kin = kin;
	}


	@Override
	public String toString() {
		return "Staff [id=" + id + ", otherName=" + otherName + ", firstName=" + firstName + ", sex=" + sex
				+ ", contact=" + contact + ", age=" + age + ", address=" + address + ", staffGroup=" + staffGroup
				+ ", classTaking=" + classTaking + ", DOE=" + DOE + ", DOB=" + DOB + ", kin=" + kin + "]";
	}








}
