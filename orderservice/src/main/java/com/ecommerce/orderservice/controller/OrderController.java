package com.ecommerce.orderservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.orderservice.dto.request.CreateOrderRequest;
import com.ecommerce.orderservice.dto.request.UpdateOrderItemsRequest;
import com.ecommerce.orderservice.dto.response.OrderResponse;
import com.ecommerce.orderservice.entity.OrderStatus;
import com.ecommerce.orderservice.service.interfaces.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    
    public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

	@PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        OrderResponse response = orderService.createOrder(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponse> getOrderById(@PathVariable("id") String id) {
	    OrderResponse response = orderService.getOrderById(id);
	    return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}/items")
	public ResponseEntity<OrderResponse> updateOrderItems(
	        @PathVariable("id") String orderId,
	        @RequestBody UpdateOrderItemsRequest request
	) {
	    OrderResponse updatedOrder = orderService.updateOrderItems(orderId, request.getItems());
	    return ResponseEntity.ok(updatedOrder);
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<OrderResponse>> getOrdersByCustomer(@PathVariable String customerId) {
	    List<OrderResponse> orders = orderService.getOrdersByCustomer(customerId);
	    return ResponseEntity.ok(orders);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<OrderResponse>> searchOrders(
	    @RequestParam(required = false) String customerId,
	    @RequestParam(required = false) OrderStatus status,
	    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
	) {
	    List<OrderResponse> orders = orderService.searchOrders(customerId, status, startDate, endDate);
	    return ResponseEntity.ok(orders);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> cancelOrder(@PathVariable String id) {
	    orderService.cancelOrder(id);
	    return ResponseEntity.noContent().build(); // 204 No Content
	}

}
