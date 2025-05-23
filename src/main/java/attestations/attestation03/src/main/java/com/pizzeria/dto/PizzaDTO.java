package com.pizzeria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PizzaDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean available;
}