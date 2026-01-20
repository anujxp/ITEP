package com.blog.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.Service.BlogService;
import com.blog.dto.BlogRequestDTO;
import com.blog.entity.Blog;

@RestController
@RequestMapping("/blog")
public class Controller {
	private final BlogService blogService;
	
	public Controller(BlogService blogService) {
		this.blogService = blogService;
	}
	@PostMapping("/write")
	public ResponseEntity<Blog> write(@RequestBody Blog blog){
		return ResponseEntity.ok(blogService.write(blog));
	}

    @PutMapping("/update")
    public ResponseEntity<Blog> update(@RequestBody BlogRequestDTO blog) {
        return ResponseEntity.ok(blogService.update(blog));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable int id) {
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }
}
 