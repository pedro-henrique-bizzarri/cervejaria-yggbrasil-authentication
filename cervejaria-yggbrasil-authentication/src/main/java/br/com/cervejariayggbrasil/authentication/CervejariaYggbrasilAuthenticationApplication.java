package br.com.cervejariayggbrasil.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CervejariaYggbrasilAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CervejariaYggbrasilAuthenticationApplication.class, args);
		System.out.println("\nADMIN criado. Usuario: admin, Senha: 123");
	}

}
