package br.com.cervejariayggbrasil.authentication.domain.dto;

import br.com.cervejariayggbrasil.authentication.domain.entity.Pessoa;

public record CadastroResponseDTO(String msg, String login, String permissao, String nome, String cpf, int idade, String endereco) {

    public CadastroResponseDTO (Pessoa pessoa){
        this(
            "Usuário cadastrado com sucesso !", 
            pessoa.getUsuario().getLogin(), 
            pessoa.getUsuario().getPermissao().getPermissao(),
            pessoa.getNome(), 
            pessoa.getCpf(), 
            pessoa.getIdade(), 
            pessoa.getEndereco());
    }
}
