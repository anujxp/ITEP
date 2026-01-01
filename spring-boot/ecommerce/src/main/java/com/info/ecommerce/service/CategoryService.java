package com.info.ecommerce.service;

import com.info.ecommerce.dto.CategoryResponseDTO;
import com.info.ecommerce.entity.Category;
import com.info.ecommerce.exception.ResourceNotFoundException;
import com.info.ecommerce.repo.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    List<Category> dblList = categoryRepo.saveAll(list);
    List<CategoryResponseDTO> dtoList = new ArrayList<CategoryResponseDTO>();
    for(Category c : dblList){
        CategoryResponseDTO categoryDTO = new CategoryResponseDTO();
        categoryDTO.setId(c.getId());
        categoryDTO.setCategoryName(c.getCategoryName());
        dtoList.add(categoryDTO);

    }
    return dtoList;
    }

    public List<CategoryResponseDTO> getAllCategory(){
        List<Category> dblist = categoryRepo.findAll();
        List<CategoryResponseDTO> dtoList = new ArrayList<CategoryResponseDTO>();
        for (Category c : dblist){
            CategoryResponseDTO dto = new CategoryResponseDTO();
            dto.setCategoryName(c.getCategoryName());
            dto.setId(c.getId());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public CategoryResponseDTO getCategoryById(int id) {
        Category dbCategory  = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(dbCategory.getId());
        dto.setCategoryName(dbCategory.getCategoryName());
        return dto;
    }
}