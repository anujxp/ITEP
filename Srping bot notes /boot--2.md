
# 🛍️ Spring Boot Product Management API

A professional RESTful API implementation demonstrating the transition from traditional MVC to a data-driven microservice architecture. This project focuses on **JSON data binding**, **REST constraints**, and **Lombok integration**.

---

## 📖 Key Architectural Concepts

### 1. RESTful Design Pattern

The API is designed around the **Product** resource, following standard HTTP method conventions:

| Action | HTTP Method | Endpoint | Description |
| --- | --- | --- | --- |
| **Create** | `POST` | `/products` | Creates a new product from a JSON payload. |
| **List** | `GET` | `/products` | Retrieves a list of all existing products. |
| **Fetch** | `GET` | `/products/{id}` | Retrieves a specific product by ID. |

### 2. The @RestController Lifecycle

Unlike a standard `@Controller`, our `ProductController` uses `@RestController` to:

* **Serialize** Java Objects directly into JSON.
* **Bypass** View Resolvers (no HTML/JSP required).
* **Communicate** directly with modern front-ends (React/Angular) or Mobile Apps.

---

## 🛠️ Implementation Details

### Model Layer (Lombok Powered)

We use Project Lombok to eliminate "Boilerplate" code, keeping our models clean and readable.

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private double price;
}

```

### Controller Layer (Data Mapping)

The controller uses `@RequestBody` to trigger the **Jackson Deserializer**, which converts incoming JSON into a Java Object automatically.

```java
@PostMapping("/products")
public String saveProduct(@RequestBody Product p) {
    // Business logic to handle the product object
    System.out.println("Received Product: " + p.getName());
    return "Product Saved Successfully!";
}

```

---

## 🧪 API Testing (Postman)

To test the **POST** functionality, send a request with the following configuration:

1. **Method:** `POST`
2. **URL:** `http://localhost:8080/products`
3. **Headers:** `Content-Type: application/json`
4. **Body (raw JSON):**

```json
{
    "id": 101,
    "name": "Wireless Gaming Mouse",
    "price": 45.50
}

```

---

## 📈 Technical Highlights

* **Automatic Data Binding:** Leveraged `@RequestBody` for JSON-to-Object mapping.
* **Immutable Configuration:** Transitioning toward **Constructor Injection** for better testability.
* **JSON Handling:** Integrated with **Jackson** library for high-performance serialization.

---

## 🔗 Project Metadata

* **Latest Commit:** [`9bc04a6`](https://www.google.com/search?q=%5Bhttps://github.com/meanbypawan/boot/commit/9bc04a67e8faec923f673966bcc257314b59ff90%5D(https://github.com/meanbypawan/boot/commit/9bc04a67e8faec923f673966bcc257314b59ff90))
* **Developer:** Pawan (BCA Student, Holkar Science College)
* **Status:** In Development (Next: Service & Repository Layer)

