package com.info.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.entity.User;

@Repository
public interface UserRepo  extends JpaRepository<User, Integer>{
	Optional<User> findUserByEmail(String email);
}
