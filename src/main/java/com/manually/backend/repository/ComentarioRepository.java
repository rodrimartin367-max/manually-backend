package com.manually.backend.repository;

import com.manually.backend.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByProductoIdOrderByFechaPublicacionDesc(Long productoId);
    // NUEVA LÍNEA:
    List<Comentario> findByUsuarioId(Long usuarioId);
}