# SettleSpot System Architecture & Monorepo Rules

## Global Tech Stack
- **Backend Environment:** Java, Spring Boot, Spring Cloud, Maven.
- **Database:** MySQL.
- **Service Discovery:** Netflix Eureka.
- **Routing:** Spring Cloud Gateway.

## Monorepo Service Boundaries
This is a monorepo containing distinct microservices. **CRITICAL RULE:** Never write code across multiple service boundaries in a single step unless explicitly instructed. Keep domain logic isolated.

1. `SettlespotEurekaServer`: The service registry running on port 8761. No business logic belongs here.
2. `SettlespotAPIGateway`: The single entry point for all clients. Handles routing to backend services.
3. `SettlespotUserService`: Manages user authentication, profiles, and roles.
4. `SettlespotPropertyService`: Manages property listings, availability, and details.
5. `SettlespotBookingService`: Handles reservation logic, coordinating between Users and Properties.

## Communication Protocol
- Microservices must never call each other's databases directly.
- Inter-service communication must happen via REST/OpenFeign or through the Gateway.
- All frontend client requests MUST hit the `SettlespotAPIGateway`. Do not expose the underlying microservice ports directly.

## Frontend (Upcoming)
- We will be integrating a React frontend. 
- All frontend data fetching must prioritize summary-first patterns and interact exclusively with the API Gateway.

# 🏠 SettleSpot — Full Architecture & Developer Guide

> **Purpose of this document:** Complete reference for what the project does, how it's structured,
> what was changed in the backend, and what the React frontend needs to build.

---

## 1. PROJECT OVERVIEW — What is SettleSpot?

SettleSpot is a **room-rental platform** where:

- **Tenants** search for and book rooms (Hostel / PG / Independent Room)
- **Hosts** list their properties, manage bookings, and approve/reject tenant requests
- **Admins** control everything — they approve properties, create hosts, and manage the platform

### Core Business Flow

```
ADMIN creates HOST → Email sent with credentials
       ↓
HOST adds PROPERTY → Goes to Admin for approval
       ↓
ADMIN approves PROPERTY → Now visible to Tenants
       ↓
TENANT browses & books PROPERTY → Request goes to HOST
       ↓
HOST approves BOOKING → Property becomes unavailable
       ↓
After checkout date → TENANT can rate the stay (1–5 stars)
```

---

## 2. TECH STACK

| Layer        | Technology                              |
|--------------|-----------------------------------------|
| Backend      | Java 17, Spring Boot 3.2                |
| Security     | Spring Security + JWT (JJWT 0.12.3)    |
| Database     | MySQL 8                                 |
| Service Mesh | Spring Cloud Netflix Eureka             |
| API Gateway  | Spring Cloud Gateway (WebFlux/Reactive) |
| Comm         | OpenFeign (service-to-service calls)    |
| Utilities    | Lombok, Bean Validation                 |
| Email        | Spring Mail (Gmail SMTP)                |
| Frontend     | React 18, Vite, Axios, React Router v6  |

---

## 3. MICROSERVICES ARCHITECTURE

```
                        ┌─────────────────────────────┐
                        │      React Frontend          │
                        │   localhost:3000             │
                        └──────────────┬──────────────┘
                                       │ HTTP/REST
                                       ▼
                        ┌─────────────────────────────┐
                        │     API GATEWAY (8080)       │
                        │  ┌─────────────────────┐    │
                        │  │  JWT Validation      │    │
                        │  │  Role Guard Filter   │    │
                        │  │  CORS Config         │    │
                        │  └─────────────────────┘    │
                        └──────┬──────┬──────┬────────┘
                               │      │      │
               ┌───────────────┘      │      └───────────────┐
               ▼                      ▼                       ▼
 ┌─────────────────────┐ ┌──────────────────────┐ ┌─────────────────────┐
 │  USER SERVICE (8081) │ │ PROPERTY SVC (8082)  │ │ BOOKING SVC (8083)  │
 │  - Login / Register  │ │ - Add property       │ │ - Create booking    │
 │  - JWT Generation    │ │ - Admin approval     │ │ - Host approve/rej  │
 │  - Admin: create host│ │ - Search/filter      │ │ - Tenant cancel     │
 │  - Admin: manage     │ │ - Availability mgmt  │ │ - Rating system     │
 │    users             │ │ - Rating update      │ │                     │
 └──────────┬──────────┘ └──────────┬───────────┘ └──────────┬──────────┘
            │                       │ Feign                   │ Feign
            │◄──────────────────────┴─────────────────────────┘
            │
 ┌──────────▼──────────┐
 │  EUREKA SERVER (8761)│
 │  Service Discovery   │
 └─────────────────────┘
```

