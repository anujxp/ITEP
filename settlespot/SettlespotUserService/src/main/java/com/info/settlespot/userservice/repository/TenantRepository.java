package com.info.settlespot.userservice.repository;

import com.info.settlespot.userservice.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant, Integer> {
    Optional<Tenant> findByEmail(String email);
}