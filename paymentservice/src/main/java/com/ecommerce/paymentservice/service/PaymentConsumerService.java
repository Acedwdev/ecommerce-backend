package com.ecommerce.paymentservice.service;

import com.ecommerce.paymentservice.entity.Payment;
import com.ecommerce.paymentservice.repository.PaymentRepository;
import com.ecommerce.paymentservice.events.OrderToPaymentEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class PaymentConsumerService {

    private static final Logger logger = Logger.getLogger(PaymentConsumerService.class.getName());
    private final PaymentRepository paymentRepository;

    public PaymentConsumerService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @KafkaListener(topics = "order.created.payment", groupId = "payment-service-group")
    public void consumeOrderCreated(OrderToPaymentEvent event) {
        logger.info("📥 Recibido evento de pedido: " + event);

        Payment payment = new Payment();
        payment.setOrderId(event.getOrderId());
        payment.setCustomerId(event.getCustomerId());
        payment.setAmount(event.getTotal());
        payment.setStatus("PAID"); // Simulación
        payment.setProcessedAt(LocalDateTime.now());

        paymentRepository.save(payment);
        logger.info("✅ Pago registrado para orden " + event.getOrderId());
    }
}
