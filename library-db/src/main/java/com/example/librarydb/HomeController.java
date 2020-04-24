package com.example.librarydb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Directs website to home page
 * @author JB
 *
 */
@Controller	
public class HomeController {
	/**
	 * Makes /home go to the index.html page
	 * @return the name of the page to go to (home)
	 */
	@RequestMapping("/home")
	public String test() {
		return "index";
	}
}