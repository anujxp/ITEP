package com.info.ecommerce.service;

import com.info.ecommerce.exception.UnauthrizedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.info.ecommerce.entity.User;
import com.info.ecommerce.repo.UserRepository;

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
		User dbUser = userRepo.findByEmail(user.getEmail()).orElseThrow(()->new UnauthrizedException("Unauthorized | Invalid Email Id"));
		if(!passwordEncoder.matches(user.getPassword(), dbUser.getPassword()))
			throw new UnauthrizedException("Unauthorized | Invalid password");
		return dbUser;
	}

	
}
