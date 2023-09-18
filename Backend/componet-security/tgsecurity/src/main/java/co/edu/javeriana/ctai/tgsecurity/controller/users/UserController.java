package co.edu.javeriana.ctai.tgsecurity.controller.users;

import co.edu.javeriana.ctai.tgsecurity.patterns.model.Cliente;
import co.edu.javeriana.ctai.tgsecurity.patterns.model.Order;
import co.edu.javeriana.ctai.tgsecurity.patterns.model.User;
import co.edu.javeriana.ctai.tgsecurity.repository.IOrderRepository;
import co.edu.javeriana.ctai.tgsecurity.repository.IUserRepository;
import co.edu.javeriana.ctai.tgsecurity.service.intf.IClientService;
import co.edu.javeriana.ctai.tgsecurity.service.payload.OrderRequest;
import co.edu.javeriana.ctai.tgsecurity.service.payload.OrderResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Qualifier("clientServiceImp")
    @Autowired
    private IClientService clientService;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IOrderRepository orderRepository;

    // Queue para guardar ordenes en orden de llegada
    private Queue<Order> orderQueue = new LinkedList<>();
    RestTemplate restTemplate = new RestTemplate();

    // Get Rol by user
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

    // Get identification by user
    @GetMapping("/cedula/username={user_name}")
    public ResponseEntity<String> getUserCedula(@PathVariable String user_name) {

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

    //Servicio para guardar ordenes y enviarlas al modulo FESTO
    @PostMapping("/save/order")
    public ResponseEntity saveOrder(@RequestBody OrderRequest orderRequest) {

        //TODO: Validar que el usuario exista y tenga permisos para crear ordenes

        if(orderRequest == null ){
            System.out.println("OrderRequest is null");
            return ResponseEntity.badRequest().build();
        }

        // -1 Validar que el usuario exista y tenga permisos para crear ordenes
        if(!clientService.existsByCedula(orderRequest.getCliente_Cedula())){
            System.out.println("Cliente no existe");
            return ResponseEntity.badRequest().build();
        }

        // -2 Crear orden y guardarla en la base de datos
        Order order = new Order();
        order.setOrderNumber(orderRequest.getOrderNumber());
        order.setTitle(orderRequest.getTitle());
        order.setCliente(clientService.findByCedula(orderRequest.getCliente_Cedula()));
        order.setId_part(orderRequest.getId_part());
        order.setId_workPlan(orderRequest.getId_workPlan());

        orderRepository.save(order);
        if (orderQueue.offer(order)) {
            // Éxito: La orden se encoló correctamente
            System.out.println("Order saved: " + order.getOrderNumber());
        } else {
            // Fallo: La cola está llena, no se pudo encolar la orden
            System.out.println("La cola está llena, no se pudo encolar la orden");
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();

    }

    @Scheduled(fixedRate = 5000) // 5 segundos
    public void processOrders() {

        while (!orderQueue.isEmpty()) {
            Order order = orderQueue.poll();

            try {

                String url = "http://localhost:8080/api/students/parts/production/new-order?partNumber=" + order.getOrderNumber()
                        + "&clientNumber=0&positions=1";

                // Realiza la solicitud POST con la URL construida
                ResponseEntity<String> responseEntity = restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        null, // No se envía un cuerpo en este caso
                        String.class
                );


                System.out.println("Procesando order: " + order.getOrderNumber());
                String response = responseEntity.getStatusCode().toString();
                System.out.println("Respuesta de FESTO module: " + response);


            } catch (Exception e) {
                System.out.println("Proceso de Orden Numero: " + order.getOrderNumber());
                e.printStackTrace();
            }
        }
    }

    //Servicio para consultar ordenes segun el numero de cedula del cliente
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
