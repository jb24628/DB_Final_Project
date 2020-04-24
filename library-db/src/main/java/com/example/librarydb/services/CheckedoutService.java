package com.example.librarydb.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarydb.models.Checkedout;
import com.example.librarydb.repositories.CheckedoutRepository;
/**
 * Lets the application retrieve and add to MySQL Database
 * @author JB
 *
 */
@Service
public class CheckedoutService {

	@Autowired
	private CheckedoutRepository checkedoutRepository;
	
	public List<Checkedout> getAll() {
		return (List<Checkedout>) checkedoutRepository.findAll();
	}
	
	public void addNew(Checkedout checkedout) {
		checkedoutRepository.save(checkedout);
	}
}