---

## 4. DATABASES

| Service          | Database                    |
|------------------|-----------------------------|
| User Service     | `settlespot_user_db`        |
| Property Service | `settlespot_property_db`    |
| Booking Service  | `settlespot_booking_db`     |

---

## 5. COMPLETE BACKEND PROJECT STRUCTURE

### 5.1 SettlespotEurekaServer (Port: 8761)
```
SettlespotEurekaServer/
├── src/main/java/com/info/
│   └── SettlespotEurekaServerApplication.java   ← NO CHANGES NEEDED
└── src/main/resources/
    └── application.properties                    ← NO CHANGES NEEDED
```
**Status:** ✅ No changes required

---

### 5.2 SettlespotAPIGateway (Port: 8080)
```
SettlespotAPIGateway/
├── pom.xml                                       ← REPLACED (added JWT deps)
├── src/main/java/com/info/
│   ├── SettlespotApiGatewayApplication.java      ← KEEP AS IS
│   └── filter/
│       └── JwtAuthGatewayFilter.java             ← NEW FILE ⭐
└── src/main/resources/
    └── application.properties                    ← REPLACED (added JWT + CORS)
```

**What the Gateway does:**
- Validates JWT on every request (except `/auth/**` and `/internal/**`)
- Blocks `/admin/**` routes if role is not ADMIN
- Adds `X-User-Id`, `X-User-Email`, `X-User-Role`, `X-User-Name` headers to all requests
- Downstream services read these headers — they trust the gateway

---

### 5.3 SettlespotUserService (Port: 8081)
```
SettlespotUserService/
├── pom.xml                                       ← REPLACED (added JWT, Lombok, Mail)
├── src/main/java/com/info/settlespot/userservice/
│   ├── SettlespotUserServiceApplication.java     ← UPDATED (@EnableFeignClients)
│   │
│   ├── entity/
│   │   ├── AppUser.java                          ← NEW ⭐ (replaces Admin+Host+Tenant)
│   │   └── UserRole.java                         ← NEW ⭐ (enum: ADMIN, HOST, TENANT)
│   │
│   ├── repository/
│   │   └── UserRepository.java                   ← NEW ⭐ (replaces 2 repos)
│   │
│   ├── dto/
│   │   ├── ApiResponse.java                      ← NEW ⭐ (universal response wrapper)
│   │   ├── request/
│   │   │   ├── LoginRequest.java                 ← NEW (with validation)
│   │   │   ├── RegisterTenantRequest.java        ← NEW (with validation)
│   │   │   ├── CreateHostRequest.java            ← NEW (admin creates host)
│   │   │   └── UpdateHostRequest.java            ← NEW
│   │   └── response/
│   │       ├── AuthResponse.java                 ← NEW (contains JWT token)
│   │       └── UserResponse.java                 ← NEW (replaces 2 response DTOs)
│   │
│   ├── security/
│   │   ├── JwtUtil.java                          ← NEW ⭐ (generate + validate JWT)
│   │   └── JwtAuthFilter.java                    ← NEW ⭐ (filter for User Service)
│   │
│   ├── config/
│   │   ├── SecurityConfig.java                   ← REPLACED ⭐
│   │   └── AdminDataInitializer.java             ← NEW ⭐ (creates admin on startup)
│   │
│   ├── service/
│   │   ├── CustomUserDetailsService.java         ← NEW ⭐ (Spring Security integration)
│   │   ├── AuthService.java                      ← NEW ⭐ (login + register)
│   │   ├── AdminService.java                     ← NEW ⭐ (create/manage hosts)
│   │   ├── EmailService.java                     ← NEW ⭐ (send credentials to host)
│   │   └── UserService.java                      ← NEW (feign client endpoints)
│   │
│   ├── controller/
│   │   ├── AuthController.java                   ← NEW ⭐ (/auth/login, /auth/tenants/register)
│   │   ├── AdminController.java                  ← NEW ⭐ (/admin/hosts/*)
│   │   └── UserController.java                   ← REPLACED (/internal/users/*, /users/*)
│   │
│   └── exception/
│       ├── GlobalExceptionHandler.java           ← REPLACED (uses ApiResponse now)
│       ├── ResourceNotFoundException.java        ← KEEP AS IS
│       └── InvalidCredentialsException.java      ← KEEP AS IS
│
└── src/main/resources/
    └── application.properties                    ← REPLACED (added JWT + Mail config)
```

