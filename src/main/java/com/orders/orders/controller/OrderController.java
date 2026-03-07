package com.orders.orders.controller;

import com.orders.orders.dto.OrderDetailResponseDTO;
import com.orders.orders.dto.OrderRequestDTO;
import com.orders.orders.dto.OrderResponseDTO;
import com.orders.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO requestDTO) {
        OrderResponseDTO response = orderService.createOrder(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable String id) {
        OrderResponseDTO order = orderService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/createdBy/{createdBy}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByCreatedBy(@PathVariable String createdBy) {
        List<OrderResponseDTO> orders = orderService.getOrdersByCreatedBy(createdBy);
        return ResponseEntity.ok(orders);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrder(
            @PathVariable String id,
            @RequestBody OrderRequestDTO requestDTO) {
        OrderResponseDTO updatedOrder = orderService.updateOrder(id, requestDTO);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        boolean deleted = orderService.deleteOrder(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/orderDetails")
    public ResponseEntity<List<OrderDetailResponseDTO>> getOrderDetails() {
        List<OrderDetailResponseDTO> orderDetails = orderService.getOrderDetails();
        return ResponseEntity.ok(orderDetails);
    }
}
