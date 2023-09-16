package co.edu.javeriana.ctai.tgsecurity.controller.admin;

import co.edu.javeriana.ctai.tgsecurity.service.payload.dbRouteRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@RestController
@RequestMapping("api/admin")
public class AdminController {

    // Servicio para añadir la ruta de la base de datos al módulo FESTO y ejecutar en segundo plano
    @PostMapping("/dbroute")
    public ResponseEntity<String> putDBRoute(@RequestBody dbRouteRequest dbRouteRequest) {
        if (dbRouteRequest == null || dbRouteRequest.getDbRoute() == null || dbRouteRequest.getDbRoute().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo 'dbRoute' es obligatorio.");
        }

        // Configura la variable de entorno DATABASE_FILE
        String dbRoute = dbRouteRequest.getDbRoute();
        System.setProperty("DATABASE_FILE", dbRoute);

        // Verifica si el proceso está en ejecución y lo detiene
        if (detenerProceso()) {
            System.out.println("Proceso anterior detenido.");
        }

        // Ejecuta el módulo JAR en un subproceso en segundo plano
        Thread executionThread = new Thread(() -> {
            try {
                ejecutarModuloJar();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Limpia la variable de entorno después de la ejecución
                System.clearProperty("DATABASE_FILE");
            }
        });

        executionThread.start(); // Inicia el subproceso
        System.out.println("Proceso secundario iniciado.");

        // Responde inmediatamente indicando que la solicitud se está procesando en segundo plano
        return ResponseEntity.ok().body("La ejecución del módulo JAR está en curso en segundo plano.");
    }

    private boolean detenerProceso() {
        try {
            // Ejecuta el comando "jps" para listar todos los procesos de Java
            ProcessBuilder jpsProcessBuilder = new ProcessBuilder("jps");
            Process jpsProcess = jpsProcessBuilder.start();

            // Lee la salida del comando "jps"
            BufferedReader jpsReader = new BufferedReader(new InputStreamReader(jpsProcess.getInputStream()));
            String line;
            while ((line = jpsReader.readLine()) != null) {
                // Busca una línea que contenga el nombre del módulo JAR
                if (line.contains("tg-0.0.1-SNAPSHOT.jar")) {
                    // Obtiene el ID del proceso
                    String[] parts = line.split(" ");
                    if (parts.length >= 2) {
                        String processID = parts[0];

                        // Detiene el proceso
                        ProcessBuilder killProcessBuilder = new ProcessBuilder("kill", "-9", processID);
                        killProcessBuilder.start();

                        return true; // Proceso detenido
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Proceso no encontrado o no se pudo detener
    }

    private void ejecutarModuloJar() throws IOException {
        // Comando para ejecutar el módulo JAR
        // Ruta del módulo JAR (configurada externamente)
        String rutaModuloJar = "/Users/nabu/Desktop/TG-Backend-2/Backend/CTAI/Festo/target/tg-0.0.1-SNAPSHOT.jar";
        String[] comando = {"java", "-jar", "-Dspring.profiles.active=dev", rutaModuloJar};

        // Iniciar el proceso
        ProcessBuilder processBuilder = new ProcessBuilder(comando);
        processBuilder.environment().put("DATABASE_FILE", System.getProperty("DATABASE_FILE"));

        Process process = processBuilder.start();

        // Espera a que el proceso secundario finalice
        try {
            int exitCode = process.waitFor();
            System.out.println("Proceso anterior finalizado con código de salida: " + exitCode);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaura la bandera de interrupción
            e.printStackTrace();
        }
    }
}
