package com.bookstore.entity;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
@Entity
@Table(name="users")
public class User {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
@Column(unique = true, nullable= false)
private String email;
private String password;

@ManyToMany
@JoinTable(
		name="user_books",
		joinColumns = @JoinColumn(name="user_id"),
		inverseJoinColumns= @JoinColumn(name="book_id")
		)

private Set<Book> books = new HashSet<>();

public User(Set<Book> books) {
	super();
	this.books = books;
}

public User() {}

public User(Long id, String name, String email, String password) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.password = password;
}

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public Set<Book> getBooks() {
	return books;
}

public void setBooks(Set<Book> books) {
	this.books = books;
}


}
