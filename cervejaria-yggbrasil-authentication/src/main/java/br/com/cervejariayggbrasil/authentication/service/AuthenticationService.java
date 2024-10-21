package br.com.cervejariayggbrasil.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cervejariayggbrasil.authentication.config.service.JwtService;
import br.com.cervejariayggbrasil.authentication.domain.dto.AuthenticationDTO;
import br.com.cervejariayggbrasil.authentication.domain.dto.CadastroDTO;
import br.com.cervejariayggbrasil.authentication.domain.entity.Usuario;
import br.com.cervejariayggbrasil.authentication.domain.entity.PermissaoEnum;
import br.com.cervejariayggbrasil.authentication.domain.entity.Pessoa;
import br.com.cervejariayggbrasil.authentication.repository.PessoaRepository;
import br.com.cervejariayggbrasil.authentication.repository.UsuarioRepository;
import jakarta.validation.Valid;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private JwtService jwtService;

    public String authenticate(AuthenticationDTO data){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        return jwtService.generateToken((Usuario) auth.getPrincipal());
    }

    public Pessoa cadastrar(CadastroDTO data) {
        Usuario usuario = new Usuario();
        usuario.setLogin(data.login());
        usuario.setSenha( new BCryptPasswordEncoder().encode(data.senha()));
        usuario.setPermissao(PermissaoEnum.USER);
        usuarioRepository.save(usuario);
        
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(data.nome());
        pessoa.setSobrenome(data.sobrenome());
        pessoa.setIdade(data.idade());
        pessoa.setCpf(data.cpf());
        pessoa.setEndereco(data.endereco());
        pessoa.setUsuario(usuario);
        pessoaRepository.save(pessoa);

        return pessoa;
    }

}
