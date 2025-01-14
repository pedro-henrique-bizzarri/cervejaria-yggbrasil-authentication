package br.com.cervejariayggbrasil.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CervejariaYggbrasilAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CervejariaYggbrasilAuthenticationApplication.class, args);
		System.out.println("\nAplicacao de autenticacao Cervejaria Yggbrasil iniciada !\nUse o usuario ADMIN para liberar o acesso de administrador a novos usuarios.\nUsuario: admin, Senha: 123");
	}

}
