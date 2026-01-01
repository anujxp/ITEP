package com.info.ecommerce.controller;

import com.info.ecommerce.dto.ProductResponseDTO;
import com.info.ecommerce.entity.Product;
import com.info.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @PostMapping("/{categoryId}")
    public ResponseEntity<ProductResponseDTO> save(@PathVariable int categoryId, @RequestBody Product product) {
        ProductResponseDTO dbProduct =  productService.save(categoryId, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(dbProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getList(){
        return ResponseEntity.ok(productService.getList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long id ){
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping("/{categoryId}/bulk")
    public ResponseEntity<List<ProductResponseDTO>> saveInBulk(@PathVariable int categoryId,@RequestBody List<Product> list){
        List<ProductResponseDTO> dbList = productService.saveInBulk(categoryId,list);
        return ResponseEntity.status(HttpStatus.CREATED).body(dbList);
    }


}
