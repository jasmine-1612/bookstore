package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bookstore.entity.Book;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByCategory(String name);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByCategoryIgnoreCase(String category);
    List<Book> findByNameContainingIgnoreCase(String search);

    // Count by category
    Long countByCategory(String category);

    // Books by author
    @Query("SELECT b.author, COUNT(b) FROM Book b GROUP BY b.author")
    List<Object[]> countBooksByAuthor();

    // Books added per month (based on createdAt)
    @Query("SELECT COUNT(b) FROM Book b WHERE FUNCTION('MONTH', b.createdAt) = :month")
    Long countBooksAddedInMonth(int month);

    // Top books (added by users)
    @Query("SELECT b.name, COUNT(u) FROM User u JOIN u.books b GROUP BY b.name ORDER BY COUNT(u) DESC")
    List<Object[]> findTopBooksByUsers();
}
