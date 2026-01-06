package com.info.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.entity.Category;
import com.info.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	private CategoryService categoryService;
	private CategoryController(CategoryService categoryService){
		this.categoryService = categoryService;
	}
	 @GetMapping
	   public ResponseEntity<List<Category>> getList(){
		   return ResponseEntity.ok(categoryService.getList());
	   }
	 @PostMapping	
	   public ResponseEntity<List<Category>> getCategories(){
		 return ResponseEntity.ok(categoryService.saveInBulk()); 
	   }
	 @GetMapping("/{name}")
	 public ResponseEntity<?> getProductByCategory(@PathVariable String name){
		 return ResponseEntity.ok(categoryService.getProductByCategory(name));
		 
	 }

}