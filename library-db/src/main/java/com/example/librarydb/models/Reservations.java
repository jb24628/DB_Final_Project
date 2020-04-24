package com.example.librarydb.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reservations {
	Timestamp reservetime;
	Integer roomno;
	@Id
	Integer reservationid;
	
	public Reservations() {
		super();
	}


	public Reservations(Timestamp reservetime, Integer roomno, Integer reservationid) {
		super();
		this.reservetime = reservetime;
		this.roomno = roomno;
		this.reservationid = reservationid;
	}


	public Timestamp getReservetime() {
		return reservetime;
	}

	public void setReservetime(Timestamp reservetime) {
		this.reservetime = reservetime;
	}

	public Integer getRoomno() {
		return roomno;
	}

	public void setRoomno(Integer roomno) {
		this.roomno = roomno;
	}


	public Integer getReservationid() {
		return reservationid;
	}


	public void setReservationid(Integer reservationid) {
		this.reservationid = reservationid;
	}
	
	
}
