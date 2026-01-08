package com.info.settlespot.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.settlespot.userservice.entity.Host;

@Repository
public interface HostRepository extends JpaRepository<Host, Integer>{
	Optional<Host> findByEmail(String email);
}
