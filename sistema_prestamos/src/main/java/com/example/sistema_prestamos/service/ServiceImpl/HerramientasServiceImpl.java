package com.example.sistema_prestamos.service.ServiceImpl;

import com.example.sistema_prestamos.model.Herramienta;
import com.example.sistema_prestamos.repository.HerramientasRepository;
import com.example.sistema_prestamos.service.HerramientaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HerramientasServiceImpl implements HerramientaService {

    private final HerramientasRepository herramientasRepository;

    public HerramientasServiceImpl(HerramientasRepository herramientaRepository) {
        this.herramientasRepository = herramientaRepository;
    }

    @Override
    @Transactional
    public Herramienta guardarHerramienta(Herramienta herramienta) {
        if (herramienta.getCantidad() < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        return herramientasRepository.save(herramienta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Herramienta> listarHerramientas() {
        return herramientasRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Herramienta> obtenerHerramientaPorId(Long id) {
        return herramientasRepository.findById(id);
    }
}