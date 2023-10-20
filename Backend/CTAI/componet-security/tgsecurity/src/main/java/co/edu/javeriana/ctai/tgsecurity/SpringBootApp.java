package co.edu.javeriana.ctai.tgsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class SpringBootApp {

	public static void main(String[] args) {
		// contenedor de beans
		SpringApplication.run(SpringBootApp.class, args);

	}

}