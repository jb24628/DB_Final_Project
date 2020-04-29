package com.example.librarydb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.librarydb.models.Users;
import com.example.librarydb.services.UsersService;

@Controller
public class UserSearchController {

	@Autowired
	UsersService userService;

	@RequestMapping("/usersearch")
	public String blank(Model model) {
		boolean selected[] = new boolean[6];
		model.addAttribute("selected", selected);
		
		return "usersearch";
	}
	
	@RequestMapping(value="/usersearch/search", method=RequestMethod.GET)
	public String form(
					Model model,
					@RequestParam(value="selectedField", required=true) String selectedField,
					@RequestParam(value="searchTerm", required=false, defaultValue="") String searchTerm ) {
		
		boolean selected[] = new boolean[5];
		
		if (selectedField.equals("id")) { selected[0] = true; }
		if (selectedField.equals("lname")) { selected[1] = true; }
		if (selectedField.equals("fname")) { selected[2] = true; }
		if (selectedField.equals("sex")) { selected[3] = true; }
		if (selectedField.equals("address")) { selected[4] = true; }
		
		model.addAttribute("selected", selected);
		model.addAttribute("searchTerm", searchTerm);
		
		
		if (searchTerm.equals("")) {
			List<Users> users = userService.getAll();
			model.addAttribute("users", users);
			model.addAttribute("error_msg", "");
			
			return "usersearch";
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb", "root", "password");
			Statement s = con.createStatement();
			//String selectStatement = "select * from users where title like '%a%';";
			
			String selectStatement = "select * from users where ";
			
			if (selectedField.equals("id")) { selectStatement += "id like '%" + searchTerm + "%';"; }
			if (selectedField.equals("lname")) { selectStatement += "lname like '%" + searchTerm + "%';"; }
			if (selectedField.equals("fname")) { selectStatement += "fname like '%" + searchTerm + "%';"; }
			if (selectedField.equals("sex")) { selectStatement += "sex = " + searchTerm + ";"; }
			if (selectedField.equals("address")) { selectStatement += "address like '%" + searchTerm + "%';"; }

			
			ResultSet rs = s.executeQuery(selectStatement);
			List<Users> users = new ArrayList<Users>();
			
			while (rs.next()) {
				Users user = new Users();
				
				user.setId( Integer.parseInt(rs.getString(1)) );
				user.setFname( rs.getString(2) );
				user.setLname( rs.getString(3) );
				user.setSex( rs.getString(4) );
				user.setAddress( rs.getString(5) );

				users.add(user);
			}
			
			model.addAttribute("users", users);
			model.addAttribute("error_msg", "");
			
		}
		catch (Exception e) {
			System.out.println(e);
			model.addAttribute("error_msg", "Search Failed. Error: " + e);
		}
		
		return "usersearch";
	}
}
