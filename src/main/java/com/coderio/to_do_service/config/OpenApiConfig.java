package com.coderio.to_do_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API To Do List")
                        .version("1.0")
                        .description("Documentación de la API de gestión de tareas"));
    }

    @Bean
    public GroupedOpenApi taskApi() {
        return GroupedOpenApi.builder()
                .group("tasks")
                .pathsToMatch("/tasks/**")
                .build();
    }
}