**Key Changes Made:**
- Merged `Admin`, `Host`, `Tenant` entities → single `AppUser` with `UserRole` enum
- `AppUser` implements `UserDetails` for Spring Security
- JWT generated on login, validated in filter
- Admin auto-created on app startup (`admin@settlespot.com` / `Admin@123`)
- Admin creates Host → system generates password → email sent via Gmail
- Internal feign endpoints at `/internal/**` bypass JWT (service-to-service)

---

### 5.4 SettlespotPropertyService (Port: 8082)
```
SettlespotPropertyService/
├── pom.xml                                       ← ADD Lombok + Validation deps
├── src/main/java/com/info/settlespot/propertyservice/
│   ├── SettlespotPropertyServiceApplication.java ← KEEP AS IS
│   │
│   ├── entity/
│   │   └── Property.java                         ← REPLACED ⭐ (new fields + fixed images)
│   │
│   ├── enums/
│   │   ├── PropertyType.java                     ← KEEP AS IS (PG, ROOM, HOSTEL)
│   │   └── PropertyApprovalStatus.java           ← NEW ⭐ (PENDING_APPROVAL, APPROVED, REJECTED)
│   │
│   ├── dto/
│   │   ├── ApiResponse.java                      ← NEW ⭐
│   │   ├── PropertyDTO.java                      ← REPLACED (Lombok + from/toEntity)
│   │   ├── UserDTO.java                          ← UPDATED (Lombok)
│   │   └── ApprovalRequest.java                  ← NEW (action + reason)
│   │
│   ├── repo/
│   │   └── PropertyRepository.java               ← REPLACED (new queries)
│   │
│   ├── service/
│   │   └── PropertyService.java                  ← REPLACED ⭐ (approval workflow)
│   │
│   ├── controller/
│   │   └── PropertyController.java               ← REPLACED ⭐ (ApiResponse + admin endpoints)
│   │
│   ├── externalService/
│   │   └── UserClient.java                       ← UPDATED (uses /internal/ path)
│   │
│   ├── config/
│   │   └── SecurityConfig.java                   ← NEW (permit all — gateway handles auth)
│   │
│   └── exception/
│       ├── GlobalExceptionHandler.java           ← REPLACED (uses ApiResponse)
│       └── ResourceNotFoundException.java        ← KEEP AS IS
│
└── src/main/resources/
    └── application.properties                    ← KEEP AS IS
```

**Key Changes Made:**
- `Property` entity: added `approvalStatus`, `rejectionReason`, `averageRating`, `totalRatings`
- Fixed `images` field: was `@Column(name="image_url")` on a List — now correctly `@ElementCollection`
- New workflow: property submitted → PENDING_APPROVAL → Admin approves/rejects → visible to tenants
- `hostId` extracted from JWT header (`X-User-Id`), not from request body
- Admin endpoints: `GET /properties/admin/pending`, `PATCH /properties/admin/{id}/review`

