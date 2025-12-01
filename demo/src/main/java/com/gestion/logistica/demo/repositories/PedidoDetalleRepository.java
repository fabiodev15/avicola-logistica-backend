package com.gestion.logistica.demo.repositories;

import com.gestion.logistica.demo.entities.PedidoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Long> {
}
