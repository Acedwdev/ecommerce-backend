package com.ecommerce.orderservice.dto.response;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItemResponse {
    private String productId;
    private String productName;
    private int quantity;
    private BigDecimal price;
    
	public OrderItemResponse() {
		super();
	}
	
	public OrderItemResponse(String productId, String productName, int quantity, BigDecimal price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}

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
		return "OrderItemResponse [productId=" + productId + ", productName=" + productName + ", quantity=" + quantity
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
		OrderItemResponse other = (OrderItemResponse) obj;
		return Objects.equals(price, other.price) && Objects.equals(productId, other.productId)
				&& Objects.equals(productName, other.productName) && quantity == other.quantity;
	}
    
}

