package com.info.settlespot.userservice.controller;

import com.info.settlespot.userservice.dto.ApiResponse;
import com.info.settlespot.userservice.dto.request.CreateHostRequest;
import com.info.settlespot.userservice.dto.request.UpdateHostRequest;
import com.info.settlespot.userservice.dto.response.UserResponse;
import com.info.settlespot.userservice.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/hosts/create")
    public ResponseEntity<ApiResponse<UserResponse>> createHost(
            @Valid @RequestBody CreateHostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Host created and credentials sent via email",
                        adminService.createHost(request)));
    }

    @GetMapping("/hosts")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllHosts() {
        return ResponseEntity.ok(
                ApiResponse.success("Hosts fetched", adminService.getAllHosts()));
    }

    @GetMapping("/tenants")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllTenants() {
        return ResponseEntity.ok(
                ApiResponse.success("Tenants fetched", adminService.getAllTenants()));
    }

    @PutMapping("/hosts/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateHost(
            @PathVariable Integer id,
            @RequestBody UpdateHostRequest request) {
        return ResponseEntity.ok(
                ApiResponse.success("Host updated", adminService.updateHost(id, request)));
    }

    @PatchMapping("/hosts/{id}/toggle-status")
    public ResponseEntity<ApiResponse<UserResponse>> toggleHostStatus(@PathVariable Integer id) {
        return ResponseEntity.ok(
                ApiResponse.success("Host status toggled", adminService.toggleHostStatus(id)));
    }

    @PatchMapping("/hosts/{id}/approve")
    public ResponseEntity<ApiResponse<UserResponse>> approveHost(@PathVariable Integer id) {
        return ResponseEntity.ok(
                ApiResponse.success("Host approved", adminService.approveHost(id)));
    }

    @DeleteMapping("/hosts/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteHost(@PathVariable Integer id) {
        adminService.deleteHost(id);
        return ResponseEntity.ok(ApiResponse.success("Host deleted"));
    }
}