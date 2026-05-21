package com.info.settlespot.userservice.controller;

import com.info.settlespot.userservice.dto.ApiResponse;
import com.info.settlespot.userservice.dto.response.UserResponse;
import com.info.settlespot.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This controller is used by other microservices via Feign Client.
 * Internal endpoints do NOT require authentication (called service-to-service).
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ─── Internal Feign Client endpoints ──────────────────────────────────────
    // These match the paths the Feign clients in Booking and Property services call

    @GetMapping("/internal/users/hosts/{id}")
    public UserResponse getHostByIdInternal(@PathVariable Integer id) {
        return userService.getHostById(id);
    }

    @GetMapping("/internal/users/tenants/{id}")
    public UserResponse getTenantByIdInternal(@PathVariable Integer id) {
        return userService.getTenantById(id);
    }

    @GetMapping("/internal/users/{id}/exists")
    public boolean userExists(@PathVariable Integer id) {
        return userService.getUserById(id) != null;
    }

    // ─── Authenticated user endpoints ─────────────────────────────────────────

    @GetMapping("/users/profile/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getProfile(@PathVariable Integer id) {
        return ResponseEntity.ok(
                ApiResponse.success("Profile fetched", userService.getUserById(id)));
    }
}