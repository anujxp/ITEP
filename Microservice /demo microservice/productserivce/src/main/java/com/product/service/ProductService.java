package com.product.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.product.entity.Product;
import com.product.repo.ProductRepository;
import com.product.service.ProductService;

@Service
public class ProductService{
	@Autowired
    private ProductRepository productRepository;


    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllActiveProducts() {
          return productRepository.findByIsDeletedFalse();
    }


    public Product getProductById(Long id) {
        // We use the custom Query method from the repository 
        // to ensure we don't return "deleted" items
        return productRepository.findActiveById(id)
                .orElseThrow(() -> new RuntimeException("Product not found or is inactive"));
    }

 
    public void softDeleteProduct(Long id) {
        // 1. Fetch the existing product
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        
        // 2. Flip the soft delete flag
        product.setDeleted(true);
        
        // 3. Save the updated entity (this is an UPDATE, not a DELETE)
        productRepository.save(product);
    }
}