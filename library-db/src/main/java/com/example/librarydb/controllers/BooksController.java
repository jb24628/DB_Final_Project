package com.example.librarydb.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.librarydb.models.Books;
import com.example.librarydb.services.BooksService;
import com.example.librarydb.services.CheckedoutService;

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
	@Autowired
	private CheckedoutService checkedoutService;
	
	/**
	 * Shows 
	 * @param model what stores what we put on the website
	 * @return the html page to go to (bookss.html)
	 */
	@RequestMapping("/getAll")
	public String getAll(Model model) {
		List<Books> books = booksService.getAll();
		
		int maxID = books.get(0).getBookid();
		
		for (int i = 1; i < books.size(); i++) {
			if (maxID < books.get(i).getBookid()) {
				maxID = books.get(i).getBookid();
			}
		}
		
		model.addAttribute("newID", maxID+1);
		
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
		return "redirect:/books/getAll"; //redirects back to all the results
	}
	
	@RequestMapping(value="/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Books books) {
		booksService.update(books);
		return "redirect:/books/getAll"; //redirects back to all the results
	}
	
	@RequestMapping("/getOne")
	@ResponseBody
	public Optional<Books> getOne(Integer bookid) {
		return booksService.getOne(bookid);
	}
	
	@RequestMapping(value="/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer bookid) {
		checkedoutService.delete(bookid);
		booksService.delete(bookid);
		
		return "redirect:/books/getAll";
	}
}
