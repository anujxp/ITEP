package com.info.service;

import java.util.ArrayList;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.info.dto.CategoryDTO;
import com.info.entity.Category;
import com.info.externalService.CategoryClient;
import com.info.externalService.ProductMicroServiceClient;
import com.info.repo.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {

	private CategoryRepository categoryRepo;
	private CategoryClient categoryClient;
	private ProductMicroServiceClient productClient;
	
	public CategoryService(CategoryRepository categoryRepo,CategoryClient categoryClient,ProductMicroServiceClient productClient) {
		this.categoryRepo = categoryRepo;
		this.productClient = productClient;
		this.categoryClient = categoryClient;
	}
	
	@Transactional
	  public List<Category> saveInBulk() {
		 List<CategoryDTO> dtoList =  categoryClient.getCategories();
	     List<Category> categoryList = new ArrayList<>();
	     for(CategoryDTO dto : dtoList) {
	    	 Category c = new Category();
	    	 c.setName(dto.getName());
	    	 c.setSlug(dto.getSlug());
	    	 c.setUrl(dto.getUrl());
	    	 categoryList.add(c);
	     }
	     List<Category> dbCategories =  categoryRepo.saveAll(categoryList);
	     return dbCategories;
	  }
	public List<Category> getList(){
		return categoryRepo.findAll();
	}
	public List<Object> getProductByCategory(String cateogyName) {
		return productClient.getProductByCategory(cateogyName);
		
	}
}
