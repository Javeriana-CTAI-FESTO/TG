package co.edu.javeriana.ctai.tgsecurity.controller.users;

import co.edu.javeriana.ctai.tgsecurity.patterns.model.Cliente;
import co.edu.javeriana.ctai.tgsecurity.patterns.model.Order;
import co.edu.javeriana.ctai.tgsecurity.repository.IOrderRepository;
import co.edu.javeriana.ctai.tgsecurity.service.intf.IClientService;
import co.edu.javeriana.ctai.tgsecurity.service.payload.OrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    private IOrderRepository orderRepository;

    // Queue para guardar ordenes en orden de llegada
    private Queue<Order> orderQueue = new LinkedList<>();
    RestTemplate restTemplate = new RestTemplate();

    //Servicio para guardar ordenes y enviarlas al modulo FESTO
    @PostMapping("/saveOrder")
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
                // Convertir la orden a JSON
                ObjectMapper objectMapper = new ObjectMapper();
                String orderJson = objectMapper.writeValueAsString(order);

                // Crenado los headers para el request
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                // crenado el request con los headers y el JSON
                HttpEntity<String> requestEntity = new HttpEntity<>(orderJson, headers);

                // Enviando el request al modulo FESTO
                ResponseEntity<String> responseEntity = restTemplate.exchange(
                        "FESTO_MODULE_URL_HERE", // Replace with the actual FESTO module URL
                        HttpMethod.POST,
                        requestEntity,
                        String.class
                );

                System.out.println("Procesando order: " + order.getOrderNumber());
                String response = responseEntity.getBody();
                System.out.println("Respuesta de FESTO module: " + response);


            } catch (Exception e) {
                System.out.println("Proceso de Orden Numero: " + order.getOrderNumber());
                e.printStackTrace();
            }
        }
    }

    //Servicio para consultar ordenes segun el numero de cedula del cliente
    @GetMapping("/getOrdersByCedula/{cedula}")
    public ResponseEntity<Object> getOrdersByCedula(@PathVariable("cedula") String cedula) {
        try {
            // Buscar al cliente por su número de cédula
            Cliente cliente = clientService.findByCedula(Long.valueOf(cedula));

            if (cliente == null) {
                System.out.println("Cliente no existe");
                return ResponseEntity.badRequest().build();
            }

            // Luego, puedes buscar todas las órdenes del cliente
            List<Order> ordenes = orderRepository.findByCliente(cliente);
            System.out.println("Enviando Ordenes del cliente: " + cliente.getNombre());

            return ResponseEntity.ok().body(ordenes);

        } catch (NumberFormatException e) {
            // Manejo de errores si la cédula no es un número válido
            System.out.println("Cédula no válida");
            return ResponseEntity.badRequest().build();
        }

    }

    //Servicio para validar tarjeta


}
