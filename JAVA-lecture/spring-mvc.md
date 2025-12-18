# 🍃 Spring Web MVC: Lecture Notes

## 1. Introduction: What, Why, and How

### What is Spring Web MVC?
* A framework built on the Servlet API and standard Model-View-Controller (MVC) patterns.
* It decouples the **Logic** (Controller), **Data** (Model), and **Presentation** (View).
* Uses **Inversion of Control (IoC)** to manage component lifecycles.

### Why use it?
* **Separation of Concerns:** Clean division between backend logic and UI.
* **Testability:** Controllers are simple Java classes, making them easy to test.
* **Flexibility:** Integrates with various View technologies (JSP, Thymeleaf, Freemarker).

---

## 2. The MVC Components

* **M - Model:** The data (Map) that carries information from the Controller to the View.
* **V - View:** The presentation layer (e.g., JSP, HTML) that renders the data.
* **C - Controller:** The component that handles user requests, processes business logic, and selects a View.

---

## 3. Architecture & Request Flow

The entire framework revolves around the **DispatcherServlet** (Front Controller).

![Spring MVC Flow Diagram](https://docs.spring.io/spring-framework/docs/current/reference/html/images/mvc-context-hierarchy.png)
*(Note: Replace with standard Spring MVC flow image)*

### The Execution Flow
1.  **Request:** User sends a request (e.g., `/home`).
2.  **DispatcherServlet:** Intercepts the request (Front Controller).
3.  **HandlerMapping:** Determines which **Controller** method handles this specific URL.
4.  **Controller:** Executes business logic and returns a **Model** (data) and **Logical View Name** (String).
5.  **ViewResolver:** Maps the logical name to the physical file (e.g., `home` -> `/WEB-INF/views/home.jsp`).
6.  **View:** Renders the HTML using the Model data.
7.  **Response:** The final HTML is sent back to the browser.

---

## 4. Configuration (Without Spring Boot)

We use **Java-based Configuration** instead of `web.xml`.

### A. Environment Setup
* **Archetype:** `maven-archetype-webapp`
* **Cleanup:** Delete `web.xml` and `index.jsp`
* **Project Facets:**
    * Java: **17**
    * Dynamic Web Module: **5.0** (Aligns with Jakarta EE)

### B. Dependencies (`pom.xml`)
* `spring-webmvc` (Core MVC)
* `spring-orm` (Bridge for JPA/Hibernate)
* `hibernate-core` (ORM Implementation)
* `jakarta.persistence` (Standard API)
* `mysql-connector-j` (Database Driver)

### C. Configuration Classes (`src/main/java/com/info/testmvc/config`)

#### 1. `AppInitializer.java`
* **Extends:** `AbstractAnnotationConfigDispatcherServletInitializer`
* **Role:** Replaces `web.xml`. Registers the `DispatcherServlet` and creates the Contexts.
* **Key Methods:**
    * `getRootConfigClasses()`: Returns DB/Service config.
    * `getServletConfigClasses()`: Returns Web config (`WebMvcConfig.class`).
    * `getServletMappings()`: Returns `{"/"}`.

#### 2. `WebMvcConfig.java`
* **Implements:** `WebMvcConfigurer`
* **Annotations:** `@Configuration`, `@EnableWebMvc`, `@ComponentScan`
* **Role:** Configures the web layer (View Resolvers, Resources).

---

## 5. Key Concepts Deep Dive

### View Resolver
* **Problem:** Avoiding hardcoded file paths in Controllers.
* **Solution:** Translates logical names to physical paths.
    * **Prefix:** `/WEB-INF/views/`
    * **Suffix:** `.jsp`
* **Security:** Putting views in `WEB-INF` prevents direct access by users; they *must* go through a Controller.

### Context Hierarchy (Root vs. Servlet)
Spring creates two contexts when using `AbstractAnnotationConfigDispatcherServletInitializer`:

1.  **Root WebApplicationContext (Backend):**
    * Contains: Services, Repositories, DB Config (DataSource, EntityManager).
    * Scan: `@Service`, `@Repository`.
2.  **Servlet WebApplicationContext (Frontend):**
    * Contains: Controllers, ViewResolvers.
    * Scan: `@Controller`.
    * *Note:* The Child (Servlet) can see beans in the Parent (Root), but not vice-versa.

### @Controller vs @RestController

| Annotation | Return Value | Use Case |
| :--- | :--- | :--- |
| **`@Controller`** | **View Name** (String) resolved to HTML/JSP. | Traditional Web Apps (Server-side rendering). |
| **`@RestController`** | **Data** (JSON/XML) written directly to body. | REST APIs (consumed by React/Angular/Mobile). |
| *Note* | Uses ViewResolver. | Uses `HttpMessageConverter` (ignores ViewResolver). |

---

## 6. Project Structure

```text
project-root
├── src/main/java
│   └── com.info.testmvc
│       ├── config
│       │   ├── AppInitializer.java (AbstractAnnotationConfig...)
│       │   ├── WebMvcConfig.java   (WebMvcConfigurer)
│       │   └── JpaConfig.java      (DB Configuration)
│       ├── controller
│       │   └── HomeController.java
│       ├── service
│       └── repository
├── src/main/resources
│   ├── database.properties
│   └── log4j2.xml
├── src/main/webapp
│   └── WEB-INF
│       └── views
│           └── index.jsp
└── pom.xml
