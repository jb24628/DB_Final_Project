package com.example.librarydb.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Books {

	String title;
	String author;
	String genre;
	Integer length;
	String publisher;
	@Id
	Integer bookid;
	
	public Books() {
		super();
	}
	
	public Books(String title, String author, String genre, Integer length, String publisher, Integer bookid) {
		super();
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.length = length;
		this.publisher = publisher;
		this.bookid = bookid;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	
	public String toString() {
		return bookid + " " + title + " by " + author;
	}

}
