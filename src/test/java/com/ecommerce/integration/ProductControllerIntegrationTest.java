package com.ecommerce.integration;


import com.ecommerce.controller.ProductController;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DisplayName("ProductController Integration Tests")
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    private ProductDto productDto;
    private Product savedProduct;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();

        productDto = new ProductDto();
        productDto.setName("Test Product");
        productDto.setDescription("Test Description");
        productDto.setPrice(new BigDecimal("99.99"));
        productDto.setStock(50);
        productDto.setCategory("Test Category");

        savedProduct = new Product();
        savedProduct.setName("Saved Product");
        savedProduct.setDescription("Saved Description");
        savedProduct.setPrice(new BigDecimal("199.99"));
        savedProduct.setStock(25);
        savedProduct.setCategory("Saved Category");
        savedProduct = productRepository.save(savedProduct);
    }

    @Test
    @DisplayName("Should create a product via POST /api/products")
    void testCreateProduct() throws Exception {
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is("Test Product")))
                .andExpect(jsonPath("$.description", is("Test Description")))
                .andExpect(jsonPath("$.price", is(99.99)))
                .andExpect(jsonPath("$.stock", is(50)))
                .andExpect(jsonPath("$.category", is("Test Category")));
    }

    @Test
    @DisplayName("Should return all products via GET /api/products")
    void testFindAllProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(savedProduct.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is("Saved Product")));
    }

    @Test
    @DisplayName("Should return product by id via GET /api/products/{id}")
    void testFindProductById() throws Exception {
        mockMvc.perform(get("/api/products/" + savedProduct.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(savedProduct.getId().intValue())))
                .andExpect(jsonPath("$.name", is("Saved Product")))
                .andExpect(jsonPath("$.price", is(199.99)));
    }

    @Test
    @DisplayName("Should return 404 for non-existent product")
    void testFindProductByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/products/99999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", notNullValue()));
    }

    @Test
    @DisplayName("Should update product via PUT /api/products/{id}")
    void testUpdateProduct() throws Exception {
        productDto.setName("Updated Product");
        productDto.setPrice(new BigDecimal("299.99"));

        mockMvc.perform(put("/api/products/" + savedProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(savedProduct.getId().intValue())))
                .andExpect(jsonPath("$.name", is("Updated Product")))
                .andExpect(jsonPath("$.price", is(299.99)));
    }

    @Test
    @DisplayName("Should delete product via DELETE /api/products/{id}")
    void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/" + savedProduct.getId()))
                .andExpect(status().isNoContent());

        assertFalse(productRepository.existsById(savedProduct.getId()));
    }

    @Test
    @DisplayName("Should return products by category via GET /api/products/category/{category}")
    void testFindProductsByCategory() throws Exception {
        mockMvc.perform(get("/api/products/category/Saved Category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].category", is("Saved Category")));
    }

    @Test
    @DisplayName("Should return 400 for invalid product data")
    void testCreateProductWithInvalidData() throws Exception {
        ProductDto invalidDto = new ProductDto();
        invalidDto.setName("");
        invalidDto.setPrice(new BigDecimal("-10"));

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest());
    }
}