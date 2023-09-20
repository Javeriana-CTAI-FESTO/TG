package co.edu.javeriana.ctai.tgsecurity.controller.admin;

import co.edu.javeriana.ctai.tgsecurity.service.external.ExecutionService;
import co.edu.javeriana.ctai.tgsecurity.service.payload.dbRouteRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/admin")
public class AdminController {

    private final ExecutionService executionService;

    public AdminController(ExecutionService executionService) {
        this.executionService = executionService;
    }

    /**
     * Servicio para añadir la ruta de la base de datos al módulo FESTO y ejecutar en segundo plano.
     *
     * @param dbRouteRequest Objeto que contiene la ruta de la base de datos y la ruta del módulo JAR.
     * @return ResponseEntity con un mensaje indicando que la ejecución está en curso en segundo plano.
     */
    @PostMapping("/dbroute")
    public ResponseEntity<?> putDBRoute(@RequestBody dbRouteRequest dbRouteRequest) {
        if (dbRouteRequest == null || dbRouteRequest.getDbRoute() == null || dbRouteRequest.getDbRoute().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo 'dbRoute' es obligatorio.");
        }

        // Configura la variable de entorno DATABASE_FILE
        String dbRoute = dbRouteRequest.getDbRoute();
        String rutaModuloJar = dbRouteRequest.getRutaModuloJar();

        System.setProperty("DATABASE_FILE", dbRoute);

        // Ejecuta el módulo JAR en segundo plano usando el servicio ExecutionService
        Thread executionThread = new Thread(() -> {
            try {
                // Inyecta el servicio y llama a su método para ejecutar el módulo JAR
                executionService.ejecutarModuloJar();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Limpia la variable de entorno después de la ejecución
                System.clearProperty("DATABASE_FILE");
            }
        });

        executionThread.start(); // Inicia el subproceso
        System.out.println("Modulo Controller de MES4 ddbb para FESTO. EN EJECUCION");

        // Responde inmediatamente indicando que la solicitud se está procesando en segundo plano
        return ResponseEntity.ok().body("{\"message\": \"Modulo Controller de MES4 ddbb para FESTO. EN EJECUCION\"}");
    }
}

