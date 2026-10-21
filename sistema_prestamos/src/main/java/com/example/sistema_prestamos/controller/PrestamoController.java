package com.example.sistema_prestamos.controller;

import com.example.sistema_prestamos.dto.PrestamoRequest;
import com.example.sistema_prestamos.model.Prestamo;
import com.example.sistema_prestamos.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    // POST: Registrar préstamo
    @PostMapping
    public ResponseEntity<?> registrarPrestamo(@Valid @RequestBody PrestamoRequest request) {
        try {
            Prestamo nuevoPrestamo = prestamoService.registrarPrestamo(request);
            return new ResponseEntity<>(nuevoPrestamo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Captura las reglas de negocio (falta de stock, herramienta no existe, etc.)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // GET: Consultar todos los préstamos
    @GetMapping
    public ResponseEntity<List<Prestamo>> listarPrestamos() {
        List<Prestamo> prestamos = prestamoService.listarPrestamos();
        return ResponseEntity.ok(prestamos);
    }

    // GET: Consultar préstamos de una herramienta por su ID
    @GetMapping("/herramienta/{id}")
    public ResponseEntity<List<Prestamo>> listarPrestamosPorHerramienta(@PathVariable Long id) {
        List<Prestamo> prestamos = prestamoService.listarPrestamosPorHerramienta(id);
        return ResponseEntity.ok(prestamos);
    }
}
