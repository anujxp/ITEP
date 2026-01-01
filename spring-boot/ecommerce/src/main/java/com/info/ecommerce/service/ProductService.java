package com.info.ecommerce.service;

import com.info.ecommerce.dto.ProductResponseDTO;
import com.info.ecommerce.entity.Category;
import com.info.ecommerce.entity.Product;
import com.info.ecommerce.exception.ResourceNotFoundException;
import com.info.ecommerce.repo.CategoryRepository;
import com.info.ecommerce.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepo;
    private CategoryRepository categoryRepo;

    public ProductService(CategoryRepository categoryRepo,ProductRepository productRepo){
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;

    }

    public List<ProductResponseDTO> getList(){
        List<Product> list = productRepo.findAll();
        List<ProductResponseDTO> dtoList = new ArrayList<>();
        for (Product p : list ){
            ProductResponseDTO p1 = new ProductResponseDTO();
            p1.setTitle(p.getTitle());
            p1.setBrand(p.getBrand());
            p1.setPrice(p.getPrice());
            p1.setId(p.getId());
            p1.setCategoryName(p.getCategory().getCategoryName());
            p1.setCategoryId(p.getCategory().getId());
            dtoList.add(p1);
        }
        return dtoList;
    }
    public ProductResponseDTO save(int categoryId, Product product) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category not found "));
        product.setCategory(category);
        productRepo.save(product);
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setPrice(product.getPrice());
        dto.setTitle(product.getTitle());
        dto.setCategoryName(category.getCategoryName());
        dto.setBrand(product.getBrand());
        return dto;

    }

    public List<ProductResponseDTO> saveInBulk(int categoryId, List<Product> list) {
        Category dbCategory  = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category not found "));
        for(Product p : list)
            p.setCategory(dbCategory);

        List<Product>dbproductList = productRepo.saveAll(list);
        List<ProductResponseDTO> dtoList = new ArrayList<>();
        for(Product p : dbproductList){
            ProductResponseDTO dto = new ProductResponseDTO(p);
            dtoList.add(dto);
        }

        return dtoList;
    }
    public ProductResponseDTO getProduct(Long id ){
        Product product = productRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not Found"));
        return new ProductResponseDTO(product);
    }



}
