package com.example.librarydb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.librarydb.models.Books;
import com.example.librarydb.services.BooksService;

/**
 * handles mappings for bookss
 * @author JB
 *
 */
@Controller
@RequestMapping("/books")
public class BooksController {
	/**
	 * Interacts with the database
	 */
	@Autowired
	private BooksService booksService;
	
	/**
	 * Shows 
	 * @param model what stores what we put on the website
	 * @return the html page to go to (bookss.html)
	 */
	@RequestMapping("/getAll")
	public String getAll(Model model) {
		List<Books> books = booksService.getAll();
		model.addAttribute("books", books);
		return "books";
	}
	
	/**
	 * Adds new books to the database
	 * @param books the books to add
	 * @return the html page to go to (bookss.html)
	 */
	@PostMapping("/addNew")
	public String addNew(Books books) {
		booksService.addNew(books); //adds books to books table
		return "redirect:/books/getAll"; //redirects back to all the reults
	}
}
