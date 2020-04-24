package com.example.librarydb.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rooms {
	@Id
	Integer roomno;
	Integer maxcap;
	
	public Rooms() {
		super();
	}
	public Rooms(Integer roomno, Integer maxcap) {
		super();
		this.roomno = roomno;
		this.maxcap = maxcap;
	}
	public Integer getRoomno() {
		return roomno;
	}
	public void setRoomno(Integer roomno) {
		this.roomno = roomno;
	}
	public Integer getMaxcap() {
		return maxcap;
	}
	public void setMaxcap(Integer maxcap) {
		this.maxcap = maxcap;
	}
}
