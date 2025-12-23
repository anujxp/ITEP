package com.info.bootweb.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.info.bootweb.enity.Category;
import com.info.bootweb.enity.Product;
import com.info.bootweb.repo.CategoryRepo;
import com.info.bootweb.repo.Productepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

	private final Productepository productRepo;
	@Autowired
	private  CategoryRepo categoryRepo;

	@Autowired
	public ProductService(Productepository productRepo) {
	
		this.productRepo = productRepo;
	}

	public Product saveProduct(Product p) {
		return this.productRepo.save(p);
	}

	public Optional<Product> getProductById(int id) {
		Optional<Product> product = this.productRepo.findById(id);
		return product;	        

	}

	public List<Product> getList() {
		return productRepo.findAll();
	}

	public void delete(int id) {
		productRepo.deleteById(id);
	}

	public Product updateProduct(int id, Product newDetails) {
		// 1. Find existing product or throw exception
		Product existingProduct = productRepo.findById(id).orElse(null);

		// 2. Update only specific fields
		existingProduct.setTitle(newDetails.getTitle());
		existingProduct.setPrice(newDetails.getPrice());
		existingProduct.setBrand(newDetails.getBrand());
		existingProduct.setDiscount(newDetails.getDiscount());
		if (newDetails.getCategory() != null) {
	        int catId = newDetails.getCategory().getId();
	        // Fetch the REAL category from DB
	        Optional<Category> managedCategory = categoryRepo.findById(null);
	        
	        existingProduct.setCategory(managedCategory.get());
	    }

		// 3. Save updated entity
		return productRepo.save(existingProduct);
	}

}
