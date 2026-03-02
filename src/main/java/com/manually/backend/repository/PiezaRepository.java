package com.manually.backend.repository;

import com.manually.backend.model.Pieza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PiezaRepository extends JpaRepository<Pieza, Long> {
    List<Pieza> findByProductoId(Long productoId);
}
