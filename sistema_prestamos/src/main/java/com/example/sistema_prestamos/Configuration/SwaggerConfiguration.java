package com.example.sistema_prestamos.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Sistema de Préstamos de Herramientas")
                        .version("1.0")
                        .description("API REST para la gestión de herramientas y registro de préstamos en el laboratorio de tecnología")
                        .contact(new Contact()
                                .name("Karen Castellanos")
                                .email("karencastellanos@ucundinamarca.edu.co")));
    }
}