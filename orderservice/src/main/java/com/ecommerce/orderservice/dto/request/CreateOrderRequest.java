package com.ecommerce.orderservice.dto.request;

import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.*;

public class CreateOrderRequest {
    @NotNull
    private String customerId;

    @NotEmpty
    private List<OrderItemRequest> items;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<OrderItemRequest> getItems() {
		return items;
	}

	public void setItems(List<OrderItemRequest> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "CreateOrderRequest [customerId=" + customerId + ", items=" + items + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, items);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateOrderRequest other = (CreateOrderRequest) obj;
		return Objects.equals(customerId, other.customerId) && Objects.equals(items, other.items);
	}
    
    
}
