# Spring Boot REST API Assignment - Questions 1-5 & Bonus

**Student Name:** Ishimwe Amos  
**Student ID:** 26247  
**Branch:** `restFull_api_26247`

---

## Overview

This project implements a comprehensive Spring Boot REST API with multiple microservices covering:
- Question 1: Library Book Management API
- Question 2: Student Registration API
- Question 3: Restaurant Menu API
- Question 4: E-Commerce Product API
- Question 5: Task Management API
- Bonus: User Profile API with Response Wrapper

---

## Project Structure

```
src/main/java/com/example/question1_library_api/
├── controller/
│   ├── library/BookController.java
│   ├── student/StudentController.java
│   ├── restaurant/MenuController.java
│   ├── ecommerce/ProductController.java
│   ├── task/TaskController.java
│   └── user/UserProfileController.java
├── model/
│   ├── library/Book.java
│   ├── student/Student.java
│   ├── restaurant/MenuItem.java
│   ├── ecommerce/Product.java
│   ├── task/Task.java
│   └── user/UserProfile.java & ApiResponse.java
└── Question1LibraryApiApplication.java
```

---

## How to Run

### Prerequisites
- Java 21+
- Maven 3.8+
- Spring Boot 3.2.5

### Build & Run

1. Navigate to project directory:
```bash
cd C:\Users\Amos\Downloads\question1-library-api\question1-library-api
```

2. Build the project:
```bash
mvn clean compile
```

3. Run the Spring Boot application:
```bash
mvn spring-boot:run
```

4. Access the API:
```
http://localhost:8080
```

---

## API Endpoints

### Question 1: Library Book Management API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/books` | Get all books |
| GET | `/api/books/{id}` | Get book by ID |
| GET | `/api/books/search?title={title}` | Search books by title |
| POST | `/api/books` | Add new book |
| DELETE | `/api/books/{id}` | Delete book by ID |

**Sample GET Request:**
```
GET http://localhost:8080/api/books
```

**Sample Response (200 OK):**
```json
[
  {
    "id": 1,
    "title": "Clean Code",
    "author": "Robert Martin",
    "isbn": "978-0132350884",
    "publicationYear": 2008
  },
  {
    "id": 2,
    "title": "Effective Java",
    "author": "Joshua Bloch",
    "isbn": "978-0134685991",
    "publicationYear": 2018
  }
]
```

**Sample POST Request:**
```
POST http://localhost:8080/api/books
Content-Type: application/json

{
  "id": 4,
  "title": "Design Patterns",
  "author": "Gang of Four",
  "isbn": "978-0201633610",
  "publicationYear": 1994
}
```

**Sample Response (201 Created):**
```json
{
  "id": 4,
  "title": "Design Patterns",
  "author": "Gang of Four",
  "isbn": "978-0201633610",
  "publicationYear": 1994
}
```

---

### Question 2: Student Registration API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/students` | Get all students |
| GET | `/api/students/{studentId}` | Get student by ID |
| GET | `/api/students/major/{major}` | Filter students by major |
| GET | `/api/students/filter?gpa={minGpa}` | Filter students by GPA |
| POST | `/api/students` | Register new student |
| PUT | `/api/students/{studentId}` | Update student information |

**Sample GET All:**
```
GET http://localhost:8080/api/students
```

**Sample Response (200 OK):**
```json
[
  {
    "studentId": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "major": "Computer Science",
    "gpa": 3.8
  },
  {
    "studentId": 2,
    "firstName": "Jane",
    "lastName": "Smith",
    "email": "jane@example.com",
    "major": "Computer Science",
    "gpa": 3.9
  }
]
```

**Sample Filter by Major:**
```
GET http://localhost:8080/api/students/major/Computer%20Science
```

**Sample Filter by GPA:**
```
GET http://localhost:8080/api/students/filter?gpa=3.5
```

---

### Question 3: Restaurant Menu API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/menu` | Get all menu items |
| GET | `/api/menu/{id}` | Get menu item by ID |
| GET | `/api/menu/category/{category}` | Filter by category |
| GET | `/api/menu/available?available=true` | Get available items |
| GET | `/api/menu/search?name={name}` | Search by name |
| POST | `/api/menu` | Add new menu item |
| PUT | `/api/menu/{id}/availability` | Toggle availability |
| DELETE | `/api/menu/{id}` | Delete menu item |

**Sample GET All:**
```
GET http://localhost:8080/api/menu
```

