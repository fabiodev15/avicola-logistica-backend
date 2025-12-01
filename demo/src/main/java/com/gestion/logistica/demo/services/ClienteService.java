package com.gestion.logistica.demo.services;

import com.gestion.logistica.demo.entities.Cliente;
import java.util.List;

public interface ClienteService {
    Cliente crearCliente(Cliente cliente);
    Cliente obtenerCliente(Long id);
    List<Cliente> listarClientes();
    Cliente actualizarCliente(Long id, Cliente cliente);
    void eliminarCliente(Long id);
}
