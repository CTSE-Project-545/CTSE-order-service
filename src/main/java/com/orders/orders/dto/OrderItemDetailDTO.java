package com.orders.orders.dto;

public class OrderItemDetailDTO {

    private String productId;
    private Integer quantity;
    private ProductDetailsDTO product;

    public OrderItemDetailDTO() {
    }

    public OrderItemDetailDTO(String productId, Integer quantity, ProductDetailsDTO product) {
        this.productId = productId;
        this.quantity = quantity;
        this.product = product;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductDetailsDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDetailsDTO product) {
        this.product = product;
    }
}