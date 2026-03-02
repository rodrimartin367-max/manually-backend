package com.manually.backend.controller;

import com.manually.backend.model.Comentario;
import com.manually.backend.model.Video;
import com.manually.backend.model.Pieza;
import com.manually.backend.model.Producto;
import com.manually.backend.repository.ComentarioRepository;
import com.manually.backend.repository.VideoRepository;
import com.manually.backend.repository.PiezaRepository;
import com.manually.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contenido")
@CrossOrigin(origins = "*")
public class ContenidoController {

    @Autowired private ComentarioRepository comentarioRepository;
    @Autowired private VideoRepository videoRepository;
    @Autowired private PiezaRepository piezaRepository;
    @Autowired private ProductoRepository productoRepository; // Necesario para buscar el producto

    // --- RUTAS DE LECTURA (Las que ya tenías) ---
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

    // --- NUEVAS RUTAS DE ESCRITURA (Para guardar datos) ---
    
    @PostMapping("/comentarios/{productoId}")
    public Comentario crearComentario(@PathVariable Long productoId, @RequestBody Comentario nuevoComentario) {
        Producto producto = productoRepository.findById(productoId).orElseThrow();
        nuevoComentario.setProducto(producto);
        // Como aún no tenemos el login real enlazado, el usuario será null (Anónimo)
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

    @DeleteMapping("/comentarios/{id}")
    public ResponseEntity<?> borrarComentario(@PathVariable Long id) {
        comentarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}