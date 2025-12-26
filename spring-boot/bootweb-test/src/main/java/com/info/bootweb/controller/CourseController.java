package com.info.bootweb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.info.bootweb.entity.Course;
import com.info.bootweb.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
	
	@Autowired
	CourseService courseService;

	   
	    @PostMapping
	    public ResponseEntity<Course> createCourse(@RequestParam Map<String, String> params) {
	    	Course course = new Course();
	    	course.setTitle(params.get("title"));
	    	course.setLevel(params.get("level"));
	    	
	        return ResponseEntity.status(201).body(courseService.createCourse(course));
	    }

	    @GetMapping
	    public ResponseEntity<List<Course>> getAllCourses(@RequestParam Map<String,String> params) {
	    		String level = params.get("level");
	            return ResponseEntity.ok(courseService.getCourses(level));
	    }

	   
	    @GetMapping("/{courseId}")
	    public ResponseEntity<Course> getCourseById(@PathVariable int courseId) {
	        return ResponseEntity.ok(courseService.getCourseById(courseId));
	    }

	
	    @PatchMapping("/{courseId}/status")
	    public ResponseEntity<Course> updateCourseStatus( @PathVariable int courseId,@RequestParam Map<String,Boolean> params) {
	       Boolean status = params.get("status");
	    	Course updatedCourse = courseService.updateCourseStatus(courseId, status);
	        return ResponseEntity.ok(updatedCourse);
	    }
	    
	    @DeleteMapping("/{courseId}")
	    public ResponseEntity<Void> deleteCourse(@PathVariable int courseId) {
	        courseService.deleteCourse(courseId);
	        return ResponseEntity.noContent().build();
	    }
	
}
