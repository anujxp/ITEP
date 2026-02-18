package com.ecommerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Category;
import com.ecommerce.exception.GlobalExceptinoHandler;
import com.ecommerce.service.CategoryService;


@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:5173")

public class CategoryController {


	private final CategoryService categoryService;
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
			
	}
	@GetMapping
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(categoryService.getAll());
	}
	@PostMapping("/saveInBulk")
	public ResponseEntity<?> saveBulk(@RequestBody List<String> list) {
		return ResponseEntity.ok(categoryService.saveInBulk(list));
	}
	@PostMapping("/save")
	public ResponseEntity<?> save(String name){
		return ResponseEntity.ok(categoryService.save(name));
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> fetch(@PathVariable int id){
		return ResponseEntity.ok(categoryService.fetchById(id));
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<?> delete(@PathVariable int id){
		return ResponseEntity.ok(categoryService.delete(id));
	}
	@PatchMapping
	public ResponseEntity<?> update(@RequestBody Category category){
		return ResponseEntity.ok(categoryService.update(category));
	}
}
