# 💼 Employee Payroll Management System
> A production-grade **Spring Boot REST API** with MySQL, built on OOP principles (Inheritance, Abstraction, Polymorphism).

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.3-green?style=for-the-badge&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql)
![Maven](https://img.shields.io/badge/Maven-3.9-red?style=for-the-badge&logo=apachemaven)
![REST API](https://img.shields.io/badge/REST-API-yellow?style=for-the-badge)

---

## 📌 Project Overview

This project converts a core Java OOP **Employee Payroll System** into a full-stack **Spring Boot REST API** backed by **MySQL**. It demonstrates real-world backend development skills including layered architecture, JPA inheritance mapping, validation, global error handling, and integration testing.

---

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────────┐
│                   REST Client (Postman)                 │
└─────────────────────────┬───────────────────────────────┘
                          │ HTTP Requests
┌─────────────────────────▼───────────────────────────────┐
│              Controller Layer  (@RestController)        │
│                  EmployeeController                     │
└─────────────────────────┬───────────────────────────────┘
                          │
┌─────────────────────────▼───────────────────────────────┐
│               Service Layer  (@Service)                 │
│                   PayrollService                        │
└─────────────────────────┬───────────────────────────────┘
                          │
┌─────────────────────────▼───────────────────────────────┐
│            Repository Layer  (JpaRepository)            │
│                 EmployeeRepository                      │
└─────────────────────────┬───────────────────────────────┘
                          │
┌─────────────────────────▼───────────────────────────────┐
│                    MySQL Database                       │
│        employees | full_time_employees |                │
│                  part_time_employees                    │
└─────────────────────────────────────────────────────────┘
```

---

## 🧠 OOP Concepts Used

| Concept | Where Applied |
|---|---|
| **Abstraction** | `Employee` is an abstract class with abstract `calculateSalary()` |
| **Inheritance** | `FullTimeEmployee` and `PartTimeEmployee` extend `Employee` |
| **Polymorphism** | `calculateSalary()` behaves differently per subclass |
| **Encapsulation** | Private fields with getters/setters via Lombok |

### Database Inheritance Strategy: JOINED TABLE

```
employees (id, name, department, employee_type)
    │
    ├── full_time_employees  (id FK, monthly_salary)
    └── part_time_employees  (id FK, hours_worked, hourly_rate)
```

---

## 📁 Project Structure

```
payroll-api/
├── src/
│   ├── main/
│   │   ├── java/com/abhishek/payroll/
│   │   │   ├── PayrollApplication.java       ← Entry point
│   │   │   ├── DataLoader.java               ← Seeds demo data
│   │   │   ├── model/
│   │   │   │   ├── Employee.java             ← Abstract base entity
│   │   │   │   ├── FullTimeEmployee.java     ← Fixed monthly salary
│   │   │   │   └── PartTimeEmployee.java     ← Hours × rate salary
│   │   │   ├── repository/
│   │   │   │   └── EmployeeRepository.java   ← JPA queries
│   │   │   ├── service/
│   │   │   │   └── PayrollService.java       ← Business logic
│   │   │   ├── controller/
│   │   │   │   └── EmployeeController.java   ← REST endpoints
│   │   │   ├── dto/
│   │   │   │   ├── FullTimeEmployeeRequest.java
│   │   │   │   ├── PartTimeEmployeeRequest.java
│   │   │   │   ├── EmployeeResponse.java
│   │   │   │   └── PayrollSummaryResponse.java
│   │   │   └── exception/
│   │   │       ├── EmployeeNotFoundException.java
│   │   │       └── GlobalExceptionHandler.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/com/abhishek/payroll/
│       │   └── PayrollApplicationTests.java  ← Integration tests
│       └── resources/
│           └── application.properties        ← H2 test config
└── pom.xml
```

---

## 🚀 REST API Endpoints

Base URL: `http://localhost:8080/api/employees`

### Employee CRUD

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/employees` | Get all employees |
| `GET` | `/api/employees/{id}` | Get employee by ID |
| `GET` | `/api/employees/fulltime` | Get full-time employees |
| `GET` | `/api/employees/parttime` | Get part-time employees |
| `GET` | `/api/employees/department?name=Engineering` | Filter by department |
| `GET` | `/api/employees/search?name=Abhishek` | Search by name |
| `GET` | `/api/employees/summary` | Payroll statistics |
| `POST` | `/api/employees/fulltime` | Add full-time employee |
| `POST` | `/api/employees/parttime` | Add part-time employee |
| `PUT` | `/api/employees/{id}/fulltime` | Update full-time employee |
| `PUT` | `/api/employees/{id}/parttime` | Update part-time employee |
| `DELETE` | `/api/employees/{id}` | Delete employee |

---

## 📬 Sample Requests & Responses

### ➕ Add Full-Time Employee
```http
POST /api/employees/fulltime
Content-Type: application/json

{
  "name": "Abhishek Kumar",
  "department": "Engineering",
  "monthlySalary": 75000.0
}
```
**Response 201 Created:**
```json
{
  "id": 1,
  "name": "Abhishek Kumar",
  "department": "Engineering",
  "employeeType": "FULL_TIME",
  "salary": 75000.0,
  "monthlySalary": 75000.0
}
```

### ➕ Add Part-Time Employee
```http
POST /api/employees/parttime
Content-Type: application/json

{
  "name": "Rahul Sharma",
  "department": "Marketing",
  "hoursWorked": 40,
  "hourlyRate": 500.0
}
```
**Response 201 Created:**
```json
{
  "id": 2,
  "name": "Rahul Sharma",
  "department": "Marketing",
  "employeeType": "PART_TIME",
  "salary": 20000.0,
  "hoursWorked": 40,
  "hourlyRate": 500.0
}
```

### 📊 Payroll Summary
```http
GET /api/employees/summary
```
```json
{
  "totalEmployees": 5,
  "fullTimeCount": 3,
  "partTimeCount": 2,
  "totalPayroll": 285000.0,
  "averageSalary": 57000.0
}
```

### ❌ Error Response (404)
```json
{
  "timestamp": "2024-03-22T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Employee not found with ID: 999"
}
```

---

## ⚙️ Setup & Run

### Prerequisites
- Java 17+
- Maven 3.9+
- MySQL 8.0+

### Step 1 — Create the MySQL Database
```sql
CREATE DATABASE payroll_db;
```

### Step 2 — Configure application.properties
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/payroll_db
spring.datasource.username=root
spring.datasource.password=your_password_here
```

### Step 3 — Run the Application
```bash
# Clone the repo
git clone https://github.com/Abhishek-453/Employee-Payroll-System.git
cd Employee-Payroll-System

# Build & run
mvn spring-boot:run
```

The app starts at `http://localhost:8080`  
MySQL tables are **auto-created** by Hibernate on first run.  
5 demo employees are **auto-seeded** on first startup.

### Step 4 — Run Tests
```bash
mvn test
```
Tests run against an **H2 in-memory database** — no MySQL needed for testing.

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 17 | Programming language |
| Spring Boot 3.2 | Application framework |
| Spring Data JPA | ORM & database abstraction |
| Hibernate | JPA implementation |
| MySQL 8 | Production database |
| H2 | In-memory DB for tests |
| Lombok | Reduce boilerplate code |
| Bean Validation | Request input validation |
| Maven | Dependency management |
| JUnit 5 + MockMvc | Integration testing |

---

## 💡 Key Features

- ✅ **OOP Design** — Abstract class, Inheritance, Polymorphism preserved from original design
- ✅ **JPA Inheritance** — JOINED table strategy maps class hierarchy to MySQL tables
- ✅ **RESTful API** — Proper HTTP methods, status codes, and response bodies
- ✅ **Input Validation** — `@Valid` annotations with descriptive error messages
- ✅ **Global Error Handling** — Consistent JSON error responses for all exceptions
- ✅ **DTO Pattern** — Clean separation between API contracts and DB entities
- ✅ **Integration Tests** — Full test coverage with H2 in-memory database
- ✅ **Auto DB Seeding** — Demo data loaded on first startup via `CommandLineRunner`

---

## 👤 Author

**Abhishek Kumar**  
📧 GitHub: [@Abhishek-453](https://github.com/Abhishek-453)  
📍 Lucknow, Uttar Pradesh, India

---

## 📄 License
This project is open source under the [MIT License](LICENSE).
