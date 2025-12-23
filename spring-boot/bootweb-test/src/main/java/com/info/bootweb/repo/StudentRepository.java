package com.info.bootweb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.bootweb.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