**Sample Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Spring Rolls",
    "description": "Crispy vegetable spring rolls",
    "price": 5.99,
    "category": "Appetizer",
    "available": true
  },
  {
    "id": 3,
    "name": "Grilled Salmon",
    "description": "Fresh salmon with herbs",
    "price": 15.99,
    "category": "Main Course",
    "available": true
  }
]
```

**Sample Filter by Category:**
```
GET http://localhost:8080/api/menu/category/Appetizer
```

---

### Question 4: E-Commerce Product API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/products?page=1&limit=10` | Get all products with pagination |
| GET | `/api/products/{productId}` | Get product details |
| GET | `/api/products/category/{category}` | Filter by category |
| GET | `/api/products/brand/{brand}` | Filter by brand |
| GET | `/api/products/search?keyword={keyword}` | Search products |
| GET | `/api/products/price-range?min={min}&max={max}` | Filter by price |
| GET | `/api/products/in-stock` | Get in-stock products |
| POST | `/api/products` | Add new product |
| PUT | `/api/products/{productId}` | Update product |
| PATCH | `/api/products/{productId}/stock?quantity={quantity}` | Update stock |
| DELETE | `/api/products/{productId}` | Delete product |

**Sample GET All:**
```
GET http://localhost:8080/api/products
```

**Sample Response (200 OK):**
```json
[
  {
    "productId": 1,
    "name": "Laptop",
    "description": "High-performance laptop",
    "price": 999.99,
    "category": "Electronics",
    "stockQuantity": 5,
    "brand": "Dell"
  },
  {
    "productId": 4,
    "name": "Running Shoes",
    "description": "Professional running shoes",
    "price": 89.99,
    "category": "Sports",
    "stockQuantity": 20,
    "brand": "Nike"
  }
]
```

**Sample Filter by Category:**
```
GET http://localhost:8080/api/products/category/Electronics
```

**Sample Price Range Filter:**
```
GET http://localhost:8080/api/products/price-range?min=50&max=200
```

---

### Question 5: Task Management API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/tasks` | Get all tasks |
| GET | `/api/tasks/{taskId}` | Get task by ID |
| GET | `/api/tasks/status?completed=true/false` | Filter by status |
| GET | `/api/tasks/priority/{priority}` | Filter by priority |
| POST | `/api/tasks` | Create new task |
| PUT | `/api/tasks/{taskId}` | Update task |
| PATCH | `/api/tasks/{taskId}/complete` | Mark as completed |
| DELETE | `/api/tasks/{taskId}` | Delete task |

**Sample GET All:**
```
GET http://localhost:8080/api/tasks
```

**Sample Response (200 OK):**
```json
[
  {
    "taskId": 1,
    "title": "Complete Project",
    "description": "Finish the Spring Boot project",
    "completed": false,
    "priority": "HIGH",
    "dueDate": "2026-02-20"
  },
  {
    "taskId": 2,
    "title": "Review Code",
    "description": "Code review for the team",
    "completed": false,
    "priority": "MEDIUM",
    "dueDate": "2026-02-18"
  }
]
```

**Sample Filter by Priority:**
```
GET http://localhost:8080/api/tasks/priority/HIGH
```

---

### Bonus: User Profile API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users` | Get all users (with wrapper) |
| GET | `/api/users/{userId}` | Get user by ID |
| GET | `/api/users/search/username/{username}` | Search by username |
| GET | `/api/users/search/country/{country}` | Filter by country |
| GET | `/api/users/search/age-range?minAge={min}&maxAge={max}` | Filter by age |
| POST | `/api/users` | Create user profile |
| PUT | `/api/users/{userId}` | Update user profile |
| PATCH | `/api/users/{userId}/activate` | Activate user |
| PATCH | `/api/users/{userId}/deactivate` | Deactivate user |
| DELETE | `/api/users/{userId}` | Delete user |

**Sample GET All (with ApiResponse Wrapper):**
```
GET http://localhost:8080/api/users
```

**Sample Response (200 OK):**
```json
{
  "success": true,
  "message": "All users retrieved successfully",
  "data": [
    {
      "userId": 1,
      "username": "john_doe",
      "email": "john@example.com",
      "fullName": "John Doe",
      "age": 28,
      "country": "USA",
      "bio": "Software Engineer",
      "active": true
    },
    {
      "userId": 2,
      "username": "jane_smith",
      "email": "jane@example.com",
      "fullName": "Jane Smith",
      "age": 26,
      "country": "Canada",
      "bio": "Data Scientist",
      "active": true
    }
  ]
}
```

**Sample Search by Username:**
```
GET http://localhost:8080/api/users/search/username/john_doe
```

---

## HTTP Status Codes Used

| Code | Meaning |
|------|---------|
| 200 | OK - Request successful |
| 201 | Created - Resource created successfully |
| 204 | No Content - Successful deletion |
| 404 | Not Found - Resource not found |

---

## Testing with Postman

A **Postman collection file** (`postman_collection.json`) is included in the project for easy API testing.

### Import Steps:
1. Open Postman
2. Click **Import** → **File**
3. Select `postman_collection.json`
4. All test requests are automatically configured
5. Click **Send** to test each endpoint

---

## Technologies Used

- **Framework:** Spring Boot 3.2.5
- **Language:** Java 21
- **Build Tool:** Maven
- **Server:** Apache Tomcat (embedded)
- **API Type:** RESTful Web Services
- **Data Format:** JSON

---


