package com.example.shop.repository.impl;

import com.example.shop.model.Order;
import com.example.shop.repository.OrderRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT * FROM orders";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Order(
                        rs.getLong("id"),
                        rs.getLong("customer_id"),
                        rs.getTimestamp("order_date").toLocalDateTime(),
                        rs.getDouble("total_amount"),
                        rs.getDouble("discount")
                ));
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM orders");
    }

    @Override
    public Optional<Order> findById(Long id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) ->
                new Order(
                        rs.getLong("id"),
                        rs.getLong("customer_id"),
                        rs.getTimestamp("order_date").toLocalDateTime(),
                        rs.getDouble("total_amount"),
                        rs.getDouble("discount")
                )).stream().findFirst();
    }

    @Override
    public List<Order> findByDate(LocalDateTime date) {
        String sql = "SELECT * FROM orders WHERE DATE(order_date) = DATE(?)";
        return jdbcTemplate.query(sql, new Object[]{date}, (rs, rowNum) ->
                new Order(
                        rs.getLong("id"),
                        rs.getLong("customer_id"),
                        rs.getTimestamp("order_date").toLocalDateTime(),
                        rs.getDouble("total_amount"),
                        rs.getDouble("discount")
                ));
    }
}