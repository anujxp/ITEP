package com.info.fiegnclient.controller;

import com.info.fiegnclient.externalservice.ProductFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductFeignClient productFeignClient;
    public ProductController(ProductFeignClient productFeignClient){
        this.productFeignClient = productFeignClient;
    }

    @GetMapping
    public ResponseEntity<HashMap<String, Object>>  getProduct(){
        HashMap<String,Object> hm =  productFeignClient.getProduct();
     return ResponseEntity.ok(hm);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable int id){
        return ResponseEntity.ok(productFeignClient.getProductById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int id){
        return ResponseEntity.ok(productFeignClient.deleteProduct(id));
    }
}
