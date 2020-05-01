package com.example.librarydb.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.librarydb.models.Rooms;
import com.example.librarydb.services.RoomsService;

/**
 * handles mappings for roomss
 * @author JB
 *
 */
@Controller
@RequestMapping("/rooms")
public class RoomsController {
	/**
	 * Interacts with the database
	 */
	@Autowired
	private RoomsService roomsService;
	
	/**
	 * Shows 
	 * @param model what stores what we put on the website
	 * @return the html page to go to (roomss.html)
	 */
	@RequestMapping("/getAll")
	public String getAll(Model model) {
		List<Rooms> rooms = roomsService.getAll();
		model.addAttribute("rooms", rooms);
		return "rooms";
	}
	
	/**
	 * Adds new rooms to the database
	 * @param rooms the rooms to add
	 * @return the html page to go to (rooms.html)
	 */
	@PostMapping("/addNew")
	public String addNew(Rooms rooms) {
		roomsService.addNew(rooms); //adds rooms to rooms table
		return "redirect:/rooms/getAll"; //redirects back to all the results
	}
	
	
	@RequestMapping("/getOne")
	@ResponseBody
	public Optional<Rooms> getOne(Integer roomno) {
		return roomsService.getOne(roomno);
	}
}
