package com.example.librarydb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.librarydb.models.Checkedout;
import com.example.librarydb.services.CheckedoutService;

/**
 * handles mappings for checkedouts
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
	
	/**
	 * Shows 
	 * @param model what stores what we put on the website
	 * @return the html page to go to (checkedouts.html)
	 */
	@RequestMapping("/getAll")
	public String getAll(Model model) {
		List<Checkedout> checkedouts = checkedoutService.getAll();
		model.addAttribute("checkedouts", checkedouts);
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
