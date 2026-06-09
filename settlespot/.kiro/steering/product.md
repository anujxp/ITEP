---
inclusion: always
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
