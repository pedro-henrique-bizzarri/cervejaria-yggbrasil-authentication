package br.com.cervejariayggbrasil.authentication.domain.dto;

import br.com.cervejariayggbrasil.authentication.domain.entity.PermissaoEnum;
import br.com.cervejariayggbrasil.authentication.domain.entity.Usuario;

public record UsuarioRequest(String login, String senha, PermissaoEnum permissao, String nome, String sobrenome, String cpf, int idade, String endereco) {

    public Usuario toUsuario(){
        return new Usuario(login, senha, permissao, nome, sobrenome, cpf, idade, endereco);
    }
}
