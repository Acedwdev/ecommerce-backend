package com.ecommerce.orderservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.orderservice.dto.request.CreateOrderRequest;
import com.ecommerce.orderservice.dto.response.OrderItemResponse;
import com.ecommerce.orderservice.dto.response.OrderResponse;
import com.ecommerce.orderservice.entity.Customer;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.entity.OrderItem;
import com.ecommerce.orderservice.entity.OrderStatus;
import com.ecommerce.orderservice.repository.CustomerRepository;
import com.ecommerce.orderservice.repository.OrderRepository;
import com.ecommerce.orderservice.service.interfaces.OrderService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        // Validar existencia del cliente
        Customer customer = customerRepository.findById(request.getCustomerId())
            .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        // Crear entidad Order
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setCustomer(customer);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);

        // Mapear y asociar los items
        List<OrderItem> orderItems = request.getItems().stream().map(itemReq -> {
            OrderItem item = new OrderItem();
            item.setId(UUID.randomUUID().toString());
            item.setOrder(order);
            item.setProductId(itemReq.getProductId());
            item.setProductName(itemReq.getProductName());
            item.setQuantity(itemReq.getQuantity());
            item.setPrice(itemReq.getPrice());
            return item;
        }).collect(Collectors.toList());

        order.setItems(orderItems);

        // Guardar en la BD
        Order saved = orderRepository.save(order);

        // Mapear respuesta
        OrderResponse response = new OrderResponse();
        response.setOrderId(saved.getId());
        response.setCustomerId(saved.getCustomer().getId());
        response.setCreatedAt(saved.getCreatedAt());
        response.setStatus(saved.getStatus());

        List<OrderItemResponse> items = saved.getItems().stream().map(item -> {
            OrderItemResponse res = new OrderItemResponse();
            res.setProductId(item.getProductId());
            res.setProductName(item.getProductName());
            res.setQuantity(item.getQuantity());
            res.setPrice(item.getPrice());
            return res;
        }).collect(Collectors.toList());

        response.setItems(items);
        return response;
    }
    
    @Override
    public OrderResponse getOrderById(String orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));

        // Mapear entidad a DTO de respuesta
        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getId());
        response.setCustomerId(order.getCustomer().getId());
        response.setCreatedAt(order.getCreatedAt());
        response.setStatus(order.getStatus());

        List<OrderItemResponse> items = order.getItems().stream().map(item -> {
            OrderItemResponse itemRes = new OrderItemResponse();
            itemRes.setProductId(item.getProductId());
            itemRes.setProductName(item.getProductName());
            itemRes.setQuantity(item.getQuantity());
            itemRes.setPrice(item.getPrice());
            return itemRes;
        }).collect(Collectors.toList());

        response.setItems(items);
        return response;
    }

}

