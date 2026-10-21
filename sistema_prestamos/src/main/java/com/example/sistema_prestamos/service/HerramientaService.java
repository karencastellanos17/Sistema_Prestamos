package com.example.sistema_prestamos.service;


import com.example.sistema_prestamos.model.Herramienta;
import java.util.List;
import java.util.Optional;

public interface HerramientaService {
    Herramienta guardarHerramienta(Herramienta herramienta);
    List<Herramienta> listarHerramientas();
    Optional<Herramienta> obtenerHerramientaPorId(Long id);
}