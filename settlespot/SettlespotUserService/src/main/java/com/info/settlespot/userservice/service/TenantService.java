package com.info.settlespot.userservice.service;

import com.info.settlespot.userservice.dto.LoginRequestDTO;
import com.info.settlespot.userservice.dto.TenantResponseDTO; // Import DTO
import com.info.settlespot.userservice.entity.Tenant;
import com.info.settlespot.userservice.exception.InvalidCredentialsException;
import com.info.settlespot.userservice.exception.ResourceNotFoundException;
import com.info.settlespot.userservice.repository.TenantRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;
    private final PasswordEncoder passwordEncoder;

    public TenantService(TenantRepository tenantRepository, PasswordEncoder passwordEncoder) {
        this.tenantRepository = tenantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // --- 1. REGISTER ---
    @Transactional
    public TenantResponseDTO registerTenant(Tenant tenant) {
        String encryptedPassword = passwordEncoder.encode(tenant.getPassword());
        tenant.setPassword(encryptedPassword);

        Tenant savedTenant = tenantRepository.save(tenant);
        
        // Convert Entity -> DTO
        return new TenantResponseDTO(savedTenant);
    }

    // --- 2. LOGIN ---
    public TenantResponseDTO loginTenant(LoginRequestDTO loginRequest) {
        Tenant tenant = tenantRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Tenant", "email", loginRequest.getEmail()));

        if (!passwordEncoder.matches(loginRequest.getPassword(), tenant.getPassword())) {
            throw new InvalidCredentialsException("Invalid Password!"); 
        }
        
        // Convert Entity -> DTO (Safe, no password inside)
        return new TenantResponseDTO(tenant);
    }

    // --- 3. GET BY ID ---
    public TenantResponseDTO getTenantById(Integer id) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant", "id", id));
        
        return new TenantResponseDTO(tenant);
    }
}