package it.uniroma3.siw.siw_federation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SiwFederationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiwFederationApplication.class, args);
	}

}
