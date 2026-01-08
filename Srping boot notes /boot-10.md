The code snippets you provided represent the core of a **Stateless Authentication System** using Spring Security and JWT. In this architecture, the server doesn't "remember" the user via a session; instead, it issues a signed token that the client carries for every request.

---

# 📝 Lecture Notes: Spring Security & JWT Token Generation

## 1. Why do we need Spring Security?

Spring Security is a framework that provides both **Authentication** (verifying who you are) and **Authorization** (deciding what you can do).

* **Default Security**: By default, Spring Security locks all endpoints. We use `SecurityConfig` to open specific "public" gates like `/signin`.
* **Security Filter Chain**: It acts as a series of checkpoints (filters) that a request must pass through before it ever reaches your Controller.

## 2. What is JWT (JSON Web Token)?

JWT is a compact, URL-safe way to represent user claims. It consists of three parts:

1. **Header**: Specifies the algorithm used for signing (e.g., HS256).
2. **Payload**: Contains the user data (Email, Roles, Expiration). **Important:** Do not store passwords here, as it is only Base64 encoded, not encrypted.
3. **Signature**: A hash created using the `SECRETKEY` to ensure the token hasn't been tampered with.

---

## 3. The Flow of Generating a Token (Detailed)

Based on your commit **`62d0ee7`**, here is the step-by-step internal journey of a login request.

### Step 1: Client Request

The user sends their email and password to the `/signin` endpoint.

### Step 2: The Controller Handshake

In `AuthController`, the `signIn` method receives the user object. It immediately delegates the work to the **`AuthenticationManager`**.

```java
authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

```

* This line starts the engine. If the email or password is wrong, it throws an exception here and stops the process.

### Step 3: CustomUserDetailsService (`loadUserByUsername`)

To check the password, the Manager needs to know what is stored in your database. It calls your **`CustomUserDetailsService`**.

1. **Check Database**: It uses `userRepo.findByEmail(username)` to find the user in MySQL.
2. **Grant Authorities**: It creates a `GrantedAuthority` object (permission). In your code, every valid user is given the role `"USER"`.
3. **Return Security User**: It returns a built-in `org.springframework.security.core.userdetails.User` object containing the email, the hashed password from the DB, and the authorities.

### Step 4: Password Verification

The **`AuthenticationManager`** takes the plain password from the request and the hashed password from the DB. It uses the **`BCryptPasswordEncoder`** to compare them.

### Step 5: Generating the JWT String

Once verified, the Controller calls `jwt.generateToken(userDetails)`. Inside **`JwtUtil`**:

* **Subject**: Set to the user's email.
* **Claims**: A map containing the user's roles.
* **Expiration**: Set to 1 hour (`System.currentTimeMillis() + 60*60*1000`).
* **SignWith**: The `SECRETKEY` is used with the **HS256** algorithm to "lock" the token.

### Step 6: Success Response

The server returns a JSON object containing a success message and the final token string.

---

## 4. Key Differences: AuthN vs AuthZ

| Concept | Question | In this Lecture |
| --- | --- | --- |
| **Authentication** | "Who are you?" | Checked by `AuthenticationManager` using the password. |
| **Authorization** | "What can you do?" | Defined by the `GrantedAuthority` ("USER") placed inside the JWT. |

---

## 5. Summary Table for Review

| Class | Role | Important Code |
| --- | --- | --- |
| **`SecurityConfig`** | The Firewall | Defines `permitAll()` for `/signin`. |
| **`CustomUserDetailsService`** | The Data Link | `loadUserByUsername` finds the user in MySQL. |
| **`JwtUtil`** | The Factory | `Jwts.builder()...compact()` creates the token string. |
| **`AuthController`** | The Coordinator | Calls the Manager and generates the final Response. |

