package br.com.cervejariayggbrasil.authentication.domain.dto;

public record CadastroDTO(String login, String senha, String nome, String sobrenome, String cpf, int idade, String endereco) {

}
