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
(10, 'Como otimizar consultas SQL', false, 'SQL Avançado e Otimizações');
