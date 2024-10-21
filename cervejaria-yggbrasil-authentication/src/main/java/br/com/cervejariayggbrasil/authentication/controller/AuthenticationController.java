package br.com.cervejariayggbrasil.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cervejariayggbrasil.authentication.config.service.JwtService;
import br.com.cervejariayggbrasil.authentication.domain.dto.AuthenticationDTO;
import br.com.cervejariayggbrasil.authentication.domain.dto.LoginResponseDTO;
import br.com.cervejariayggbrasil.authentication.domain.entity.Usuario;
import br.com.cervejariayggbrasil.authentication.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication auth = null;
        try {
            auth = authenticationManager.authenticate(usernamePassword);
        } catch (Exception e) {
            throw e;
        }
        
        var token = jwtService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
