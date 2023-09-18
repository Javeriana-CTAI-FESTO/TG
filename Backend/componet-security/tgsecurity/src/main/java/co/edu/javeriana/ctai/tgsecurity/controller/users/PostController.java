package co.edu.javeriana.ctai.tgsecurity.controller.users;

import co.edu.javeriana.ctai.tgsecurity.patterns.model.Order;
import co.edu.javeriana.ctai.tgsecurity.repository.IOrderRepository;
import co.edu.javeriana.ctai.tgsecurity.repository.IUserRepository;
import co.edu.javeriana.ctai.tgsecurity.service.external.OrderProcessingService;
import co.edu.javeriana.ctai.tgsecurity.service.intf.IClientService;
import co.edu.javeriana.ctai.tgsecurity.service.payload.OrderRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;
import java.util.LinkedList;
import java.util.Queue;

@RestController
@RequestMapping("api/user/post")
public class PostController {

    @Qualifier("clientServiceImp")
    @Autowired
    private IClientService clientService;

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private OrderProcessingService orderProcessingService;

    // GROUP: Endpoint POST

    /**
     * Guarda una nueva orden y la envía al módulo FESTO.
     *
     * @param orderRequest Datos de la orden a guardar.
     * @return Datos de la orden guardada en formato JSON.
     * @throws ConnectException Si hay un error de conexión con el módulo FESTO.
     */
    @PostMapping("/save/order")
    public ResponseEntity<OrderRequest> saveOrder(@RequestBody OrderRequest orderRequest) throws ConnectException {

        if(orderRequest == null ){
            System.out.println("OrderRequest is null");
            return ResponseEntity.badRequest().build();
        }

        // 1 Validar que el usuario exista y tenga permisos para crear ordenes
        if(!clientService.existsByCedula(orderRequest.getCliente_Cedula())){
            System.out.println("Cliente no existe");
            return ResponseEntity.badRequest().build();
        }

        // 2 Crear orden y guardarla en la base de datos
        Order order = new Order();
        order.setOrderNumber(0);
        order.setTitle(orderRequest.getTitle());
        order.setCliente(clientService.findByCedula(orderRequest.getCliente_Cedula()));
        order.setId_part(orderRequest.getId_part());
        order.setId_workPlan(orderRequest.getId_workPlan());

        // 3 Llama al servicio para encolar la orden
        orderProcessingService.enqueueOrder(order);
        String response = orderProcessingService.processOrders();

        if(response == null){
            System.out.println("La Cola esta vacia");
            return ResponseEntity.badRequest().build();
        }

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        int orderNumber = jsonObject.get("orderNumber").getAsInt();
        order.setOrderNumber(orderNumber);
        orderRequest.setOrderNumber(orderNumber);

        // 5. Guardar la orden en la base de datos
        orderRepository.save(order);
        System.out.println("Orden numero: " + order.getOrderNumber() + " guardada en la base de datos");

        return ResponseEntity.ok().body(orderRequest);

    }

}