---

### 5.5 SettlespotBookingService (Port: 8083)
```
SettlespotBookingService/
├── pom.xml                                       ← ADD Lombok + Validation + Security
├── src/main/java/com/info/settlespot/bookingservice/
│   ├── SettlespotBookingServiceApplication.java  ← KEEP AS IS
│   │
│   ├── entity/
│   │   └── Booking.java                          ← REPLACED ⭐ (Lombok + rating fields)
│   │
│   ├── enums/
│   │   └── BookingStatus.java                    ← KEEP AS IS
│   │
│   ├── dto/
│   │   ├── ApiResponse.java                      ← NEW ⭐
│   │   ├── BookingRequestDTO.java                ← REPLACED (Lombok, validation)
│   │   ├── BookingResponseDTO.java               ← REPLACED ⭐ (complete, with from())
│   │   ├── RatingRequestDTO.java                 ← NEW ⭐ (rating 1-5 + comment)
│   │   ├── PropertyDTO.java                      ← UPDATED (Lombok + approvalStatus)
│   │   └── UserDTO.java                          ← UPDATED (Lombok)
│   │
│   ├── repo/
│   │   └── BookingRepository.java                ← UPDATED (new query methods)
│   │
│   ├── service/
│   │   └── BookingService.java                   ← REPLACED ⭐ (full workflow)
│   │
│   ├── controller/
│   │   └── BookingController.java                ← REPLACED ⭐ (ApiResponse + rating)
│   │
│   ├── externalservice/
│   │   ├── PropertyClient.java                   ← UPDATED (added rating endpoint)
│   │   └── UserClient.java                       ← UPDATED (uses /internal/ paths)
│   │
│   ├── config/
│   │   └── SecurityConfig.java                   ← NEW (permit all)
│   │
│   └── exception/
│       ├── GlobalExceptionHandler.java           ← REPLACED (uses ApiResponse)
│       ├── BookingException.java                 ← KEEP AS IS
│       └── ResourceNotFoundException.java        ← KEEP AS IS
│
└── src/main/resources/
    └── application.properties                    ← KEEP AS IS
```

**Key Changes Made:**
- `Booking` entity: added `rating`, `ratingComment`, `isRated`, `propertyTitle`, `tenantName`, `hostName`
- New booking flow: PENDING → HOST approves (APPROVED) → property unavailable
- Host can approve/reject: `PATCH /bookings/{id}/review?action=APPROVE`
- Tenant can rate after checkout date: `POST /bookings/{id}/rate`
- `tenantId` extracted from `X-User-Id` header, not request body
- `UserClient` uses `/internal/` paths (bypass gateway auth for feign calls)

---

## 6. JWT FLOW — How Authentication Works

```
1. Tenant/Host/Admin calls POST /auth/login
         ↓
2. User Service validates credentials
         ↓
3. JWT token generated with claims:
   { "sub": "email", "userId": 5, "role": "TENANT", "fullName": "John" }
         ↓
4. Token returned in AuthResponse
         ↓
5. Frontend stores token in localStorage
         ↓
6. All subsequent requests: Authorization: Bearer <token>
         ↓
7. API Gateway intercepts → validates JWT → adds headers:
   X-User-Id: 5
   X-User-Email: john@example.com
   X-User-Role: TENANT
   X-User-Name: John
         ↓
8. Downstream services read X-User-Id from header
   (no JWT validation needed in Property/Booking services)
```

**JWT Secret:** Must be the same in `application.properties` of both:
- `SettlespotUserService` (generates tokens)
- `SettlespotAPIGateway` (validates tokens)

```
jwt.secret=404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970
```

---

## 7. ALL API ENDPOINTS

### Auth (Public — no token needed)
| Method | Path                        | Description              |
|--------|-----------------------------|--------------------------|
| POST   | /auth/login                 | Login (all roles)        |
| POST   | /auth/tenants/register      | Register as tenant       |

