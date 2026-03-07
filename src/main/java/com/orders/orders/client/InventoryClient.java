package com.orders.orders.client;

import com.orders.orders.dto.ProductDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryClient {

    @GetMapping("/api/inventory/{productId}")
    ProductDetailsDTO getProductDetails(@PathVariable("productId") String productId);
}