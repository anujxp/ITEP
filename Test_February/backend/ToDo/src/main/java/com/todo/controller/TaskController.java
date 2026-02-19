package com.todo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.entity.Task;
import com.todo.repo.TaskRepository;
import com.todo.service.TaskService;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {

    private final TaskRepository taskRepository;
	
	private final TaskService taskService;
	public TaskController(TaskService taskService, TaskRepository taskRepository) {
		this.taskService = taskService;
		this.taskRepository = taskRepository;
		
	}
	@PostMapping("/save-in-bulk")
	public ResponseEntity<?> saveBulk(@RequestBody List<Task> list) {
		return ResponseEntity.status(201).body(taskService.saveInBulk(list));
	}
	@PostMapping("/save")
	public ResponseEntity<Task> save(@RequestBody Task task){
		return ResponseEntity.status(201).body(taskService.save(task));
	}
	@GetMapping("/{id}")
	public ResponseEntity<Task> get(@PathVariable int id){
		return ResponseEntity.ok(taskService.fetch(id));
	}
	
	@PatchMapping("/update-status/{id}")
	public ResponseEntity<Task> updateStatus(@PathVariable Integer id, @RequestParam String status) {
	    Task task = taskService.fetch(id); 
	    task.setStatus(status);
	    return ResponseEntity.ok(taskService.save(task));
	}
	@PutMapping("/update")
	public ResponseEntity<String> updateTask(@RequestBody Task task) {
	    taskService.update(task); 
	    return ResponseEntity.ok("Updated");
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTask(@PathVariable Integer id) {
	    taskService.delete(id);
	    return ResponseEntity.ok("Task Deleted Successfully");
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Task>> getAll(){
		return ResponseEntity.ok(taskService.fetchAll());
	}
	@GetMapping("/priority/{pid}")
	public ResponseEntity<List<Task>> fetchByPid(@PathVariable int pid){
		return ResponseEntity.ok(taskService.feftchBypriority(pid));
	}
	
}
