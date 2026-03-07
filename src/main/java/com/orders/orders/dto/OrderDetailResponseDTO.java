package com.orders.orders.dto;

import com.orders.orders.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDetailResponseDTO {

    private String id;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<OrderItemDetailDTO> items;

    public OrderDetailResponseDTO() {
    }

    public OrderDetailResponseDTO(Order order, List<OrderItemDetailDTO> items) {
        this.id = order.getId();
        this.createdBy = order.getCreatedBy();
        this.createdAt = order.getCreatedAt();
        this.updatedAt = order.getUpdatedAt();
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<OrderItemDetailDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDetailDTO> items) {
        this.items = items;
    }
}