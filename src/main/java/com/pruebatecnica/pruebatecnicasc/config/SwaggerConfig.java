package com.pruebatecnica.pruebatecnicasc.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Películas")
                        .description("Documentación técnica para la prueba backend")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Juan Pablo")
                                .email("cpujuanpis@gmail.com")
                                .url("https://github.com/CpuJP"))
                        .license(new License().name("MIT").url("https://opensource.org/licenses/MIT")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositorio en GitHub")
                        .url("https://github.com/CpuJP/prueba-tecnica-backend"))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Servidor local")
                ));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("Películas")
                .pathsToMatch("/movie/**", "/movies")
                .build();
    }
}
