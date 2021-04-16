package com.mastery.java.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    // http://localhost:8080/swagger-ui.html

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(generateApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mastery.java.task.controller"))
                .build();
    }

    private ApiInfo generateApiInfo() {
        return new ApiInfoBuilder()
                .title("Test API")
                .description("API allows you to control the employees!")
                .version("1.0")
                .build();
    }
}