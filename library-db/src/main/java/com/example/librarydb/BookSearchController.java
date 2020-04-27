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

import com.example.librarydb.models.Books;
import com.example.librarydb.services.BooksService;

@Controller
public class BookSearchController {

	@Autowired
	BooksService bookService;

	@RequestMapping("/booksearch")
	public String blank(Model model) {
		boolean selected[] = new boolean[6];
		model.addAttribute("selected", selected);
		
		return "booksearch";
	}
	
	@RequestMapping(value="/booksearch/search", method=RequestMethod.GET)
	public String form(
					Model model,
					@RequestParam(value="selectedField", required=true) String selectedField,
					@RequestParam(value="searchTerm", required=false, defaultValue="") String searchTerm ) {
		
		boolean selected[] = new boolean[6];
		
		if (selectedField.equals("title")) { selected[0] = true; }
		if (selectedField.equals("author")) { selected[1] = true; }
		if (selectedField.equals("genre")) { selected[2] = true; }
		if (selectedField.equals("maxLength")) { selected[3] = true; }
		if (selectedField.equals("publisher")) { selected[4] = true; }
		if (selectedField.equals("bookid")) { selected[5] = true; }
		
		model.addAttribute("selected", selected);
		model.addAttribute("searchTerm", searchTerm);
		
		
		if (searchTerm.equals("")) {
			List<Books> books = bookService.getAll();
			model.addAttribute("books", books);
			model.addAttribute("error_msg", "");
			
			return "booksearch";
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb", "root", "password");
			Statement s = con.createStatement();
			//String selectStatement = "select * from books where title like '%a%';";
			
			String selectStatement = "select * from books where ";
			
			if (selectedField.equals("title")) { selectStatement += "title like '%" + searchTerm + "%';"; }
			if (selectedField.equals("author")) { selectStatement += "author like '%" + searchTerm + "%';"; }
			if (selectedField.equals("genre")) { selectStatement += "genre like '%" + searchTerm + "%';"; }
			if (selectedField.equals("maxLength")) { selectStatement += "length <= " + searchTerm + ";"; }
			if (selectedField.equals("publisher")) { selectStatement += "publisher like '%" + searchTerm + "%';"; }
			if (selectedField.equals("bookid")) { selectStatement += "bookid = " + searchTerm + ";"; }
			
			ResultSet rs = s.executeQuery(selectStatement);
			List<Books> books = new ArrayList<Books>();
			
			while (rs.next()) {
				Books book = new Books();
				
				book.setTitle( rs.getString(1) );
				book.setAuthor( rs.getString(2) );
				book.setGenre( rs.getString(3) );
				book.setLength( Integer.parseInt( rs.getString(4) ) );
				book.setPublisher( rs.getString(5) );
				book.setBookid( Integer.parseInt( rs.getString(6) ) );
				
				books.add(book);
			}
			
			model.addAttribute("books", books);
			model.addAttribute("error_msg", "");
			
		}
		catch (Exception e) {
			System.out.println(e);
			model.addAttribute("error_msg", "Query cannot be executed");
		}
		
		return "booksearch";
	}
}
