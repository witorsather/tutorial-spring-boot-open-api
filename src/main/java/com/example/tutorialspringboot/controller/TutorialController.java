// controlador fornece APIs(end points) ponto de extremidade para reagir as ações da view(do cliente) atráves de recebimento de requisições http no formato arquitetural REST (arquitetura de transferencia de estado representacional)

package com.example.tutorialspringboot.controller;

import com.example.tutorialspringboot.model.Tutorial;
import com.example.tutorialspringboot.repository.TutorialRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name="Tutorial Controller", description = "Tutorial Gerenciador de APIs") // Tag swagger que altera o nome do grupo de endpoints tutorial-controller padrao para um nome personalizado
@CrossOrigin(origins = "http://localhost:8081")
// estou no mesmo domínio da solicitação cliente e do servidor, não preciso configurar o cors
@RestController // essa anotação diz para o spring gerenciar essa classe como um controller
@RequestMapping("/api") // esse controler irá reagir a ações do cliente no endpoint "/api"
public class TutorialController { // camada que controla o fluxo ou o meio de campo entre a camada de view(tela, cliente) e a camada de service(regra de negócios, obtém dados do banco através da repository)
    // A anotação @Autowired é usada para injetar (ou seja, fornecer automaticamente) uma instância de TutorialRepository para a classe TutorialController. Isso é feito pelo Spring, que gerencia a criação e o ciclo de vida das instâncias de objetos.
    // Em vez de a classe TutorialController criar manualmente uma instância de TutorialRepository usando new TutorialRepository(), o Spring faz isso e injeta a instância pronta para uso no TutorialController. Isso segue o princípio de inversão de controle (IoC) e ajuda a gerenciar as dependências de forma mais eficaz.
    // A IoC implica que o controle sobre a criação e gerenciamento das instâncias de TutorialRepository seja passado para um container ou framework, como o Spring. Isso significa que a classe de alto nível não precisa se preocupar em criar ou gerenciar diretamente as instâncias de suas dependências de baixo nível, como TutorialRepository.
    // O controle é invertido do código da aplicação para o framework (Spring, neste caso) para gerenciar essas dependências.
    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping("/tutorials")
    // Outra opcao para trabalhar com os parametros do swagger
//    @Parameters({
//            @Parameter(name = "title", description = "Search Tutorials by title"),
//            @Parameter(name = "page", description = "Page number, starting from 0", required = true),
//            @Parameter(name = "size", description = "Number of items per page", required = true)
//    })
    @Operation(
            summary = "Buscar lista de Tutoriais",
            description = "Obtenha uma lista de Tutoriais por título ou sem título. A resposta é um uma lista de Tutoriais com id, título, descrição e status publicado.",
            tags = {"tutorial", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Tutorial.class), mediaType = "application/JSON") }),
            @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<List<Tutorial>> getAllTutorials( // @RequestParam(requered = false indico que o parâmetro não é obrigatório. Se fornecido, retornaremos uma resposta filtrada; caso contrário, retornaremos todos os tutoriais sem filtro.
                                                           @Parameter(description = "Busque Tutorials por título.") @RequestParam(required = false) String title,
                                                           @Parameter(description = "Número da página, começando por 0.", required = true) @RequestParam(defaultValue = "0") int page,
                                                           @Parameter(description = "Número de itens por página.", required = true) @RequestParam(defaultValue = "3") int size
    ) {
        try {
            List<Tutorial> tutorials = new ArrayList<>();

            if (title == null) {
                tutorialRepository.findAll().forEach(tutorials::add);
            } else
                tutorialRepository.findByTitleIgnoreCase(title).forEach(tutorials::add);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/published")
    @Operation(
            summary = "Buscar lista de Tutoriais",
            description = "Obtenha uma lista de Tutoriais por status de publicação = true. A resposta é um uma lista de Tutoriais com id, título, descrição e status publicado.",
            tags = {"tutorial", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Tutorial.class), mediaType = "application/JSON") }),
            @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<List<Tutorial>> findByPublished() {  // o cliente irá receber uma resposta http json com uma lista de tutorials ou apenas o status sem conteúdo.
        try {
            List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/tutorials/{id}")
    // Tag do Swagger que informa qual operacao esse endpoint faz na API
    @Operation(
            summary = "Buscar um Tutorial pelo Id", // O que faz: Da uma ideia geral do que o metodo faz. No caso, ele busca um tutorial usando seu Id (Identificador).
            description = "Obtenha um objeto Tutorial especificando seu id. A resposta é um objeto Tutorial com id, título, descrição e status publicado.", // O que faz: Explica com mais detalhes o que a funcao faz, o que voce precisa para usa-la (um Id) e o que voce vai receber de volta (um objeto Tutorial).
            tags = {"tutorial", "get"}) // O que faz: As "etiquetas" sao usadas para organizar os endpoints na documentacao. Todos os metodos relacionados a "tutoriais" e que usam o metodo "GET" podem ser agrupados sob essas etiquetas. Isso ajuda a encontrar mais facilmente o que voce está procurando na documentacao.
    @ApiResponses({ // Define varias respostas esperadas da api com seu 1 status e 2 body
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Tutorial.class), mediaType = "application/JSON") }), // Informa que pode esperar que se a API encontrar por meio do id o objeto procurado ela irá retornar o status 200 e um objeto Tutorial na estrutura json
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
        try {
            Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

            if (tutorialData.isPresent()) {
                return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/tutorials")
    @Operation(
            summary = "Criar um Tutorial",
            description = "Crie um objeto Tutorial especificando seu título e descrição",
            tags = {"tutorial", "post"})
    @ApiResponses({ 
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = Tutorial.class), mediaType = "application/JSON") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {  // spring irá desserializar o corpo JSON(requisição http post enviada pelo cliente) e criar um objeto java Tutorial
        try {
            Tutorial _tutorial = tutorialRepository
                    .save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tutorials/{id}")
    @Operation(
            summary = "Atualizar um Tutorial",
            description = "Atualize um objeto Tutorial especificando seu novo título, descrição e status de publicação",
            tags = {"tutorial", "put"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Tutorial.class), mediaType = "application/JSON") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        try {
            Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

            if (tutorialData.isPresent()) {
                Tutorial _tutorial = tutorialData.get();
                _tutorial.setTitle(tutorial.getTitle());
                _tutorial.setDescription(tutorial.getDescription());
                _tutorial.setPublished(tutorial.isPublished());
                return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Excluir todos os Tutoriais",
            description = "Excluir toda a lista de Tutoriais.",
            tags = {"tutorial", "delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/tutorials")
    public ResponseEntity<Tutorial> deleteAllTutorials() {
        try {
            tutorialRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Excluir um Tutorial pelo Id",
            description = "Exclua um objeto Tutorial especificando seu id.",
            tags = {"tutorial", "delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

            if (tutorialData.isPresent()) {
                tutorialRepository.deleteById(id);
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

//@CrossOrigin
// Política de Mesma Origem (SOP):
// Os navegadores aplicam a Política de Mesma Origem (Same-Origin Policy, SOP) para garantir a segurança dos usuários. Isso significa que, por padrão, quando o código JavaScript em uma página web, como no domínio http://localhost:4200 (Angular), tenta fazer uma solicitação para um domínio diferente, como http://localhost:8081 (servidor Spring Boot), o navegador bloqueia essa solicitação. Essa é uma medida de segurança importante para proteger os dados dos usuários.

// Compartilhamento de Recursos entre Origens Diferentes (CORS Cross-Origin Resource Sharing):
// Para permitir que o navegador faça exceções à Política de Mesma Origem, configuramos o Compartilhamento de Recursos entre Origens Diferentes (CORS) no servidor Spring Boot, que está em http://localhost:8081. Isso informa ao navegador que o domínio http://localhost:4200 não precisa aplicar a política SOP e pode fazer solicitações HTTP livremente para o servidor em http://localhost:8081. É como se o servidor dissesse: "Ei, navegador, o Angular em http://localhost:4200 é permitido aqui, pode confiar nele e deixar as solicitações passarem."