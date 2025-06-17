package com.ecommerce.orderservice.service.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.ecommerce.orderservice.dto.request.CreateOrderRequest;
import com.ecommerce.orderservice.dto.request.OrderItemRequest;
import com.ecommerce.orderservice.dto.request.OrderSearchRequest;
import com.ecommerce.orderservice.dto.response.OrderResponse;
import com.ecommerce.orderservice.entity.OrderStatus;

public interface OrderService {
    OrderResponse createOrder(CreateOrderRequest request);
    
    OrderResponse getOrderById(String orderId);

    OrderResponse updateOrderItems(String orderId, List<OrderItemRequest> updatedItems);
    
    List<OrderResponse> getOrdersByCustomer(String customerId);
    
    List<OrderResponse> searchOrders(String customerId, OrderStatus status, LocalDate startDate, LocalDate endDate);
    
    void cancelOrder(String orderId);


}

