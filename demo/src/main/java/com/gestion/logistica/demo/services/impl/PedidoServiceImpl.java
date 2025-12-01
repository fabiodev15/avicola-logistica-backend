package com.gestion.logistica.demo.services.impl;

import com.gestion.logistica.demo.entities.Pedido;
import com.gestion.logistica.demo.entities.PedidoDetalle;
import com.gestion.logistica.demo.entities.Producto;
import com.gestion.logistica.demo.repositories.PedidoRepository;
import com.gestion.logistica.demo.repositories.ProductoRepository;
import com.gestion.logistica.demo.services.PedidoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public Pedido crearPedido(Pedido pedido) {

        // Actualizar stock de cada detalle
        for (PedidoDetalle detalle : pedido.getDetalles()) {
            Producto producto = productoRepository.findById(detalle.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (producto.getStock() < detalle.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoRepository.save(producto);

            detalle.setSubtotal(detalle.getCantidad() * producto.getPrecio());
            detalle.setPedido(pedido);
        }

        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido obtenerPedido(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido cambiarEstado(Long id, String estado) {
        Pedido pedido = obtenerPedido(id);
        pedido.setEstado(estado);
        return pedidoRepository.save(pedido);
    }
}
