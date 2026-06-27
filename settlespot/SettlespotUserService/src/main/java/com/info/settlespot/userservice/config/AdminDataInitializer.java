package com.info.settlespot.userservice.config;

import com.info.settlespot.userservice.entity.AppUser;
import com.info.settlespot.userservice.entity.UserRole;
import com.info.settlespot.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminDataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.findByRole(UserRole.ADMIN).isEmpty()) {
            AppUser admin = AppUser.builder()
                    .fullName("Super Admin")
                    .email("admin@settlespot.com")
                    .password(passwordEncoder.encode("Admin@123"))
                    .role(UserRole.ADMIN)
                    .isActive(true)
                    .build();
            userRepository.save(admin);
//            log.info("✅ Default admin created → Email: admin@settlespot.com | Password: Admin@123");
        }
    }
}