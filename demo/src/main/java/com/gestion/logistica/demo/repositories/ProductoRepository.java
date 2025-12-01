package com.gestion.logistica.demo.repositories;

import com.gestion.logistica.demo.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
