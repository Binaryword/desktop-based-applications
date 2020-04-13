package com.binary.csvio;

import java.io.Serializable;


public class Student implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private int id ; 
	private String name ; 
	private boolean sex ; 
	private int age ;
	
	public Student(int id, String name, boolean sex, int age) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	
	public String[] toCSV(){
		return String.format("%d,%s,%b,%d", getId() , getName() , isSex() , getAge()).split(","); 
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}
	
	

}
