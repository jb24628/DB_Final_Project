package com.example.librarydb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.librarydb.models.Reservations;
import com.example.librarydb.services.ReservationsService;

/**
 * handles mappings for reservationss
 * @author JB
 *
 */
@Controller
@RequestMapping("/reservations")
public class ReservationsController {
	/**
	 * Interacts with the database
	 */
	@Autowired
	private ReservationsService reservationsService;
	
	/**
	 * Shows 
	 * @param model what stores what we put on the website
	 * @return the html page to go to (reservationss.html)
	 */
	@RequestMapping("/getAll")
	public String getAll(Model model) {
		List<Reservations> reservations = reservationsService.getAll();
		model.addAttribute("reservations", reservations);
		return "reservations";
	}
	
	/**
	 * Adds new reservations to the database
	 * @param reservations the reservations to add
	 * @return the html page to go to (reservationss.html)
	 */
	@PostMapping("/addNew")
	public String addNew(Reservations reservations) {
		reservationsService.addNew(reservations); //adds reservations to reservations table
		return "redirect:/reservations/getAll"; //redirects back to all the reults
	}
}
