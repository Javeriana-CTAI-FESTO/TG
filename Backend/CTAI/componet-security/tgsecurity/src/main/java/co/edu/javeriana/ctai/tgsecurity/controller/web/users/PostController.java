package co.edu.javeriana.ctai.tgsecurity.controller.web.users;

import co.edu.javeriana.ctai.tgsecurity.entities.Order;
import co.edu.javeriana.ctai.tgsecurity.services.IClientService;
import co.edu.javeriana.ctai.tgsecurity.services.external.OrderProcessingService;
import co.edu.javeriana.ctai.tgsecurity.services.external.payloads.OrderRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.util.logging.Logger;

@RestController
@RequestMapping("api/user/post")
public class PostController {

    private static final Logger LOGGER = Logger.getLogger(PostController.class.getName());

    @Qualifier("clientServiceImp")
    @Autowired
    private IClientService clientService;

    @Autowired
    private OrderProcessingService orderProcessingService;

    @Operation(summary = "Guardar Orden", responses = {
            @ApiResponse(responseCode = "200", description = "Orden guardada satisfactoriamente", content = @Content(schema = @Schema(implementation = OrderRequest.class)),
                    headers = {@io.swagger.v3.oas.annotations.headers.Header(name = "X-Order-Number", description = "Número de orden", schema = @Schema(type = "integer"))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Guarda una orden en el sistema")
    @PostMapping("/save/order")
    public ResponseEntity<OrderRequest> saveOrder(@RequestBody OrderRequest orderRequest) {
        String response = null;
        try {
            if (orderRequest == null) {
                System.out.println("El objeto es nulo");
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
            order.setImage_filePath(orderRequest.getImage_filePath());

            // Nueva ordenResponse
            OrderRequest orderResponse = new OrderRequest();
            orderResponse.setOrderNumber(0);
            orderResponse.setTitle(order.getTitle());
            orderResponse.setCliente_Cedula(order.getCliente().getIdentificacion());
            orderResponse.setId_part(order.getId_part());
            orderResponse.setId_workPlan(order.getId_workPlan());
            orderResponse.setImage_filePath(order.getImage_filePath());

            // Llama al servicio externo de FESTO para obtener el número de orden
            orderProcessingService.enqueueOrder(order);

            // Espera hasta que el número de orden esté disponible
            while (order.getOrderNumber() == 0) {
                LOGGER.info("Esperando número de orden...");
                Thread.sleep(1000);
            }
            int orderNumber = orderProcessingService.getOrderNumber();

            orderResponse.setOrderNumber(orderNumber);
            return ResponseEntity.ok().body(orderResponse);


        } catch (RestClientException ex) {
            // Captura excepciones de problemas de red o conexión
            LOGGER.info("Error al guardar la orden");
            LOGGER.warning(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Puedes ajustar el código de estado según corresponda
        } catch (Exception e) {
            // Captura otras excepciones generales
            LOGGER.info("Error al guardar la orden");
            LOGGER.warning(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }
}

