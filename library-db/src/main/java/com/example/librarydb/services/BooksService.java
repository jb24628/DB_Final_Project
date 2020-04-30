package com.example.librarydb.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarydb.models.Books;
import com.example.librarydb.repositories.BooksRepository;
/**
 * Lets the application retrieve and add to MySQL Database
 * @author JB
 *
 */
@Service
public class BooksService {

	@Autowired
	private BooksRepository booksRepository;
	
	public List<Books> getAll() {
		return (List<Books>) booksRepository.findAll();
	}
	
	public void addNew(Books books) {
		booksRepository.save(books);
	}
	
	public void update(Books books) {
		booksRepository.save(books);
	}
	
	public Optional<Books> getOne(Integer bookid) {
		return booksRepository.findById(bookid);
	}
	
	public void delete(Integer bookid) {
		booksRepository.deleteById(bookid);
	}
}
