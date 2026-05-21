package com.info.settlespot.userservice.controller;

import com.info.settlespot.userservice.dto.ApiResponse;
import com.info.settlespot.userservice.dto.request.LoginRequest;
import com.info.settlespot.userservice.dto.request.RegisterTenantRequest;
import com.info.settlespot.userservice.dto.response.AuthResponse;
import com.info.settlespot.userservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/tenants/register")
    public ResponseEntity<ApiResponse<AuthResponse>> registerTenant(
            @Valid @RequestBody RegisterTenantRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tenant registered successfully",
                        authService.registerTenant(request)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(
                ApiResponse.success("Login successful", authService.login(request)));
    }
}