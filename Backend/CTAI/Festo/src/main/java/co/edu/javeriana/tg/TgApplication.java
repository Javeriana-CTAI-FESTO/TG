package co.edu.javeriana.tg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class TgApplication {

	public static void main(String[] args) {
		SpringApplication.run(TgApplication.class, args);
	}

	@Bean
	public OpenAPI openAPI() {
		Contact contact = new Contact().name("Pontificia Universidad Javeriana").url("https://javeriana.edu.co")
				.email("Fb6o2@example.com");

		Info info = new Info().description(
				"Software para la administración de maquinas y estaciones de trabajo mediantes MES4.")
				.title("CTAI API").version("v1")
				.termsOfService("http://swagger.io/terms/")
				.contact(contact)
				.version("v1");
		return new OpenAPI().info(info);
	}
}
