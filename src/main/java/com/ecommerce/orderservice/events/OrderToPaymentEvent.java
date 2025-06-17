package com.ecommerce.orderservice.events;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class OrderToPaymentEvent {
    private String orderId;
    private String customerId;
    private BigDecimal total;
    private List<Item> items;

    public OrderToPaymentEvent() {
    }

    public OrderToPaymentEvent(String orderId, String customerId, BigDecimal total, List<Item> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.total = total;
        this.items = items;
    }

    // Getters y Setters
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderToPaymentEvent{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", total=" + total +
                ", items=" + items +
                '}';
    }

    // Clase interna
    public static class Item {
        private String productId;
        private int quantity;
        private BigDecimal price;

        public Item() {
        }

        public Item(String productId, int quantity, BigDecimal price) {
            this.productId = productId;
            this.quantity = quantity;
            this.price = price;
        }

        // Getters y Setters
        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
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
            return "Item{" +
                    "productId='" + productId + '\'' +
                    ", quantity=" + quantity +
                    ", price=" + price +
                    '}';
        }
    }
}

