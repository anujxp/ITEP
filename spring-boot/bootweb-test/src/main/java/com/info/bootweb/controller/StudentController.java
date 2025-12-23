package com.info.bootweb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.info.bootweb.entity.Student;
import com.info.bootweb.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {
		
	private final StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;		
	}
	@PostMapping("/students")
	public ResponseEntity<Student> register(@RequestBody Student s){
		try {  
			  Student dbStudent = studentService.registerStudent(s);
			  return ResponseEntity.status(201).body(dbStudent);
			}
			catch(Exception e) {
				return ResponseEntity.internalServerError().build();
			}
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAll(){
		try {  
			  List<Student> dbList = studentService.getAll();
			  return ResponseEntity.ok(dbList);
			}
			catch(Exception e) {
				return ResponseEntity.internalServerError().build();
			}
	}
	
	@GetMapping("/students/{studentId}")
	public ResponseEntity<Student> getById(@PathVariable int  studentId){
		
		try {  
			Optional<Student> dbStudent  =  studentService.getStudentById(studentId);
			  return ResponseEntity.ok(dbStudent.get());
			}
			catch(Exception e) {
				return ResponseEntity.internalServerError().build();
			}
	}
	
	@PutMapping("/students/{studentId}")
	public ResponseEntity<Student> update(@PathVariable int studentId , @RequestBody Student s){
		try {  
		
			  return ResponseEntity.ok(studentService.updateProduct(studentId, s));
			}
			catch(Exception e) {
				return ResponseEntity.internalServerError().build();
			}
		
	}
	
	@PatchMapping("/students/{studentId}/deactivate")
	public ResponseEntity<String> deactivate(@PathVariable int studentId){
		
		try {  
			studentService.deactivateStudent(studentId);
			  return ResponseEntity.ok("student deactivated successfully");
			}
			catch(Exception e) {
				return ResponseEntity.internalServerError().build();
			}
	}
	
	@DeleteMapping("students/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int studentId) {
       
        try {  
        	 studentService.deleteStudent(studentId);
             return ResponseEntity.noContent().build();
			}
			catch(Exception e) {
				return ResponseEntity.internalServerError().build();
			}
    }
	
	
	
	
}
