package co.edu.javeriana.ctai.tgsecurity.controller;

import co.edu.javeriana.ctai.tgsecurity.patterns.model.Cliente;
import co.edu.javeriana.ctai.tgsecurity.patterns.model.User;
import co.edu.javeriana.ctai.tgsecurity.patterns.builder.ClienteBuilder;
import co.edu.javeriana.ctai.tgsecurity.patterns.builder.UserBuilder;
import co.edu.javeriana.ctai.tgsecurity.repository.IUserRepository;
import co.edu.javeriana.ctai.tgsecurity.security.jwt.JwtTokenUtil;
import co.edu.javeriana.ctai.tgsecurity.security.payload.JwtResponse;
import co.edu.javeriana.ctai.tgsecurity.security.payload.MessageResponse;
import co.edu.javeriana.ctai.tgsecurity.security.payload.RegisterRequest;
import co.edu.javeriana.ctai.tgsecurity.service.imp.MailService;
import co.edu.javeriana.ctai.tgsecurity.service.intf.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * Controlador para llevar a cabo la autenticación utilizando JWT
 *
 * Se utiliza AuthenticationManager para autenticar las credenciales que son el
 * usuario y password que llegan por POST en el cuerpo de la petición
 *
 * Si las credenciales son válidas se genera un token JWT como respuesta
 */
// @CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final IUserRepository userRepository;
    @Qualifier("clientServiceImp")
    @Autowired
    private IClientService clientService;

    @Autowired
    private MailService mailService;
    private final PasswordEncoder encoder;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(AuthenticationManager authManager,
                          IUserRepository userRepository,
                          PasswordEncoder encoder,
                          JwtTokenUtil jwtTokenUtil) {
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        byte[] decodedBytes = Base64.getDecoder().decode(authHeader.substring(6));
        String[] credential = new String(decodedBytes).split(":");
        String username = credential[0];
        String password = credential[1];
        // Print the decoded string
        System.out.println(new String(decodedBytes));

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);

        //mailService.sendEmail("soportequickparked@gmail.com", request.);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest signUpRequest) {
        System.out.println(signUpRequest.getPassword());

        // Check 1: username
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        User user = new UserBuilder()
                .setUsername(signUpRequest.getUsername())
                .setPassword(encoder.encode(signUpRequest.getPassword()))
                .build();

        userRepository.save(user);
        if (clientService.existsByCedula(signUpRequest.getIdentification())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Identification is already taken!"));
        }

        if (clientService.existsByCorreoElectronico(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        if (clientService.existsByCelular(signUpRequest.getPhone())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Phone is already taken!"));
        }

        Cliente cliente = new ClienteBuilder()
                .setNombre(signUpRequest.getName())
                .setApellido(signUpRequest.getLastName())
                .setCorreoElectronico(signUpRequest.getEmail())
                .setUsuario(user)
                .setIdentificacion(signUpRequest.getIdentification())
                .setCelular(signUpRequest.getPhone())
                .build();

        clientService.save(cliente);
        mailService.sendEmail("soportequickparked@gmail.com", cliente.getCorreoElectronico(), " TG CTAI FESTO ACCOUNT" +
                " Security", "Bienvenido a TG Security, " + cliente.getNombre() + " " + cliente.getApellido() + "!");
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}

