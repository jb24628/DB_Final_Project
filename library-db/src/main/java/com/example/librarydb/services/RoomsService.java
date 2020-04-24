package com.example.librarydb.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarydb.models.Rooms;
import com.example.librarydb.repositories.RoomsRepository;
/**
 * Lets the application retrieve and add to MySQL Database
 * @author JB
 *
 */
@Service
public class RoomsService {

	@Autowired
	private RoomsRepository roomsRepository;
	
	public List<Rooms> getAll() {
		return (List<Rooms>) roomsRepository.findAll();
	}
	
	public void addNew(Rooms rooms) {
		roomsRepository.save(rooms);
	}
}
