package com.todo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{
	List<Task> findByPid(Integer priority);
	
	
}
