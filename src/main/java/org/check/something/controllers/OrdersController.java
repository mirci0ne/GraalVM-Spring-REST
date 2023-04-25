package org.check.something.controllers;

import lombok.extern.log4j.Log4j2;
import org.check.something.dtos.OrdersDto;
import org.check.something.entities.Orders;
import org.check.something.entities.Product;
import org.check.something.services.OrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
@Log4j2
public class OrdersController {

    @Autowired
    private OrdersServiceImpl ordersService;

    @PostMapping("/add")
    public ResponseEntity<Orders> addProduct(@RequestBody OrdersDto ordersDto) {
        return new ResponseEntity<>(ordersService.save(ordersDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Optional<Orders> getOrder(@PathVariable Long id) {
        return ordersService.getOrder(id);
    }

    @GetMapping("/all")
    public List<Orders> getOrders() {
        return ordersService.getOrders();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        ordersService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
