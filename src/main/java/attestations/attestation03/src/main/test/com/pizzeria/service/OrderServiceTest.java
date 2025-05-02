package com.pizzeria.service;

import com.pizzeria.dto.OrderItemRequest;
import com.pizzeria.dto.OrderRequest;
import com.pizzeria.dto.OrderResponse;
import com.pizzeria.exception.BadRequestException;
import com.pizzeria.exception.ResourceNotFoundException;
import com.pizzeria.model.*;
import com.pizzeria.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private PizzaRepository pizzaRepository;
    private OrderItemRepository orderItemRepository;
    private ModelMapper modelMapper;

    private OrderService orderService;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        customerRepository = mock(CustomerRepository.class);
        pizzaRepository = mock(PizzaRepository.class);
        orderItemRepository = mock(OrderItemRepository.class);
        modelMapper = new ModelMapper();

        orderService = new OrderService(orderRepository, customerRepository, pizzaRepository, orderItemRepository, modelMapper);
    }

    // проверяет успешное создание заказа
    @Test
    void createOrder_shouldCreateOrderSuccessfully() {
        Customer customer = new Customer();
        customer.setId(1L);

        Pizza pizza = new Pizza();
        pizza.setId(2L);
        pizza.setName("Pepperoni");
        pizza.setAvailable(true);
        pizza.setPrice(BigDecimal.valueOf(12));

        OrderItemRequest itemRequest = new OrderItemRequest();
        itemRequest.setPizzaId(2L);
        itemRequest.setQuantity(2);

        OrderRequest request = new OrderRequest();
        request.setCustomerId(1L);
        request.setDeliveryAddress("123 Main St");
        request.setItems(List.of(itemRequest));

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(pizzaRepository.findById(2L)).thenReturn(Optional.of(pizza));
        when(orderRepository.save(any(Order.class))).thenAnswer(inv -> inv.getArgument(0));
        when(orderItemRepository.findByOrder(any())).thenReturn(List.of());

        OrderResponse response = orderService.createOrder(request);

        assertEquals("123 Main St", response.getDeliveryAddress());
        assertEquals(OrderStatus.NEW, response.getStatus());
        verify(orderItemRepository).saveAll(anyList());
    }

    // проверяет корректное получение данных заказа по его ID
    @Test
    void getOrderById_shouldReturnOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setCustomer(new Customer());

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderItemRepository.findByOrder(order)).thenReturn(List.of());

        OrderResponse response = orderService.getOrderById(1L);

        assertNotNull(response);
    }

    // проверяет корректность отмены заказа
    @Test
    void cancelOrder_shouldUpdateStatusToCancelled() {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.NEW);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        orderService.cancelOrder(1L);

        assertEquals(OrderStatus.CANCELLED, order.getStatus());
        verify(orderRepository).save(order);
    }


    // проверяет корректную обработку попытки заказа недоступной пиццы
    @Test
    void createOrder_shouldThrowIfPizzaUnavailable() {
        Customer customer = new Customer();
        customer.setId(1L);

        Pizza pizza = new Pizza();
        pizza.setId(2L);
        pizza.setAvailable(false);

        OrderItemRequest item = new OrderItemRequest();
        item.setPizzaId(2L);
        item.setQuantity(1);

        OrderRequest request = new OrderRequest();
        request.setCustomerId(1L);
        request.setItems(List.of(item));

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(pizzaRepository.findById(2L)).thenReturn(Optional.of(pizza));

        assertThrows(BadRequestException.class, () -> orderService.createOrder(request));
    }
}
