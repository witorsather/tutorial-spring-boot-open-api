package com.example.tutorialspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TutorialSpringBootApplication {
	// 'main' e o ponto de entrada (porta da festa) de uma aplicacao Java ele inicia o contexto spring
	// Contexto (ambiente) spring (organizacao da festa) ele prepara beans, servicos, controllers (bedidas, comidas, som)
	// Controllers sao os garcons que interagem com os usuarios (clientes)
	// 'main' > 'contexto ou ambiente spring' > 'controllers'
	public static void main(String[] args) {
		SpringApplication.run(TutorialSpringBootApplication.class, args);
	}

}
