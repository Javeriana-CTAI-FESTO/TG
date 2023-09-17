package co.edu.javeriana.ctai.tgsecurity.service.imp;
// ModuleExecutionServiceImpl.java

import co.edu.javeriana.ctai.tgsecurity.service.intf.IModuleExecutionService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModuleExecutionServiceImpl implements IModuleExecutionService {
    // Ruta del módulo JAR (configurada externamente)
    private final String rutaModuloJar = "/Users/nabu/Desktop/TG-Backend-2/Backend/CTAI/Festo/target/tg-0.0.1-SNAPSHOT.jar";

    @Override
    public void executeModule(String dbRoute) throws IOException {
        // Comando para ejecutar el módulo JAR
        String[] comando = {"java", "-jar", "-Dspring.profiles.active=dev", rutaModuloJar};

        // Configura la variable de entorno DATABASE_FILE
        System.setProperty("DATABASE_FILE", dbRoute);

        // Iniciar el proceso
        ProcessBuilder processBuilder = new ProcessBuilder(comando);
        processBuilder.environment().put("DATABASE_FILE", System.getProperty("DATABASE_FILE"));

        Process moduleProcess = processBuilder.start();

        // Espera a que el proceso secundario finalice
        try {
            int exitCode = moduleProcess.waitFor();
            System.out.println("Proceso secundario finalizado con código de salida: " + exitCode);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaura la bandera de interrupción
            e.printStackTrace();
        } finally {
            // Limpia la variable de entorno después de la ejecución
            System.clearProperty("DATABASE_FILE");
        }
    }

    @Override
    public void stopModule() {
        try {
            // Ejecuta el comando "jps" para listar todos los procesos de Java
            ProcessBuilder jpsProcessBuilder = new ProcessBuilder("jps");
            Process jpsProcess = jpsProcessBuilder.start();

            // Lee la salida del comando "jps"
            BufferedReader jpsReader = new BufferedReader(new InputStreamReader(jpsProcess.getInputStream()));
            List<String> processLines = jpsReader.lines().collect(Collectors.toList());

            // Busca y detiene todos los procesos con el nombre "tg-0.0.1-SNAPSHOT.jar"
            for (String line : processLines) {
                if (line.contains("tg-0.0.1-SNAPSHOT.jar")) {
                    String[] parts = line.split(" ");
                    if (parts.length >= 2) {
                        String processID = parts[0];
                        ProcessBuilder killProcessBuilder = new ProcessBuilder("kill", "-9", processID);
                        killProcessBuilder.start();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
