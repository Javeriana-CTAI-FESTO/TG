package co.edu.javeriana.ctai.tgsecurity.service.external;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Servicio para ejecutar un módulo JAR en segundo plano y detener un proceso anterior si está en ejecución.
 */
@Service
public class ExecutionService {
   // @Value("${rutaModuloJar}") // Lee la ruta del módulo JAR desde la configuración (application.properties o application.yml)
    private String rutaModuloJar;

    /**
     * Ejecuta el módulo JAR en un proceso secundario en segundo plano.
     */
    public void ejecutarModuloJar() {
        try {
            // Detiene el proceso anterior si está en ejecución
            if (detenerProceso()) {
                System.out.println("Modulo Controller de MES4 ddbb para FESTO. DETENIDO");
            }

            // Comando para ejecutar el módulo JAR
            String[] comandoProduccion = {"java", "-jar", "-Dspring.profiles.active=dev", rutaModuloJar};

            // Iniciar el proceso
            ProcessBuilder processBuilder = new ProcessBuilder(comandoProduccion);
            processBuilder.environment().put("DATABASE_FILE", System.getProperty("DATABASE_FILE"));

            Process process = processBuilder.start();

            // Espera a que el proceso secundario finalice
            int exitCode = process.waitFor();
            System.out.println("Modulo Controller de MES4 ddbb para FESTO. DETENIDO Codigo: " + exitCode);

        } catch (IOException e) {

            System.err.println("Error al ejecutar el módulo JAR: " + e.getMessage());
            e.printStackTrace();

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt(); // Restaura la bandera de interrupción
            System.err.println("El hilo de ejecución se interrumpió: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Detiene un proceso anterior si está en ejecución.
     *
     * @return `true` si el proceso fue detenido, `false` si no se encontró o no se pudo detener.
     */
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

            System.err.println("Error al detener el proceso anterior: " + e.getMessage());
            e.printStackTrace();
        }
        return false; // Proceso no encontrado o no se pudo detener
    }
}

