package com.example.sistema_prestamos.controller;

import com.example.sistema_prestamos.model.Herramienta;
import com.example.sistema_prestamos.service.HerramientaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/herramientas")
public class HerramientaController {

    private final HerramientaService herramientaService;

    public HerramientaController(HerramientaService herramientaService) {
        this.herramientaService = herramientaService;
    }

    // POST: Registrar herramienta
    @PostMapping
    public ResponseEntity<Herramienta> registrarHerramienta(@Valid @RequestBody Herramienta herramienta) {
        Herramienta nuevaHerramienta = herramientaService.guardarHerramienta(herramienta);
        return new ResponseEntity<>(nuevaHerramienta, HttpStatus.CREATED);
    }

    // GET: Consultar herramientas
    @GetMapping
    public ResponseEntity<List<Herramienta>> listarHerramientas() {
        List<Herramienta> herramientas = herramientaService.listarHerramientas();
        return ResponseEntity.ok(herramientas);
    }
}