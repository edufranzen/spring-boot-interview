package com.compasso.interview.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Desafio técnico Compasso - REST API", "Desafio técnico apresentando para a Compasso", "v1",
				"Termos de serviço",
				new Contact("Eduardo Franzen", "https://www.linkedin.com/in/edufranzen/", "edufranzen@hotmail.com"),
				"MIT", "https://opensource.org/licenses/MIT", Collections.emptyList());
	}
}