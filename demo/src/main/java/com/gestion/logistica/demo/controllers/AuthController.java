package com.gestion.logistica.demo.controllers;

import com.gestion.logistica.demo.entities.Usuario;
import com.gestion.logistica.demo.security.jwt.JwtUtil;
import com.gestion.logistica.demo.services.UsuarioService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authManager, UsuarioService usuarioService,
                          JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioService.crearUsuario(usuario);
    }

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword())
        );
        return jwtUtil.generarToken(usuario.getUsername());
    }
}
