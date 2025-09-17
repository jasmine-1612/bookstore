package com.bookstore.controller;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entity.User;
import com.bookstore.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService UserService;
	
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user){
		System.out.println(user.getName());
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		if(user.getName()== null || user.getEmail()==null || user.getPassword()== null) {
			return ResponseEntity.badRequest().body("All fields are required");
		}
		
	
	if(UserService.emailexists(user.getEmail())) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Exists");
	}

	UserService.addUser(user);
	return ResponseEntity.ok("new registration");
		
	}
	
	
@PostMapping("/login")
public ResponseEntity <?>login(@RequestBody Map <String , String> Body){
String email = Body.get("email");
String password = Body.get("password");
if(email==null || password== null) {
	return ResponseEntity.badRequest().body("email and password required");
}

User user=UserService.authenticate(email, password);
if (user!= null) {
	Map<String, Object> response= new HashMap<String, Object>();
	response.put("status", "Success");
	response.put("message", "Login successful");
	response.put("name", user.getName());
	response.put("id", user.getId());
	response.put("books", user.getBooks());
	
	
	
	System.out.println(response.getClass());
	return ResponseEntity.ok(response);
}else
{
	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid");
}
	
}



}