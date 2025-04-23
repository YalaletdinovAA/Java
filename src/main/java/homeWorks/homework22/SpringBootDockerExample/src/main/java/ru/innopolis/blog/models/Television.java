
package ru.innopolis.blog.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "televisions")
public class Television {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;          // nullable = false убрано
    private String model;          // nullable = false убрано
    private double price;          // nullable = false убрано
    private double diagonal;       // nullable = false убрано
    private String resolution;     // nullable = false убрано
    private boolean smartTv;       // nullable = false убрано (примитив не может быть null)

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}