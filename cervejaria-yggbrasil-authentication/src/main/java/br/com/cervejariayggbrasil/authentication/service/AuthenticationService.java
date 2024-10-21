package br.com.cervejariayggbrasil.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import br.com.cervejariayggbrasil.authentication.config.service.JwtService;
import br.com.cervejariayggbrasil.authentication.repository.UsuarioRepository;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private JwtService jwtService;
}
