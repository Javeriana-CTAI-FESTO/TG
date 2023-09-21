package co.edu.javeriana.ctai.tgsecurity.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuración de Swagger para documentación de API REST.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	/**
	 * Define el esquema de autenticación JWT.
	 *
	 * @return ApiKey
	 */
	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	/**
	 * Configura Docket, que es el principal punto de entrada de Swagger.
	 *
	 * @return Docket
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiDetails())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	/**
	 * Define el contexto de seguridad para Swagger.
	 *
	 * @return SecurityContext
	 */
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	/**
	 * Define los detalles de autorización por defecto.
	 *
	 * @return Lista de SecurityReference
	 */
	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

	/**
	 * Define los detalles generales de la API.
	 *
	 * @return ApiInfo
	 */
	private ApiInfo apiDetails() {
		return new ApiInfo("Awesome API REST",
				"Pruebas Spring Boot API REST",
				"1.0",
				"http://localhost/terms",
				new Contact("Prueba", "Prueba", "Prueba"),
				"Prueba",
				"Prueba",
				Collections.emptyList());
	}
}
