package com.info.settlespot.userservice.service;

import com.info.settlespot.userservice.dto.HostResponseDTO; 
import com.info.settlespot.userservice.dto.LoginRequestDTO;
import com.info.settlespot.userservice.entity.Host;
import com.info.settlespot.userservice.exception.InvalidCredentialsException;
import com.info.settlespot.userservice.exception.ResourceNotFoundException;
import com.info.settlespot.userservice.repository.HostRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HostService {

    private final HostRepository hostRepository;
    private final PasswordEncoder passwordEncoder;

    public HostService(HostRepository hostRepository, PasswordEncoder passwordEncoder) {
        this.hostRepository = hostRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public HostResponseDTO registerHost(Host host) {

        String encryptedPassword = passwordEncoder.encode(host.getPassword());
        host.setPassword(encryptedPassword);

        host.setVerified(false);

        Host savedHost = hostRepository.save(host);

        return new HostResponseDTO(savedHost);
    }

    
    public HostResponseDTO loginHost(LoginRequestDTO loginRequest) {
        
        Host host = hostRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new ResourceNotFoundException("Host", "email", loginRequest.getEmail()));

        if (!passwordEncoder.matches(loginRequest.getPassword(), host.getPassword())) {
            throw new InvalidCredentialsException("Invalid Password!");
        }

        return new HostResponseDTO(host);
    }

    public HostResponseDTO getHostById(Integer id) {
        Host host = hostRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Host", "id", id));
        return new HostResponseDTO(host);
    }

    public boolean hostExists(Integer id) {
        return hostRepository.existsById(id);
    }
}