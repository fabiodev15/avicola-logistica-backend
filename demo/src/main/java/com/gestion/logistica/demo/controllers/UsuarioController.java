package com.gestion.logistica.demo.controllers;

import com.gestion.logistica.demo.entities.Usuario;
import com.gestion.logistica.demo.services.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping("/{username}")
    public Usuario buscarPorUsername(@PathVariable String username) {
        return usuarioService.buscarPorUsername(username);
    }
}
