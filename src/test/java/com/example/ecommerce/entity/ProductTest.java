package com.example.ecommerce.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testProductGettersAndSetters() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100.0);
        product.setQuantity(10);
        product.setResponse("Success");
        product.setStatus("200");

        assertEquals(1L, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals(100.0, product.getPrice());
        assertEquals(10, product.getQuantity());
        assertEquals("Success", product.getResponse());
        assertEquals("200", product.getStatus());
    }
}
