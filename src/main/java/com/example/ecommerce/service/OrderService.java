package com.example.ecommerce.service;

import java.time.LocalDateTime;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;

@Service
public class OrderService { 
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public boolean orderProduct(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                                           .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        if (product.getQuantity() >= quantity) {
            // Kurangi kuantitas produk yang tersedia
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);

            // Simpan data order
            Order order = new Order();
            order.setProductId(productId);
            order.setQuantity(quantity);
            order.setOrderDateTime(LocalDateTime.now());
            orderRepository.save(order);

            return true;
        } else {
            return false; // Produk tidak tersedia dalam jumlah yang diminta
        }
    }
}
