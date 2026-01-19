package com.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.dto.ProductDTO;
 // You may need a DTO version of Product here

@FeignClient(name = "PRODUCTSERIVCE")
public interface ProductClient {

    @GetMapping("/products/{id}")
    public ProductDTO getProduct(@PathVariable Long id);
    //http://localhost:8083/products/1
}