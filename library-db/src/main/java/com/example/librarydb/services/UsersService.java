package com.example.librarydb.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarydb.models.Users;
import com.example.librarydb.repositories.UsersRepository;
/**
 * Lets the application retrieve and add to MySQL Database
 * @author JB
 *
 */
@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;
	
	public List<Users> getAll() {
		return (List<Users>) usersRepository.findAll();
	}
	
	public void addNew(Users users) {
		usersRepository.save(users);
	}
}
