package com.pizzeria.service;

import com.pizzeria.dto.*;
import com.pizzeria.exception.BadRequestException;
import com.pizzeria.exception.ResourceNotFoundException;
import com.pizzeria.model.*;
import com.pizzeria.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final PizzaRepository pizzaRepository;
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Customer customer = customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + orderRequest.getCustomerId()));

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setDeliveryAddress(orderRequest.getDeliveryAddress());
        order.setStatus(OrderStatus.NEW);
        order.setActive(true);

        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderItem> items = orderRequest.getItems().stream()
                .map(itemRequest -> {
                    Pizza pizza = pizzaRepository.findById(itemRequest.getPizzaId())
                            .orElseThrow(() -> new ResourceNotFoundException("Pizza not found with id: " + itemRequest.getPizzaId()));

                    if (!pizza.isAvailable()) {
                        throw new BadRequestException("Pizza with id " + pizza.getId() + " is not available");
                    }

                    OrderItem item = new OrderItem();
                    item.setPizza(pizza);
                    item.setQuantity(itemRequest.getQuantity());
                    item.setItemPrice(pizza.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));
                    item.setOrder(order);
                    return item;
                })
                .collect(Collectors.toList());

        totalPrice = items.stream()
                .map(OrderItem::getItemPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(totalPrice);
        Order savedOrder = orderRepository.save(order);
        orderItemRepository.saveAll(items);

        return mapToOrderResponse(savedOrder);
    }

    public List<OrderResponse> getAllActiveOrders() {
        return orderRepository.findAllByActiveTrue()
                .stream()
                .map(this::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return mapToOrderResponse(order);
    }

    @Transactional
    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        if (order.getStatus() != OrderStatus.NEW) {
            throw new BadRequestException("Only NEW orders can be cancelled");
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        order.setActive(false);
        orderRepository.save(order);
    }

    private OrderResponse mapToOrderResponse(Order order) {
        OrderResponse response = modelMapper.map(order, OrderResponse.class);
        response.setCustomer(modelMapper.map(order.getCustomer(), CustomerDTO.class));

        List<OrderItemDTO> itemDTOs = orderItemRepository.findByOrder(order).stream()
                .map(item -> {
                    OrderItemDTO dto = new OrderItemDTO();
                    dto.setPizzaId(item.getPizza().getId());
                    dto.setPizzaName(item.getPizza().getName());
                    dto.setQuantity(item.getQuantity());
                    dto.setItemPrice(item.getItemPrice());
                    return dto;
                })
                .collect(Collectors.toList());

        response.setItems(itemDTOs);
        return response;
    }
}