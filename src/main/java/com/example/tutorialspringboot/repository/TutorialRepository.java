package com.example.tutorialspringboot.repository;

import com.example.tutorialspringboot.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// A interface JpaRepository fornece suporte robusto para operacoes CRUD basicas e consultas personalizadas sem necessidade de codigo adicional.
// Esta interface tambem nos permite usar metodos ja implementados, como 'save' e 'findById', sem termos que criar essas funcionalidades.

// Metodos personalizados que não estão presentes em JpaRepository podem ser adicionados aqui.
// A implementacao desses metodos e gerenciada automaticamente pelo Spring Data JPA, uma camada de abstracao que se comunica com o Hibernate, que por sua vez implementa a JPA (Java Persistence API).
@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

    
    // Spring Data JPA usa consulta derivada de metodo:
    // Passos para criar a consulta derivada de metodo:
    // 1. Retorno do metodo: Com base no tipo de retorno que foi declarado (List<Tutorial>), ele sabe o tipo de resultado que se espera.
    // 2. Nome do metodo: Com base no nome do metodo que foi escolhido (findByPublished), ele sabe que quero usar o campo "published" para fazer a consulta.
    // 3. Parametro do metodo: Com base no parâmetro que foi declarado (boolean published), ele usa o valor fornecido para filtrar os resultados.
    List<Tutorial> findByPublished(boolean published); // Este metodo retorna todos os tutoriais que estao publicados. O campo 'published' deve ser verdadeiro.

    // Este metodo retorna todos os tutoriais cujo titulo contem a string dada, ignorando maiusculas e minusculas.
    // Consulta personalizada
    @Query("SELECT t FROM Tutorial t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Tutorial> findByTitleIgnoreCase(String title);
}
