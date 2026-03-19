package com.manually.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manually.backend.model.Usuario;
import com.manually.backend.model.Video;
import com.manually.backend.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. Ruta para REGISTRAR un nuevo usuario
    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        // Comprobamos si el correo ya existe en la base de datos
        Optional<Usuario> existente = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (existente.isPresent()) {
            return ResponseEntity.badRequest().body("{\"mensaje\": \"El correo ya está registrado\"}");
        }
        
        // Si no existe, lo guardamos
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    // 2. Ruta para INICIAR SESIÓN
    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody Usuario credenciales) {
        // Buscamos al usuario por su correo
        Optional<Usuario> usuarioDb = usuarioRepository.findByCorreo(credenciales.getCorreo());
        
        // Comprobamos si existe y si la contraseña coincide
        if (usuarioDb.isPresent() && usuarioDb.get().getContrasena().equals(credenciales.getContrasena())) {
            return ResponseEntity.ok(usuarioDb.get()); // Login exitoso
        } else {
            return ResponseEntity.status(401).body("{\"mensaje\": \"Correo o contraseña incorrectos\"}");
        }
    }

    // --- RUTAS DE ADMINISTRADOR ---
    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Cambiar el rol de un usuario (Hacer ADMIN o USER)
    @PutMapping("/{id}/rol")
    public org.springframework.http.ResponseEntity<?> cambiarRol(@PathVariable Long id, @RequestBody java.util.Map<String, String> body) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setRol(body.get("rol"));
            usuarioRepository.save(usuario);
            return org.springframework.http.ResponseEntity.ok().build();
        }).orElse(org.springframework.http.ResponseEntity.notFound().build());
    }

    @Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByProductoId(Long productoId);
    List<Video> findByUsuarioId(Long usuarioId); 
}
}
