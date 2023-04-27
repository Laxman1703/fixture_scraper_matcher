package com.xadmin.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket postsApi() {

		return new Docket(DocumentationType.SWAGGER_2).groupName("xadmin").apiInfo(apiInfo()).select()

				.paths(regex("/api.*")).build();

	}

	private ApiInfo apiInfo() {

		return new ApiInfoBuilder()
				.title("Web-Sraping API")

				.description("Web Scraping API Documentation Generated Using SWAGGER2")

				.termsOfServiceUrl("https://sportsit-tech.net/index.html")

				.license("Xadmin Rest API License")

				.licenseUrl(" https://sportsit-tech.net/index.html ").version("1.0").build();

	}

}
