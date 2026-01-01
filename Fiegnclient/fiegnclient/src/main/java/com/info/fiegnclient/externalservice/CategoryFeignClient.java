package com.info.fiegnclient.externalservice;

import com.info.fiegnclient.dto.CategoryResponseDTO;
import com.info.fiegnclient.entity.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "category-feign-client" ,url = "https://dummyjson.com/")
public interface CategoryFeignClient {
    @GetMapping("/product/categories")
    List<CategoryResponseDTO> getCategories();
}
