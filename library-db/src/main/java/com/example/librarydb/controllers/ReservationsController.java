package com.example.librarydb.controllers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.librarydb.models.Reservations;
import com.example.librarydb.models.Rooms;
import com.example.librarydb.models.Users;
import com.example.librarydb.services.ReservationsService;
import com.example.librarydb.services.RoomsService;
import com.example.librarydb.services.UsersService;

/**
 * handles mappings for reservations
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
	@Autowired
	private UsersService usersService;
	@Autowired
	private RoomsService roomsService;
	/**
	 * Shows 
	 * @param model what stores what we put on the website
	 * @return the html page to go to (reservationss.html)
	 */
	@RequestMapping("/getAll")
	public String getAll(Model model) {
		List<Reservations> reservations = reservationsService.getAll();
		List<Users> users = usersService.getAll();
		List<Rooms> rooms = roomsService.getAll();
		
		int maxID = reservations.get(0).getReservationid();
		
		for (int i = 1; i < reservations.size(); i++) {
			if (maxID < reservations.get(i).getReservationid()) {
				maxID = reservations.get(i).getReservationid();
			}
		}
		
		model.addAttribute("newID", maxID+1);
		
		model.addAttribute("rooms", rooms);
		model.addAttribute("reservations", reservations);
		model.addAttribute("users", users);
		model.addAttribute("curTime", new Timestamp(System.currentTimeMillis()) ) ;
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
	
	@RequestMapping(value="/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Reservations reservations) {
		reservationsService.update(reservations);
		return "redirect:/reservations/getAll"; //redirects back to all the results
	}
	
	@RequestMapping("/getOne")
	@ResponseBody
	public Optional<Reservations> getOne(Integer reservationid) {
		return reservationsService.getOne(reservationid);
	}
	
	@RequestMapping(value="/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer reservationid) {
		reservationsService.delete(reservationid);
		return "redirect:/reservations/getAll";
	}
}
