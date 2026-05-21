package com.info.settlespot.userservice.service;

import com.info.settlespot.userservice.dto.request.CreateHostRequest;
import com.info.settlespot.userservice.dto.request.UpdateHostRequest;
import com.info.settlespot.userservice.dto.response.UserResponse;
import com.info.settlespot.userservice.entity.AppUser;
import com.info.settlespot.userservice.entity.UserRole;
import com.info.settlespot.userservice.exception.ResourceNotFoundException;
import com.info.settlespot.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Transactional
    public UserResponse createHost(CreateHostRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email already registered: " + req.getEmail());
        }

        // Generate a temporary password
        String rawPassword = "Host@" + UUID.randomUUID().toString().substring(0, 8);

        AppUser host = AppUser.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(rawPassword))
                .phoneNumber(req.getPhoneNumber())
                .role(UserRole.HOST)
                .businessName(req.getBusinessName())
                .officeAddress(req.getOfficeAddress())
                .isActive(true)
                .isHostApproved(false)
                .build();

        AppUser saved = userRepository.save(host);

        // Send welcome email with credentials
        try {
            emailService.sendHostCredentials(saved.getEmail(), saved.getFullName(), rawPassword);
        } catch (Exception e) {
            log.warn("Failed to send email to host {}: {}", saved.getEmail(), e.getMessage());
        }

        return UserResponse.from(saved);
    }

    public List<UserResponse> getAllHosts() {
        return userRepository.findByRole(UserRole.HOST)
                .stream().map(UserResponse::from).collect(Collectors.toList());
    }

    public List<UserResponse> getAllTenants() {
        return userRepository.findByRole(UserRole.TENANT)
                .stream().map(UserResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public UserResponse updateHost(Integer hostId, UpdateHostRequest req) {
        AppUser host = userRepository.findById(hostId)
                .filter(u -> u.getRole() == UserRole.HOST)
                .orElseThrow(() -> new ResourceNotFoundException("Host not found with id: " + hostId));

        if (req.getFullName() != null) host.setFullName(req.getFullName());
        if (req.getPhoneNumber() != null) host.setPhoneNumber(req.getPhoneNumber());
        if (req.getBusinessName() != null) host.setBusinessName(req.getBusinessName());
        if (req.getOfficeAddress() != null) host.setOfficeAddress(req.getOfficeAddress());
        if (req.getIsActive() != null) host.setActive(req.getIsActive());

        return UserResponse.from(userRepository.save(host));
    }

    @Transactional
    public UserResponse toggleHostStatus(Integer hostId) {
        AppUser host = userRepository.findById(hostId)
                .filter(u -> u.getRole() == UserRole.HOST)
                .orElseThrow(() -> new ResourceNotFoundException("Host not found with id: " + hostId));
        host.setActive(!host.isActive());
        return UserResponse.from(userRepository.save(host));
    }

    @Transactional
    public void deleteHost(Integer hostId) {
        AppUser host = userRepository.findById(hostId)
                .filter(u -> u.getRole() == UserRole.HOST)
                .orElseThrow(() -> new ResourceNotFoundException("Host not found with id: " + hostId));
        userRepository.delete(host);
    }

    @Transactional
    public UserResponse approveHost(Integer hostId) {
        AppUser host = userRepository.findById(hostId)
                .filter(u -> u.getRole() == UserRole.HOST)
                .orElseThrow(() -> new ResourceNotFoundException("Host not found with id: " + hostId));
        host.setHostApproved(true);
        return UserResponse.from(userRepository.save(host));
    }
}