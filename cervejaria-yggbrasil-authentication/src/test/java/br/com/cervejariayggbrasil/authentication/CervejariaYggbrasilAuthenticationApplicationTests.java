package br.com.cervejariayggbrasil.authentication;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CervejariaYggbrasilAuthenticationApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("Oi");
		assertTrue(true);
	}

}
