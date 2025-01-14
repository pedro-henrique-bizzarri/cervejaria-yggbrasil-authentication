package br.com.cervejariayggbrasil.authentication.domain.dto;

import br.com.cervejariayggbrasil.authentication.domain.entity.Usuario;

public record AuthenticationRequest(String login, String password) {

    public Usuario toUser(){
        return new Usuario(login, password);
    }
}
