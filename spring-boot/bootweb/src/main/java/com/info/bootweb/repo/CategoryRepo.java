package com.info.bootweb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.bootweb.enity.Product;

@Repository
public interface  CategoryRepo extends JpaRepository<Product, Integer> {

}
