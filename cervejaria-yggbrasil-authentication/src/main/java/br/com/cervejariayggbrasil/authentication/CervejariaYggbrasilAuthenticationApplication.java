package br.com.cervejariayggbrasil.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.cervejariayggbrasil.authentication.entity.PermissaoEnum;
import br.com.cervejariayggbrasil.authentication.entity.Usuario;
import br.com.cervejariayggbrasil.authentication.repository.UsuarioRepository;

@SpringBootApplication
public class CervejariaYggbrasilAuthenticationApplication implements CommandLineRunner{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CervejariaYggbrasilAuthenticationApplication.class, args);
	}

	@Override
    public void run(String... args) {
		String encryptedPassword = new BCryptPasswordEncoder().encode("123");

		Usuario usuario = new Usuario();
		usuario.setLogin("admin");
		usuario.setSenha(encryptedPassword);
		usuario.setPermissao(PermissaoEnum.ADMIN);
		usuarioRepository.save(usuario);

		System.out.println("Incluido usuário 'admin' para autenticação do Login !");
    }
}
