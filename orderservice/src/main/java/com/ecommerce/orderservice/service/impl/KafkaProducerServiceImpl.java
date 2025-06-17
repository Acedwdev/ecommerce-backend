package com.ecommerce.orderservice.service.impl;

import com.ecommerce.orderservice.events.OrderToPaymentEvent;
import com.ecommerce.orderservice.service.interfaces.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);
    private static final String TOPIC = "order.created.payment";

    private final KafkaTemplate<String, OrderToPaymentEvent> kafkaTemplate;

    public KafkaProducerServiceImpl(KafkaTemplate<String, OrderToPaymentEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendOrderCreatedEvent(OrderToPaymentEvent event) {
        log.info("📤 Enviando evento a Kafka: {}", event);
        kafkaTemplate.send(TOPIC, event);
    }
}

