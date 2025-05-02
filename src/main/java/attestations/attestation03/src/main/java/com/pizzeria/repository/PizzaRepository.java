package com.pizzeria.repository;

import com.pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    List<Pizza> findAllByAvailableTrue();
    boolean existsByName(String name);
}