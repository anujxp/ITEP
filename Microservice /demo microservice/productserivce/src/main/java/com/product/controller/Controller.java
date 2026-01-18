package com.product.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.product.entity.Product;
import com.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class Controller {

    @Autowired
    private ProductService productService;

    // 1. Create a new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    // 2. Get all active (non-deleted) products
    @GetMapping
    public List<Product> getAllActive() {
        return productService.getAllActiveProducts();
    }

    // 3. Get a specific product by ID (Used by Order Service)
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            // Returns 404 if the product is soft-deleted or doesn't exist
            return ResponseEntity.notFound().build();
        }
    }

    // 4. Soft Delete a product
    @PatchMapping("/{id}/delete")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.softDeleteProduct(id);
        return ResponseEntity.ok("Product soft-deleted successfully");
    }
}