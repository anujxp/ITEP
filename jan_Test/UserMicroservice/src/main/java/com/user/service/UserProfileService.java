package com.user.service;

import org.springframework.stereotype.Service;

import com.user.dto.UserProfileDTO;
import com.user.entity.User;
import com.user.entity.UserProfile;
import com.user.exception.ResourceNotFoundException;
import com.user.repo.UserProfileRepository;
import com.user.repo.UserRepository;

@Service
public class UserProfileService {
	private UserProfileRepository repo;
	private UserRepository userRepo;
	public UserProfileService(UserProfileRepository repo, UserRepository userRepo) {
		this.repo = repo;
		this.userRepo = userRepo;
	}
	
	public String create(UserProfileDTO dto) {
		User user = userRepo.findById(dto.getUserId()).orElseThrow(()-> new ResourceNotFoundException("user not found with id " + dto.getUserId()));
		UserProfile userProfile = new UserProfile();
		userProfile.setBio(dto.getBio());
		userProfile.setUsername(dto.getUsername());
		userProfile.setUser(user);
		repo.save(userProfile);
		return "Profile Created Successfully";
	}
	
	
	public UserProfileDTO viewProfile(int user_id) {
		UserProfile dbProfile = repo.getByUserId(user_id).orElseThrow(()-> new ResourceNotFoundException("profile not found for user id "+ user_id));
		
		UserProfileDTO dto = new UserProfileDTO();
		dto.setId(dbProfile.getId());
		dto.setUserId(dbProfile.getUser().getId());
		dto.setUsername(dbProfile.getUsername());
		dto.setBio(dbProfile.getBio());
		return dto;
	}
	
	
	public String updateProfile(UserProfileDTO dto) {
		UserProfile dbProfile = repo.getByUserId(dto.getUserId()).orElseThrow(()-> new ResourceNotFoundException("profile not found for user id "+ dto.getUserId()));
		
		dbProfile.setUsername(dto.getUsername());
		dbProfile.setBio(dto.getBio());
		repo.save(dbProfile);
		return "profile updated successfully";
		
	}
}