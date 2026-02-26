package com.info.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.info.entity.Blog;
import com.info.service.BlogService;

@RestController
@RequestMapping("/blog")
public class BlogController {

	private BlogService service;
	public BlogController(BlogService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<Blog> insertBlog(@RequestBody Blog blog){
		return ResponseEntity.ok(service.insert(blog));
	}
	
	@GetMapping
	public ResponseEntity<List<Blog>> getAllBlog(){
		return ResponseEntity.ok(service.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Blog> getBlog(@PathVariable int id){
		return ResponseEntity.ok(service.getById(id));
	}
	
	@PutMapping
	public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog){
		return ResponseEntity.ok(service.updateBlog(blog));
	}
}
