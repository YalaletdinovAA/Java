package com.pizzeria.repository;

import com.pizzeria.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByActiveTrue();
    boolean existsByEmail(String email);
}