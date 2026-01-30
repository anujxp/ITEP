package com.user.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer>{
	Optional<UserProfile> getByUserId(int id);
}
