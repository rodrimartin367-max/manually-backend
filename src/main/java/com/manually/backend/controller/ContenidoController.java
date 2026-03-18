package com.manually.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manually.backend.model.Comentario;
import com.manually.backend.model.Pieza;
import com.manually.backend.model.Producto;
import com.manually.backend.model.Video;
import com.manually.backend.repository.ComentarioRepository;
import com.manually.backend.repository.PiezaRepository;
import com.manually.backend.repository.ProductoRepository;
import com.manually.backend.repository.VideoRepository;

@RestController
@RequestMapping("/api/contenido")
@CrossOrigin(origins = "*")
public class ContenidoController {

    @Autowired private ComentarioRepository comentarioRepository;
    @Autowired private VideoRepository videoRepository;
    @Autowired private PiezaRepository piezaRepository;
    @Autowired private ProductoRepository productoRepository; 

    // --- RUTAS DE LECTURA ---
    @GetMapping("/comentarios/{productoId}")
    public List<Comentario> obtenerComentarios(@PathVariable Long productoId) {
        return comentarioRepository.findByProductoIdOrderByFechaPublicacionDesc(productoId);
    }

    @GetMapping("/videos/{productoId}")
    public List<Video> obtenerVideos(@PathVariable Long productoId) {
        return videoRepository.findByProductoIdOrderByFechaPublicacionDesc(productoId);
    }

    @GetMapping("/piezas/{productoId}")
    public List<Pieza> obtenerPiezas(@PathVariable Long productoId) {
        return piezaRepository.findByProductoId(productoId);
    }

    // --- RUTAS DE ESCRITURA (Para guardar datos) ---
    @PostMapping("/comentarios/{productoId}")
    public Comentario crearComentario(@PathVariable Long productoId, @RequestBody Comentario nuevoComentario) {
        Producto producto = productoRepository.findById(productoId).orElseThrow();
        nuevoComentario.setProducto(producto);
        return comentarioRepository.save(nuevoComentario);
    }

    @PostMapping("/videos/{productoId}")
    public Video crearVideo(@PathVariable Long productoId, @RequestBody Video nuevoVideo) {
        Producto producto = productoRepository.findById(productoId).orElseThrow();
        nuevoVideo.setProducto(producto);
        return videoRepository.save(nuevoVideo);
    }

    @PostMapping("/piezas/{productoId}")
    public Pieza crearPieza(@PathVariable Long productoId, @RequestBody Pieza nuevaPieza) {
        Producto producto = productoRepository.findById(productoId).orElseThrow();
        nuevaPieza.setProducto(producto);
        return piezaRepository.save(nuevaPieza);
    }

    // --- RUTAS DE ADMINISTRADOR ---
    @GetMapping("/comentarios/usuario/{usuarioId}")
    public List<Comentario> obtenerComentariosDeUsuario(@PathVariable Long usuarioId) {
        return comentarioRepository.findByUsuarioId(usuarioId);
    }

    // ¡ESTAS DOS RUTAS FALTABAN! Son las que mandan los datos al panel admin
    @GetMapping("/videos/usuario/{usuarioId}")
    public List<Video> obtenerVideosDeUsuario(@PathVariable Long usuarioId) {
        return videoRepository.findByUsuarioId(usuarioId);
    }

    @GetMapping("/piezas/usuario/{usuarioId}")
    public List<Pieza> obtenerPiezasDeUsuario(@PathVariable Long usuarioId) {
        return piezaRepository.findByUsuarioId(usuarioId);
    }

    // --- BORRADOS (Limpiados y mejorados) ---
    @DeleteMapping("/comentarios/{id}")
    public ResponseEntity<?> borrarComentario(@PathVariable Long id) {
        comentarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/videos/{id}")
    public ResponseEntity<?> borrarVideo(@PathVariable Long id) {
        videoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/piezas/{id}")
    public ResponseEntity<?> borrarPieza(@PathVariable Long id) {
        piezaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}