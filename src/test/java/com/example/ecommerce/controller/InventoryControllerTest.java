package com.example.ecommerce.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100.0);
        product.setQuantity(10);
    }

    @Test
    public void testGetAllProducts() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(Arrays.asList(product));
        
        when(inventoryService.getAllProducts(pageable)).thenReturn(productPage);

        mockMvc.perform(get("/api/inventory")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Test Product"));
    }

    @Test
    public void testAddNewProduct() throws Exception {
        when(inventoryService.addNewProduct(product)).thenReturn(product);

        mockMvc.perform(post("/api/inventory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Product"))
                .andExpect(jsonPath("$.response").value("Data Users sudah berhasil di input"))
                .andExpect(jsonPath("$.status").value("200"));
    }

    @Test
    public void testUpdateProductQuantity() throws Exception {
        when(inventoryService.updateProductQuantity(1L, 20)).thenReturn(product);

        mockMvc.perform(put("/api/inventory/1")
                .param("quantity", "20")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(10))  // assuming the quantity does not actually change in mock
                .andExpect(jsonPath("$.response").value("Perubahan Data Users Berhasil"))
                .andExpect(jsonPath("$.status").value("200"));
    }

    @Test
    public void testCheckProductAvailability() throws Exception {
        when(inventoryService.checkProductAvailability(1L)).thenReturn(10);

        mockMvc.perform(get("/api/inventory/1/availability")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(10))
                .andExpect(jsonPath("$.response").value("Pencarian Data Users Berhasil"))
                .andExpect(jsonPath("$.status").value("200"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
