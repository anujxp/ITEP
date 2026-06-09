---
inclusion: always
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
