package com.info.fiegnclient.controller;

import com.info.fiegnclient.dto.CategoryResponseDTO;
import com.info.fiegnclient.externalservice.CategoryFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryFeignClient categoryClient;
    public CategoryController(CategoryFeignClient categoryClient){
        this.categoryClient = categoryClient;
    }
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getCategories(){
        List<CategoryResponseDTO> list =  categoryClient.getCategories();
        return ResponseEntity.ok(list);
    }
}
