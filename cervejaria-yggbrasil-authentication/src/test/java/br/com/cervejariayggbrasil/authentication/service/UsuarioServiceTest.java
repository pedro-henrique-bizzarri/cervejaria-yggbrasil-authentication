package br.com.cervejariayggbrasil.authentication.service;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cervejariayggbrasil.authentication.domain.entity.PermissaoEnum;
import br.com.cervejariayggbrasil.authentication.domain.entity.Pessoa;
import br.com.cervejariayggbrasil.authentication.domain.entity.Usuario;
import br.com.cervejariayggbrasil.authentication.exception.IllegalUserAgeException;
import br.com.cervejariayggbrasil.authentication.exception.UserExistsException;
import br.com.cervejariayggbrasil.authentication.exception.UserNotFoundException;
import br.com.cervejariayggbrasil.authentication.repository.UsuarioRepository;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private JwtService jwtService;

    @Autowired
    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void registrar_WhenUserFilledCorrectly_Success(){
        Usuario usuario = new Usuario(
            "marcelo.fogaca", 
            "123", 
            "Marcelo Vitor Lucca", 
            "Fogaça", 
            "672.456.870-64", 
            21, 
            "Rua Orestes Pedrassani, 375");

        when(usuarioRepository.findByLogin(usuario.getLogin())).thenReturn(Optional.ofNullable(null));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        usuarioService.registrar(usuario);

        verify(usuarioRepository).findByLogin(usuario.getLogin());
        verify(usuarioRepository).save(usuario);
        assertThat(Optional.of(usuario.getId()).isPresent()).isTrue();
        assertThat(Optional.of(usuario.getPermissao()).isPresent()).isTrue();
    }

    @Test
    public void registrar_WhenUserExisting_Error(){
        Usuario usuario = getUsuarioDataBase();
        
        when(usuarioRepository.findByLogin(usuario.getLogin())).thenReturn(Optional.of(usuario));
        
        assertThrows(UserExistsException.class, () -> {
            usuarioService.registrar(usuario);
        });

        verify(usuarioRepository).findByLogin(usuario.getLogin());
    }

    @Test
    public void registrar_WhenUserLessaThanEighteenYears_Error(){
        Usuario usuario = new Usuario(
            "marcelo.fogaca", 
            "123", 
            "Marcelo Vitor Lucca", 
            "Fogaça", 
            "672.456.870-64", 
            15, 
            "Rua Orestes Pedrassani, 375");
        
        when(usuarioRepository.findByLogin(usuario.getLogin())).thenReturn(Optional.ofNullable(null));
        
        assertThrows(IllegalUserAgeException.class, () -> {
            usuarioService.registrar(usuario);
        });

        verify(usuarioRepository).findByLogin(usuario.getLogin());
    }

    @Test
    public void pesquisar_WhenUserWasFound_Success(){
        Usuario usuario = getUsuarioDataBase();
        String login = "marcelo.fogaca";

        when(jwtService.validateToken(any())).thenReturn(login);
        when(usuarioRepository.findByLogin(login)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.pesquisar(any());

        verify(jwtService).validateToken(any());
        verify(usuarioRepository).findByLogin(login);
        assertThat(Optional.of(resultado).isPresent()).isTrue();
    }

    @Test
    public void pesquisar_WhenUserNotFound_Error(){
        String login = "marcelo.fogaca";

        when(usuarioRepository.findByLogin(login)).thenReturn(Optional.ofNullable(null));

        assertThrows(UserNotFoundException.class, () -> {
            usuarioService.pesquisar(any());
        });

        verify(usuarioRepository).findByLogin(any());
    }

    @Test
    public void listar_WhenDataBaseHasUsers_Success(){
        when(usuarioRepository.findAll()).thenReturn(List.of(getUsuarioDataBase()));

        List<Usuario> usuarios = usuarioService.listar();

        verify(usuarioRepository).findAll();
        assertThat(usuarios.isEmpty()).isFalse();
    }

    @Test
    public void listar_WhenDataBaseHasNotUsers_Error(){
        when(usuarioRepository.findAll()).thenReturn(List.of());

        assertThrows(UserNotFoundException.class, () -> {
            usuarioService.listar();
        });

        verify(usuarioRepository).findAll();
    }

    @Test
    public void liberarAdmin_WhenUserExists_Success(){
        Usuario usuario = getUsuarioDataBase();
        String login = "marcelo.fogaca";

        when(usuarioRepository.findByLogin(login)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.liberarAdmin(login);

        verify(usuarioRepository).findByLogin(login);
        verify(usuarioRepository).save(usuario);
        assertThat(Optional.of(resultado).isPresent()).isTrue();
        assertThat(resultado.getPermissao().equals(PermissaoEnum.ADMIN));
    }

    @Test
    public void liberarAdmin_WhenUserNotFound_Error(){
        String login = "marcelo.fogaca";

        when(usuarioRepository.findByLogin(login)).thenReturn(Optional.ofNullable(null));

        assertThrows(UserNotFoundException.class, () -> {
            usuarioService.pesquisar(any());
        });

        verify(usuarioRepository).findByLogin(any());
    }

    private Usuario getUsuarioDataBase(){
        return new Usuario(
            1, 
            "marcelo.fogaca", 
            "123", 
            PermissaoEnum.USER, 
            new Pessoa(
                1, 
                "Marcelo Vitor Lucca", 
                "Fogaça", 
                21, 
                "672.456.870-64", 
                "Rua Orestes Pedrassani, 375")) ;
    }

}