### Admin Endpoints (role: ADMIN)
| Method | Path                              | Description                    |
|--------|-----------------------------------|--------------------------------|
| POST   | /admin/hosts                      | Create host (sends email)      |
| GET    | /admin/hosts                      | List all hosts                 |
| GET    | /admin/tenants                    | List all tenants               |
| PUT    | /admin/hosts/{id}                 | Update host details            |
| PATCH  | /admin/hosts/{id}/approve         | Approve host account           |
| PATCH  | /admin/hosts/{id}/toggle-status   | Activate/deactivate host       |
| DELETE | /admin/hosts/{id}                 | Delete host                    |
| GET    | /properties/admin/pending         | View pending properties        |
| PATCH  | /properties/admin/{id}/review     | Approve/reject property        |
| GET    | /bookings/admin/all               | View all bookings              |

### Host Endpoints (role: HOST)
| Method | Path                         | Description                      |
|--------|------------------------------|----------------------------------|
| POST   | /properties                  | Add new property (goes to admin) |
| GET    | /properties/my-properties    | View own properties              |
| PUT    | /properties/{id}             | Update property                  |
| DELETE | /properties/{id}             | Delete property                  |
| GET    | /bookings/host/pending       | View pending booking requests    |
| GET    | /bookings/host/all           | View all bookings                |
| PATCH  | /bookings/{id}/review        | Approve/reject booking           |

### Tenant Endpoints (role: TENANT)
| Method | Path                         | Description                      |
|--------|------------------------------|----------------------------------|
| GET    | /properties/available        | Browse approved properties       |
| GET    | /properties/search           | Search by city, type, area       |
| GET    | /properties/filter           | Filter by city, type, price      |
| GET    | /properties/{id}             | View single property             |
| POST   | /bookings                    | Book a property                  |
| GET    | /bookings/my-bookings        | View own bookings                |
| DELETE | /bookings/{id}               | Cancel booking                   |
| POST   | /bookings/{id}/rate          | Rate after stay (1–5 stars)      |

### Profile
| Method | Path                   | Description         |
|--------|------------------------|---------------------|
| GET    | /users/profile/{id}    | Get own profile     |

---

## 8. COMMON API RESPONSE FORMAT

Every API returns this structure:

```json
{
  "success": true,
  "message": "Booking sent to host",
  "data": {
    "id": 1,
    "propertyTitle": "Cozy PG in Koregaon",
    "status": "PENDING",
    ...
  }
}
```

Error response:
```json
{
  "success": false,
  "message": "Property is already occupied.",
  "data": null
}
```

Validation error response:
```json
{
  "success": false,
  "message": "Validation failed",
  "data": {
    "email": "Invalid email format",
    "password": "Password must be at least 6 characters"
  }
}
```

---

## 9. DATABASE SETUP (MySQL)

```sql
-- Run this in MySQL Workbench or CLI before starting services

CREATE DATABASE IF NOT EXISTS settlespot_user_db;
CREATE DATABASE IF NOT EXISTS settlespot_property_db;
CREATE DATABASE IF NOT EXISTS settlespot_booking_db;
```

Spring Boot with `ddl-auto=update` will create tables automatically on first run.

---

## 10. HOW TO RUN — Step by Step

### Prerequisites
- Java 17+ installed
- MySQL 8 running on localhost:3306 (root/root)
- Maven installed
- Node.js 18+ for frontend

### Start Order (MUST follow this order)

```bash
# Step 1 — Eureka Server
cd SettlespotEurekaServer
mvn spring-boot:run
# Wait until: "Started SettlespotEurekaServerApplication"
# Open: http://localhost:8761

# Step 2 — User Service
cd SettlespotUserService
mvn spring-boot:run
# Wait until: "✅ Default admin: admin@settlespot.com / Admin@123"

# Step 3 — Property Service
cd SettlespotPropertyService
mvn spring-boot:run

# Step 4 — Booking Service
cd SettlespotBookingService
mvn spring-boot:run

# Step 5 — API Gateway (start last)
cd SettlespotAPIGateway
mvn spring-boot:run

# Step 6 — Frontend
cd settlespot-frontend
npm install
npm run dev
# Open: http://localhost:3000
```

