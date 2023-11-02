package co.edu.javeriana.ctai.tgsecurity.controller.web.users;


import co.edu.javeriana.ctai.tgsecurity.entities.users.Cliente;
import co.edu.javeriana.ctai.tgsecurity.entities.users.User;
import co.edu.javeriana.ctai.tgsecurity.entities.users.cp_factory.Order;
import co.edu.javeriana.ctai.tgsecurity.repository.interfaces.users.IUserRepository;
import co.edu.javeriana.ctai.tgsecurity.repository.interfaces.users.cp_factory.IOrderRepository;
import co.edu.javeriana.ctai.tgsecurity.services.cp_facrory.impl.payloads.OrderResponse;
import co.edu.javeriana.ctai.tgsecurity.services.interfaces.users.IClientService;
import co.edu.javeriana.ctai.tgsecurity.services.utils.OrderFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.ConnectException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user/get")
public class GetController {

    private static final Logger LOGGER = Logger.getLogger(GetController.class.getName());

    private final IClientService clientService;

    private final IUserRepository userRepository;

    private final IOrderRepository orderRepository;
    private final OrderFilter orderFilter;

    public GetController(@Qualifier("clientServiceImp") IClientService clientService, IUserRepository userRepository, IOrderRepository orderRepository) {
        this.clientService = clientService;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderFilter = new OrderFilter();
    }

    // GROUP: Endpoints GET

    /**
     * Obtiene el rol de un usuario por su nombre de usuario.
     *
     * @param user_name Nombre de usuario.
     * @return Rol del usuario en formato JSON.
     */
    @Operation(summary = "Get user role by username", responses = {
            @ApiResponse(responseCode = "200", description = "Reportes obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    }, description = "Obtiene el rol de un usuario por su nombre de usuario.")
    @Parameter(name = "user_name", description = "Nombre de usuario", required = true)
    @GetMapping("/rol/username={user_name}")
    public ResponseEntity<String> getUserRole(@PathVariable String user_name) {

        if (user_name != null) {

            User user = userRepository.findByUsername(user_name).get();

            Cliente cliente = clientService.findByUsuario(user);


            if (cliente != null) {
                // El objeto cliente no es nulo, puedes acceder a sus propiedades
                System.out.println("Cliente: " + cliente.getNombre());

                // Determine the role
                String role = getRole(cliente);

                LOGGER.info("Rol: " + role);

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

    private static String getRole(Cliente cliente) {
        String role;
        if (cliente.getRol().name().contains("ADMIN")) {
            role = "ADMIN";
        } else if (cliente.getRol().name().contains("TEACHER")) {
            role = "TEACHER";
        } else if (cliente.getRol().name().contains("STUDENT")) {
            role = "STUDENT";
        } else if (cliente.getRol().name().contains("SHOPPER")) {
            role = "SHOPPER";
        } else {
            role = "UNKNOWN";
        }
        return role;
    }

    /**
     * Obtiene la cédula de un usuario por su nombre de usuario.
     *
     * @param user_name Nombre de usuario.
     * @return Cédula del usuario en formato JSON.
     */
    @Operation(summary = "Get user cedula by username", responses = {
            @ApiResponse(responseCode = "200", description = "Cédula obtenida satisfactoriamente", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    }, description = "Obtiene la cédula de un usuario por su nombre de usuario.")
    @Parameter(name = "nombre", description = "Nombre de usuario", required = true)

    @GetMapping("/cedula/username={user_name}")
    public ResponseEntity<String> getUserCedula(@PathVariable String user_name) throws ConnectException {

        if (user_name != null) {

            User user = userRepository.findByUsername(user_name).get();
            System.out.println("Hello user: " + user.getUsername());

            Cliente cliente = clientService.findByUsuario(user);

            if (cliente != null) {
                // El objeto cliente no es nulo, puedes acceder a sus propiedades

                System.out.println("Cliente: " + cliente.getNombre() + " Cc: " + cliente.getIdentificacion());

                // Return a JSON response with the identification
                return ResponseEntity.ok().body("{\"cedula\":\"" + cliente.getIdentificacion() + "\"}");
            } else {
                // Return a 404 response if the client was not found
                return ResponseEntity.notFound().build();
            }

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("/username={user_name}");
    }

    /**
     * Obtiene las órdenes de un cliente por su número de cédula.
     *
     * @param cedula Número de cédula del cliente.
     * @return Lista de órdenes en formato JSON.
     */
    @Operation(summary = "Obtener órdenes por cédula",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Órdenes obtenidas satisfactoriamente",
                            content = @Content(schema = @Schema(implementation = OrderResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Cédula no válida")
            },
            description = "Obtiene las órdenes de un cliente por su número de cédula.")
    @GetMapping("/order/cedula={cedula}")
    public ResponseEntity<Object> getOrdersByCedula(@PathVariable("cedula") String cedula) {
        try {
            // Buscar al cliente por su número de cédula
            Cliente cliente = clientService.findByCedula(Long.valueOf(cedula));

            if (cliente == null) {
                System.out.println("Cliente no existe");
                return ResponseEntity.badRequest().body("Cliente no existe");
            }

            // Cargar las órdenes de forma ansiosa (eager loading)
            List<Order> eagerLoadedOrders = orderRepository.findByClienteFetchOrders(cliente);

            // Obtener las órdenes del MES
            List<Long> mesOrders = this.orderFilter.getMESOrders();
            // ordenar la lista de números de orden


            // Filtrar las órdenes del cliente que coinciden con las órdenes del MES
            List<Order> recentClientOrders = eagerLoadedOrders.stream()
                    .filter(order -> mesOrders.contains(order.getOrderNumber()))
                    .collect(Collectors.toList());

            // Mapear las órdenes a OrderResponse utilizando expresiones lambda
            List<OrderResponse> orderResponses = recentClientOrders.stream()
                    .map(order -> new OrderResponse(
                            order.getId_part(),
                            order.getId_workPlan(),
                            order.getCliente().getIdentificacion(),
                            order.getTitle(),
                            order.getOrderNumber(),
                            order.getImage_filePath()
                    ))
                    .collect(Collectors.toList());

            System.out.println("Ordenes Filtradas para: " + cliente.getNombre());
            System.out.println(orderResponses);

            return ResponseEntity.ok().body(orderResponses);


        } catch (NumberFormatException e) {
            // Manejo de errores si la cédula no es un número válido
            LOGGER.info("Cédula no válida");
            return ResponseEntity.badRequest().body("Cédula no válida");
        }
    }

}
