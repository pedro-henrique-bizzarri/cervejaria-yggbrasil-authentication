package br.com.cervejariayggbrasil.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cervejariayggbrasil.authentication.domain.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
