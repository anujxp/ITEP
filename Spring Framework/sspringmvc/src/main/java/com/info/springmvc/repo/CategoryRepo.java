package com.info.springmvc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.springmvc.entity.Category;

public class CategoryRepo {
	

	@Repository
	public interface CategoryRepository extends JpaRepository<Category, Integer>{

	}
}
