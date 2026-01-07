

# Lecture Notes: User Microservice Security & Token Generation

## 1. Code Explanation: `SecurityConfig.java`

Your provided code is the "Heart" of your security configuration. Here is the line-by-line breakdown:

```java
public class SecurityConfig {

   @Bean   
   public SecurityFilterChain securityFilterChain(HttpSecurity http) {
       // 1. Disable CSRF (Cross-Site Request Forgery)
       // Why? In stateless APIs using JWT, we don't use cookies, so CSRF isn't a threat.
       http.csrf(csrf->csrf.disable())
       
       // 2. Define Authorization Rules
       // .requestMatchers(...).permitAll() -> Allows these specific URLs to be accessed without a token.
       // "/auth" and "/auth/sign-in" must be public so users can actually log in to get a token.
       .authorizeHttpRequests(auth->auth.requestMatchers("/auth","/auth/sign-in").permitAll());
       
       // 3. Build and return the security filter chain
       return http.build();
   }
   
   @Bean
   public PasswordEncoder passwordEncoder() {
       // Defines the algorithm to hash passwords before saving to the DB.
       // BCrypt is the industry standard—it's a one-way hash that is nearly impossible to reverse.
       return new BCryptPasswordEncoder();
   }
   
   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
       // This is the "Engine" that manages the login process.
       // It coordinates between the UserDetailsService (to find the user) and the PasswordEncoder (to check the secret).
       return config.getAuthenticationManager();
   }
}

```

---

## 2. Why do we need Spring Boot Security?

In a modern web application, especially microservices, security is not just about a login page. We need it for:

* **Protection against attacks:** It automatically handles threats like Session Fixation, Clickjacking, and (if enabled) CSRF.
* **Standardization:** Instead of writing custom "if-else" logic for every API, Spring Security provides a central "Filter" that checks every request before it hits your controller.
* **Decoupling:** It separates your business logic from your security logic.

---

## 3. What is JWT (JSON Web Token)?

JWT is a **stateless** way to handle security.

* **Traditional (Stateful):** The server creates a "Session" and remembers you in its RAM. This is bad for microservices because if you have 10 servers, they all have to share that RAM.
* **JWT (Stateless):** The server gives you a signed "Certificate" (the token). You carry it. The server doesn't need to remember you; it just needs to "verify" that the certificate hasn't been tampered with.

---

## 4. The Flow of Generating a Token (The "Sign-In" Process)

This is a **6-step process** that happens inside your User Microservice:

1. **Request Arrival:** The client hits `POST /auth/sign-in` with a `username` and `password`.
2. **Filter Check:** The `SecurityFilterChain` sees that this URL is `permitAll()`, so it lets the request go through.
3. **Controller Action:** The `AuthController` receives the credentials. It calls `authenticationManager.authenticate()`.
4. **The "Brain" (AuthenticationManager):** * It uses your **`CustomUserDetailsService`** to go to the database and find the user by their name.
* It uses the **`BCryptPasswordEncoder`** to compare the password from the request with the hashed password in the DB.


5. **Success:** If the passwords match, the manager returns an "Authenticated" object.
6. **Token Creation:** The Controller then calls the `JwtService`. The service takes the username, signs it with your **Secret Key**, and creates the `header.payload.signature` string. This string is sent back to the client.

---

## 5. Progress Report (Status of Commit `b686775`)

Based on the file tree in this commit, here is what has been accomplished in the **UserMicroService**:

* **Project Structure:** Created as a separate Maven project under `boot_microservices`.
* **Entity Layer:** Created the `User.java` entity to represent the database table.
* **Repository Layer:** Added `UserRepository` for DB operations.
* **Service Layer:** Added `UserService` to handle business logic (like saving users).
* **Security Infrastructure:**
* Configured **`SecurityConfig`** (The code you provided).
* Added **`AuthController`** to handle login/sign-in requests.


* **Error Handling:** Implemented a **`GlobalExceptionHandler`** to handle errors gracefully (like "User Not Found").

**Summary:** You now have a microservice that can connect to a database, save users with encrypted passwords, and is ready to start issuing JWT tokens for authentication.


