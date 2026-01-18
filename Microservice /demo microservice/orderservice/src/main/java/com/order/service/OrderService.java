package com.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.order.entity.Order;
import com.order.repo.OrderRepository;
import com.order.feign.ProductClient;
import com.order.dto.ProductDTO;

@Service
public class OrderService { // Actually your OrderService logic

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    public Order placeOrder(Long productId, Integer quantity) {
        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);

        try {
            // This now calls http://localhost:8083/products/{id}
            ProductDTO product = productClient.getProduct(productId);
            
            order.setTotalAmount(product.getPrice() * quantity);
            order.setStatus("PLACED");
        } catch (Exception e) {
            // Handles 404 (soft-deleted) or connection issues
            order.setTotalAmount(0.0);
            order.setStatus("FAILED: Product Unavailable or Deleted");
        }

        return orderRepository.save(order);
    }
}