package com.ecommerce.orderservice.dto.request;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.validation.constraints.*;

public class OrderItemRequest {
    @NotNull
    private String productId;

    @NotBlank
    private String productName;

    @Min(1)
    private int quantity;

    @DecimalMin("0.01")
    private BigDecimal price;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderItemRequest [productId=" + productId + ", productName=" + productName + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(price, productId, productName, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemRequest other = (OrderItemRequest) obj;
		return Objects.equals(price, other.price) && Objects.equals(productId, other.productId)
				&& Objects.equals(productName, other.productName) && quantity == other.quantity;
	}

    
}
