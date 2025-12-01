package com.gestion.logistica.demo.services;

import com.gestion.logistica.demo.entities.Pedido;
import java.util.List;

public interface PedidoService {
    Pedido crearPedido(Pedido pedido);
    Pedido obtenerPedido(Long id);
    List<Pedido> listarPedidos();
    Pedido cambiarEstado(Long id, String estado);
}
