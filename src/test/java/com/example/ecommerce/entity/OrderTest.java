package com.example.ecommerce.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

public class OrderTest {

    @Test
    public void testOrderGettersAndSetters() {
        Order order = new Order();
        LocalDateTime now = LocalDateTime.now();
        order.setId(1L);
        order.setProductId(1L);
        order.setQuantity(5);
        order.setOrderDateTime(now);
        order.setResponse("Order telah Berhasil");
        order.setStatus("200");

        assertEquals(1L, order.getId());
        assertEquals(1L, order.getProductId());
        assertEquals(5, order.getQuantity());
        assertEquals(now, order.getOrderDateTime());
        assertEquals("Order telah Berhasil", order.getResponse());
        assertEquals("200", order.getStatus());
    }
}
