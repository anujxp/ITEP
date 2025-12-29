package com.info.ecommerce.service;

import com.info.ecommerce.dto.CategoryResponseDTO;
import com.info.ecommerce.entity.Category;
import com.info.ecommerce.repo.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepo;
    public CategoryService(CategoryRepository categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    @Transactional
    public CategoryResponseDTO save(Category category){
        Category dbCategory = categoryRepo.save(category);
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(dbCategory.getId());
        dto.setCategoryName(dbCategory.getCategoryName());
        return dto;
    }

    public List<CategoryResponseDTO> saveInBulk(List<Category> list){


    }



}
