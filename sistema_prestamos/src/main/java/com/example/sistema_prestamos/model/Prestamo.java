package com.example.sistema_prestamos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
@Data
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El responsable es obligatorio")
    private String responsable;

    @NotNull(message = "La fecha de préstamo es obligatoria")
    @Column(name = "fecha_prestamo")
    private LocalDate fechaPrestamo;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad a prestar debe ser al menos 1")
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "herramienta_id", nullable = false)
    private Herramienta herramienta;
}

