package com.example.librarydb.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users {

	@Id
	Integer id;
	String fname;
	String lname;
	String sex;
	String address;
	
	public Users() {
		super();
	}

	public Users(Integer id, String fname, String lname, String sex, String address) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.sex = sex;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String toString() {
		return id + " " + fname + " " + lname;
	}
}
