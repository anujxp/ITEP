package com.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.todo.entity.Task;
import com.todo.exception.ResourceNotFoundException;
import com.todo.repo.TaskRepository;

@Service
public class TaskService {
	
	private final TaskRepository taskRepo;
	public TaskService(TaskRepository taskRepo) {
		this.taskRepo = taskRepo;
	}
	
	public List<Task>saveInBulk(List<Task> list){
		return taskRepo.saveAll(list);		
	}

	public Task save(Task task) {
		return taskRepo.save(task);
	}
	
	
	public Task fetch(Integer id) {
		return taskRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("task jwith this id "+ id +" not found"));
	}
	public List<Task> fetchAll() {
		return taskRepo.findAll();
	}
	public List<Task> feftchBypriority(Integer priority){
		return taskRepo.findByPid(priority);
	}
	
	
	
	public void delete(Integer id) {
	    if (taskRepo.existsById(id)) {
	        taskRepo.deleteById(id);
	    } else {
	        throw new RuntimeException("Task not found with id: " + id);
	    }
	}
	public void update(Task incomingTask) {
	    Task existingTask = taskRepo.findById(incomingTask.getId())
	        .orElseThrow(() -> new RuntimeException("Task not found"));
	    existingTask.setTitle(incomingTask.getTitle());
	    existingTask.setDate(incomingTask.getDate());
	    existingTask.setPid(incomingTask.getPid());
	    existingTask.setStatus(incomingTask.getStatus());
	    taskRepo.save(existingTask);
	}
	
//	public void updateStatus()
	
	
}
