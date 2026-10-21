package com.example.sistema_prestamos.service;

import com.example.sistema_prestamos.dto.PrestamoRequest;
import com.example.sistema_prestamos.model.Prestamo;
import java.util.List;

public interface PrestamoService {
    Prestamo registrarPrestamo(PrestamoRequest request);
    List<Prestamo> listarPrestamos();
    List<Prestamo> listarPrestamosPorHerramienta(Long herramientaId);
}