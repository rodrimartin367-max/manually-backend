package com.manually.backend.controller;

import com.manually.backend.model.Producto;
import com.manually.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
