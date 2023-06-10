package org.check.something.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.check.something.entities.Orders;
import org.check.something.services.OrdersServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class OrdersControllerTest {

    @MockBean
    OrdersServiceImpl ordersService;

    ObjectMapper om = new ObjectMapper();

    @Autowired
    OrdersController ordersController;

    private MockMvc mockMvc;
    private final Orders orders = new Orders();


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ordersController)
                .build();
        orders.setId(1L);
        orders.setOrderDate(Date.valueOf("2023-02-06"));
        orders.setOrderProduct("Tomato");
        orders.setAmount(100);
        orders.setClientFirstName("John");
        orders.setClientLastName("Doe");
    }

    @SneakyThrows
    @Test
    void addOrder() {
        when(ordersService.save(any()))
                .thenReturn(orders);

        mockMvc.perform(post("/api/v1/orders/add")
                        .content(om.writeValueAsString(orders))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.orderProduct", Matchers.is("Tomato")))
                .andExpect(jsonPath("$.orderDate", Matchers.is(1675634400000L)))
                .andExpect(jsonPath("$.amount", Matchers.is(100)))
                .andExpect(jsonPath("$.clientFirstName", Matchers.is("John")))
                .andExpect(jsonPath("$.clientLastName", Matchers.is("Doe")));
        ;

    }

    @SneakyThrows
    @Test
    void getOrder() {
        when(ordersService.getOrder(1L))
                .thenReturn(List.of(orders));

        mockMvc.perform(get("/api/v1/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$.[0].orderProduct", Matchers.is("Tomato")))
                .andExpect(jsonPath("$.[0].orderDate", Matchers.is(1675634400000L)))
                .andExpect(jsonPath("$.[0].amount", Matchers.is(100)))
                .andExpect(jsonPath("$.[0].clientFirstName", Matchers.is("John")))
                .andExpect(jsonPath("$.[0].clientLastName", Matchers.is("Doe")));
    }

    @SneakyThrows
    @Test
    void getOrders() {
        when(ordersService.getOrders())
                .thenReturn(List.of(orders));
        mockMvc.perform(get("/api/v1/orders/all"))
                .andExpect(jsonPath("$.[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$.[0].orderProduct", Matchers.is("Tomato")))
                .andExpect(jsonPath("$.[0].orderDate", Matchers.is(1675634400000L)))
                .andExpect(jsonPath("$.[0].amount", Matchers.is(100)))
                .andExpect(jsonPath("$.[0].clientFirstName", Matchers.is("John")))
                .andExpect(jsonPath("$.[0].clientLastName", Matchers.is("Doe")));
    }

    @SneakyThrows
    @Test
    void deleteProduct() {
        willDoNothing().given(ordersService).deleteOrder(1L);

        mockMvc.perform(delete("/api/v1/orders/1"))
                .andExpect(status().isNoContent());
    }
}