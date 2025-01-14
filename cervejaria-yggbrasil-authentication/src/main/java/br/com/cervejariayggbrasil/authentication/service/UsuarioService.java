package br.com.cervejariayggbrasil.authentication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cervejariayggbrasil.authentication.domain.entity.PermissaoEnum;
import br.com.cervejariayggbrasil.authentication.domain.entity.Usuario;
import br.com.cervejariayggbrasil.authentication.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrar(Usuario usuario) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        usuario.setPermissao(PermissaoEnum.USER);

        usuarioRepository.save(usuario);
        
        return usuario;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public boolean liberarAdmin(String login) {
        Usuario usuario = (Usuario) usuarioRepository.findByLogin(login);
        if (usuario == null) 
            return false;

        usuario.setPermissao(PermissaoEnum.ADMIN);
        usuarioRepository.save(usuario);
        return true;
    }
}
