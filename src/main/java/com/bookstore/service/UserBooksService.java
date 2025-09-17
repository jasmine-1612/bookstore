package com.bookstore.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Book;
import com.bookstore.entity.User;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.UserRepository;

@Service
public class UserBooksService {
	@Autowired
	
	private UserRepository userRepository;
	
	@Autowired 
	private BookRepository bookRepository;

	
	public User addBookToUser(Long userId, Integer bookId) {
		User user = userRepository.findById(userId).orElseThrow();
		Book book= bookRepository.findById(bookId).orElseThrow();
		user.getBooks().add(book);
		return userRepository.save(user);
	}
	
	public User removeBookFromUser(Long userId, Integer bookId) {
		User user = userRepository.findById(userId).orElseThrow();
		Book book= bookRepository.findById(bookId).orElseThrow();
		user.getBooks().remove(book);
		return userRepository.save(user);
	}

	public Set<Book> getBooksByUser(Long userId) {
		User user= userRepository.findById(userId).orElseThrow();
		// TODO Auto-generated method stub
		return user.getBooks();
	}
	

}
