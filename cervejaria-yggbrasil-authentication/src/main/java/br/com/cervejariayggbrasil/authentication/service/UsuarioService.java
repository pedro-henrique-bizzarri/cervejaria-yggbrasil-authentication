package br.com.cervejariayggbrasil.authentication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cervejariayggbrasil.authentication.domain.entity.PermissaoEnum;
import br.com.cervejariayggbrasil.authentication.domain.entity.Usuario;
import br.com.cervejariayggbrasil.authentication.exception.IllegalUserAgeException;
import br.com.cervejariayggbrasil.authentication.exception.UserExistsException;
import br.com.cervejariayggbrasil.authentication.exception.UserNotFoundException;
import br.com.cervejariayggbrasil.authentication.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    public Usuario registrar(Usuario usuario) {
        validaUsuario(usuario);

        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        usuario.setPermissao(PermissaoEnum.USER);
        usuario = usuarioRepository.save(usuario);
        
        return usuario;
    }

    public Usuario pesquisar(HttpServletRequest request){
        Optional<UserDetails> usuario = 
            usuarioRepository.findByLogin(
                jwtService.validateToken(
                    jwtService.recoverToken(request)));

        if (usuario.isEmpty()) {
            throw new UserNotFoundException();
        }

        return (Usuario) usuario.get();
    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        if(usuarios.isEmpty())
            throw new UserNotFoundException();

        return usuarios;
    }

    public Usuario liberarAdmin(String login) {
        Optional<Usuario> usuario = Optional.of((Usuario) usuarioRepository.findByLogin(login).get());

        if (usuario.isEmpty()) 
            throw new UserNotFoundException();

        usuario.get().setPermissao(PermissaoEnum.ADMIN);
        return usuarioRepository.save(usuario.get());
    }

    private void validaUsuario(Usuario usuario) {
        if(usuarioRepository.findByLogin(usuario.getLogin()).isPresent())
            throw new UserExistsException();

        if(usuario.getPessoa().getIdade() < 18){
            throw new IllegalUserAgeException();
        }
    }

}
