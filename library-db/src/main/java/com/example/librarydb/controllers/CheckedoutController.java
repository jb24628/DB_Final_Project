package com.example.librarydb.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.librarydb.models.Books;
import com.example.librarydb.models.Checkedout;
import com.example.librarydb.models.Users;
import com.example.librarydb.services.BooksService;
import com.example.librarydb.services.CheckedoutService;
import com.example.librarydb.services.UsersService;

/**
 * handles mappings for checkedout books
 * @author JB
 *
 */
@Controller
@RequestMapping("/checkedouts")
public class CheckedoutController {
	/**
	 * Interacts with the database
	 */
	@Autowired
	private CheckedoutService checkedoutService;
	@Autowired
	private BooksService booksService;
	@Autowired
	private UsersService usersService;
	/**
	 * Shows 
	 * @param model what stores what we put on the website
	 * @return the html page to go to (checkedouts.html)
	 */
	@RequestMapping("/getAll")
	public String getAll(Model model) {
		List<Checkedout> checkedouts = checkedoutService.getAll();
		List<Books> books = booksService.getAll();
		List<Users> users = usersService.getAll();
		
		for (int i = 0; i < books.size(); i++) { //makes sure you cannot see a book that is already checked out
			int tempID = books.get(i).getBookid();
			for (int j = 0; j < checkedouts.size(); j++) {
				if (tempID == checkedouts.get(j).getBookid()) {
					books.remove(i);
					continue;
				}
			}
		}
		
		model.addAttribute("checkedouts", checkedouts);
		model.addAttribute("curDate", new Date(System.currentTimeMillis()) );
		model.addAttribute("books", books);
		model.addAttribute("users", users);
		return "checkedouts";
	}
	
	/**
	 * Adds new checkedout to the database
	 * @param checkedout the checkedout to add
	 * @return the html page to go to (checkedouts.html)
	 */
	@PostMapping("/addNew")
	public String addNew(Checkedout checkedout) {
		checkedoutService.addNew(checkedout); //adds checkedout to checkedout table
		return "redirect:/checkedouts/getAll"; //redirects back to all the reults
	}
}
