package com.ecommerce.orderservice.dto.request;

import java.util.List;

public class UpdateOrderItemsRequest {

    private List<OrderItemRequest> items;

    // Constructor vacío
    public UpdateOrderItemsRequest() {
    }

    // Constructor con parámetros
    public UpdateOrderItemsRequest(List<OrderItemRequest> items) {
        this.items = items;
    }

    // Getter
    public List<OrderItemRequest> getItems() {
        return items;
    }

    // Setter
    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "UpdateOrderItemsRequest{" +
                "items=" + items +
                '}';
    }
}

