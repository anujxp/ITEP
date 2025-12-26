package com.info.ecommerce.service;

import org.springframework.stereotype.Service;

import com.info.ecommerce.entity.User;
import com.info.ecommerce.repo.UserRepository;

@Service
public class UserService {
	private UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
		
	}
	
	public User registerUser(User user) {
		return userRepo.save(user);
	}
	
	
}
