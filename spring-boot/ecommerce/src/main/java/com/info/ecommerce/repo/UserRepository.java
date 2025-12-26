package com.info.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.ecommerce.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
