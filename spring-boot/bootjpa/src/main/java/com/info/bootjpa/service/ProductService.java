package com.info.bootjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.bootjpa.entity.Product;
import com.info.bootjpa.repo.ProductRepo;

import jakarta.transaction.Transactional;
@Service
public class ProductService {
	private final ProductRepo productRepo;
	
	@Autowired
	public ProductService(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}
	
	@Transactional
	public Product saveProduct(Product p) {
		return productRepo.save(p);
	}
	
	public List<Product> getProductList(){
		return productRepo.findAll();
	}
	
	
	
}
