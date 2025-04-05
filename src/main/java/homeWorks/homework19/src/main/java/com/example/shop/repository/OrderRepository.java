package com.example.shop.repository;

import com.example.shop.model.Order;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> findAll();
    void deleteAll();
    Optional<Order> findById(Long id);
    List<Order> findByDate(LocalDateTime date);
}