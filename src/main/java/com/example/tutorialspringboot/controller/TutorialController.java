// controlador fornece APIs(end points) ponto de extremidade para reagir as ações da view(do cliente) atráves de recebimento de requisições http no formato arquitetural REST (arquitetura de transferencia de estado representacional)

package com.example.tutorialspringboot.controller;

import com.example.tutorialspringboot.model.Tutorial;
import com.example.tutorialspringboot.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {  // requered = false indico que o parâmetro não é obrigatório. Se fornecido, retornaremos uma resposta filtrada; caso contrário, retornaremos todos os tutoriais sem filtro.
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

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getById(@PathVariable("id") long id) {
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

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) { // esse método poderia usar apenas 1 consulta no banco, apenas o deleteById, o que seria melhor pra performance, mas visando um feedback mais claro, achei melhor colocar 2 consultas permitindo retornar ao cliente um resposta de não encontrado quando não houver id no banco em vez de um erro NO_CONTENT que pode ser enganoso.
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

    @DeleteMapping("/tutorials")
    public ResponseEntity<Tutorial> deleteAllTutorials() {
        try {
            tutorialRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/published")
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
}

//@CrossOrigin
// Política de Mesma Origem (SOP):
// Os navegadores aplicam a Política de Mesma Origem (Same-Origin Policy, SOP) para garantir a segurança dos usuários. Isso significa que, por padrão, quando o código JavaScript em uma página web, como no domínio http://localhost:4200 (Angular), tenta fazer uma solicitação para um domínio diferente, como http://localhost:8081 (servidor Spring Boot), o navegador bloqueia essa solicitação. Essa é uma medida de segurança importante para proteger os dados dos usuários.

// Compartilhamento de Recursos entre Origens Diferentes (CORS Cross-Origin Resource Sharing):
// Para permitir que o navegador faça exceções à Política de Mesma Origem, configuramos o Compartilhamento de Recursos entre Origens Diferentes (CORS) no servidor Spring Boot, que está em http://localhost:8081. Isso informa ao navegador que o domínio http://localhost:4200 não precisa aplicar a política SOP e pode fazer solicitações HTTP livremente para o servidor em http://localhost:8081. É como se o servidor dissesse: "Ei, navegador, o Angular em http://localhost:4200 é permitido aqui, pode confiar nele e deixar as solicitações passarem."