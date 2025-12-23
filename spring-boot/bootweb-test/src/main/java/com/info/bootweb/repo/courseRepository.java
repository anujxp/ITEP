package com.info.bootweb.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.bootweb.entity.Course;

@Repository
public interface courseRepository extends JpaRepository<Course, Integer>{

	List<Course> findByLevel(String level);
}
