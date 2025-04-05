package com.example.shop.repository;

import com.example.shop.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository{
    List<Customer> findAll();
    void deleteAll();
    Optional<Customer> findById(Long id);
}