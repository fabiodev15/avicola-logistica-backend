package com.gestion.logistica.demo.services.impl;

import com.gestion.logistica.demo.entities.Producto;
import com.gestion.logistica.demo.repositories.ProductoRepository;
import com.gestion.logistica.demo.services.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto obtenerProducto(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto actualizarProducto(Long id, Producto productData) {
        Producto producto = obtenerProducto(id);
        producto.setNombre(productData.getNombre());
        producto.setUnidad(productData.getUnidad());
        producto.setPrecio(productData.getPrecio());
        producto.setStock(productData.getStock());
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
