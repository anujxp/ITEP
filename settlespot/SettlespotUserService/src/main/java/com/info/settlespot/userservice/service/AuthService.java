package com.info.settlespot.userservice.service;

import com.info.settlespot.userservice.dto.request.LoginRequest;
import com.info.settlespot.userservice.dto.request.RegisterTenantRequest;
import com.info.settlespot.userservice.dto.response.AuthResponse;
import com.info.settlespot.userservice.entity.AppUser;
import com.info.settlespot.userservice.entity.UserRole;
import com.info.settlespot.userservice.exception.InvalidCredentialsException;
import com.info.settlespot.userservice.exception.ResourceNotFoundException;
import com.info.settlespot.userservice.repository.UserRepository;
import com.info.settlespot.userservice.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public AuthResponse registerTenant(RegisterTenantRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email already registered: " + req.getEmail());
        }

        AppUser user = AppUser.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .phoneNumber(req.getPhoneNumber())
                .role(UserRole.TENANT)
                .age(req.getAge())
                .occupation(req.getOccupation())
                .permanentAddress(req.getPermanentAddress())
                .isActive(true)
                .build();

        AppUser saved = userRepository.save(user);
        String token = jwtUtil.generateToken(saved);

        return AuthResponse.builder()
                .token(token)
                .userId(saved.getId())
                .fullName(saved.getFullName())
                .email(saved.getEmail())
                .role(saved.getRole())
                .build();
    }

    public AuthResponse login(LoginRequest req) {
        AppUser user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("No account found with email: " + req.getEmail()));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid password");
        }

        if (!user.isActive()) {
            throw new InvalidCredentialsException("Account is deactivated. Contact admin.");
        }

        String token = jwtUtil.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .userId(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}