### Default Login Credentials
| Role  | Email                    | Password   |
|-------|--------------------------|------------|
| Admin | admin@settlespot.com     | Admin@123  |
| Host  | (created by admin)       | (sent via email) |
| Tenant| (self-register)          | (chosen by user) |

---

## 11. FRONTEND — What to Build

### Tech Stack
```
React 18 + Vite
React Router v6      (routing)
Axios                (HTTP calls)
localStorage         (JWT storage)
CSS (glassmorphism)  (custom styling — no Tailwind needed)
```

### Folder Structure to Create
```
settlespot-frontend/
├── public/
│   └── index.html
├── src/
│   ├── main.jsx                         ← Entry point
│   ├── App.jsx                          ← Routes definition
│   │
│   ├── api/
│   │   ├── axios.js                     ← Axios instance with JWT interceptor
│   │   ├── authApi.js                   ← login(), registerTenant()
│   │   ├── propertyApi.js               ← getAvailable(), search(), addProperty()...
│   │   ├── bookingApi.js                ← createBooking(), rate(), approve()...
│   │   └── adminApi.js                  ← createHost(), getAllHosts()...
│   │
│   ├── context/
│   │   └── AuthContext.jsx              ← Global auth state (user, token, role)
│   │
│   ├── components/
│   │   ├── PrivateRoute.jsx             ← Protects routes by role
│   │   └── Navbar.jsx                   ← Changes based on role
│   │
│   ├── styles/
│   │   └── globals.css                  ← All glassmorphism + themes
│   │
│   └── pages/
│       ├── Landing.jsx                  ← Home page (no auth needed)
│       ├── auth/
│       │   ├── Login.jsx                ← Login (blue/glass theme)
│       │   └── TenantRegister.jsx       ← Register form
│       │
│       ├── tenant/                      ← TENANT THEME: Blue/Cyan
│       │   ├── TenantDashboard.jsx      ← Stats + quick links
│       │   ├── PropertySearch.jsx       ← Browse + filter properties
│       │   ├── PropertyDetail.jsx       ← Single property + book button
│       │   └── MyBookings.jsx           ← Bookings list + rate button
│       │
│       ├── host/                        ← HOST THEME: Purple/Violet
│       │   ├── HostDashboard.jsx        ← Stats overview
│       │   ├── MyProperties.jsx         ← List + add + delete
│       │   ├── AddProperty.jsx          ← Form to add property
│       │   └── ManageBookings.jsx       ← Pending bookings to approve/reject
│       │
│       └── admin/                       ← ADMIN THEME: Dark/Red
│           ├── AdminDashboard.jsx       ← Platform stats
│           ├── PendingProperties.jsx    ← Approve/reject properties
│           ├── ManageHosts.jsx          ← List hosts + toggle status
│           └── CreateHost.jsx          ← Form to create new host
│
├── package.json
├── vite.config.js
└── index.html
```

### Theme Design Reference

#### Tenant Theme — Serene Blue
```
Background gradient: #0f2027 → #203a43 → #2c5364
Glass card: rgba(255,255,255,0.08) with backdrop-filter: blur(20px)
Accent: #00d4ff (cyan)
Button: linear-gradient(135deg, #667eea, #764ba2)
```

#### Host Theme — Royal Purple
```
Background gradient: #1a0533 → #2d1057 → #4a1080
Glass card: rgba(255,255,255,0.07) with backdrop-filter: blur(20px)
Accent: #c77dff (violet)
Button: linear-gradient(135deg, #c77dff, #7b2ff7)
```

