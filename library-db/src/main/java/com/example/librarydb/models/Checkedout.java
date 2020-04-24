package com.example.librarydb.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Checkedout {
	Date date;
	@Id
	Integer bookid;
	
	public Checkedout() {
		super();
	}

	public Checkedout(Date date, Integer bookid) {
		super();
		this.date = date;
		this.bookid = bookid;
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
