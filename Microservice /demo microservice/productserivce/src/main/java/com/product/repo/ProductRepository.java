package com.product.repo;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Only find products where isDeleted is false
    List<Product> findByIsDeletedFalse();

    // Ensure fetching a single product by ID also respects soft delete
    @Query("SELECT p FROM Product p WHERE p.id = ?1 AND p.isDeleted = false")
    Optional<Product> findActiveById(Long id);
}