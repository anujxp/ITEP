package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Category;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repo.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepo;

	public CategoryService(CategoryRepository categoryRepo) {
		this.categoryRepo = categoryRepo;
	}

	@Transactional
	public List<Category> saveInBulk(List<String> list) {
		List<Category> categoryList = new ArrayList<Category>();
		for (String s : list) {
			Category category = new Category();
			category.setName(s);
			categoryList.add(category);
		}
		return categoryRepo.saveAll(categoryList);
	}

	@Transactional
	public Category save(String categoryName) {
		Category category = new Category();
		category.setName(categoryName);
		categoryRepo.save(category);
		return new Category();
	}

	public Category fetchById(int id) {
		return categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category with this id :" + id + "Not found"));
	}
	@Transactional
	public Category delete(int id) {
		Category dbcategory =  categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category with this id :" + id + "Not found"));
		categoryRepo.delete(dbcategory);
		return dbcategory;
	}


	@Transactional
	public Category update(Category category) {
		Category dbcategory =  categoryRepo.findById(category.getId()).orElseThrow(() -> new ResourceNotFoundException("Category with this id :" + category.getId() + "Not found"));
		dbcategory.setName(category.getName());
		return categoryRepo.save(dbcategory);
		
	}
	
	public List<Category> getAll() {
		return categoryRepo.findAll();
	}
}
