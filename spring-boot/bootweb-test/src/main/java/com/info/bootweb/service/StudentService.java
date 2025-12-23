package com.info.bootweb.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.bootweb.entity.Student;
import com.info.bootweb.repo.StudentRepository;

@Service
public class StudentService {

	private final StudentRepository studentRepo;

	@Autowired
	public StudentService(StudentRepository studentRepo) {
		this.studentRepo = studentRepo;
	}

	public Student registerStudent(Student student) {

		return studentRepo.save(student);
	}

	public List<Student> getAll() {
		return studentRepo.findAll();
	}

	
	public Optional<Student> getStudentById(int id) {
		return studentRepo.findById(id);

	}

	public Student updateProduct(int id, Student newDetails) {
		// 1. Find existing product or throw exception
		Student existingStudent = studentRepo.findById(id).orElse(null);
		

		
		if (existingStudent != null) {
			existingStudent.setName(newDetails.getName());
			existingStudent.setEmail(newDetails.getEmail());
			existingStudent.setPhone(newDetails.getPhone());
			return studentRepo.save(existingStudent);
		}
		return existingStudent;

	}

	public void deactivateStudent(int studentId) {
		Optional<Student> student = studentRepo.findById(studentId);
		Student s = student.get();
		s.setIsActive(null); 
		studentRepo.save(s);
	}

	public void deleteStudent(int studentId) {
        Student student = studentRepo.findById(studentId).orElse(null);
        studentRepo.delete(student);
    }
}
