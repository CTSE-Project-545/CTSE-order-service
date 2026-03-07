package com.orders.orders.dto;

import java.util.Map;

public class OrderRequestDTO {
    
    private Map<String, Integer> productIdsAndQuantity;
    private String createdBy;
    
    public OrderRequestDTO() {
    }
    
    public OrderRequestDTO(Map<String, Integer> productIdsAndQuantity, String createdBy) {
        this.productIdsAndQuantity = productIdsAndQuantity;
        this.createdBy = createdBy;
    }
    
    // Getters and Setters
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
}
