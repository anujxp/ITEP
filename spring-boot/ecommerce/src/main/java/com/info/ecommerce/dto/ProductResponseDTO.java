package com.info.ecommerce.dto;

import com.info.ecommerce.entity.Product;

public class ProductResponseDTO {
    private Long id;
    private  String title;
    private String brand;
    private double price;
    private Long categoryId;

    public ProductResponseDTO(){

    }
    public  ProductResponseDTO(Product p){
        this.setId(p.getId());
        this.setTitle(p.getTitle());
        this.setPrice(p.getPrice());
        this.setBrand(p.getBrand());
        this.setCategoryId(p.getCategory().getId());
        this.setCategoryName(p.getCategory().getCategoryName());
    }
    public ProductResponseDTO(Long id, String title, String brand, double price, Long categoryId, String categoryName) {
        this.id = id;
        this.title = title;
        this.brand = brand;
        this.price = price;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    private String categoryName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
