package org.check.something;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.check.something.controllers.ProductsController;
import org.check.something.entities.Product;
import org.check.something.services.ProductsServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductsControllerTests {

    @MockBean
    ProductsServiceImpl productsService;

    ObjectMapper om = new ObjectMapper();

    @Autowired
    ProductsController productsController;
    private MockMvc mockMvc;
    private final Product product = new Product();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productsController)
                .build();
        product.setId(1L);
        product.setProductName("Watermelon");
        product.setStock(100);
        product.setLastUpdate(Date.valueOf("2021-02-01"));
    }

    @Test
    @SneakyThrows
    public void addProduct() {
        when(productsService.save(any()))
                .thenReturn(product);

        mockMvc.perform(post("/api/v1/products/add")
                        .content(om.writeValueAsString(product))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.productName", Matchers.is("Watermelon")))
                .andExpect(jsonPath("$.lastUpdate", Matchers.is(1612130400000L)))
                .andExpect(jsonPath("$.stock", Matchers.is(100)));
    }

    @Test
    @SneakyThrows
    public void getProducts() {
        when(productsService.getProducts())
                .thenReturn(List.of(product));

        mockMvc.perform(get("/api/v1/products/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$.[0].productName", Matchers.is("Watermelon")))
                .andExpect(jsonPath("$.[0].lastUpdate", Matchers.is(1612130400000L)))
                .andExpect(jsonPath("$.[0].stock", Matchers.is(100)));
    }

    @Test
    @SneakyThrows
    public void getProduct() {
        when(productsService.getProduct(1L))
                .thenReturn(Optional.of(product));

        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.productName", Matchers.is("Watermelon")))
                .andExpect(jsonPath("$.lastUpdate", Matchers.is(1612130400000L)))
                .andExpect(jsonPath("$.stock", Matchers.is(100)));
    }


    @Test
    @SneakyThrows
    public void updateProduct() {
        product.setProductName("Watermelon edited");
        product.setStock(200);
        when(productsService.updateProduct(1L, product))
                .thenReturn(product);

        mockMvc.perform(put("/api/v1/products/1")
                        .content(om.writeValueAsString(product))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.productName", Matchers.is("Watermelon edited")))
                .andExpect(jsonPath("$.lastUpdate", Matchers.is(1612130400000L)))
                .andExpect(jsonPath("$.stock", Matchers.is(200)));
    }

    @Test
    @SneakyThrows
    public void deleteProduct() {
        willDoNothing().given(productsService).deleteProduct(1L);

        mockMvc.perform(delete("/api/v1/products/1"))
                .andExpect(status().isNoContent());
    }
}
