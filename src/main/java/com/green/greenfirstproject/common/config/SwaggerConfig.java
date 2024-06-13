package com.green.greenfirstproject.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;

import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "1차 프로젝트 - 제목미정",
                description = "",
                version = "v1"
        )
)
@SecurityScheme(
        name = "oauth2",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                authorizationCode = @OAuthFlow(
                        authorizationUrl = "https://accounts.google.com/o/oauth2/auth",
                        tokenUrl = "https://oauth2.googleapis.com/token",
                        scopes = {
                                @OAuthScope(name = "email", description = "Access email information")
                        }
                )
        )
)
public class SwaggerConfig {

//        @Bean
//        public OpenAPI customOpenAPI() {
//                return new OpenAPI()
//                        .info(new io.swagger.v3.oas.models.info.Info().title("API Documentation").version("v1"))
//                        .components(new Components()
//                                .addSecuritySchemes("oauth2", new SecurityScheme()
//                                        .type(SecurityScheme.Type.OAUTH2)
//                                        .flows(new OAuthFlows()
//                                                .authorizationCode(new OAuthFlow()
//                                                        .authorizationUrl("https://accounts.google.com/o/oauth2/auth")
//                                                        .tokenUrl("https://oauth2.googleapis.com/token")
//                                                        .scopes(new Scopes()
//                                                                .addString("profile", "Access profile information")
//                                                                .addString("email", "Access email information"))))));
//        }
}