#### Admin Theme — Dark Command
```
Background gradient: #0a0a0a → #1a0000 → #2d0505
Glass card: rgba(255,255,255,0.05) with backdrop-filter: blur(20px)
Accent: #ff4444 (red)
Button: linear-gradient(135deg, #ff4444, #cc0000)
```

### Axios Setup (src/api/axios.js)
```javascript
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',   // All calls go through API Gateway
  headers: { 'Content-Type': 'application/json' }
});

// Attach JWT to every request automatically
api.interceptors.request.use(config => {
  const token = localStorage.getItem('token');
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

// Handle 401 — redirect to login
api.interceptors.response.use(
  response => response,
  error => {
    if (error.response?.status === 401) {
      localStorage.clear();
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

export default api;
```

### AuthContext (src/context/AuthContext.jsx)
```javascript
import { createContext, useContext, useState } from 'react';

const AuthContext = createContext();

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    const saved = localStorage.getItem('user');
    return saved ? JSON.parse(saved) : null;
  });

  const login = (userData, token) => {
    localStorage.setItem('token', token);
    localStorage.setItem('user', JSON.stringify(userData));
    setUser(userData);
  };

  const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export const useAuth = () => useContext(AuthContext);
```

### Route Structure (src/App.jsx)
```javascript
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';

// Public routes — no auth needed
/                     → Landing page
/login                → Login
/register             → Tenant Register

// Tenant routes (role: TENANT)
/tenant/dashboard     → TenantDashboard
/tenant/properties    → PropertySearch
/tenant/properties/:id → PropertyDetail
/tenant/bookings      → MyBookings

// Host routes (role: HOST)
/host/dashboard       → HostDashboard
/host/properties      → MyProperties
/host/properties/add  → AddProperty
/host/bookings        → ManageBookings

// Admin routes (role: ADMIN)
/admin/dashboard      → AdminDashboard
/admin/properties     → PendingProperties
/admin/hosts          → ManageHosts
/admin/hosts/create   → CreateHost
```

### PrivateRoute (src/components/PrivateRoute.jsx)
```javascript
import { Navigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function PrivateRoute({ children, allowedRole }) {
  const { user } = useAuth();

  if (!user) return <Navigate to="/login" />;

  if (allowedRole && user.role !== allowedRole)
    return <Navigate to="/login" />;

  return children;
}

// Usage in App.jsx:
// <Route path="/admin/*" element={
//   <PrivateRoute allowedRole="ADMIN"><AdminDashboard /></PrivateRoute>
// } />
```

### After Login — Where to Redirect
```javascript
// In Login.jsx, after successful login:
const { data } = response.data;   // ApiResponse.data = AuthResponse
login(data, data.token);          // Save to context + localStorage

// Redirect based on role
if (data.role === 'ADMIN') navigate('/admin/dashboard');
else if (data.role === 'HOST') navigate('/host/dashboard');
else navigate('/tenant/dashboard');
```

---

## 12. ENTITY RELATIONSHIP (What Each User Can Do)

```
AppUser (users table)
├── role = ADMIN
│   ├── Can: createHost, updateHost, deleteHost, toggleHostStatus, approveHost
│   ├── Can: viewPendingProperties, approveProperty, rejectProperty
│   └── Can: viewAllBookings
│
├── role = HOST
│   ├── Must be: isHostApproved = true to login
│   ├── Can: addProperty (goes to admin), updateProperty, deleteProperty
│   ├── Can: viewMyProperties (all statuses)
│   ├── Can: viewPendingBookings, approveBooking, rejectBooking
│   └── Cannot: register themselves (admin creates them)
│
└── role = TENANT
    ├── Can: self-register
    ├── Can: browseApprovedProperties, searchProperties, filterProperties
    ├── Can: createBooking, cancelBooking
    ├── Can: rateBooking (only after checkOut date, only if APPROVED)
    └── Cannot: see pending/rejected properties
```

---

## 13. PROPERTY LIFECYCLE

