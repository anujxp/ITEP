package com.info.settlespot.userservice.repository;

import com.info.settlespot.userservice.entity.AppUser;
import com.info.settlespot.userservice.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByEmail(String email);
    boolean existsByEmail(String email);
    List<AppUser> findByRole(UserRole role);
    List<AppUser> findByRoleAndIsHostApproved(UserRole role, boolean approved);
}