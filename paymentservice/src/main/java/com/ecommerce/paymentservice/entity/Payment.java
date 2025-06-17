package com.ecommerce.paymentservice.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String orderId;
    private String customerId;
    private BigDecimal amount;
    private LocalDateTime processedAt;
    private String status; // e.g. "PAID", "FAILED"
    
	public Payment() {
		super();
	}

	public Payment(String id, String orderId, String customerId, BigDecimal amount, LocalDateTime processedAt,
			String status) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.customerId = customerId;
		this.amount = amount;
		this.processedAt = processedAt;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getProcessedAt() {
		return processedAt;
	}

	public void setProcessedAt(LocalDateTime processedAt) {
		this.processedAt = processedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", orderId=" + orderId + ", customerId=" + customerId + ", amount=" + amount
				+ ", processedAt=" + processedAt + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, customerId, id, orderId, processedAt, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(customerId, other.customerId)
				&& Objects.equals(id, other.id) && Objects.equals(orderId, other.orderId)
				&& Objects.equals(processedAt, other.processedAt) && Objects.equals(status, other.status);
	}
    
    
}

