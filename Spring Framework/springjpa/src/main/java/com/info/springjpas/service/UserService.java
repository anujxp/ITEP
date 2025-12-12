package com.info.springjpas.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.springjpa.entity.User;
import com.info.springjpa.repo.UserRepositry;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	@Autowired
	UserRepositry userRepo;
	@Transactional
	public User saveUser(User user) {
		return userRepo.save(user);
	}
	public java.util.List<User> getUserList(){
		return userRepo.findAll();
		
	}
}
