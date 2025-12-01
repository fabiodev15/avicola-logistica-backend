package com.gestion.logistica.demo.services;

import com.gestion.logistica.demo.entities.Usuario;

public interface UsuarioService {
    Usuario crearUsuario(Usuario usuario);
    Usuario buscarPorUsername(String username);
}
