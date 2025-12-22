package com.info.bootweb.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.info.bootweb.enity.Product;
import com.info.bootweb.repo.Productepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

	private final Productepository productRepo;

	@Autowired
	public ProductService(Productepository productRepo) {
		this.productRepo = productRepo;
	}

	public Product saveProduct(Product p) {
		return this.productRepo.save(p);
	}

	public List<Product> getList() {
		return productRepo.findAll();
	}
	public void delete(int id ) {
	productRepo.deleteById(id);
	}
	
	}
