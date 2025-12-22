package com.info.bootweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.info.bootweb.BootwebApplication;
import com.info.bootweb.enity.Product;
import com.info.bootweb.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@RestController
@RequestMapping("/product")
public class ProductController {

    

private final ProductService  productService;

	@Autowired	
	
	public ProductController(ProductService productsService){
		this.productService = productsService;
	}
	
	  @PostMapping
	  public ResponseEntity<?> save(@RequestBody Product p) {	
		  if (p.getTitle() == null || p.getTitle().isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product name cannot be empty");
	        }
		  Product savedProduct =  productService.saveProduct(p);
		  return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
	  }
	  
	  @GetMapping
	  public List<Product> getList(){
		  return productService.getList();
	  }
	  @GetMapping("/{id}")
	  public ResponseEntity<?> getById(@PathVariable int id){
		 Optional<Product> product = productService.getProductById(id);
		 if(product.isPresent())
			 return ResponseEntity.ok(product.get());
		 else 
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No product found with ID: " + id);
		 
 	  }
	  
	  @DeleteMapping("/{id}")
	  public ResponseEntity<?> delete(@PathVariable int id ) {
		 try {
			 if(productService.getProductById(id)!= null) {
			  productService.delete(id);
			 return ResponseEntity.ok("product deleted success fully");
			 }
			 else
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("product not deleted");
		 }catch(Exception e) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("product not deleted");
		 }
	  }

	  
	  
	  @PutMapping("/{id}")
	  public ResponseEntity<Product> update(@PathVariable int id ,@RequestBody Product p ){
		  return ResponseEntity.ok(productService.updateProduct(id, p));
		  
	  }
	  
	  
	  
	  
	  
}
