package com.example.shop.repository.impl;

import com.example.shop.model.Customer;
import com.example.shop.repository.CustomerRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM shop.customer";  // явно указана схема shop
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Customer(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                ));
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("TRUNCATE TABLE shop.customer CASCADE");  // исправлено
    }

    @Override
    public Optional<Customer> findById(Long id) {
        String sql = "SELECT * FROM shop.customer WHERE id = ?";  // явно указана схема
        return jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) ->
                new Customer(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                )).stream().findFirst();
    }
}