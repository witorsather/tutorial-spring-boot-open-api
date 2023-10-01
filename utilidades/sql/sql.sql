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
(20, 'Construindo microserviços com Spring Boot', false, 'Microserviços com Java Spring Boot'),
(21, 'Introdução ao desenvolvimento com Python', true, 'Python para Iniciantes'),
(22, 'Entendendo o Git e GitHub', false, 'Git e GitHub Básico'),
(23, 'Desenvolvimento Web com HTML, CSS e JavaScript', true, 'Desenvolvimento Web Front-End'),
(24, 'Aprenda SQL e Bancos de Dados Relacionais', true, 'SQL e Bancos de Dados'),
(25, 'Uso de Docker para Contêineres', false, 'Docker Básico'),
(26, 'Desenvolvimento Mobile com Flutter', true, 'Flutter para Iniciantes'),
(27, 'Entendendo o GraphQL', true, 'GraphQL Básico'),
(28, 'Aprofundamento em Algoritmos e Estruturas de Dados', false, 'Algoritmos e Estruturas de Dados'),
(29, 'Desenvolvimento de APIs com Node.js', true, 'Node.js para APIs'),
(30, 'Introdução ao Machine Learning', false, 'Machine Learning para Iniciantes'),
(31, 'Automatização com Shell Script', true, 'Shell Script Básico'),
(32, 'Introdução ao DevOps', false, 'DevOps para Iniciantes'),
(33, 'Aprenda a programar com Scratch', true, 'Programação para Crianças com Scratch'),
(34, 'Desenvolvimento de jogos com Unity', true, 'Unity para Iniciantes'),
(35, 'Introdução ao Kubernetes', false, 'Kubernetes Básico'),
(36, 'Desenvolvimento Front-End com React', true, 'React para Iniciantes'),
(37, 'Desenvolvimento Back-End com Ruby on Rails', true, 'Ruby on Rails para Iniciantes'),
(38, 'Introdução ao Blockchain', false, 'Blockchain para Iniciantes'),
(39, 'Testes Automatizados com Selenium', true, 'Testes com Selenium'),
(40, 'Conhecendo o mundo do Open Source', false, 'Introdução ao Open Source'),
(41, 'Introdução ao desenvolvimento com Go', true, 'Go para Iniciantes'),
(42, 'Redes de Computadores para Desenvolvedores', false, 'Redes de Computadores'),
(43, 'Desenvolvimento com C#', true, 'C# para Iniciantes'),
(44, 'Segurança da Informação em Desenvolvimento', true, 'Segurança da Informação'),
(45, 'NoSQL e Bancos de Dados Não-Relacionais', false, 'NoSQL Básico'),
(46, 'JavaScript Avançado', true, 'JavaScript Avançado'),
(47, 'Introdução ao Big Data', true, 'Big Data para Iniciantes'),
(48, 'Aprendendo CI/CD', false, 'Introdução a CI/CD'),
(49, 'Desenvolvimento de Software Ágil', true, 'Metodologias Ágeis'),
(50, 'Uso de APIs RESTful', false, 'RESTful APIs para Iniciantes'),
(51, 'Aprofundamento em Cloud Computing', true, 'Cloud Computing Avançado'),
(52, 'Desenvolvimento com TypeScript', false, 'TypeScript para Iniciantes'),
(53, 'Introdução ao IoT', true, 'IoT para Iniciantes'),
(54, 'Construindo aplicações com Firebase', true, 'Firebase para Desenvolvedores'),
(55, 'Desenvolvimento Front-End com Vue.js', false, 'Vue.js para Iniciantes'),
(56, 'Construindo aplicações em tempo real', true, 'Tempo Real em Aplicações Web'),
(57, 'Desenvolvimento de plugins para WordPress', true, 'WordPress Plugins'),
(58, 'Introdução à Computação Quântica', false, 'Computação Quântica para Iniciantes'),
(59, 'Acessibilidade na Web', true, 'Acessibilidade Web'),
(60, 'Desenvolvimento para Apple Watch', false, 'Apple Watch Development'),
(61, 'Otimização de Performance em Websites', true, 'Performance em Websites'),
(62, 'Desenvolvimento de Chatbots', true, 'Chatbots para Iniciantes'),
(63, 'Aprenda SASS e LESS', false, 'SASS e LESS para Desenvolvedores'),
(64, 'Aprofunde-se em Machine Learning', true, 'Machine Learning Avançado'),
(65, 'Uso de WebSockets', true, 'WebSockets para Desenvolvedores'),
(66, 'Introdução ao AR e VR', false, 'Realidade Aumentada e Virtual para Iniciantes'),
(67, 'Aprendendo Design de UI/UX', true, 'UI/UX Design'),
(68, 'Introdução à Análise de Dados', true, 'Análise de Dados para Iniciantes'),
(69, 'Desenvolvimento com Swift', false, 'Swift para Iniciantes'),
(70, 'Aprenda a usar GitLab', true, 'GitLab para Iniciantes'),
(71, 'Desenvolvimento de aplicações multiplataforma', true, 'Aplicações Multiplataforma'),
(72, 'Introdução ao Scrum', false, 'Scrum para Iniciantes'),
(73, 'Testes de Software', true, 'Testes de Software para Iniciantes'),
(74, 'Desenvolvimento de extensões para navegadores', true, 'Extensões de Navegador'),
(75, 'Introdução ao PHP', false, 'PHP para Iniciantes'),
(76, 'Otimização de SEO', true, 'SEO para Desenvolvedores'),
(77, 'Introdução ao Data Science', true, 'Data Science para Iniciantes'),
(78, 'Aprenda a usar Figma para design', false, 'Figma para Designers'),
(79, 'Desenvolvimento de jogos com Unreal Engine', true, 'Unreal Engine para Iniciantes'),
(80, 'Introdução ao Raspberry Pi', true, 'Raspberry Pi para Iniciantes');




