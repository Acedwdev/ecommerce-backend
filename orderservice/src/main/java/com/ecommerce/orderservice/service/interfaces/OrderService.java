package com.ecommerce.orderservice.service.interfaces;

import java.util.List;

import com.ecommerce.orderservice.dto.request.CreateOrderRequest;
import com.ecommerce.orderservice.dto.request.OrderItemRequest;
import com.ecommerce.orderservice.dto.response.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(CreateOrderRequest request);
    
    OrderResponse getOrderById(String orderId);

    OrderResponse updateOrderItems(String orderId, List<OrderItemRequest> updatedItems);

}

