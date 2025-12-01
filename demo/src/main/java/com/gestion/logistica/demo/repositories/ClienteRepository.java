package com.gestion.logistica.demo.repositories;

import com.gestion.logistica.demo.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
