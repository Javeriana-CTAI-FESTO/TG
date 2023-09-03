package co.edu.javeriana.tg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class TgApplication {

	public static void main(String[] args) {
		SpringApplication.run(TgApplication.class, args);
	}

	@Bean
	public OpenAPI openAPI() {
		Info info = new Info().title("Festo").version("1.0").description("Trabajo de grado, grupo 9, 2023-30");
		return new OpenAPI().info(info);
	}
}
