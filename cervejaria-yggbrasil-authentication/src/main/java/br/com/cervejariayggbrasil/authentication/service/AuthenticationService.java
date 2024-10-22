package br.com.cervejariayggbrasil.authentication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cervejariayggbrasil.authentication.domain.dto.AuthenticationDTO;
import br.com.cervejariayggbrasil.authentication.domain.dto.CadastroDTO;
import br.com.cervejariayggbrasil.authentication.domain.entity.Usuario;
import br.com.cervejariayggbrasil.authentication.domain.entity.PermissaoEnum;
import br.com.cervejariayggbrasil.authentication.domain.entity.Pessoa;
import br.com.cervejariayggbrasil.authentication.repository.PessoaRepository;
import br.com.cervejariayggbrasil.authentication.repository.UsuarioRepository;

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
        usuario.setSenha(new BCryptPasswordEncoder().encode(data.senha()));
        usuario.setPermissao(PermissaoEnum.USER);
        usuarioRepository.save(usuario);
        
        Pessoa pessoa = new Pessoa();
        pessoa.setLogin(data.login());
        pessoa.setNome(data.nome());
        pessoa.setSobrenome(data.sobrenome());
        pessoa.setIdade(data.idade());
        pessoa.setCpf(data.cpf());
        pessoa.setEndereco(data.endereco());
        pessoaRepository.save(pessoa);

        return pessoa;
    }

    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    public boolean liberarAdmin(String login) {
        Usuario usuario = (Usuario) usuarioRepository.findByLogin(login);
        if (usuario == null) 
            return false;

        usuario.setPermissao(PermissaoEnum.ADMIN);
        usuarioRepository.save(usuario);
        return true;
    }

    public void criarUsuarioAdmin(){
		Usuario usuario = new Usuario();
		usuario.setLogin("admin");
		usuario.setSenha(new BCryptPasswordEncoder().encode("123"));
		usuario.setPermissao(PermissaoEnum.ADMIN);
		usuarioRepository.save(usuario);

        System.out.println("");
		System.out.println("Incluido usuário ADMIN no sistema !");
        System.out.println("Login: admin");
        System.out.println("Senha: 123");
        System.out.println("");
    }
}
