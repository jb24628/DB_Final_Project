package com.example.librarydb.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarydb.models.Reservations;
import com.example.librarydb.repositories.ReservationsRepository;
/**
 * Lets the application retrieve and add to MySQL Database
 * @author JB
 *
 */
@Service
public class ReservationsService {

	@Autowired
	private ReservationsRepository reservationsRepository;
	
	public List<Reservations> getAll() {
		return (List<Reservations>) reservationsRepository.findAll();
	}
	
	public void addNew(Reservations reservations) {
		reservationsRepository.save(reservations);
	}
}