```
HOST adds property
       ↓
   status = PENDING_APPROVAL
       ↓
ADMIN reviews at GET /properties/admin/pending
       ↓
   PATCH /properties/admin/{id}/review  { "action": "APPROVE" }
       ↓
   status = APPROVED  →  Visible to tenants
       (OR)
   PATCH /properties/admin/{id}/review  { "action": "REJECT", "reason": "..." }
       ↓
   status = REJECTED  →  Host notified

When property is updated → goes back to PENDING_APPROVAL
```

---

## 14. BOOKING LIFECYCLE

```
TENANT creates booking
       ↓
   status = PENDING  (property still available)
       ↓
HOST reviews at GET /bookings/host/pending
       ↓
   PATCH /bookings/{id}/review?action=APPROVE
       ↓
   status = APPROVED  →  property.isAvailable = false
       (OR)
   PATCH /bookings/{id}/review?action=REJECT
       ↓
   status = REJECTED  →  property stays available

TENANT can cancel at any time → DELETE /bookings/{id}
   If was APPROVED → property.isAvailable = true

After checkOutDate passes → TENANT can rate
   POST /bookings/{id}/rate  { "rating": 5, "comment": "Great place!" }
   → property.averageRating updated
```

---

## 15. IMPORTANT NOTES FOR DEVELOPMENT

### Email Setup
In `SettlespotUserService/application.properties`, replace:
```properties
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
```
To get Gmail app password: Google Account → Security → 2-Step Verification → App Passwords

### Feign Client Calls (Service-to-Service)
All feign calls use `/internal/**` paths which bypass Gateway JWT validation:
- `UserClient` in Property Service → `GET /internal/users/hosts/{id}`
- `UserClient` in Booking Service → `GET /internal/users/tenants/{id}`
- `PropertyClient` in Booking Service → `GET /properties/{id}` (direct, no auth header needed)

### What Still Uses Old Repos
The old `HostRepository.java` and `TenantRepository.java` can be deleted.
The old entity classes `Admin.java`, `Host.java`, `Tenant.java` can be deleted.
Everything now goes through `UserRepository` + `AppUser`.

---

## 16. MYSQL SCHEMA (Auto-created, but for reference)

```sql
-- settlespot_user_db
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    role ENUM('ADMIN','HOST','TENANT') NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    business_name VARCHAR(255),
    office_address TEXT,
    is_host_approved BOOLEAN DEFAULT FALSE,
    age INT,
    occupation VARCHAR(255),
    permanent_address TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- settlespot_property_db
CREATE TABLE properties (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    city VARCHAR(100) NOT NULL,
    area VARCHAR(100) NOT NULL,
    address TEXT NOT NULL,
    rent_amount DOUBLE NOT NULL,
    property_type ENUM('PG','ROOM','HOSTEL') NOT NULL,
    is_available BOOLEAN DEFAULT TRUE,
    host_id INT NOT NULL,
    approval_status ENUM('PENDING_APPROVAL','APPROVED','REJECTED') DEFAULT 'PENDING_APPROVAL',
    rejection_reason TEXT,
    average_rating DOUBLE DEFAULT 0.0,
    total_ratings INT DEFAULT 0,
    posted_on DATETIME DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE property_images (
    property_id INT NOT NULL,
    image_url VARCHAR(500),
    FOREIGN KEY (property_id) REFERENCES properties(id) ON DELETE CASCADE
);

-- settlespot_booking_db
CREATE TABLE bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    property_id INT NOT NULL,
    tenant_id INT NOT NULL,
    host_id INT NOT NULL,
    property_title VARCHAR(255),
    tenant_name VARCHAR(255),
    host_name VARCHAR(255),
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    total_amount DOUBLE NOT NULL,
    status ENUM('PENDING','APPROVED','REJECTED','CANCELLED') DEFAULT 'PENDING',
    rating INT,
    rating_comment TEXT,
    is_rated BOOLEAN DEFAULT FALSE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
```

---

*Document generated for SettleSpot Microservices Project*
*Backend: Spring Boot 3.2 | Frontend: React 18 + Vite*