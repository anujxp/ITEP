package com.info.bootweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.info.bootweb.BootwebApplication;
import com.info.bootweb.enity.Product;
import com.info.bootweb.service.ProductService;

import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final BootwebApplication bootwebApplication;

private final ProductService  productService;
@Autowired
public ProductController(ProductService productsService, BootwebApplication bootwebApplication){
    this.productService = productsService;
    this.bootwebApplication = bootwebApplication;
}
	
	

	  @PostMapping
	  
	  public Product save(@RequestBody Product p) {
		  return productService.saveProduct(p);
	  }
	  
	  @GetMapping
	  
	  public List<Product> getList(){
		  return productService.getList();
	  }
	  @DeleteMapping("/{id}")
	  public List<Product> delete(@PathVariable int id ) {
			  productService.delete(id);
			 return productService.getList();
			 		 		  
	  }
	  
}
