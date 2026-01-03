I apologize for the confusion! I understand you're looking for the clean, professionally structured Markdown content for your **Netflix Eureka** notes, ready to be used as a technical guide or a README file.

Here is the properly formatted content based on the information you provided:

---

# Mastering Service Discovery with Netflix Eureka

Establishing a **Service Discovery** pattern using Netflix Eureka is the first major step in building a scalable microservices architecture. It allows services (like Product and Category) to find each other dynamically without hardcoding IP addresses or ports.

---

## 1. The Eureka Server (Service Registry)

Think of this as the **"Phonebook"** of your architecture. Every microservice will list its name and address here.

### A. Dependencies (`pom.xml`)

Include the Eureka Server starter and the `spring-cloud-dependencies` BOM to manage versions.

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>
</dependencies>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>2023.0.0</version> 
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

```

### B. Configuration (`application.properties`)

By default, Eureka Server tries to register with another Eureka Server. For a standalone registry, we disable this.

```properties
# 1. Standard Port for Eureka
server.port=8761

# 2. Application Name
spring.application.name=service-registry

# 3. Disable self-registration
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false

# 4. Use IP addresses instead of hostnames (Recommended for local testing)
eureka.instance.prefer-ip-address=true

```

### C. Activation

Annotate your main class with `@EnableEurekaServer`.

```java
@SpringBootApplication
@EnableEurekaServer 
public class ServiceRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistryApplication.class, args);
    }
}

```

---

## 2. The Eureka Clients (Microservices)

This configuration applies to your **Product**, **Category**, and **Order** services.

### A. Dependencies (`pom.xml`)

Clients require the Discovery Client starter.

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>

```

### B. Configuration (`application.properties`)

Each client must know where to find the server to register itself.

```properties
# 1. Service Identification
spring.application.name=product-service
server.port=8081

# 2. Registry Location
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/

# 3. Heartbeat Frequency (Default is 30s)
eureka.instance.lease-renewal-interval-in-seconds=30

```

### C. Activation

Use the `@EnableDiscoveryClient` annotation to allow the application to register and discover others.

```java
@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication { 
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}

```

---

## 3. The Workflow

1. **Registry Startup:** The Eureka Server starts on port `8761` and initializes an empty registry.
2. **Client Registration:** Upon startup, a service (e.g., Product) sends a REST `POST` request to Eureka with its name, IP, and port.
3. **The Heartbeat:** Every 30 seconds, the client sends a `PUT` request to confirm it is still alive. If the server misses this for 90 seconds, the service is removed from the registry.
4. **Discovery (Lookup):** When Service A needs Service B, it asks Eureka: *"Where is 'product-service'?"*
5. **Caching:** Clients fetch and cache the registry locally, ensuring communication remains possible even if the Eureka Server is temporarily unavailable.

---

## 4. Troubleshooting

| Symptom | Probable Cause | Resolution |
| --- | --- | --- |
| **Dashboard says "No instances"** | Connectivity issue. | Verify `eureka.client.service-url.defaultZone` matches the Server's URL. |
| **"EMERGENCY! EUREKA MAY BE..."** | Self-Preservation Mode. | Occurs if services stop/start too quickly; it is a safety feature, not an error. |
| **Service name is "UNKNOWN"** | Missing metadata. | Ensure `spring.application.name` is defined in `application.properties`. |

---

Would you like me to generate the **Feign Client** code next so your Category service can start communicating with your Product service using these names?
Based on the commit `9456446` in the `ITEP-15` repository and the dependencies shown in your Spring Initializr screenshot, here are the dependencies used in the project:

### 1. For the Service Registry (Eureka Server)

* **Eureka Server** (`spring-cloud-starter-netflix-eureka-server`): Used to create the centralized service registry.
* **Spring Boot DevTools**: Included for fast application restarts and enhanced development experience.

### 2. For the Microservices (Product & Category Clients)

* **Eureka Discovery Client** (`spring-cloud-starter-netflix-eureka-client`): Allows the microservices to register themselves with the Service Registry.
* **Spring Web** (`spring-boot-starter-web`): Used to build the RESTful APIs for Category and Product services.
* **OpenFeign** (`spring-cloud-starter-openfeign`): Used for declarative REST client communication between services (seen in `CategoryClient.java` and `ProductClient.java`).
* **Cloud Bootstrap** (`spring-cloud-starter-bootstrap`): Provides support for the bootstrap context and `@RefreshScope`.
* **Spring Data JPA** (`spring-boot-starter-data-jpa`): Required for the repository layers (`CategoryRepo.java` and `ProductRepository.java`).
* **MySQL Driver** (`mysql-connector-j`): Typically used for database connectivity in these services.
* **Lombok**: Used for reducing boilerplate code in Entity and DTO classes like `Category.java` and `ProductDTO.java`.
* **Spring Boot DevTools**: Also included in the client services for development efficiency.

### 3. Versions and Management

* **Spring Cloud BOM**: The project uses `spring-cloud-dependencies` (likely version `2023.0.0` as per your previous notes) to manage these Spring Cloud versions.
