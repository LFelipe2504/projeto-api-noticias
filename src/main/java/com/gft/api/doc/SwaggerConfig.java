package com.gft.api.doc;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@OpenAPIDefinition(security = {@SecurityRequirement(name = "bearer-key")})
public class SwaggerConfig {
	
	private Contact contato() {
		return new springfox.documentation.service.Contact("Luiz Felipe",
				"https://github.com/LFelipe2504", "luizfelipedlima25@gmail.com");
	}
	
	private ApiInfoBuilder informacoesApi() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("REST API Notícias");
        apiInfoBuilder.description("API para usuários consumirem notícias de forma rápida e prática");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.termsOfServiceUrl("Termo de uso: Open Source");
        apiInfoBuilder.license("Open Source ");
        apiInfoBuilder.licenseUrl("http://www.exemplo.com.br");
        apiInfoBuilder.contact(this.contato());

        return apiInfoBuilder;

    }
	
	@Bean
    public Docket detalheApi() {
        Docket docket = new Docket(DocumentationType.OAS_30);

        docket
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gft.api.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.informacoesApi().build())
                .consumes(new HashSet<String>(Arrays.asList("application/json")))
                .produces(new HashSet<String>(Arrays.asList("application/json")));

        return docket;
    }
	
	

}
