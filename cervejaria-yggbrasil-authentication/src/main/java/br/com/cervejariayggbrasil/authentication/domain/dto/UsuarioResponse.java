package br.com.cervejariayggbrasil.authentication.domain.dto;

import br.com.cervejariayggbrasil.authentication.domain.entity.Usuario;

public record UsuarioResponse(String msg, String login, String nome, String cpf, int idade, String endereco) {

    public UsuarioResponse (Usuario usuario){
        this(
            "Usuário cadastrado com sucesso !",
            usuario.getLogin(), 
            usuario.getPessoa().getNome(), 
            usuario.getPessoa().getCpf(), 
            usuario.getPessoa().getIdade(), 
            usuario.getPessoa().getEndereco());
    }
}
