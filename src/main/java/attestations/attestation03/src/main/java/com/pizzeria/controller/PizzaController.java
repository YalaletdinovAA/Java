package com.pizzeria.controller;

import com.pizzeria.dto.PizzaDTO;
import com.pizzeria.service.PizzaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
@RequiredArgsConstructor
@Tag(name = "Pizzas", description = "Pizza management APIs")
public class PizzaController {
    private final PizzaService pizzaService;

    @GetMapping
    @Operation(summary = "Get all available pizzas")
    public ResponseEntity<List<PizzaDTO>> getAllAvailablePizzas() {
        return ResponseEntity.ok(pizzaService.getAllAvailablePizzas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get pizza by ID")
    public ResponseEntity<PizzaDTO> getPizzaById(@PathVariable Long id) {
        return ResponseEntity.ok(pizzaService.getPizzaById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new pizza")
    public ResponseEntity<PizzaDTO> createPizza(@RequestBody PizzaDTO pizzaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pizzaService.createPizza(pizzaDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update pizza information")
    public ResponseEntity<PizzaDTO> updatePizza(@PathVariable Long id, @RequestBody PizzaDTO pizzaDTO) {
        return ResponseEntity.ok(pizzaService.updatePizza(id, pizzaDTO));
    }

    @PatchMapping("/{id}/toggle-availability")
    @Operation(summary = "Toggle pizza availability")
    public ResponseEntity<Void> togglePizzaAvailability(@PathVariable Long id) {
        pizzaService.togglePizzaAvailability(id);
        return ResponseEntity.noContent().build();
    }
}