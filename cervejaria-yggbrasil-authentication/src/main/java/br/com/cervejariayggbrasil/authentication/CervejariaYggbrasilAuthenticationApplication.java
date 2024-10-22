package br.com.cervejariayggbrasil.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cervejariayggbrasil.authentication.service.AuthenticationService;

@SpringBootApplication
public class CervejariaYggbrasilAuthenticationApplication implements CommandLineRunner{

	@Autowired
	private AuthenticationService authenticationService;
	
	public static void main(String[] args) {
		SpringApplication.run(CervejariaYggbrasilAuthenticationApplication.class, args);
	}

	@Override
    public void run(String... args) {
		authenticationService.criarUsuarioAdmin();
    }
}
