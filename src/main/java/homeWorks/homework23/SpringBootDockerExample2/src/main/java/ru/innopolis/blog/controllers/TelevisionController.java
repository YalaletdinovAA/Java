package ru.innopolis.blog.controllers;

import ru.innopolis.blog.models.Television;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.blog.services.impl.TelevisionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/televisions")
@Tag(name = "Телевизоры", description = "API для управления телевизорами")
public class TelevisionController {
    private final TelevisionService televisionService;

    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @GetMapping
    @Operation(summary = "Получить все телевизоры", description = "Возвращает список всех телевизоров")
    @ApiResponse(responseCode = "200", description = "Успешное получение списка телевизоров",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Television.class)))
    public ResponseEntity<List<Television>> getAllTelevisions() {
        List<Television> televisions = televisionService.getAllTelevisions();
        return new ResponseEntity<>(televisions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить телевизор по ID", description = "Возвращает телевизор по указанному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Телевизор найден",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Television.class))),
            @ApiResponse(responseCode = "404", description = "Телевизор не найден")
    })
    public ResponseEntity<Television> getTelevisionById(
            @Parameter(description = "ID телевизора", required = true, example = "1")
            @PathVariable Long id) {
        Optional<Television> television = televisionService.getTelevisionById(id);
        return television.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(summary = "Создать новый телевизор", description = "Создает новый телевизор с указанными параметрами")
    @ApiResponse(responseCode = "201", description = "Телевизор успешно создан",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Television.class)))
    public ResponseEntity<Television> createTelevision(
            @Parameter(description = "Данные телевизора", required = true,
                    content = @Content(schema = @Schema(implementation = Television.class)))
            @RequestBody Television television) {
        Television savedTelevision = televisionService.saveTelevision(television);
        return new ResponseEntity<>(savedTelevision, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить телевизор", description = "Обновляет данные телевизора по указанному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Телевизор успешно обновлен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Television.class))),
            @ApiResponse(responseCode = "404", description = "Телевизор не найден")
    })
    public ResponseEntity<Television> updateTelevision(
            @Parameter(description = "ID телевизора", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Обновленные данные телевизора", required = true,
                    content = @Content(schema = @Schema(implementation = Television.class)))
            @RequestBody Television television) {
        Television updatedTelevision = televisionService.updateTelevision(id, television);
        return new ResponseEntity<>(updatedTelevision, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить телевизор", description = "Удаляет телевизор по указанному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Телевизор успешно удален"),
            @ApiResponse(responseCode = "404", description = "Телевизор не найден")
    })
    public ResponseEntity<Void> deleteTelevision(
            @Parameter(description = "ID телевизора", required = true, example = "1")
            @PathVariable Long id) {
        televisionService.deleteTelevision(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}