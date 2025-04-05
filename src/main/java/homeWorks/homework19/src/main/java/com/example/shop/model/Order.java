package com.example.shop.model;

import java.time.LocalDateTime;
import java.util.Objects;


public class Order {
    private Long id;
    private Long customerId;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private Double discount;

    // constructors, getters, setters
    public Order() {}

    public Order(Long id, Long customerId, LocalDateTime orderDate, Double totalAmount, Double discount) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.discount = discount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(customerId, order.customerId) && Objects.equals(orderDate, order.orderDate) && Objects.equals(totalAmount, order.totalAmount) && Objects.equals(discount, order.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, orderDate, totalAmount, discount);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", discount=" + discount +
                '}';
    }

}