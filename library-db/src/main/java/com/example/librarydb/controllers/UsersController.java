package com.example.librarydb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.librarydb.models.Users;
import com.example.librarydb.services.UsersService;

/**
 * handles mappings for userss
 * @author JB
 *
 */
@Controller
@RequestMapping("/users")
public class UsersController {
	/**
	 * Interacts with the database
	 */
	@Autowired
	private UsersService usersService;
	
	/**
	 * Shows 
	 * @param model what stores what we put on the website
	 * @return the html page to go to (userss.html)
	 */
	@RequestMapping("/getAll")
	public String getAll(Model model) {
		List<Users> users = usersService.getAll();
		
		int maxID = users.get(0).getId();
		
		for (int i = 1; i < users.size(); i++) {
			if (maxID < users.get(i).getId()) {
				maxID = users.get(i).getId();
			}
		}
		
		model.addAttribute("users", users);
		model.addAttribute("newID", maxID+1);
		return "users";
	}
	
	/**
	 * Adds new users to the database
	 * @param users the users to add
	 * @return the html page to go to (userss.html)
	 */
	@PostMapping("/addNew")
	public String addNew(Users users) {
		usersService.addNew(users); //adds users to users table
		return "redirect:/users/getAll"; //redirects back to all the reults
	}
}
