package com.bookstore.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.entity.Book;
import com.bookstore.entity.User;
import com.bookstore.service.UserBooksService;
import com.bookstore.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserBooksController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserBooksService userBooksService;
	
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam String email,
			                @RequestParam String password,
			                HttpSession session,
			                Model model
			) {
		User user = userService.authenticate(email,password);
		if(user!=null) {
			session.setAttribute("userId" , user.getId());
			session.setAttribute("userName", user.getName());
			return "redirect:/myBooks";
			
		}
		else {
			model.addAttribute("error", "Invalid email or password");
			return "login";
		}
	}
	                                       
@GetMapping("/register")
public String registerPage() {
	return "register";
}

@PostMapping("/register")
public String registerUser(@RequestParam String name,
		                   @RequestParam String email,
		                   @RequestParam String password,
		                   HttpSession session,
		                   Model model 
		) {
	if (userService.emailexists(email)) {
		model.addAttribute("error", "email already exists");
		return "register";
	}
	
	User user = new User();
	user.setName(name);
	user.setEmail(email);
	user.setPassword(password);
	userService.addUser(user);
	
	
	session.setAttribute("userId", user.getId());
	session.setAttribute("userName", user.getName());
	
	return "redirect:/myBooks";
	}




@GetMapping("/logout")

public String logout(HttpSession session) {
	session.invalidate();
	return "redirect:/login";
	
	

}


// show My Books

@GetMapping("/myBooks")
public String myBooks(HttpSession session, Model model) {
    // 1️⃣ Check if user is logged in
    Long userId = (Long) session.getAttribute("userId");
    if (userId == null) {
        // 2️⃣ Not logged in → redirect to login page
        return "redirect:/login";
    
    }
    // 3️⃣ Fetch books for this user
    Set<Book> myBooks = userBooksService.getBooksByUser(userId);
    model.addAttribute("myBooks", myBooks);

    // 4️⃣ Return Thymeleaf page
    return "books";
}





@PostMapping("/userbooks/add/{bookId}")
public String addBook(@PathVariable Integer bookId, HttpSession session) {
    Long userId = (Long) session.getAttribute("userId");
    if (userId != null) {
        userBooksService.addBookToUser(userId, bookId);
    }
    return "redirect:/myBooks";
}

@PostMapping("/userbooks/remove/{bookId}")
public String removeBook(@PathVariable Integer bookId, HttpSession session) {
    Long userId = (Long) session.getAttribute("userId");
    if (userId != null) {
        userBooksService.removeBookFromUser(userId, bookId);
    }
    return "redirect:/myBooks";
}
   
}

















