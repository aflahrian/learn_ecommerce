package com.example.ecommerce.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;

@Service
public class InventoryService {
    @Autowired
    private ProductRepository productRepository;

    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProductQuantity(Long productId, int newQuantity) {
        Product product = productRepository.findById(productId)
                                           .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        product.setQuantity(newQuantity);
        return productRepository.save(product);
    }

    public int checkProductAvailability(Long productId) {
        Product product = productRepository.findById(productId)
                                           .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return product.getQuantity();
    }

	public Page<Product> getAllProducts(Pageable pageable) {
		
		return productRepository.findAll(pageable);
			
	}
	
}

