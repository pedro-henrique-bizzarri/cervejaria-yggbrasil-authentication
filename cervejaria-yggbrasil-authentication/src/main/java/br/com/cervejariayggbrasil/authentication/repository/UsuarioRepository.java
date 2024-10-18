package br.com.cervejariayggbrasil.authentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cervejariayggbrasil.authentication.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

    Optional<Usuario> findByLogin(String login);
}
