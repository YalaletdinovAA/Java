package ru.innopolis.blog.controllers;

import ru.innopolis.blog.models.Television;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.blog.services.impl.TelevisionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/televisions")
public class TelevisionController {
    private final TelevisionService televisionService;

    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @GetMapping
    public ResponseEntity<List<Television>> getAllTelevisions() {
        List<Television> televisions = televisionService.getAllTelevisions();
        return new ResponseEntity<>(televisions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevisionById(@PathVariable Long id) {
        Optional<Television> television = televisionService.getTelevisionById(id);
        return television.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Television> createTelevision(@RequestBody Television television) {
        Television savedTelevision = televisionService.saveTelevision(television);
        return new ResponseEntity<>(savedTelevision, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable Long id, @RequestBody Television television) {
        Television updatedTelevision = televisionService.updateTelevision(id, television);
        return new ResponseEntity<>(updatedTelevision, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelevision(@PathVariable Long id) {
        televisionService.deleteTelevision(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}