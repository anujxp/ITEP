package com.info.bootweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.info.bootweb.entity.Course;
import com.info.bootweb.entity.Student;
import com.info.bootweb.repo.courseRepository;

@Service
public class CourseService {
	
	    @Autowired
	    private courseRepository courseRepo;

	
	    public Course createCourse(Course course) {
	        return courseRepo.save(course);
	    }

	   
	    public List<Course> getCourses(String level) {
	        if (level != null && !level.isEmpty()) {
	            return courseRepo.findByLevel(level);
	        }
	        return courseRepo.findAll();
	    }

	   
	    
	    public Course getCourseById(int courseId) {
	    	Optional<Course> course = courseRepo.findById(courseId);
	        return course.get();
	    }

	    
	    public Course updateCourseStatus(int courseId, Boolean status) {
	    	Optional<Course> dbCourse = courseRepo.findById(courseId);
			Course c = dbCourse.get();
			c.setIsActive(false); 
			return courseRepo.save(c);
	    }

	    
	    public void deleteCourse(int courseId) {
	    	courseRepo.deleteById(null);
	       
	    }
	

}
