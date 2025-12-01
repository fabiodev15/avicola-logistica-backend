package com.gestion.logistica.demo.services;

import com.gestion.logistica.demo.entities.Producto;
import java.util.List;

public interface ProductoService {
    Producto crearProducto(Producto producto);
    Producto obtenerProducto(Long id);
    List<Producto> listarProductos();
    Producto actualizarProducto(Long id, Producto producto);
    void eliminarProducto(Long id);
}
