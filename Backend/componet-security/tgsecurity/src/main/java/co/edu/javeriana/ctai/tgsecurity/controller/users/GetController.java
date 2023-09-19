package co.edu.javeriana.ctai.tgsecurity.controller.users;

import co.edu.javeriana.ctai.tgsecurity.patterns.model.Cliente;
import co.edu.javeriana.ctai.tgsecurity.patterns.model.Order;
import co.edu.javeriana.ctai.tgsecurity.patterns.model.User;
import co.edu.javeriana.ctai.tgsecurity.repository.IOrderRepository;
import co.edu.javeriana.ctai.tgsecurity.repository.IUserRepository;
import co.edu.javeriana.ctai.tgsecurity.service.intf.IClientService;
import co.edu.javeriana.ctai.tgsecurity.service.payload.OrderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@RestController
@RequestMapping("api/user/get")
public class GetController {

    @Qualifier("clientServiceImp")
    @Autowired
    private IClientService clientService;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IOrderRepository orderRepository;


    // GROUP: Endpoints GET

    /**
     * Obtiene el rol de un usuario por su nombre de usuario.
     *
     * @param user_name Nombre de usuario.
     * @return Rol del usuario en formato JSON.
     */
    @GetMapping("/rol/username={user_name}")
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

    /**
     * Obtiene la cédula de un usuario por su nombre de usuario.
     *
     * @param user_name Nombre de usuario.
     * @return Cédula del usuario en formato JSON.
     */
    @GetMapping("/cedula/username={user_name}")
    public ResponseEntity<String> getUserCedula(@PathVariable String user_name) throws ConnectException {

        if (user_name != null) {

            User user = userRepository.findByUsername(user_name).get();
            System.out.println("Hello user: "+user.getUsername());

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
    @GetMapping("/order/cedula={cedula}")
    public ResponseEntity<Object> getOrdersByCedula(@PathVariable("cedula") String cedula) {
        try {
            // Buscar al cliente por su número de cédula
            Cliente cliente = clientService.findByCedula(Long.valueOf(cedula));

            if (cliente == null) {
                System.out.println("Cliente no existe");
                return ResponseEntity.badRequest().build();
            }

            // Cargar las órdenes de forma ansiosa (eager loading)
            List<Order> ordenes = orderRepository.findByClienteFetchOrders(cliente);

            // Cast to OrderResponse
            List<OrderResponse> orderResponses = new ArrayList<>();
            for (Order order : ordenes) {
                orderResponses.add(new OrderResponse(

                        order.getId_part(),
                        order.getId_workPlan(),
                        order.getCliente().getIdentificacion(),
                        order.getTitle(),
                        order.getOrderNumber()
                ));
            }

            System.out.println("Enviando Ordenes del cliente: " + cliente.getNombre());


            return ResponseEntity.ok().body(orderResponses);

        } catch (NumberFormatException e) {
            // Manejo de errores si la cédula no es un número válido
            System.out.println("Cédula no válida");
            return ResponseEntity.badRequest().build();
        }
    }



}
