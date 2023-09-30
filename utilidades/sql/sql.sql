SELECT id, description, published, title
FROM public.tutorial;

INSERT INTO public.tutorial(id, description, published, title) VALUES 
(1, 'Um tutorial sobre Spring Boot', true, 'Spring Boot para Iniciantes'),
(2, 'Aprenda Maven com facilidade', true, 'Maven Básico'),
(3, 'Como usar PostgreSQL', false, 'Introdução ao PostgreSQL'),
(4, 'Aprofunde-se em Java', true, 'Java Avançado'),
(5, 'Descubra as melhores práticas de programação', false, 'Clean Code e Boas Práticas'),
(6, 'Um guia sobre Angular', true, 'Angular para Iniciantes'),
(7, 'Entendendo o Vue.js', true, 'Vue.js Básico'),
(8, 'Usando banco de dados em nuvem', false, 'Cloud Databases 101'),
(9, 'Programação orientada a objetos em Java', true, 'OOP em Java'),
(10, 'Como otimizar consultas SQL', false, 'SQL Avançado e Otimizações'),
(11, 'Aprenda a criar uma API RESTful com Spring Boot', true, 'Criando APIs com Java Spring Boot'),
(12, 'Construa aplicativos Android usando Java', true, 'Desenvolvimento Android com Java'),
(13, 'Introdução a estruturas de dados em Java', false, 'Estruturas de Dados em Java'),
(14, 'Como escrever código limpo em Java', true, 'Clean Code em Java'),
(15, 'Conhecendo as novidades do Java 11', false, 'O que há de novo no Java 11'),
(16, 'Desvende o JavaFX para interfaces gráficas', true, 'JavaFX para Iniciantes'),
(17, 'Aprenda testes unitários em Java com JUnit', true, 'Testes Unitários com JUnit'),
(18, 'Entenda a programação assíncrona com Java', false, 'Programação Assíncrona em Java'),
(19, 'Uso avançado de Generics em Java', true, 'Generics Avançados em Java'),
(20, 'Construindo microserviços com Spring Boot', false, 'Microserviços com Java Spring Boot');

