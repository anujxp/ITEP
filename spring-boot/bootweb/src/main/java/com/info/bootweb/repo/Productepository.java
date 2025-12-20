package com.info.bootweb.repo;

import com.info.bootweb.enity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Productepository extends JpaRepository<Product, Integer> {


}
