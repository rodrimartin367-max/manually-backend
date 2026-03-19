package com.manually.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manually.backend.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByDestacadoTrue();
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByCategoria(String categoria);
    List<Producto> findByUsuarioId(Long usuarioId);
}
