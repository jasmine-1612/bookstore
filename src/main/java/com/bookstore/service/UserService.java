package com.bookstore.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Book;
import com.bookstore.entity.User;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	public User authenticate(String email, String password) {
		User user= userRepository.findByEmail(email);
		if(user!= null && validatePassword(password, user.getPassword())) {
			return user;
		}
		return null;
	}


	private boolean validatePassword(String inputpassword, String storedPasswordHash) {
		// TODO Auto-generated method stub
		return inputpassword.equals(storedPasswordHash);
	}

	public ArrayList<User> getAllUser(){
		return (ArrayList<User>) userRepository.findAll();
	}
	public String addUser(User newUser) {
		userRepository.save(newUser);
		return "Successfully inserted a new user";
		
	}
	public User getUserInfo(Long id) {
		Optional<User> user= userRepository.findById(id);
		return user.orElse(null);
	}
	
	public boolean emailexists(String email) {
		return userRepository.findByEmail(email)!= null;
		
	}
	
	public User addBookToUser(Long userId , Integer bookId) {
 		User user= userRepository.findById(userId).orElseThrow();
 		Book book= bookRepository.findById(bookId).orElseThrow();
 		user.getBooks().add(book);
 		return userRepository.save(user);
	}
	
	
	public User removeBookFromUser(Long userId, Integer bookId) {
		User user= userRepository.findById(userId).orElseThrow();
		Book book= bookRepository.findById(bookId).orElseThrow();
		user.getBooks().remove(book);
		return userRepository.save(user);
	}

	
	}
	




