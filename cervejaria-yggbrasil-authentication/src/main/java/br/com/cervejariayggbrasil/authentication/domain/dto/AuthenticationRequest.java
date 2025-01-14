package br.com.cervejariayggbrasil.authentication.domain.dto;

import br.com.cervejariayggbrasil.authentication.domain.entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthenticationRequest(
    
    @NotNull(message = "O login não pode ser nulo")
    @NotBlank(message = "O login não pode ser vazio")
    String login, 
    
    @NotNull(message = "A senha não pode ser nula")
    @NotBlank(message = "A senha não pode ser vazia")
    String password) {

    public Usuario toUser(){
        return new Usuario(login, password);
    }
}
