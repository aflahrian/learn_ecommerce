package com.example.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    
    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable) {
        Page<Product> product = inventoryService.getAllProducts(pageable);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping 
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
        Product newProduct = inventoryService.addNewProduct(product);
        if(newProduct!=null) {
        	newProduct.setResponse("Data Users sudah berhasil di input");
        	newProduct.setStatus("200");
        }
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProductQuantity(@PathVariable Long productId, @RequestParam int quantity) {
        Product updatedProduct = inventoryService.updateProductQuantity(productId, quantity);
        if (updatedProduct != null) {
        	updatedProduct.setResponse("Perubahan Data Users Berhasil");
        	updatedProduct.setStatus("200");
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
        	Product  updatedProduct2 = new Product();
        	updatedProduct2.setStatus("201");
        	updatedProduct2.setResponse("product dengan id tersebut tidak di temukan pada database");
            return new ResponseEntity<>(updatedProduct2,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{productId}/availability")
    public ResponseEntity<Product> checkProductAvailability(@PathVariable Long productId) {
        int availability = inventoryService.checkProductAvailability(productId);
        if (availability>0) {
        	Product product = new Product();
        	product.setQuantity(availability);
        	product.setResponse("Pencarian Data Users Berhasil");
        	product.setStatus("200");
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
        	Product product = new Product();
        	product.setQuantity(availability);
        	product.setResponse("Product dengan id tersebut tidak di temukan pada database");
        	product.setStatus("201");
            return new ResponseEntity<Product>(product,HttpStatus.NOT_FOUND);
        }
    }
}
