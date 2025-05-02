package com.pizzeria.repository;

import com.pizzeria.model.Customer;
import com.pizzeria.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByActiveTrue();
    List<Order> findByCustomerAndActiveTrue(Customer customer);
}