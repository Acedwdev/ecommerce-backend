package com.ecommerce.orderservice.service.interfaces;

import com.ecommerce.orderservice.events.OrderToPaymentEvent;

public interface KafkaProducerService {
    void sendOrderCreatedEvent(OrderToPaymentEvent event);
}