package com.pizzeria.dto;

import com.pizzeria.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private CustomerDTO customer;
    private LocalDateTime orderDate;
    private String deliveryAddress;
    private OrderStatus status;
    private BigDecimal totalPrice;
    private List<OrderItemDTO> items;
}