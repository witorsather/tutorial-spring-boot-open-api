package com.example.tutorialspringboot.repository;

import com.example.tutorialspringboot.model.Tutorial;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
    Page<Tutorial> findByPublished(boolean published, Pageable pageable); // Este metodo retorna todos os tutoriais que estao publicados. O campo 'published' deve ser verdadeiro.

    // Este metodo retorna todos os tutoriais cujo titulo contem a string dada, ignorando maiusculas e minusculas.
    // Consulta personalizada
    @Query("SELECT t FROM Tutorial t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<Tutorial> findByTitleIgnoreCase(String title, Pageable pageable);
    
    // Exemplos de Consulta JPA com @Query Condicao where
    @Query("SELECT t from Tutorial t")
    List<Tutorial> findAll();

    @Query("SELECT t FROM Tutorial t WHERE t.published=?1")
    List<Tutorial> findByPublished(boolean isPublished);

    @Query("SELECT t FROM Tutorial t WHERE t.title LIKE %?1%")
    List<Tutorial> findByTitleLike(String title);

    @Query("SELECT t FROM Tutorial t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Tutorial> findByTitleLikeCaseInsensitive(String title);

    // Exemplos de Consulta JPA com @Query >=(maior ou igual) a
    @Query("SELECT t FROM Tutorial t WHERE t.level >= ?1")
    List<Tutorial> findByLevelIsGreaterThanEqual(int level); // metodo encontrar por nivel e Maior Ou Igual

    // usando @Query
    @Query("SELECT t FROM Tutorial t WHERE t.createdAt >= ?1")
    List<Tutorial> findByDataGreaterThanEqual(Date date);

    // usando Query Methods (metodo de consulta do Spring Data JPA
    // recurso "Query Methods" (Metodos de Consulta) e uma funcao do Spring Data JPA. Com ele, voce so precisa nomear o metodo de uma forma especifica que o Spring entende o que voce quer fazer na consulta SQL.
    // O nome do metodo diz ao Spring Data JPA o que voce quer fazer, como buscar (find), contar (count), etc. E os parametros ajudam a especificar os detalhes dessa operação.
    List<Tutorial> findByCreatedAtGreaterThanEqual(Date date); // com o 'metodo de consulta' do Spring Data JPA o spring entende com base no nome do metodo e o seu retorno o que eu quero realizar com ele
    
    // Exemplos de Consulta JPA com @Query BETWEEN(entre)
    @Query("SELECT t FROM Tutorial t WHERE t.level BETWEEN ?1 AND ?2")
    List<Tutorial> findByLevelBetween(int start, int end);

    @Query("SELECT t FROM Tutorial t WHERE t.createdAt BETWEEN ?1 AND ?2")
    List<Tutorial> findByCreatedAtBetween(Date start, Date end); // com o 'metodo de consulta' do Spring Data JPA o spring entende com base no nome do metodo e o seu retorno o que eu quero realizar com ele

    // Exemplos de Consulta JPA com @Query com parametros
    @Query("SELECT t FROM Tutorial t WHERE t.published=:isPublished AND t.level BETWEEN :start AND :end")
    List<Tutorial> findByLevelBetween(@Param("start") int start, @Param("end") int end, @Param("isPublished") boolean isPublished);

    // Exemplos de Consulta JPA com @Query por DESC/ASC
    @Query("SELECT t FROM Tutorial t ORDER BY t.level DESC")
    List<Tutorial> findAllOrderByLevelDesc();

    @Query("SELECT t FROM Tutorial t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', ?1,'%')) ORDER BY t.level ASC")
    List<Tutorial> findByTitleOrderByLevelAsc(String title);

    @Query("SELECT t FROM Tutorial t WHERE t.published=true ORDER BY t.createdAt DESC")
    List<Tutorial> findAllPublishedOrderByCreatedDesc();

    // Exemplos de Consulta JPA com Sort Classificar por
    @Query("SELECT t FROM Tutorial t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', ?1,'%'))") // tutorials = tutorialRepository.findByTitleAndSort("at", Sort.by("level").descending());
    List<Tutorial> findByTitleAndSort(String title, Sort sort);

    @Query("SELECT t FROM Tutorial t WHERE t.published=?1")
    List<Tutorial> findByPublishedAndSort(boolean isPublished, Sort sort); // Objeto Sort tem direcao e campo

    // Exemplos de Consulta JPA com Paginacao Pageable
    // Na controller
    // pageable = PageRequest.of(page, size, Sort.by("level").descending());
    // tutorials = tutorialRepository.findAllWithPagination(pageable).getContent();
    @Query("SELECT t FROM Tutorial t")
    Page<Tutorial> findAllWithPagination(Pageable pageable); // pageable tem 1 page(pagina solicitada) 2 size(tamanho para todas as paginas da tabela) e 3 Objeto Sort (direcao e campo)

    Page<Tutorial> findByTitleContaining(String title, Pageable pageable);

    //
    // :( Sem usar @Transactional, @Modifying e @Query
    // Para atualizar um campo:
    // 1 Buscar o objeto do banco de dados.
    // 2 Mudar o valor no objeto.
    // 3 Salvar o objeto de volta no banco de dados.
    // Esse processo e longo e precisa de mais codigo.
    // :) Usando @Transactional, @Modifying e @Query
    // Para autalizar um campo:
    // Com esses recursos, voce faz tudo de uma vez: procura e atualiza o objeto no banco de dados. e mais rapido e mais limpo.
    @Transactional // Esse código diz que tudo dentro desse metodo vai ser uma transacao. Ou seja, se alguma coisa der errado, ele volta atras nas alteracoes no banco de dados.
    @Modifying // Esse e para dizer que a consulta vai mudar os dados, nao apenas pega-los. Pense nisso como um aviso para o programa se preparar para mudancas.
    @Query("UPDATE Tutorial t SET t.published=true WHERE t.id=?1") // Isso atualiza a coluna published para true onde o id e igual ao que voce passou.
    int publishTutorial(Long id);

}
    // JPQL: ok
    //@Query("SELECT * FROM tutorials t WHERE t.title LIKE %?1%")
    //List<Tutorial> findByTitleAndSort(String title, Pageable pageable);

    // Native query: throw InvalidJpaQueryMethodException porque nao se pode usar classificacao dinamica com nativeQuery = true
    //@Query(value = "SELECT * FROM tutorials t WHERE t.title LIKE %?1%", nativeQuery = true)
    //List<Tutorial> findByTitleAndSortNative(String title, Pageable pageable);


    // Uso de @Transactional para realiar Rollback
    // Imagine que voce esta comprando algo pela internet. Voce adiciona o item ao carrinho e decide pagar. O site faz varias coisas:

    // 1 Diminui a quantidade do produto no estoque.
    // 2 Adiciona pontos ao seu programa de fidelidade.
    // 3 Cobra o valor no seu cartao de credito.

    // Agora, suponha que a etapa 3 falhe (por exemplo, o cartao do cliente e recusado). Se isso acontecer, voce nao vai querer que as outras acoes (etapas 1 e 2) permanecam, certo? Entao o sistema fara um "rollback", ou seja, vai voltar atras nas acoes 1 e 2, como se elas nunca tivessem acontecido.
    // :( Sem uso de Rollback
    // Se o sistema nao usasse "rollback", o cliente ficaria com pontos no programa de fidelidade sem realmente ter feito a compra, e o estoque do produto seria reduzido sem razao. Isso causaria muitos problemas para a loja e para o cliente.
    // Entao, "rollback" e um recurso muito util para manter tudo organizado e correto.