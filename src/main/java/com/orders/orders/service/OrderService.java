package com.orders.orders.service;

import com.orders.orders.client.InventoryClient;
import com.orders.orders.dto.OrderDetailResponseDTO;
import com.orders.orders.dto.OrderItemDetailDTO;
import com.orders.orders.dto.OrderRequestDTO;
import com.orders.orders.dto.ProductDetailsDTO;
import com.orders.orders.dto.OrderResponseDTO;
import com.orders.orders.model.Order;
import com.orders.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feign.FeignException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryClient inventoryClient;
    
    public OrderResponseDTO createOrder(OrderRequestDTO requestDTO) {
        Order order = new Order();
        order.setProductIdsAndQuantity(requestDTO.getProductIdsAndQuantity());
        order.setCreatedBy(requestDTO.getCreatedBy());
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        
        Order savedOrder = orderRepository.save(order);
        return new OrderResponseDTO(savedOrder);
    }
    
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderResponseDTO::new)
                .collect(Collectors.toList());
    }
    
    public OrderResponseDTO getOrderById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(OrderResponseDTO::new).orElse(null);
    }
    
    public List<OrderResponseDTO> getOrdersByCreatedBy(String createdBy) {
        return orderRepository.findByCreatedBy(createdBy)
                .stream()
                .map(OrderResponseDTO::new)
                .collect(Collectors.toList());
    }
    
    public OrderResponseDTO updateOrder(String id, OrderRequestDTO requestDTO) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setProductIdsAndQuantity(requestDTO.getProductIdsAndQuantity());
            order.setCreatedBy(requestDTO.getCreatedBy());
            order.setUpdatedAt(LocalDateTime.now());
            
            Order updatedOrder = orderRepository.save(order);
            return new OrderResponseDTO(updatedOrder);
        }
        
        return null;
    }
    
    public boolean deleteOrder(String id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<OrderDetailResponseDTO> getOrderDetails() {
        return orderRepository.findAll()
                .stream()
                .map(this::buildOrderDetailResponse)
                .collect(Collectors.toList());
    }

    private OrderDetailResponseDTO buildOrderDetailResponse(Order order) {
        List<OrderItemDetailDTO> items = order.getProductIdsAndQuantity() == null
                ? Collections.emptyList()
                : order.getProductIdsAndQuantity()
                .entrySet()
                .stream()
                .map(this::buildOrderItemDetail)
                .collect(Collectors.toList());

        return new OrderDetailResponseDTO(order, items);
    }

    private OrderItemDetailDTO buildOrderItemDetail(Map.Entry<String, Integer> orderItem) {
        ProductDetailsDTO productDetails = fetchProductDetails(orderItem.getKey());
        return new OrderItemDetailDTO(orderItem.getKey(), orderItem.getValue(), productDetails);
    }

    private ProductDetailsDTO fetchProductDetails(String productId) {
        try {
            return inventoryClient.getProductDetails(productId);
        } catch (FeignException exception) {
            return null;
        }
    }
}
