package com.example.sistema_prestamos.service.ServiceImpl;


import com.example.sistema_prestamos.dto.PrestamoRequest;
import com.example.sistema_prestamos.model.Herramienta;
import com.example.sistema_prestamos.model.Prestamo;
import com.example.sistema_prestamos.repository.HerramientasRepository;
import com.example.sistema_prestamos.repository.PrestamoRepository;
import com.example.sistema_prestamos.service.PrestamoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final HerramientasRepository herramientaRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository, HerramientasRepository herramientaRepository) {
        this.prestamoRepository = prestamoRepository;
        this.herramientaRepository = herramientaRepository;
    }

    @Override
    @Transactional
    public Prestamo registrarPrestamo(PrestamoRequest request) {

        // 1. Comprobar que la herramienta exista
        Herramienta herramienta = herramientaRepository.findById(request.getHerramientaId())
                .orElseThrow(() -> new RuntimeException("La herramienta con ID " + request.getHerramientaId() + " no existe."));

        // 2. Rechazar préstamos cuya cantidad sea cero, negativa o superior a las unidades disponibles
        if (request.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad a prestar debe ser mayor a cero.");
        }

        if (request.getCantidad() > herramienta.getCantidad()) {
            throw new RuntimeException("No hay suficientes unidades disponibles. Unidades actuales: " + herramienta.getCantidad());
        }

        // 3. Descontar las unidades prestadas
        herramienta.setCantidad(herramienta.getCantidad() - request.getCantidad());
        herramientaRepository.save(herramienta);

        // 4. Crear y guardar el préstamo
        Prestamo prestamo = new Prestamo();
        prestamo.setResponsable(request.getResponsable());
        prestamo.setFechaPrestamo(request.getFechaPrestamo());
        prestamo.setCantidad(request.getCantidad());
        prestamo.setHerramienta(herramienta);

        return prestamoRepository.save(prestamo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Prestamo> listarPrestamosPorHerramienta(Long herramientaId) {
        return prestamoRepository.findByHerramientaId(herramientaId);
    }
}