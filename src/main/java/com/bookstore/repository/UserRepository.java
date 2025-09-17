package com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.bookstore.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	List<User> findByNameContainingIgnoreCase(String name);
	List<User> findDistinctByBooksIsNotEmpty();
	long countByBooks_Name(String bookName);

	
	

}
