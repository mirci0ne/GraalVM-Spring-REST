package org.check.something.controllers;

import org.check.something.dtos.OrdersDto;
import org.check.something.entities.Orders;
import org.check.something.services.OrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {

    @Autowired
    private OrdersServiceImpl ordersService;

    @PostMapping("/add")
    public ResponseEntity<Orders> addOrder(@RequestBody OrdersDto ordersDto) {
        return new ResponseEntity<>(ordersService.save(ordersDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public List<Orders> getOrder(@PathVariable Long id) {
        return ordersService.getOrder(id);
    }

    @GetMapping("/all")
    public List<Orders> getOrders() {
        return ordersService.getOrders();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Orders> deleteOrder(@PathVariable Long id) {
        ordersService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @RequestBody OrdersDto ordersDto) {
        return new ResponseEntity<>(ordersService.updateOrder(id, ordersDto), HttpStatus.ACCEPTED);
    }
}
