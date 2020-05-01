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

import com.example.librarydb.models.Checkedout;
import com.example.librarydb.models.Reservations;
import com.example.librarydb.models.Users;
import com.example.librarydb.services.CheckedoutService;
import com.example.librarydb.services.ReservationsService;
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
	@Autowired
	private CheckedoutService checkedoutService;
	@Autowired
	private ReservationsService reservationsService;
	
	
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
	
	
	@RequestMapping(value="/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Users users) {
		usersService.update(users);
		return "redirect:/users/getAll"; //redirects back to all the results
	}
	
	@RequestMapping("/getOne")
	@ResponseBody
	public Optional<Users> getOne(Integer id) {
		return usersService.getOne(id);
	}
	
	@RequestMapping(value="/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		
		List<Checkedout> checkedout = checkedoutService.getAll();
		List<Reservations> reservations = reservationsService.getAll();
		
		for (Checkedout co : checkedout) {
			if ((int) co.getUserid() == (int) id) {
				checkedoutService.delete(co.getBookid());
			}
		}
		
		for (Reservations r : reservations) {
			if ((int) r.getUserid() == (int) id) {
				reservationsService.delete(r.getReservationid());
			}
		}
		
		usersService.delete(id);
		
		return "redirect:/users/getAll";
	}
}
