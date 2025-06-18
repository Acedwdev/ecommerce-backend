package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.dto.request.CreateOrderRequest;
import com.ecommerce.orderservice.dto.request.OrderItemRequest;
import com.ecommerce.orderservice.entity.OrderStatus;
import com.ecommerce.orderservice.entity.Customer;
import com.ecommerce.orderservice.repository.CustomerRepository;
import com.ecommerce.orderservice.repository.OrderRepository;
import com.ecommerce.orderservice.service.interfaces.KafkaProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired 
    private OrderRepository orderRepository;
    
    @MockBean
    private KafkaProducerService kafkaProducerService;


    @BeforeEach
    void setup() {
        orderRepository.deleteAll(); 
        customerRepository.deleteAll();

        // Clientes necesarios
        customerRepository.saveAll(List.of(
                new Customer("customer123", "Cliente Test", "cliente@test.com"),
                new Customer("customerX", "Cliente X", "clienteX@test.com"),
                new Customer("cancelTest", "Cliente Cancel", "cancel@test.com")
        ));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createOrder_ShouldReturnCreated() throws Exception {
    	Mockito.doNothing().when(kafkaProducerService).sendOrderCreatedEvent(Mockito.any());

        CreateOrderRequest request = new CreateOrderRequest();
        request.setCustomerId("customer123");

        OrderItemRequest item = new OrderItemRequest();
        item.setProductId("product1");
        item.setProductName("Producto Test");
        item.setQuantity(2);
        item.setPrice(new BigDecimal("100.00"));

        request.setItems(List.of(item));

        mockMvc.perform(post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerId").value("customer123"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getOrderById_ShouldReturnOk() throws Exception {
        // Crear pedido primero
        CreateOrderRequest request = new CreateOrderRequest();
        request.setCustomerId("customer123");

        OrderItemRequest item = new OrderItemRequest();
        item.setProductId("productX");
        item.setProductName("Producto Test Two");
        item.setQuantity(1);
        item.setPrice(new BigDecimal("80.00"));
        request.setItems(List.of(item));

        String response = mockMvc.perform(post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String orderId = objectMapper.readTree(response).get("orderId").asText();

        mockMvc.perform(get("/api/v1/orders/{id}", orderId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(orderId));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void searchOrders_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/v1/orders/search")
                        .param("status", OrderStatus.PENDING.name()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void cancelOrder_ShouldReturnNoContent() throws Exception {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setCustomerId("customer123");

        OrderItemRequest item = new OrderItemRequest();
        item.setProductId("product1");
        item.setProductName("Producto Test");
        item.setQuantity(2);
        item.setPrice(new BigDecimal("100.00"));
        request.setItems(List.of(item));

        String response = mockMvc.perform(post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String orderId = objectMapper.readTree(response).get("orderId").asText();

        mockMvc.perform(delete("/api/v1/orders/{id}", orderId))
                .andDo(print())
                .andExpect(status().isNoContent()); 
    }

}
