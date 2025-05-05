package com.pizzeria.service;

import com.pizzeria.dto.PizzaDTO;
import com.pizzeria.exception.ResourceNotFoundException;
import com.pizzeria.model.Pizza;
import com.pizzeria.repository.PizzaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final ModelMapper modelMapper;

    public List<PizzaDTO> getAllAvailablePizzas() {
        return pizzaRepository.findAllByAvailableTrue()
                .stream()
                .map(pizza -> modelMapper.map(pizza, PizzaDTO.class))
                .collect(Collectors.toList());
    }

    public PizzaDTO getPizzaById(Long id) {
        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pizza not found with id: " + id));
        return modelMapper.map(pizza, PizzaDTO.class);
    }

    public PizzaDTO createPizza(PizzaDTO pizzaDTO) {
        if (pizzaRepository.existsByName(pizzaDTO.getName())) {
            throw new IllegalArgumentException("Pizza with this name already exists");
        }

        Pizza pizza = modelMapper.map(pizzaDTO, Pizza.class);
        pizza.setAvailable(true);
        Pizza savedPizza = pizzaRepository.save(pizza);
        return modelMapper.map(savedPizza, PizzaDTO.class);
    }

    public PizzaDTO updatePizza(Long id, PizzaDTO pizzaDTO) {
        Pizza existingPizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pizza not found with id: " + id));

        if (pizzaDTO.getName() != null && !existingPizza.getName().equals(pizzaDTO.getName())) {
            if (pizzaRepository.existsByName(pizzaDTO.getName())) {
                throw new IllegalArgumentException("Pizza with this name already exists");
            }
            existingPizza.setName(pizzaDTO.getName());
        }

        modelMapper.map(pizzaDTO, existingPizza);
        Pizza updatedPizza = pizzaRepository.save(existingPizza);
        return modelMapper.map(updatedPizza, PizzaDTO.class);
    }

    public void togglePizzaAvailability(Long id) {
        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pizza not found with id: " + id));
        pizza.setAvailable(!pizza.isAvailable());
        pizzaRepository.save(pizza);
    }
}