package com.manually.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "piezas")
public class Pieza {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Double precio;

    @Column(name = "url_compra", nullable = false, length = 500)
    private String urlCompra;

    @ManyToOne @JoinColumn(name = "producto_id")
    private Producto producto;

    public Pieza() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public String getUrlCompra() { return urlCompra; }
    public void setUrlCompra(String urlCompra) { this.urlCompra = urlCompra; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
}
