package com.Lab04Backend.TaskFlow.security.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME = "Bearer Authentication";

    @Bean
    public OpenAPI customOpenAPI() {

        SecurityScheme securityScheme = new SecurityScheme()
                .name(SECURITY_SCHEME)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI()

                .info(
                        new Info()
                                .title("TaskFlow API")
                                .version("1.0")
                                .description("TaskFlow REST API")
                )

                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(SECURITY_SCHEME)
                )

                .schemaRequirement(
                        SECURITY_SCHEME,
                        securityScheme
                );
    }
}