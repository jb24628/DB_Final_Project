package com.example.librarydb.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Checkedout {
	Date date;
	@Id
	Integer bookid;
	Integer userid;
	
	public Checkedout() {
		super();
	}

	public Checkedout(Date date, Integer bookid, Integer userid) {
		super();
		this.date = date;
		this.bookid = bookid;
		this.userid = userid;
	}
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getBookid() {
		return bookid;
	}

	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
}
