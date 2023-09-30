package com.example.tutorialspringboot.exception;

// Criando minha propria excessao que extends RuntimeException > Exception > Throwable
// Em java existem 2 tipos de excessoes 1 nao verificadas e 2 verificadas
// 1 nao verificadas
// 1.1 sao excessoes que estendem RuntimeException (excessao em tempo de execucao) como NullPointerException (excessao de posicao nula)
// 1.2 sao excessoes que nao usamos throws Exception, porque sao referentes a bugs no codigo, uma implementacao ruim
// 1.2 sao excessoes que fazem parte do java.lang pois sao em tempo de execucao da JVM
// 2 verificadas
// 2.1 sao excessoes que fogem do campo do codigo java, da JVM, como IOException (Excessao de Input Entrada Output Saida, acontece na leitura ou escrita de arquivo), SQL Exception (excessao do banco postgresql),
// ValidationException (Excessao de validacao) o usuario digitou valores invalidos
// 2.2 sao excessoes que usamos o throws Exception pois queremos informar o usuario ou dev do erro esperado fora do codigo java
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
