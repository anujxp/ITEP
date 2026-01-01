package com.info.fiegnclient.externalservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;

@FeignClient(name = "product-feign-client",url = "https://dummyjson.com/products")
public interface ProductFeignClient {
    @GetMapping
    HashMap<String,Object> getProduct();
    @GetMapping("/{id}")
    Object getProductById(@PathVariable int id);

    @DeleteMapping("/{id}")
    Object deleteProduct(@PathVariable int id);

}
