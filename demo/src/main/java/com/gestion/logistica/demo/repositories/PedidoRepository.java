package com.gestion.logistica.demo.repositories;

import com.gestion.logistica.demo.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
