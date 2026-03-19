package com.manually.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manually.backend.model.Pieza;

@Repository
public interface PiezaRepository extends JpaRepository<Pieza, Long> {
    List<Pieza> findByProductoId(Long productoId);// En el de Video
    List<Pieza> findByUsuarioId(Long usuarioId); // En el de Pieza
}

