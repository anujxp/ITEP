package com.info.externalService;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCTMICROSERVICE")
public interface ProductMicroServiceClient {
	@GetMapping("/products/{name}")
	List<Object> getProductByCategory(@PathVariable String name);
}
