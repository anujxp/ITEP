package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.order.entity.Order;
import com.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/place/{productId}/{quantity}")
    public Order createOrder(@PathVariable Long productId, @PathVariable Integer quantity) {
        return orderService.placeOrder(productId, quantity);
    }
}