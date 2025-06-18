package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.request.CreateOrderRequest;
import com.ecommerce.orderservice.dto.request.OrderItemRequest;
import com.ecommerce.orderservice.dto.response.OrderResponse;
import com.ecommerce.orderservice.entity.Customer;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.entity.OrderItem;
import com.ecommerce.orderservice.entity.OrderStatus;
import com.ecommerce.orderservice.events.OrderToPaymentEvent;
import com.ecommerce.orderservice.repository.CustomerRepository;
import com.ecommerce.orderservice.repository.OrderRepository;
import com.ecommerce.orderservice.service.impl.OrderServiceImpl;
import com.ecommerce.orderservice.service.interfaces.KafkaProducerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private KafkaProducerService kafkaProducerService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrder_shouldCreateOrderAndSendEvent() {
        // Arrange
        String customerId = "cust123";

        CreateOrderRequest request = new CreateOrderRequest();
        request.setCustomerId(customerId);
        OrderItemRequest itemRequest = new OrderItemRequest();
        itemRequest.setProductId("prod001");
        itemRequest.setProductName("Producto X");
        itemRequest.setQuantity(2);
        itemRequest.setPrice(new BigDecimal("10.50"));
        request.setItems(List.of(itemRequest));

        Customer mockCustomer = new Customer();
        mockCustomer.setId(customerId);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(mockCustomer));

        Order savedOrder = new Order();
        savedOrder.setId(UUID.randomUUID().toString());
        savedOrder.setCustomer(mockCustomer);
        savedOrder.setStatus(OrderStatus.CREATED);
        savedOrder.setCreatedAt(LocalDateTime.now());

        OrderItem savedItem = new OrderItem();
        savedItem.setId(UUID.randomUUID().toString());
        savedItem.setProductId("prod001");
        savedItem.setProductName("Producto X");
        savedItem.setQuantity(2);
        savedItem.setPrice(new BigDecimal("10.50"));
        savedItem.setOrder(savedOrder);
        savedOrder.setItems(List.of(savedItem));

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        // Act
        OrderResponse response = orderService.createOrder(request);

        // Assert
        assertNotNull(response);
        assertEquals(customerId, response.getCustomerId());
        assertEquals(1, response.getItems().size());
        assertEquals("prod001", response.getItems().get(0).getProductId());

        verify(orderRepository, times(1)).save(any(Order.class));
        verify(kafkaProducerService, times(1)).sendOrderCreatedEvent(any(OrderToPaymentEvent.class));
    }
}
