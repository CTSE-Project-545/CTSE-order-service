package com.orders.orders.dto;

import com.orders.orders.model.Order;

import java.time.LocalDateTime;
import java.util.Map;

public class OrderResponseDTO {
    
    private String id;
    private Map<String, Integer> productIdsAndQuantity;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public OrderResponseDTO() {
    }
    
    public OrderResponseDTO(Order order) {
        this.id = order.getId();
        this.productIdsAndQuantity = order.getProductIdsAndQuantity();
        this.createdBy = order.getCreatedBy();
        this.createdAt = order.getCreatedAt();
        this.updatedAt = order.getUpdatedAt();
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Map<String, Integer> getProductIdsAndQuantity() {
        return productIdsAndQuantity;
    }
    
    public void setProductIdsAndQuantity(Map<String, Integer> productIdsAndQuantity) {
        this.productIdsAndQuantity = productIdsAndQuantity;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
