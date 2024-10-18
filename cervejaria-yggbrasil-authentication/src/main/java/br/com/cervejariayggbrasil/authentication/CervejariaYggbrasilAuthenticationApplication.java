package br.com.cervejariayggbrasil.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		Usuario usuario = new Usuario();
		usuario.setLogin("admin");
		usuario.setSenha("$2a$10$GiseHkdvwOFr7A9KRWbeiOmg/PYPhWVjdm42puLfOzR/gIAQrsAGy");
		usuarioRepository.save(usuario);

		System.out.println("Incluido usuário 'admin' para autenticação do Login !");
    }
}
