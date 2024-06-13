package com.green.greenfirstproject.common;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@OpenAPIDefinition(
        info = @Info(
                title = "오늘할일",
                description = "TodoList",
                version = "v0.0.1"

        )
)

public class SwaggerConfiguration {
}
