package com.ecommerce.orderservice.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.ecommerce.orderservice.entity.OrderStatus;

public class OrderResponse {
    private String orderId;
    private String customerId;
    private LocalDateTime createdAt;
    private OrderStatus status;
    private List<OrderItemResponse> items;
    
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public List<OrderItemResponse> getItems() {
		return items;
	}
	public void setItems(List<OrderItemResponse> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "OrderResponse [orderId=" + orderId + ", customerId=" + customerId + ", createdAt=" + createdAt
				+ ", status=" + status + ", items=" + items + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(createdAt, customerId, items, orderId, status);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderResponse other = (OrderResponse) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(customerId, other.customerId)
				&& Objects.equals(items, other.items) && Objects.equals(orderId, other.orderId)
				&& status == other.status;
	}
    
    
}

