package com.example.tutorialspringboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice // Um Controller Advice e uma especie de interceptador que envolve a logica de nossos controladores e nos permite aplicar alguma logica comum a eles.
public class ControllerExceptionHandler {

    // O que faz? Metodo que e chamado em algum metodo (getAllTutorials) de um @Controller (TutorialController)
    // Parametro necessario - Excessao (tipo de erro ResourceNotFoundException) e WebRequest(anotacoes/informacoes sobre a requesicao HTTP que estava acontecendo quando o erro aconteceu,
    // pode ter varios detalhes, como qual metodo HTTP get, put, delete, qual url estava sendo acessada, quais parametros foram enviados, quais cabecalhos,
    // pode usa o WebRequest pra personalizar a mensagem de erro
    // Retorno - objeto resposta HTTP(ResponseEntity) com a mensagem de erro <ErrorMessage>
    @ExceptionHandler(ResourceNotFoundException.class) // 1 @ControllerAdvice achou uma excessao lancada na controller, 2 olhou o tipo dela e 3 mandou pro metodo correspondente dele que trata essa excessao (mostrando uma mensagem de erro especifica)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(), // mensagem que o TutorialController colocou quando criou uma instancia de ResourceNotFoundException 'Não foi encontrado um Tutorial com o id = ?'
                request.getDescription(false)); // detalhes sobre a requisicao, metodo put, url tentou acessar, parametros enviados, cabecalhos
        
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND); // 1 operador diamante <>, o compilador Java javac ve que o tipo de retorno do metodo e ResponseEntity<ErrorMessage> 2 Quando voce usa o operador diamante <> no new ResponseEntity<>(...), o compilador entende que voce quer criar um novo objeto ResponseEntity<ErrorMessage>.
    }

    @ExceptionHandler(Exception.class) // Exception excessao generica do java mae das excessoes
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {  // WebRequest esta aqui para nao termos que lidar diretamente com a requisicao http, ele encobre a requisicao e nos fornece metodos faceis pra acessar metadados da requisicao, ja que nao precisamos da requisicao pura, pois nao estamos tratando ela, so pegando seus metados e jogando numa mensagem de erro para o cliente
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

// Explicacao Ludica
// @ControllerAdvice - Super-Heroi que voa sobre os predios procurando um crime
// @RestController - Predio
// Exception - crime
// @ExceptionHandler - super poderes do Super-Heroi, para cada crime (exception) ele usa um poder diferente (apagar fogo, alagamento)

/*
// outra versao usando @ResponseBody
@ControllerAdvice
@ResponseBody
// Se usar essa anotacao ele ira automaticamente criar o ResponseEntity (a resposta HTTP) ficando desncessario dar new ResponseEntity e deixando o codigo mais simples
// mas ainda preciso usar o @ResponseStatus pra definir o codigo de status http para essa excessao
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(...);
        return message;
    }
}


*/