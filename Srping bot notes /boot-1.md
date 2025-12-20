

# 🚀 Spring Boot Foundation - 100 Days of Java

This repository documents my transition from traditional Spring MVC to **Spring Boot**. It focuses on modern Java development standards, reducing boilerplate code, and leveraging the "Magic" of Spring Boot's auto-configuration.

## 📌 Key Concepts Covered

### 1. The Core Architecture

Spring Boot follows the **"Convention over Configuration"** philosophy. The heart of the application is the `@SpringBootApplication` annotation, which is a combination of:

* **`@SpringBootConfiguration`**: Marks the class as a source of bean definitions.
* **`@EnableAutoConfiguration`**: Automatically configures beans based on the JARs present in the `pom.xml`.
* **`@ComponentScan`**: Scans the project for components, services, and controllers.

### 2. Dependency Management (Starters)

Instead of manual version management, I am using **Starter Dependencies**. These are aggregated bundles that pull in all compatible JARs.

* `spring-boot-starter-web`: For RESTful APIs and Embedded Tomcat.
* `spring-boot-starter-data-jpa`: For ORM and Database connectivity.

### 3. Professional Coding Standards

* **Constructor Injection:** Moving away from `@Autowired` on field variables. This ensures **Immutability** using `final` fields and makes the code more testable.
* **Lombok Integration:** Using `@Data` and `@RequiredArgsConstructor` to eliminate repetitive Getters, Setters, and Constructors.

### 4. Database Integration (MySQL)

Configured centralized settings in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_name
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

```

The `ddl-auto=update` feature allows Hibernate to automatically sync the database schema with Java Entity classes.

---

## 🛠️ Tools Used

* **IDE:** Spring Tool Suite (STS 4)
* **Language:** Java 17+
* **Build Tool:** Maven
* **Database:** MySQL 8.0

---

## 🏃 How to Run

1. Clone the repository.
2. Update `application.properties` with your MySQL credentials.
3. Run the application using the following command:

```bash
java -jar target/boot-0.0.1-SNAPSHOT.jar

```

*Note: You can override the port at runtime using:* `--server.port=9090`

---

## 🔗 Latest Commit

* **Commit:** [6cc66cb](https://github.com/meanbypawan/boot/commit/6cc66cb079badefe64df2a77e20eca7599b1998c)
* **Topic:** Spring Configuration and Bootstrapping logic.

---

### **What's next?**

Since your code is already on GitHub and you have a solid foundation, the next logical step is to create a **Controller** that actually uses this MySQL connection to perform CRUD operations.

**Would you like me to provide a simple "User Registration" Controller and Entity example that uses all these concepts (Lombok, Constructor Injection, and JPA)?**

# 📔 Comprehensive Lecture Notes: Spring Boot Fundamentals

**Date:** December 20, 2025

**Topic:** Spring Boot Architecture, Project Setup, and Best Practices

**Tools:** Java 17+, Maven, Spring Tool Suite (STS), MySQL, Lombok

---

## 1. Introduction: What, Why, and How?

Spring Boot is an **extension** of the Spring Framework designed to simplify the "bootstrapping" (starting) of new applications.

* **The Problem:** Traditional Spring required complex XML or Java configuration and manual server setup (WAR deployment).
* **The Solution:** Spring Boot uses **Opinionated Defaults**. It assumes you want to follow best practices and sets up the "boring" parts (like the server and database connection) automatically.
* **Core Philosophy:** **Convention over Configuration (CoC)**. If you place files in the standard folders, Spring Boot "conventionally" knows how to handle them without you writing a single line of config.

---

## 2. Spring vs. Spring Boot: The Comparison

| Feature | Spring Framework (Classic) | Spring Boot |
| --- | --- | --- |
| **Main Focus** | Dependency Injection (DI) | Rapid Application Development (RAD) |
| **Setup** | Manual (Heavy Configuration) | **Auto-Configuration** |
| **Server** | Requires External Tomcat | **Embedded Tomcat** (Built-in) |
| **Deployment** | WAR (Web Archive) | **JAR** (Java Archive - "Run as Java Application") |
| **Complexity** | High (Boilerplate code) | Low (Streamlined) |

---

## 3. The Entry Point: `@SpringBootApplication`

Your project's main class (like `SpringConfigur`) uses this meta-annotation. It is a combination of three critical annotations:

1. **`@SpringBootConfiguration`**: A specialized version of `@Configuration`. It identifies the class as a source of bean definitions for the application context.
2. **`@EnableAutoConfiguration`**: This tells Spring Boot to look at the JAR files in your `pom.xml`. If it sees `mysql-connector-java`, it automatically sets up the DataSource bean.
3. **`@ComponentScan`**: This tells Spring to scan the current package and all sub-packages for classes marked with `@Component`, `@RestController`, `@Service`, or `@Repository`.

---

## 4. Modern Dependency Injection: Constructor Injection

The lecture emphasized that **Constructor Injection** is the industry standard over Field Injection.

* **Field Injection (Avoid):** Uses `@Autowired` on a variable. Hard to test and allows for "hidden" null pointers.
* **Constructor Injection (Best Practice):** Pass dependencies through the constructor.
* **Immutability:** Allows you to use the `final` keyword.
* **Testing:** Easy to pass "Mock" objects into the constructor.
* **Clarity:** All dependencies are visible at a glance.



---

## 5. Project Lombok: Eliminating Boilerplate

Lombok is a library that generates standard Java code at compile-time using annotations.

* **`@Getter` / `@Setter**`: Generates your accessors and mutators.
* **`@ToString`**: Creates a clean string representation of your object.
* **`@Data`**: A "shortcut" annotation that includes Getters, Setters, ToString, and Equals.
* **`@RequiredArgsConstructor`**: Generates a constructor for all fields marked as `final`. This is perfect for Constructor Injection.

---

## 6. External Configuration (MySQL & Properties)

Settings are kept in **`application.properties`**. This allows you to change database credentials without re-compiling the Java code.

### Sample MySQL Configuration:

```properties
# DB Connection
spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate Automation
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

```

* **DDL-Auto=update:** This is the most popular setting. It compares your Java Entity classes with the Database and updates the tables automatically.

---

## 7. Operational Best Practices

* **Embedded Server:** No need to install Tomcat. The server is "embedded" in your JAR.
* **Command Line Overrides:** You can run the app with `java -jar app.jar` and override settings via the terminal: `--server.port=9090`.
* **Exception Handling:** Spring Boot works best with **Unchecked Exceptions** (RuntimeExceptions). By default, `@Transactional` only rolls back for these types of errors.

---

### **Next Step for you:**


