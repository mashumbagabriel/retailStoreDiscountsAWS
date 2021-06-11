package com.generic.theRetailStoreDiscounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
public class TheRetailStoreDiscountApplication {

	public static void main(String[] args) {

	    SpringApplication.run(TheRetailStoreDiscountApplication.class, args);
	}

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(regex("/api.*"))
                .apis(RequestHandlerSelectors.basePackage("com.generic.theRetailStoreDiscounts.controller"))
                .build().apiInfo(metadata());

    }

    public static ApiInfo metadata(){
        return new ApiInfoBuilder()
                .title("Spring Boot REST API")
                .description("Spring Boot REST API for The Retail Store API")
                .version("1.0")
                .build();

    }


}
