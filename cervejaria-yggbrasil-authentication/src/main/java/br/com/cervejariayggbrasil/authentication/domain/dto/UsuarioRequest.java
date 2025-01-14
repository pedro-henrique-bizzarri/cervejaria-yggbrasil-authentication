package br.com.cervejariayggbrasil.authentication.domain.dto;

import br.com.cervejariayggbrasil.authentication.domain.entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequest(
    
    @NotNull(message = "O login não pode ser nulo")
    @NotBlank(message = "O login não pode ser vazio")
    String login, 
    
    @NotNull(message = "A senha não pode ser nula")
    @NotBlank(message = "A senha não pode ser vazia")
    String senha, 
    
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser vazio")
    String nome, 
    
    @NotNull(message = "O sobrenome não pode ser nulo")
    @NotBlank(message = "O sobrenome não pode ser vazio")
    String sobrenome, 
    
    @NotNull(message = "O cpf não pode ser nulo")
    @NotBlank(message = "O cpf não pode ser vazio")
    String cpf, 
    
    @NotNull(message = "A idade não pode ser nula")
    int idade, 
    
    String endereco) {

    public Usuario toUsuario(){
        return new Usuario(login, senha, nome, sobrenome, cpf, idade, endereco);
    }
}
