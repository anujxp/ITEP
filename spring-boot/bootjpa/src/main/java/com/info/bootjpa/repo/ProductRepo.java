package com.info.bootjpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.info.bootjpa.entity.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{
	@Query("select p from Product p")
	List<Product> getAllProduct();
	
}
