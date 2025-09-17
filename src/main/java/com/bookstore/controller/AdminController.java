package com.bookstore.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.entity.Book;
import com.bookstore.entity.User;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    private final String ADMIN_EMAIL = "admin@bookstore.com";
    private final String ADMIN_PASSWORD = "admin123";

    public AdminController(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "admin-login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email, @RequestParam String password,
                          Model model, HttpSession session) {
        if (ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password)) {
            session.setAttribute("isAdminLoggedIn", true);
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("error", "Invalid credential");
            return "admin-login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("isAdminLoggedIn") != null &&
            (boolean) session.getAttribute("isAdminLoggedIn")) {

            List<Book> allBooks = bookRepository.findAll();
            List<User> allUsers = userRepository.findAll();

            // Count books by category
            Map<String, Long> categoryCount = new LinkedHashMap<>();
            categoryCount.put("Popular", bookRepository.countByCategory("Popular"));
            categoryCount.put("Kids", bookRepository.countByCategory("Kids"));
            categoryCount.put("Biography", bookRepository.countByCategory("Biography"));
            categoryCount.put("Fiction", bookRepository.countByCategory("Fiction"));

            // Count books added per month
            Map<String, Integer> monthMap = new LinkedHashMap<>();
            monthMap.put("Jan", 1); monthMap.put("Feb", 2); monthMap.put("Mar", 3); monthMap.put("Apr", 4);
            monthMap.put("May", 5); monthMap.put("Jun", 6); monthMap.put("Jul", 7); monthMap.put("Aug", 8);
            monthMap.put("Sep", 9); monthMap.put("Oct", 10); monthMap.put("Nov", 11); monthMap.put("Dec", 12);

            Map<String, Long> booksPerMonth = new LinkedHashMap<>();
            monthMap.forEach((monthName, monthNumber) -> {
                booksPerMonth.put(monthName, bookRepository.countBooksAddedInMonth(monthNumber));
            });

            // Pass data to Thymeleaf template
            model.addAttribute("allBooks", allBooks);
            model.addAttribute("allUsers", allUsers);
            model.addAttribute("categoryCount", categoryCount);
            model.addAttribute("booksPerMonth", booksPerMonth);

            return "admin-dashboard";

        } else {
            return "redirect:/admin/login";
        }
    }
    @GetMapping("/charts")
    public String viewCharts(HttpSession session, Model model) {
        if (session.getAttribute("isAdminLoggedIn") != null &&
            (boolean) session.getAttribute("isAdminLoggedIn")) {

            // 1. Books by Category
            Map<String, Long> categoryCount = new LinkedHashMap<>();
            categoryCount.put("Popular", bookRepository.countByCategory("Popular"));
            categoryCount.put("Kids", bookRepository.countByCategory("Kids"));
            categoryCount.put("Biography", bookRepository.countByCategory("Biography"));
            categoryCount.put("Fiction", bookRepository.countByCategory("Fiction"));

            model.addAttribute("categories", categoryCount.keySet());
            model.addAttribute("categoryCounts", categoryCount.values());

            // 2. Books by Author
            List<Object[]> authorData = bookRepository.countBooksByAuthor();
            List<String> authors = new ArrayList<>();
            List<Long> authorCounts = new ArrayList<>();
            for (Object[] row : authorData) {
                authors.add((String) row[0]);
                authorCounts.add((Long) row[1]);
            }
            model.addAttribute("authors", authors);
            model.addAttribute("authorCounts", authorCounts);

            // 3. Books Added Per Month
            Map<String, Integer> monthMap = new LinkedHashMap<>();
            monthMap.put("Jan", 1); monthMap.put("Feb", 2); monthMap.put("Mar", 3);
            monthMap.put("Apr", 4); monthMap.put("May", 5); monthMap.put("Jun", 6);
            monthMap.put("Jul", 7); monthMap.put("Aug", 8); monthMap.put("Sep", 9);
            monthMap.put("Oct", 10); monthMap.put("Nov", 11); monthMap.put("Dec", 12);

            List<String> months = new ArrayList<>();
            List<Long> monthlyCounts = new ArrayList<>();
            monthMap.forEach((monthName, monthNumber) -> {
                months.add(monthName);
                monthlyCounts.add(bookRepository.countBooksAddedInMonth(monthNumber));
            });
            model.addAttribute("months", months);
            model.addAttribute("monthlyCounts", monthlyCounts);

            // 4. Top Books by Users
            List<Object[]> topBooksData = bookRepository.findTopBooksByUsers();
            List<String> topBooks = new ArrayList<>();
            List<Long> topBookCounts = new ArrayList<>();
            for (Object[] row : topBooksData) {
                topBooks.add((String) row[0]);
                topBookCounts.add((Long) row[1]);
            }
            model.addAttribute("topBooks", topBooks);
            model.addAttribute("topBookCounts", topBookCounts);

            return "admin-charts";
        } else {
            return "redirect:/admin/login";
        }
    }


    @GetMapping("/book_register")
    public String bookRegister(HttpSession session) {
        if (session.getAttribute("isAdminLoggedIn") != null &&
            (boolean) session.getAttribute("isAdminLoggedIn")) {
            return "bookRegister";
        } else {
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/users")
    public String viewUsers(HttpSession session, Model model) {
        if (session.getAttribute("isAdminLoggedIn") != null &&
            (boolean) session.getAttribute("isAdminLoggedIn")) {

            List<User> users = userRepository.findAll();
            model.addAttribute("users", users);
            return "admin-users";

        } else {
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}
