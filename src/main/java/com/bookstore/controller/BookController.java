package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookController {

	 @Autowired
	    private BookRepository bookRepository;  // Correct type and case

	    @GetMapping("/")
	    public String home(Model model) {
	        // Get Popular books
	    	 List<Book> popularBooks = bookRepository.findByCategory("Popular");
	    	    List<Book> kidsBooks = bookRepository.findByCategory("Kids");

	    	    model.addAttribute("popularBooks", popularBooks);
	    	    model.addAttribute("kidsBooks", kidsBooks);
	        return "home";
	    }

	    @GetMapping("/book_register")
	    public String bookRegister(HttpSession session) {
	        if (session.getAttribute("admin") == null) {
	            return "redirect:/admin/login"; // redirect if not logged in
	        }
	        return "bookRegister"; // show page if logged in
	    }


	    @GetMapping("/available_books")
	    public String getAllBooks(
	    		@RequestParam(value= "search", required=false) String search,
	    		@RequestParam(value= "author", required= false) String author,
	    		@RequestParam(value= "category", required =false) String category,
	    		
	    		Model model) {
	        List<Book> books;
	        if(search!= null && !search.isEmpty()) {
	        	books= bookRepository.findByNameContainingIgnoreCase(search);
	        }
	        else if(author!= null && !author.isEmpty()) {
	        	books= bookRepository.findByAuthorContainingIgnoreCase(author);
	        	
	        }
	        else if(category!=null && !category.isEmpty()) {
	        	books= bookRepository.findByCategoryIgnoreCase(category);
	        }
	        else {
	       books = bookRepository.findAll(); 
	        }
	        model.addAttribute("books", books);         
	        return "bookList";
	    }

	    }

