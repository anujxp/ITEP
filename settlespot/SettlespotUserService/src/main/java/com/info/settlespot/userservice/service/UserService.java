package com.info.settlespot.userservice.service;

import com.info.settlespot.userservice.dto.response.UserResponse;
import com.info.settlespot.userservice.entity.UserRole;
import com.info.settlespot.userservice.exception.ResourceNotFoundException;
import com.info.settlespot.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUserById(Integer id) {
        return userRepository.findById(id)
                .map(UserResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public UserResponse getHostById(Integer id) {
        return userRepository.findById(id)
                .filter(u -> u.getRole() == UserRole.HOST)
                .map(UserResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("Host not found with id: " + id));
    }

    public UserResponse getTenantById(Integer id) {
        return userRepository.findById(id)
                .filter(u -> u.getRole() == UserRole.TENANT)
                .map(UserResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found with id: " + id));
    }
}