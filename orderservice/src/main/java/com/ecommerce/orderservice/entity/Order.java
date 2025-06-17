package com.ecommerce.orderservice.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();
    
	public Order() {
		super();
	}

	public Order(String id, Customer customer, LocalDateTime createdAt, OrderStatus status, List<OrderItem> items) {
		super();
		this.id = id;
		this.customer = customer;
		this.createdAt = createdAt;
		this.status = status;
		this.items = items;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", createdAt=" + createdAt + ", status=" + status
				+ ", items=" + items + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, customer, id, items, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(customer, other.customer)
				&& Objects.equals(id, other.id) && Objects.equals(items, other.items) && status == other.status;
	}
    
    
}

