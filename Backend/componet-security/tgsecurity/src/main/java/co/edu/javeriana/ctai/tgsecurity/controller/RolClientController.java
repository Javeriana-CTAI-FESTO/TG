package co.edu.javeriana.ctai.tgsecurity.controller;

import co.edu.javeriana.ctai.tgsecurity.patterns.model.Cliente;
import co.edu.javeriana.ctai.tgsecurity.patterns.model.User;
import co.edu.javeriana.ctai.tgsecurity.repository.IUserRepository;
import co.edu.javeriana.ctai.tgsecurity.service.intf.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;


@RestController
@RequestMapping("/api/rolclient")
public class RolClientController {

    @Qualifier("clientServiceImp")
    @Autowired
    private IClientService clientService;

    @Autowired
    private IUserRepository userRepository;


    // Servicio para obtener un por cedula
    @GetMapping("/cedula={cedula}")
    public ResponseEntity<?> findByCedula(@PathVariable Long cedula) {

        System.out.println("cedula: " + cedula);

        if (cedula == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La cedula no puede ser nula");
        }
        Cliente cliente = clientService.findByCedula(cedula);
        boolean existsByCedula = clientService.existsByCedula(cedula);

        System.out.println("cliente: " + cliente);
        System.out.println("existsByCedula: " + existsByCedula);


        if (cliente != null) {
            boolean isAdmin = cliente.isAdmin();
            boolean isProfesor = cliente.isProfesor();
            boolean isEstudiante = cliente.isEstudiante();

            if (isAdmin) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body("rol:'admin'");
            } else if (isProfesor) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body("rol:'profesor'");
            } else if (isEstudiante) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body("rol:'estudiante'");
            } else {
                return ResponseEntity.status(HttpStatus.OK)
                        .body("rol:'cliente'");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontro el cliente");
        }
    }

    // Get identification by user
    @GetMapping("/username={user_name}")
    public ResponseEntity<String> getUserRole(@PathVariable String user_name) {

        if (user_name != null) {

            User user = userRepository.findByUsername(user_name).get();
            System.out.println("Hello user: "+user.getUsername());

            Cliente cliente = clientService.findByUsuario(user);

            if (cliente != null) {
                // El objeto cliente no es nulo, puedes acceder a sus propiedades

                System.out.println("Cliente: " + cliente.getNombre());

                // Determine the role
                String role;
                if (cliente.isAdmin()) {
                    role = "admin";
                } else if (cliente.isProfesor()) {
                    role = "profesor";
                } else if (cliente.isEstudiante()) {
                    role = "estudiante";
                } else {
                    role = "cliente";
                }

                // Return a JSON response with the role
                return ResponseEntity.ok().body("{\"rol\":\"" + role + "\"}");
            } else {
                // Return a 404 response if the client was not found
                return ResponseEntity.notFound().build();
            }

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("/username={user_name}");
    }



}