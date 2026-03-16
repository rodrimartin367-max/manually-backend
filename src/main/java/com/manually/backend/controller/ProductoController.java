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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manually.backend.model.Producto;
import com.manually.backend.repository.ProductoRepository;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") 
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // Ruta para obtener TODOS los productos
    @GetMapping
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    // Ruta para obtener solo los productos DESTACADOS
    @GetMapping("/destacados")
    public List<Producto> obtenerDestacados() {
        return productoRepository.findByDestacadoTrue();
    }

    // Ruta para BUSCAR productos por texto (¡Esta es la que faltaba!)
    @GetMapping("/buscar")
    public List<Producto> buscarProductos(@RequestParam String query) {
        return productoRepository.findByNombreContainingIgnoreCase(query);
    }

    // Guardar un nuevo producto
    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    // Borrar un producto (Solo admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
