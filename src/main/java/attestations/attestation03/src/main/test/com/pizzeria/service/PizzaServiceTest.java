package com.pizzeria.service;

import com.pizzeria.dto.PizzaDTO;
import com.pizzeria.exception.ResourceNotFoundException;
import com.pizzeria.model.Pizza;
import com.pizzeria.repository.PizzaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PizzaServiceTest {

    private PizzaRepository pizzaRepository;
    private ModelMapper modelMapper;
    private PizzaService pizzaService;

    @BeforeEach
    void setUp() {
        pizzaRepository = mock(PizzaRepository.class);
        modelMapper = new ModelMapper();
        pizzaService = new PizzaService(pizzaRepository, modelMapper);
    }

    // получение данных пиццы в формате DTO по её ID
    @Test
    void getPizzaById_shouldReturnPizzaDTO() {
        Pizza pizza = new Pizza();
        pizza.setId(1L);
        pizza.setName("Margherita");
        pizza.setPrice(BigDecimal.valueOf(8.99));

        when(pizzaRepository.findById(1L)).thenReturn(Optional.of(pizza));

        PizzaDTO result = pizzaService.getPizzaById(1L);

        assertEquals("Margherita", result.getName());
    }

    // попытка получить несуществующую пиццу
    @Test
    void getPizzaById_shouldThrowIfNotFound() {
        when(pizzaRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> pizzaService.getPizzaById(2L));
    }


    // создание новой пиццы
    @Test
    void createPizza_shouldCreateAndReturnPizzaDTO() {
        PizzaDTO pizzaDTO = new PizzaDTO();
        pizzaDTO.setName("Pepperoni");
        pizzaDTO.setPrice(BigDecimal.valueOf(10.50));

        when(pizzaRepository.existsByName("Pepperoni")).thenReturn(false);
        when(pizzaRepository.save(any(Pizza.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PizzaDTO result = pizzaService.createPizza(pizzaDTO);

        assertEquals("Pepperoni", result.getName());
    }

    //  защита от создания пицц с дублирующимися именами
    @Test
    void createPizza_shouldThrowIfNameExists() {
        PizzaDTO pizzaDTO = new PizzaDTO();
        pizzaDTO.setName("Four Cheese");

        when(pizzaRepository.existsByName("Four Cheese")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> pizzaService.createPizza(pizzaDTO));
    }


    // статус доступности пиццы
    @Test
    void togglePizzaAvailability_shouldToggleAvailability() {
        Pizza pizza = new Pizza();
        pizza.setId(1L);
        pizza.setAvailable(true);

        when(pizzaRepository.findById(1L)).thenReturn(Optional.of(pizza));

        pizzaService.togglePizzaAvailability(1L);

        assertFalse(pizza.isAvailable());
        verify(pizzaRepository).save(pizza);
    }
}
