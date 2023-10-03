// todas as classes java são subclasses de java.lang.Object, java.lang.Object é a superclasse de todas as classes java

package com.example.tutorialspringboot.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;  // antigo java ee, jakarta ee vem da eclipse foundation com primeira versao 2019 e um conjunto de especificacoes (regras) para ajudar os dev a construirem aplicativos empresariais de forma padronizada

import java.util.Date;

@Entity
@Table(name = "Tutorial")  // table name e column name são definidos para uma maior clareza e controle, apesar do hibernate automaticamente usar o nome da entidade java para o nome da tabela do banco p
@Schema(description = "Informação do Modelo de Tutorial")
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)   // o java confia que o postgresql ira olhar a sequencia e gerar automaticamente o proximo valor disponivel para o id
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Id(identificador) do Tutorial", example = "4")
    private long id;

    @Column(name = "title")
    @Schema(description = "Título do Tutorial", example = "Banco de Dados")
    private String title;

    @Column(name = "description")
    @Schema(description = "Descrição do Tutorial", example = "Postgresql")
    private String description;

    @Column(name = "level")
    @Schema(description = "Level do Tutorial", example = "5")
    private int level;

    @Column(name = "published")
    @Schema(description = "Status do Tutorial (publicado ou não)", example = "true")
    private boolean published;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @Schema(description = "Data de criação do Tutorial", example = "2023/01/01")
    private Date createdAt;

    public Tutorial() { // construtor sem argumentos que me permite na hora de instanciar a classe, new Tutorial() não precisar passar argumentos, posso passar depois com sets setTitle
        
    }

    public Tutorial(String title, String description, int level, boolean published, Date createdAt) {  // construtor com todos os argumentos necessários para criar um Tutorial, não tem o id porque quem cria é o banco de dados de forma incremental, não preciso passar
        this.title = title;
        this.description = description;
        this.level = level;
        this.published = published;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {  //sobscreve o método que faz parte da classe base java "java.lang.Object" e está disponível em todas as classes Java, por padrão ele retorna uma representação em String no formato "nomeDaClasse@endereçoDeMemória" por isso é feito o Override para um formato mais amigável
        return String.format("[id=%s, title=%s, desc=%s, published=%s]", id, title, description, published);
    }
}
