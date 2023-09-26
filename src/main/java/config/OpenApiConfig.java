package config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;



@Configuration
// @Configuration: Essa anotacao diz ao Spring que a classe contem configuracoes. Mas a classe em si nao faz nada ate que voce diga ao Spring quais objetos ele precisa criar e gerenciar. E aí que entra @Bean.
public class OpenApiConfig {

    // A anotacao @Value pega o valor associado a chave openapi-dev-url do arquivo src>resources>application.properties e coloca na variavel devUrl. Isso evita que você tenha que escrever
    // o valor "localhost:8080" diretamente no codigo, conhecido como "hardcoding". Centralizar as informacoes em um arquivo de configuracao torna mais facil fazer mudancas futuras.
    // Essa abordagem é mais flexivel porque, se você decidir mudar a URL, basta atualizar o arquivo application.properties sem ter que mexer no codigo e recompilar o projeto.
    @Value("${openapi-dev-url}")
    private String devUrl;

    @Value("${openapi-prod-url}")
    private String prodUrl;

    @Bean  // @Bean: Essa anotacao e usada nos metodos para dizer ao Spring: "Ei, execute esse metodo e guarde o resultado porque vou precisar dele mais tarde".
    public OpenAPI myOpenApi() {
        Server devServer = new Server();
        devServer.setUrl(devUrl); // Esta linha de codigo utiliza um marcador de posicao (${devUrl}) (placeholder) que sera substituido pela URL real. Essa URL esta configurada no arquivo "application.properties".
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setName("Witor Sather");
        contact.setEmail("satherws@gmail.com");
        contact.setUrl("https://github.com/witorsather");

        // tecnica da orientacao a objetos encadeamento de metodos, chamo o construtor para criar um objeto License e depois modifico o name e a url desse mesmo objeto, deixa o código mais simples e facil para entendimento e manutencao
        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Tutorial Management API")
                .version("1.0")
                .contact(contact)
                .description("Essa API expõe endpoints para gerenciar Tutoriais").termsOfService("https://github.com/witorsather/terms")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer)); // List.of cria uma lista imutavel
    }

}

// Inicializacao Spring Boot
// 1 Quando a aplicacao Spring Boot inicia, ela procura todas as classes marcadas com @Configuration.
// 2 Para cada classe de configuracao, ele olha os metodos marcados com @Bean.
// O Spring chama esses metodos @Bean e guarda o resultado para uso futuro.

// @Configuration: Spring Boot vê essa anotação e sabe que esta classe tem configurações importantes.
// @Value: Pega as URLs (endereços na internet) do arquivo application.properties e coloca nas variáveis devUrl e prodUrl.
// @Bean: Diz ao Spring Boot para prestar atenção nesse método, porque ele vai criar um objeto que será usado mais tarde.

//        Criação de OpenAPI:
//        4.1 Server: Cria informações sobre o servidor de desenvolvimento e produção. Ele diz onde o servidor está localizado usando devUrl e prodUrl.
//        4.2 Contact: Cria informações de contato, como nome, e-mail e GitHub.
//        4.3 Licença: Usa uma técnica chamada "encadeamento de métodos" para criar e preencher os detalhes da licença MIT.
//        4.4 Info: Junta todas essas informações (título, versão, contato, descrição, termos de serviço e licença) em um objeto Info.
//        4.5 Retorna a OpenAPI: Retorna um objeto OpenAPI com todas as informações juntadas. Ele também diz quais servidores estão disponíveis usando List.of(devServer, prodServer).