This video provides a practical, step-by-step walkthrough of [Integrating JWT into a Spring Boot application](https://www.youtube.com/watch?v=MWvnmyLRUik), which is perfect for understanding the centralized security unit you are building in your microservices project.

**Would you like me to start on the next part of the lecture, which is how the API Gateway will verify this token?**

# Lecture Notes: JWT Implementation & Authentication Flow

## 1. Dependencies: The JWT Toolkit

To handle tokens, we use the **JJWT (Java JWT)** library.

* **`jjwt-api`**: Provides the standard interfaces and classes for creating and parsing JWTs.
* **`jjwt-impl`**: The actual logic (implementation) of the library, set to `runtime` scope because your code only needs to talk to the API.
* **`jjwt-jackson`**: Allows the library to convert JSON data (like your `Map` of roles) into the token string using the Jackson JSON mapper.

---

## 2. Component Explanation: `JwtUtil.java`

This class is the "Factory" that manufactures your digital ID cards.

```java
@Component
public class JwtUtil {
  // 1. The Signing Key: A long secret string known only to the server. 
  // It is used to create the mathematical signature of the token.
  private final String SECRETKEY = "fadklfdrerieriovnmvncmmbberhkhgfkhkfhghdfghdfreriogflhjfk";
  
  public String generateToken(UserDetails user) {
      String token = Jwts.builder()
      // 2. Subject: Identifies who the token belongs to (the email/username).
      .setSubject(user.getUsername()) 
      // 3. Claims: Extra data. We are putting the user's Roles/Authorities here.
      .setClaims(Map.of("role",user.getAuthorities())) 
      // 4. IssuedAt: The timestamp of when the token was created.
      .setIssuedAt(new Date()) 
      // 5. Expiration: Set for 1 hour (60*60*1000 ms). After this, the token is dead.
      .setExpiration(new Date(System.currentTimeMillis()+60*60*1000)) 
      // 6. SignWith: Uses the secret key and the HS256 algorithm to lock the token.
      .signWith(Keys.hmacShaKeyFor(SECRETKEY.getBytes()), SignatureAlgorithm.HS256) 
      // 7. Compact: Finalizes the build and converts it into the final string.
      .compact();
      return token;  
  }
}

```

---

## 3. The "Sign-In" Logic: `AuthController.java`

This is where the user provides credentials and receives their token.

```java
@PostMapping("/signin")
public ResponseEntity<?> signIn(@RequestBody User user){
    // 1. Handshake: The Manager checks if the password matches the DB hash.
    // If it fails, this line throws an exception and stops the code.
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

    // 2. Fetch Details: Now that we know they are valid, get their full info from our DB service.
    UserDetails userDetails = detailsService.loadUserByUsername(user.getEmail());

    // 3. Generate Token: Call the factory to create the JWT.
    String token = jwt.generateToken(userDetails);

    // 4. Response: Send a success message and the token back to the client.
    HashMap<String, Object> hm = new HashMap<>();
    hm.put("message","Sign in success");
    hm.put("token", token);
    return ResponseEntity.ok(hm);
}

```

---

## 4. Bridging the Database: `CustomUserDetailsService`

This class is the bridge between your MySQL `User` table and Spring Security.

```java
@Service
public class CustomUserDetailsService implements UserDetailsService {
    // 1. Repository: Used to communicate with the MySQL database.
    private final UserRepository userRepo;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 2. Database Lookup: Try to find the user by their email.
        User dbUser = userRepo.findByEmail(username)
                .orElseThrow(()->new ResourceNotFoundException("Username not found"));
        
        // 3. Authorities: Map the user's role to a GrantedAuthority object.
        // Currently hardcoded as "USER" for this lecture.
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        
        // 4. Return Security User: Create the built-in Spring Security User object.
        // This object holds the email, hashed password, and roles.
        return new org.springframework.security.core.userdetails
                .User(dbUser.getEmail(), dbUser.getPassword(), List.of(authority));
    }
}

```

---

## 5. Detailed Execution Flow (Step-by-Step)

1. **Client Request**: Sends Email and Password to `/signin`.
2. **AuthenticationManager**: Triggered by the controller. It internally calls `loadUserByUsername`.
3. **Database Hit**: `CustomUserDetailsService` finds the user in MySQL and returns the hashed password.
4. **Verification**: The Manager compares the request password with the hashed password.
5. **Token Request**: If passwords match, the controller asks `JwtUtil` for a token.
6. **Token Construction**: `JwtUtil` takes the email and roles, signs them with the `SECRETKEY`, and creates a string.
7. **Final Response**: The Client receives the token and stores it (usually in LocalStorage).

---

## 6. Important Definitions for Review

* **`SECRETKEY`**: A private string used to ensure the token cannot be faked by anyone else.
* **`BCrypt`**: (Involved in the Manager step) The algorithm used to compare the user's password without ever seeing the actual text.
* **Statelessness**: The server never "remembers" you. You must send that `token` in the header of every future request.

**Would you like me to generate a single `.md` file combining all these code blocks and explanations for your project documentation?**
