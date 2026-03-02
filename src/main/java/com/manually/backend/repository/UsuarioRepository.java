package com.manually.backend.repository;

import com.manually.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Con solo escribir esta línea, Spring Boot ya sabe buscar un usuario por su correo en la BD
    Optional<Usuario> findByCorreo(String correo);
}
