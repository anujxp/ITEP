package com.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.dto.UserDto;
import com.user.entity.User;
import com.user.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepo,PasswordEncoder passwordEncoder) {
		this.userRepo  = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	@Transactional
	public UserDto saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User dbUser = userRepo.save(user);
		UserDto dto = new UserDto();
		dto.setId(dbUser.getId());
		dto.setEmail(dbUser.getEmail());
		dto.setContact(dbUser.getContact());
		return dto;
		
	}
	

}