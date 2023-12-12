package com.rest;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2
public class RestaurantAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantAPIApplication.class, args);
	}
	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();
	}
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).groupName("Restaurant API")
//				.apiInfo(apiInfo()).
//				select().
//				//apis(RequestHandlerSelectors.basePackage("com.rest.controller")).
//				paths(PathSelectors.any()).build();
//	}
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder().title("Restaurant service")
//				.description("services provided ")
//				.build();
//	}
}
