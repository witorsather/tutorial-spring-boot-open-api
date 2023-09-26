// todas as classes java são subclasses de java.lang.Object, java.lang.Object é a superclasse de todas as classes java

package com.example.tutorialspringboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Tutorial")  // table name e column name são definidos para uma maior clareza e controle, apesar do hibernate automaticamente usar o nome da entidade java para o nome da tabela do banco p
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)   // o java confia que o postgresql irá olhar a sequência e gerar automáticamente o próximo valor disponível para o id
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "published")
    private boolean published;

    public Tutorial() { // construtor sem argumentos que me permite na hora de instanciar a classe, new Tutorial() não precisar passar argumentos, posso passar depois com sets setTitle
        
    }

    public Tutorial(String title, String description, boolean published) {  // construtor com todos os argumentos necessários para criar um Tutorial, não tem o id porque quem cria é o banco de dados de forma incremental, não preciso passar
        this.title = title;
        this.description = description;
        this.published = published;
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

    @Override
    public String toString() {  //sobscreve o método que faz parte da classe base java "java.lang.Object" e está disponível em todas as classes Java, por padrão ele retorna uma representação em String no formato "nomeDaClasse@endereçoDeMemória" por isso é feito o Override para um formato mais amigável
        return String.format("[id=%s, title=%s, desc=%s, published=%s]", id, title, description, published);
    }
}
