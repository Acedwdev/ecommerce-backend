package com.ecommerce.orderservice.dto.request;

import com.ecommerce.orderservice.entity.OrderStatus;

import java.time.LocalDate;

public class OrderSearchRequest {
    private String customerId;
    private OrderStatus status;
    private LocalDate startDate;
    private LocalDate endDate;

    public OrderSearchRequest() {}

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}

