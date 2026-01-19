package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.user.entity.User;
import com.user.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return authService.register(user);
    }
    @PostMapping("/login")
    public String getToken(@RequestParam String username, @RequestParam String password) {
        return authService.login(username, password);
    }
}