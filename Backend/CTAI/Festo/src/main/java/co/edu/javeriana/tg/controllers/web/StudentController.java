package co.edu.javeriana.tg.controllers.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.dtos.OrderDTO;
import co.edu.javeriana.tg.entities.dtos.PartDTO;
import co.edu.javeriana.tg.services.users.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // This is what the user can produce
    @Operation(summary = "Get all parts that can be produced", responses = {
            @ApiResponse(responseCode = "200", description = "Partes obtenidas satsisfactoriamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PartDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content
            })
    }, description = "Obtiene todas las partes que se pueden producir, retorna una lista de partes (PartDTO)")
    @GetMapping("/parts/production")
    public ResponseEntity<List<PartDTO>> getProduceableParts() {
        List<PartDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = studentService.getPartsThatCanBeProduced();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<PartDTO>>(workPlans, status);
    }

    // The user can generate a new order based on the availability, its recommended
    // to check what user can do before
    @Operation(summary = "Generate a new order", responses = {
            @ApiResponse(responseCode = "200", description = "Orden generada satisfactoriamente", content = @Content(schema = @Schema(implementation = OrderDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "400", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Genera una nueva orden (OrderDTO) en produccion dado un numero de parte(partNumber), un numero de cliente (clientNumber) y un numero de posiciones(positions)")
    @Parameter(name = "partNumber", description = "Long : Numero de parte", required = true, example = "1")
    @Parameter(name = "clientNumber", description = "Long : Numero de cliente", required = true, example = "20271125")
    @Parameter(name = "positions", description = "Long : Numero de posiciones", required = true, example = "1")
    @PostMapping("/parts/production/new-order")
    public ResponseEntity<OrderDTO> newOrder(@RequestParam Long partNumber, @RequestParam Long clientNumber,
            @RequestParam Long positions) {
        OrderDTO workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = studentService.generateNewOrder(partNumber, clientNumber, positions);
            if (workPlans == null)
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<OrderDTO>(workPlans, status);
    }
    //

    // The user can view what is being produced
    @Operation(summary = "Get all orders with status", responses = {
            @ApiResponse(responseCode = "200", description = "Ordenes obtenidas satisfactoriamente", content = @Content(schema = @Schema(implementation = OrderDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Retorna una lista de todas ordenes (OrderDTO) en el sistema")
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrdersWithStatus() {
        List<OrderDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = studentService.getOrdersWithStatus();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<OrderDTO>>(workPlans, status);
    }

    @Operation(summary = "Get orders planned ends", responses = {
            @ApiResponse(responseCode = "200", description = "Fechas de ordenes obtenidas satisfactoriamente", content = @Content(schema = @Schema(implementation = Date.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "400", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)

    }, description = "Retorna una lista con todas las fechas (Date)de las ordenes finalizadas")
    @GetMapping("/orders/ends")
    public ResponseEntity<List<Date>> getAllPlannedEnds() {
        List<Date> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = studentService.getAllOrdersPlannedEnds();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<Date>>(workPlans, status);
    }

    @Operation(summary = "Get status of orders", responses = {
            @ApiResponse(responseCode = "200", description = "Status of orders obtained successfully", content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Retorna una lista con todos los estados de ordenes posibles, retorna un Map<Long, String> con las ordenes con un posible status")
    @GetMapping("/orders/status")
    public ResponseEntity<Map<Long, String>> getStatus() {
        Map<Long, String> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = studentService.getOrdersPossibleStatus();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<Long, String>>(workPlans, status);
    }

    @Operation(summary = "Get orders by status", responses = {
            @ApiResponse(responseCode = "200", description = "Orders obtained successfully", content = @Content(schema = @Schema(implementation = OrderDTO.class))),
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Retorna una lista con todas las ordenes (OrderDTO) filtradas por un status como parametro")
    @Parameter(name = "orderStatus", description = "Estado de orden", required = true, example = "1")
    @GetMapping("/orders/status/{orderStatus}")
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@PathVariable Long orderStatus) {
        List<OrderDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = studentService.filterOrdersByStatus(orderStatus);
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<OrderDTO>>(workPlans, status);
    }
    //

    // Calculate production time
    @Operation(summary = "Get all orders with time", responses = {
            @ApiResponse(responseCode = "200", description = "Tiempo de producción obtenido satisfactoriamente", content = @Content(schema = @Schema(implementation = PartDTO.class))),
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Se obtienen todas las ordenes (OrderDTO) con su tiempo de producción, retorna una lista <OrderDTO>")
    @GetMapping("/orders/time")
    public ResponseEntity<List<OrderDTO>> getAllOrdersTime() {
        List<OrderDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = studentService.getOrdersWithTime();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<OrderDTO>>(workPlans, status);
    }
    //

    // View perfomance indicators
    @Operation(summary = "Get performance indicators", responses = {
            @ApiResponse(responseCode = "200", description = "Indicators obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = IndicatorAux.class))),
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Retorna una lista con todos los indicadores (IndicatorAux) en el sistema, obtiene los indicadores de producción")

    @GetMapping("/indicators")
    public ResponseEntity<List<IndicatorAux>> getAllIndicators() {
        List<IndicatorAux> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = studentService.getProductionIndicators();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<IndicatorAux>>(workPlans, status);
    }
    //

}
