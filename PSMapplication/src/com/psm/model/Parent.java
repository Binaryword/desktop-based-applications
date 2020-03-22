package com.psm.model;

public class Parent {

	String family_name  ;
	String family_contact ;
	String family_address ;

	public Parent(String name , String contact , String address){

		this.family_name = name ;
		this.family_contact = contact ;
		this.family_address = address ;
	}

	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	public String getFamily_contact() {
		return family_contact;
	}

	public void setFamily_contact(String family_contact) {
		this.family_contact = family_contact;
	}

	public String getFamily_address() {
		return family_address;
	}

	public void setFamily_address(String family_address) {
		this.family_address = family_address;
	}

	@Override
	public String toString() {
		return "Parent [family_name=" + family_name + ", family_contact=" + family_contact + ", family_address="
				+ family_address + "]";
	}





}
