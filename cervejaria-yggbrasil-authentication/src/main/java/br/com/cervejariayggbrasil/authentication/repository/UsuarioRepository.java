package br.com.cervejariayggbrasil.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.cervejariayggbrasil.authentication.domain.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    UserDetails findByLogin(String login);
}
