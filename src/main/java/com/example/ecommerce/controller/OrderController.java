package com.example.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> orderProduct(@RequestParam Long productId, @RequestParam int quantity) {
        boolean ordered = orderService.orderProduct(productId, quantity);
        Order order = new Order();
        if (ordered) {
        	
        	order.setResponse("Order telah Berhasil");
        	order.setStatus("200");
          
        } 
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}

