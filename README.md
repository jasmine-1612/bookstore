# Free Online Bookstore 📚
![Java](https://img.shields.io/badge/Java-8+-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-2.5.0-green)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Template-orange)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-yellow)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5-purple)
![GitHub repo size](https://img.shields.io/github/repo-size/YourUsername/bookstore)

---

## 📖 Overview  

The **Free Online Bookstore** is a Spring Boot + Thymeleaf web application where users can **browse, search, and view books at no cost**.  
This project demonstrates how to build a **full-stack Java web application** using Spring Boot, Spring Data JPA, MySQL, and Thymeleaf.  

It is ideal for:
- 📚 Learning Spring Boot web development  
- 🏫 Academic projects & demos  
- 💡 Practicing CRUD operations with JPA  
- 🌐 Creating a responsive web application  

---

## ✨ Features  

### 👩‍💻 User Features  
- 🔑 User Registration & Login (so users can save favorite books)
- 🔎 **Search Books** by name, author, or category  
- 📂 **Browse Categories** (Popular, Kids, Biography, Fiction, etc.)  
- 📖 **View Book Details** (title, author, price, image)  
- 📚 **Available Books Page** with filters  
- 📱 **Responsive UI** with Bootstrap 5  

### 🔐 Admin Features  
- 🛡 **Admin Login** page    
- 🗂 **Manage Books** (view all)  
- 📊 **Interactive Dashboard** with charts:
  - Books by Category 📘  
  - Books by Author ✍️  
  - Monthly Book Additions 📅  

### ⚙️ Technical Features  
- ✅ **Spring Boot MVC** for backend  
- ✅ **Spring Data JPA (Hibernate)** for database queries  
- ✅ **Thymeleaf** for dynamic HTML rendering  
- ✅ **Bootstrap 5** for modern UI styling  
- ✅ **MySQL Database** for data storage  
- ✅ **Chart.js** integration for admin dashboard visualization  

---

## 🏗️ Project Structure

📂 bookstore  
├── 📂 src  
│   ├── 📂 main  
│   │   ├── 📂 java/com/bookstore  
│   │   │   ├── 📂 controller          # Controllers (User & Admin)  
│   │   │   ├── 📂 entity              # JPA Entities (Book, User, Admin)  
│   │   │   ├── 📂 repository          # Repositories (BookRepository, UserRepository)  
│   │   │   └── 📄 BookstoreApplication.java  
│   │   │  
│   │   └── 📂 resources  
│   │       ├── 📂 static              # CSS, JS, Images  
│   │       ├── 📂 templates           # Thymeleaf templates (HTML pages)  
│   │       └── 📄 application.properties  
│   │  
│   └── 📂 test/java/com/bookstore  
│       └── 📄 BookstoreApplicationTests.java  
│  
├── 📄 pom.xml                         # Maven Dependencies  
└── 📄 README.md                       # Documentation  



---

## 📸 Screenshots  

### 🏠 Home Page  
Users can browse books by category and explore popular titles.  

<img width="1895" height="877" alt="image" src="https://github.com/user-attachments/assets/3e236c3a-322f-421c-9b65-1ae0d399d125" />

<img width="1880" height="882" alt="image" src="https://github.com/user-attachments/assets/9eed42c5-1004-4780-a3ac-0100b9b62b2d" />

<img width="1889" height="588" alt="image" src="https://github.com/user-attachments/assets/c8a8d2c3-194b-4420-8c79-93558d557bf5" />




### 📚 Available Books  
A searchable and filterable list of all available books.  

<img width="1895" height="892" alt="image" src="https://github.com/user-attachments/assets/aeb55b01-e47f-4f97-84b3-d84926a27bb2" />


### 🔐 Admin Login  
Secure login page for administrators.  

<img width="1920" height="855" alt="image" src="https://github.com/user-attachments/assets/630a5fcb-ef32-43e9-bf40-2b8b032b4ee0" />


### 📊 Admin Dashboard  
Visual representation of book data.  

<img width="1915" height="894" alt="image" src="https://github.com/user-attachments/assets/f3bd395e-aa20-4f12-b954-9bb41cf1f8d3" />


---

## 🚀 Getting Started  

### 1️⃣ Clone the repository
git clone https://github.com/YourUsername/bookstore.git
cd bookstore



### 2️⃣ Configure the database (MySQL)

src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/bookstore
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update


### 3️⃣ Build & Run

mvn spring-boot:run


### 4️⃣ Access in Browser

User: http://localhost:8080/

Admin: http://localhost:8080/admin/login

---


## 🛠️ Tech Stack

Backend: Spring Boot, Spring Data JPA

Frontend: Thymeleaf, Bootstrap 5, Chart.js

Database: MySQL 8.0+

Build Tool: Maven

Version Control: Git & GitHub

---


## 📜 License

This project is licensed under the MIT License – free to use, modify, and share.

---

## 👩‍💻 Author

Developed by Jasmine ✨
