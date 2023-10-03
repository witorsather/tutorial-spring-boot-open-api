// Importacoes de bibliotecas
package com.example.tutorialspringboot;

import com.example.tutorialspringboot.model.Tutorial;
import com.example.tutorialspringboot.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

// Implementamos CommandLineRunner Ela implementa a interface CommandLineRunner, que e uma forma do Spring Boot permitir que voce execute algum codigo logo que a aplicacao e iniciada.
// e adicionamos a dependencia TutorialRepository para podermos executar metodos de consulta JPA direto nos controllers
// Anotacao que diz para o Spring Boot que esta e uma classe de inicializacao
@SpringBootApplication
public class TutorialSpringBootApplication implements CommandLineRunner {
	// 'main' e o ponto de entrada (porta da festa) de uma aplicacao Java ele inicia o contexto spring
	// Contexto (ambiente) spring (organizacao da festa) ele prepara beans, servicos, controllers (bedidas, comidas, som)
	// Controllers sao os garcons que interagem com os usuarios (clientes)
	// 'main' > 'contexto ou ambiente spring' > 'controllers'

	@Autowired
	TutorialRepository tutorialRepository;

	// inicia o spring, diz qual a classe principal para comecar a configuracao, inicia a infraestrutura, inicia o servidor embutido, carrega os beans, aplica as configuracoes passadas pela linha de comandos do args
	public static void main(String[] args) { // main e a porta da festa(programa java), todo programa inicia aqui, args permite passar comandos adicionais pela linha de comando, pedindo para ele fazer coisas diferentes conforme os comandos passados no terminal
		SpringApplication.run(TutorialSpringBootApplication.class, args); // run prepara o contexto/servico spring, diz para o spring arrumar a festa para comecarmos
	}

	@Override
	public void run(String... args) throws Exception {   // Este metodo e chamado automaticamente quando a aplicacao e iniciada, por causa da interface CommandLineRunner. Voce pode colocar codigos aqui para serem executados no inicio.
		tutorialRepository.findAllPublishedOrderByCreatedDesc();
	}

	private void show(List<Tutorial> tutorials) { // Este é um metodo auxiliar simples que imprime a lista de tutoriais.
		tutorials.forEach(System.out::println);
	}

}

// Suponha que voce tem um programa que le arquivos e voce quer que o usuario possa escolher qual arquivo ler. Voce poderia iniciar o programa no terminal assim:
// java -jar MeuPrograma.jar nomeDoArquivo.txt

//	Nesse caso, "nomeDoArquivo.txt" seria um argumento que voce passa para o programa. Dentro do seu codigo, voce poderia acessa-lo assim:
//	public static void main(String[] args) {
//		String nomeDoArquivo = args[0];  // pega o primeiro argumento
//		// agora você pode usar 'nomeDoArquivo' para abrir e ler o arquivo
//	}
