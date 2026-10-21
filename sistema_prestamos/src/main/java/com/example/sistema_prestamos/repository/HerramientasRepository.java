package com.example.sistema_prestamos.repository;


import com.example.sistema_prestamos.model.Herramienta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HerramientasRepository extends JpaRepository<Herramienta, Long> {
}