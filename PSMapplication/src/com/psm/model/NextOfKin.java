package com.psm.model;

public class NextOfKin {


	private String name ;
	private String contact ;

	public NextOfKin(String name , String contact){

		this.name = name ;
		this.contact = contact ;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "NextOfKin [name=" + name + ", contact=" + contact + "]";
	}



}

