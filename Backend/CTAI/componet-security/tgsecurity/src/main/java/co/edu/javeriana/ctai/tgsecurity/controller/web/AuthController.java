package co.edu.javeriana.ctai.tgsecurity.controller.web;

import co.edu.javeriana.ctai.tgsecurity.entities.builder.ClienteBuilder;
import co.edu.javeriana.ctai.tgsecurity.entities.builder.UserBuilder;
import co.edu.javeriana.ctai.tgsecurity.entities.Cliente;
import co.edu.javeriana.ctai.tgsecurity.entities.User;
import co.edu.javeriana.ctai.tgsecurity.repository.interfaces.IUserRepository;
import co.edu.javeriana.ctai.tgsecurity.security.jwt.JwtTokenUtil;
import co.edu.javeriana.ctai.tgsecurity.security.payload.JwtResponse;
import co.edu.javeriana.ctai.tgsecurity.security.payload.LoginRequest;
import co.edu.javeriana.ctai.tgsecurity.security.payload.MessageResponse;
import co.edu.javeriana.ctai.tgsecurity.security.payload.RegisterRequest;
import co.edu.javeriana.ctai.tgsecurity.services.IClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.logging.Logger;

/**
 * Controlador para llevar a cabo la autenticación utilizando JWT
 * <p>
 * Se utiliza AuthenticationManager para autenticar las credenciales que son el
 * usuario y password que llegan por POST en el cuerpo de la petición
 * <p>
 * Si las credenciales son válidas se genera un token JWT como respuesta
 */

 
// @CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger LOGGER = Logger.getLogger(AuthController.class.getName());

    private final AuthenticationManager authManager;
    private final IUserRepository userRepository;
    private final IClientService clientService;



    private final PasswordEncoder encoder;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(AuthenticationManager authManager,
                          IUserRepository userRepository,
                          PasswordEncoder encoder,
                          JwtTokenUtil jwtTokenUtil, @Qualifier("clientServiceImp") IClientService clientService) {
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.clientService = clientService;
    }

    @Operation(
            summary = "Login",
            description = "Autenticación de usuario"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Autenticación exitosa, token JWT generado",
                    content = @Content(schema = @Schema(implementation = JwtResponse.class)),
                    headers = @Header(
                            name = "Authorization",
                            description = "Token JWT para autorización",
                            schema = @Schema(type = "string")
                    )
            ),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @SecurityRequirement(name = "Basic Auth")
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestHeader("Authorization") String authHeader) {
        final String basicPrefix = "Basic ";
        if (authHeader == null || !authHeader.startsWith(basicPrefix)) {
            LOGGER.warning("Unauthorized access: Invalid authorization header");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            byte[] decodedBytes = Base64.getDecoder().decode(authHeader.substring(basicPrefix.length()));
            String[] credentials = new String(decodedBytes).split(":");
            String username = credentials[0];
            String password = credentials[1];

            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtTokenUtil.generateJwtToken(authentication);

            LOGGER.info("User '{}' has successfully logged in ");

            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (IllegalArgumentException e) {
            LOGGER.warning("Failed to decode Base64: {}"+ e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @Operation(summary = "Registro de Usuario", description = "Registra un nuevo usuario y genera un token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest signUpRequest) {
        System.out.println(signUpRequest.getPassword());


        // Check 1: username
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        else {
            LOGGER.info("Username: " + signUpRequest.getUsername());
            User user = new UserBuilder()
                    .setUsername(signUpRequest.getUsername())
                    .setPassword(encoder.encode(signUpRequest.getPassword()))
                    .build();

            userRepository.save(user);


            if(
                    clientService.existsByCedula(signUpRequest.getIdentification()) &&
                            clientService.existsByCorreoElectronico(signUpRequest.getEmail()) &&
                            clientService.existsByCelular(signUpRequest.getPhone())
            ){
                // Delete user
                userRepository.delete(user);
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Identification, Email and Phone are already taken!"));

            } else {

                LOGGER.info("Identification: " + signUpRequest.getIdentification());
                LOGGER.info("Email: " + signUpRequest.getEmail());
                LOGGER.info("Phone: " + signUpRequest.getPhone());


                Cliente cliente = new ClienteBuilder()
                        .setNombre(signUpRequest.getName())
                        .setApellido(signUpRequest.getLastName())
                        .setCorreoElectronico(signUpRequest.getEmail())
                        .setUsuario(user)
                        .setIdentificacion(signUpRequest.getIdentification())
                        .setCelular(signUpRequest.getPhone())
                        .setRol(signUpRequest.getRol())
                        .build();

                clientService.save(cliente);
            }

        }


        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}

