package com.manually.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manually.backend.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByProductoIdOrderByFechaPublicacionDesc(Long productoId);
    List<Video> findByUsuarioId(Long usuarioId); // Solo videos aquí
}
