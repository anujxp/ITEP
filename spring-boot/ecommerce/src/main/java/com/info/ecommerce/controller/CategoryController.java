package com.info.ecommerce.controller;

import com.info.ecommerce.dto.CategoryResponseDTO;
import com.info.ecommerce.entity.Category;
import com.info.ecommerce.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> save(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.save(category));
    }
    @PostMapping("")
    public ResponseEntity<List<CategoryResponseDTO>> saveInBulk(){
    List<Category> dbCategory  = categoryService.get
    }
}
