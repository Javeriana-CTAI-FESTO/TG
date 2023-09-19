package co.edu.javeriana.ctai.tgsecurity.controller.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException; // Importa la excepción específica de Spring

import co.edu.javeriana.ctai.tgsecurity.patterns.model.Order;
import co.edu.javeriana.ctai.tgsecurity.repository.IOrderRepository;
import co.edu.javeriana.ctai.tgsecurity.service.external.OrderProcessingService;
import co.edu.javeriana.ctai.tgsecurity.service.intf.IClientService;
import co.edu.javeriana.ctai.tgsecurity.service.payload.OrderRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static java.lang.Thread.sleep;

@RestController
@RequestMapping("api/user/post")
public class PostController {

    @Qualifier("clientServiceImp")
    @Autowired
    private IClientService clientService;

    @Autowired
    private OrderProcessingService orderProcessingService;

    @PostMapping("/save/order")
    public ResponseEntity<OrderRequest> saveOrder(@RequestBody OrderRequest orderRequest) {
        String response = null;
        try {
            if (orderRequest == null) {
                return ResponseEntity.badRequest().build();
            }

            if (!clientService.existsByCedula(orderRequest.getCliente_Cedula())) {
                return ResponseEntity.badRequest().build();
            }

            Order order = new Order();
            order.setOrderNumber(0);
            order.setTitle(orderRequest.getTitle());
            order.setCliente(clientService.findByCedula(orderRequest.getCliente_Cedula()));
            order.setId_part(orderRequest.getId_part());
            order.setId_workPlan(orderRequest.getId_workPlan());

            // Llama al servicio externo de FESTO para obtener el número de orden
            orderProcessingService.enqueueOrder(order);
            orderRequest.setOrderNumber(orderProcessingService.getOrderNumber() + 1);

            
            return ResponseEntity.ok().body(orderRequest);

        } catch (RestClientException ex) {
            // Captura excepciones de problemas de red o conexión
            ex.printStackTrace(); // Manejo de errores específicos
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Puedes ajustar el código de estado según corresponda
        } catch (Exception e) {
            // Captura otras excepciones generales
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}

