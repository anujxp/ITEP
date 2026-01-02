package com.info.user.service;

import com.info.user.exception.UnautherizedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.info.user.entity.User;
import com.info.user.repo.UserRepository;

@Service
public class UserService {
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo,PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        String encodedPassword =  passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepo.save(user);
    }

    public User authenticateUser(User user) {
        User dbUser = userRepo.findByEmail(user.getEmail()).orElseThrow(()->new UnautherizedException("Unauthorized | Invalid Email Id"));
        if(!passwordEncoder.matches(user.getPassword(), dbUser.getPassword()))
            throw new UnautherizedException("Unauthorized | Invalid password");
        return dbUser;
    }


}