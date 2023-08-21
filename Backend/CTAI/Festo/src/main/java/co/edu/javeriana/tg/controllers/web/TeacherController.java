package co.edu.javeriana.tg.controllers.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.tg.entities.auxiliary.CreateWorkPlanAux;
import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.dtos.OperationDTO;
import co.edu.javeriana.tg.entities.dtos.OrderDTO;
import co.edu.javeriana.tg.entities.dtos.PartDTO;
import co.edu.javeriana.tg.entities.dtos.PartsConsumedByOrderDTO;
import co.edu.javeriana.tg.entities.dtos.ResourceDTO;
import co.edu.javeriana.tg.entities.dtos.ResourceForOperationDTO;
import co.edu.javeriana.tg.entities.dtos.StepDefinitionDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanWithStepsDTO;
import co.edu.javeriana.tg.services.users.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // View Stock Parts in the system
    @Operation(summary = "Get all parts available", responses = {
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
            }),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "502", description = "Bad Gateway", content = {
                    @Content
            })
    }, description = "Retorna una lista de tipo PartDTO, donde se encuentran todas las partes disponibles en el sistema para la producción")
    @GetMapping("/parts/available")
    public ResponseEntity<List<PartDTO>> getAllPartsAvailable() {
        List<PartDTO> reports = null;
        HttpStatus status = HttpStatus.OK;
        try {
            reports = teacherService.getAllPartsAvailable();
            if (reports.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<PartDTO>>(reports, status);
    }

    @Operation(summary = "Get all parts unavailable", responses = {
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
            }),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "502", description = "Bad Gateway", content = {
                    @Content
            })
    }, description = "Retorna una lista de tipo PartDTO, donde se encuentran todas las partes no disponibles en el sistema para la producción")
    @GetMapping("/parts/unavailable")
    public ResponseEntity<List<PartDTO>> getAllPartsUnavailable() {
        List<PartDTO> reports = null;
        HttpStatus status = HttpStatus.OK;
        try {
            reports = teacherService.getAllPartsUnavailable();
            if (reports.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<PartDTO>>(reports, status);
    }

    @Operation(summary = "Get all parts", responses = {
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
            }),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "502", description = "Bad Gateway", content = {
                    @Content
            })
    }, description = "Retorna una lista de tipo PartDTO, donde se encuentran todas las partes del sistema")
    @GetMapping("/parts")
    public ResponseEntity<List<PartDTO>> getAllParts() {
        List<PartDTO> reports = null;
        HttpStatus status = HttpStatus.OK;
        try {
            reports = teacherService.getAllParts();
            if (reports.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<PartDTO>>(reports, status);
    }

    @Operation(summary = "Get all parts available", responses = {
            @ApiResponse(responseCode = "200", description = "Tipos de partes obtenidos satsisfactoriamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))
            }),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "502", description = "Bad Gateway", content = {
                    @Content
            })
    }, description = "Obtiene los tipos de partes existentes, retorna un Map<Long, String> donde se encuentran los tipos de partes disponibles en el sistema")
    @GetMapping("/parts/type")
    public ResponseEntity<Map<Long, String>> getAllPartsType() {
        Map<Long, String> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = teacherService.getAllPartsTypes();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<Long, String>>(workPlans, status);
    }

    @Operation(summary = "Get all parts by type", responses = {
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
            }),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "502", description = "Bad Gateway", content = {
                    @Content
            })
    }, description = "Dado un id de tipo de parte, retorna una lista de todas partes (PartDTO) que cumplan con ese tipo de parte")
    @Parameter(description = "Id del tipo de parte")
    @GetMapping("/parts/type/{typeId}")
    public ResponseEntity<List<PartDTO>> getAllPartsByType(@PathVariable Long typeId) {
        List<PartDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = teacherService.getAllPartsByType(typeId);
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<PartDTO>>(workPlans, status);
    }

    @Operation(summary = "Get parts can be produced", responses = {
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
            }),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "502", description = "Bad Gateway", content = {
                    @Content
            })
    }, description = "Retorna una lista de tipo PartDTO, donde se encuentran todas las partes que pueden ser producidas")
    @GetMapping("/parts/production")
    public ResponseEntity<List<PartDTO>> getProduceableParts() {
        List<PartDTO> resources = null;
        HttpStatus status = HttpStatus.OK;
        try {
            resources = teacherService.getPartsThatCanBeProduced();
            if (resources.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<PartDTO>>(resources, status);
    }
    //

    // View product plans

    @Operation(summary = "Get all workPlans", responses = {
            @ApiResponse(responseCode = "200", description = "WorkPlans obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = WorkPlanDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    }, description = "Retorna una lista con todos los workPlans (workPlanDTO) en el sistema")
    @GetMapping("/work-plans")
    public ResponseEntity<List<WorkPlanDTO>> getAllWorkPlans() {
        List<WorkPlanDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = teacherService.getAllWorkPlans();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<WorkPlanDTO>>(workPlans, status);
    }

    @Operation(summary = "Get workPlan by id", responses = {
            @ApiResponse(responseCode = "200", description = "WorkPlan obtenido satisfactoriamente", content = @Content(schema = @Schema(implementation = WorkPlanWithStepsDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    }, description = "Retorna un workPlan (workPlanWithStepsDTO) y un status OK, dado un id de workPlan como parametro")
    @Parameter(description = "Id del workPlan")
    @GetMapping("/work-plans/{id}")
    public ResponseEntity<WorkPlanWithStepsDTO> getWorkPlansById(@PathVariable Long id) {
        WorkPlanWithStepsDTO workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = teacherService.getWorkPlanById(id);
            if (workPlans == null)
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<WorkPlanWithStepsDTO>(workPlans, status);
    }

    @Operation(summary = "Get all workPlans types", responses = {
            @ApiResponse(responseCode = "200", description = "Tipos de workPlans obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    }, description = "Retorna una lista con todos los tipos de workPlans (Map<Long, String>) en el sistema")
    @GetMapping("/work-plans/type")
    public ResponseEntity<Map<Long, String>> getAllWorkPlanTypes() {
        Map<Long, String> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = teacherService.getAllWorkPlansTypes();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<Long, String>>(workPlans, status);
    }

    @Operation(summary = "Get workPlans by type", responses = {
            @ApiResponse(responseCode = "200", description = "WorkPlans obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = WorkPlanDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    }, description = "Retorna una lista con todos los workPlans (workPlanDTO) de un tipo dado como parametro")
    @Parameter(description = "Id del tipo de workPlan")
    @GetMapping("/work-plans/type/{typeId}")
    public ResponseEntity<List<WorkPlanDTO>> getAllWorkPlansByType(@PathVariable Long typeId) {
        List<WorkPlanDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = teacherService.getWorkPlansByType(typeId);
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<WorkPlanDTO>>(workPlans, status);
    }
    //

    // View defined steps
    @Operation(summary = "Get all defined steps", responses = {
            @ApiResponse(responseCode = "200", description = "Pasos definidos obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = StepDefinitionDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    }, description = "Retorna una lista con todos los pasos definidos (stepDefinitionDTO) en el sistema")
    @GetMapping("/steps")
    public ResponseEntity<List<StepDefinitionDTO>> getAllDefinedSteps() {
        List<StepDefinitionDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = teacherService.getAllStepsDefined();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<StepDefinitionDTO>>(workPlans, status);
    }

    // View machines
    @Operation(summary = "Get all Resources", responses = {
            @ApiResponse(responseCode = "200", description = "Recursos obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = ResourceDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    }, description = "Retorna una lista con todos los recursos (resourceDTO) en el sistema")
    @GetMapping("/resources")
    public ResponseEntity<List<ResourceDTO>> getAllResources() {
        List<ResourceDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = teacherService.getAllResources();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<ResourceDTO>>(workPlans, status);
    }

    // View defined operations
    @Operation(summary = "Get all operations", responses = {
            @ApiResponse(responseCode = "200", description = "Operaciones obtenidas satisfactoriamente", content = @Content(schema = @Schema(implementation = OperationDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    }, description = "Retorna una lista con todas las operaciones (operationDTO) en el sistema")
    @GetMapping("/operations")
    public ResponseEntity<List<OperationDTO>> getAllOperations() {
        List<OperationDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = teacherService.getAllOperations();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<OperationDTO>>(workPlans, status);
    }

    @Operation(summary = "Get operations by resource", responses = {
            @ApiResponse(responseCode = "200", description = "Operaciones obtenidas satisfactoriamente", content = @Content(schema = @Schema(implementation = ResourceForOperationDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    }, description = "Retorna una lista con todas las operaciones (resourceForOperationDTO) de un recurso dado como parametro")
    @Parameter(description = "Id del recurso")
    @GetMapping("/operations/{resource}")
    public ResponseEntity<List<ResourceForOperationDTO>> getOperationsByResource(@PathVariable Long resource) {
        List<ResourceForOperationDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = teacherService.getOperationsForResource(resource);
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<ResourceForOperationDTO>>(workPlans, status);
    }

    // Create product plans
    @Operation(summary = "Create workPlan", responses = {
            @ApiResponse(responseCode = "200", description = "WorkPlan creado satisfactoriamente", content = @Content(schema = @Schema(implementation = WorkPlanDTO.class))),
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content)

    }, description = "Crea un nuevo workPlan, retorna un WorkPlanDTO y un status 200 si todo ha ido bien")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Estructura de información de un WorkPlanDTOAux, para ser utilizado como parámetro y crear un WorkPlanDTO nuevo", required = true)
    @Parameter(name = "createRequest", description = "WorkPlanDTO auxiliar para ser creado, es requerido", schema = @Schema(implementation = CreateWorkPlanAux.class))
    @PostMapping("/work-plans")
    public ResponseEntity<WorkPlanDTO> createWorkPlan(@RequestBody CreateWorkPlanAux createRequest) {
        WorkPlanDTO workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = teacherService.createWorkPlan(createRequest);
            if (workPlans == null)
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<WorkPlanDTO>(workPlans, status);
    }
    //

    // View product orders
    @Operation(summary = "Get all orders with status", responses = {
            @ApiResponse(responseCode = "200", description = "Ordenes obtenidas satisfactoriamente", content = @Content(schema = @Schema(implementation = OrderDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    }, description = "Retorna una lista con todas las ordenes (OrderDTO) en el sistema con un determinado estado")
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrdersStatus() {
        List<OrderDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = teacherService.getOrdersWithStatus();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<OrderDTO>>(workPlans, status);
    }

    @Operation(summary = "Get orders planned ends", responses = {
            @ApiResponse(responseCode = "200", description = "Fechas de fin planificadas obtenidas satisfactoriamente", content = @Content(schema = @Schema(implementation = Date.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    }, description = "Retorna una lista con todas las fechas (Date)de las ordernes finalizadas")
    @GetMapping("/orders/ends")
    public ResponseEntity<List<Date>> getAllPlannedEnds() {
        List<Date> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = teacherService.getAllOrdersPlannedEnds();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<Date>>(workPlans, status);
    }

    @Operation(summary = "Get status of orders", responses = {
            @ApiResponse(responseCode = "200", description = "Status obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    }, description = "Retorna un Map<Long, String> con las ordenes con un posible status")
    @GetMapping("/orders/status")
    public ResponseEntity<Map<Long, String>> getStatus() {
        Map<Long, String> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = teacherService.getOrdersPossibleStatus();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<Long, String>>(workPlans, status);
    }

    @Operation(summary = "Get orders by status", responses = {
            @ApiResponse(responseCode = "200", description = "Orders obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = OrderDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    }, description = "Retorna una lista con los WorkPlans por status (orderStatus) en el sistema dado un orderStatus como parametro")
    @Parameter(description = "Order status")
    @GetMapping("/orders/status/{orderStatus}")
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@PathVariable Long orderStatus) {
        List<OrderDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = teacherService.filterOrdersByStatus(orderStatus);
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<OrderDTO>>(workPlans, status);
    }

    @Operation(summary = "Enable order production", description = "Dado un numero de orden, el metodo permite habilitar la orden para ser producida, actualiza la orden en el sistema y retorna la orden actualizada, retorna una OrdenDTO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden habilitada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class)) }),
            @ApiResponse(responseCode = "204", description = "Orden no encontrada", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "405", description = "Invalid Input", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error en el servidor", content = @Content)
    })
    @Parameter(description = "Numero de orden para habilitar su producción", example = "1")
    @PutMapping("/orders/{orderNumber}")
    public ResponseEntity<OrderDTO> enableOrder(@PathVariable Long orderNumber) {
        OrderDTO workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = teacherService.enableOrder(orderNumber);
            if (workPlans == null)
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<OrderDTO>(workPlans, status);
    }

    @Operation(summary = "Get all parts consumed by orders", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de partes consumidas por ordenes", content = @Content(schema = @Schema(implementation = PartsConsumedByOrderDTO.class))),
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    }, description = "Retorna una lista con todas las partes consumidas por ordenes (PartsConsumedByOrderDTO) en el sistema")
    @GetMapping("/orders/parts")
    public ResponseEntity<List<PartsConsumedByOrderDTO>> getAllPartsConsumedByOrders() {
        List<PartsConsumedByOrderDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = teacherService.getAllPartsConsumedByOrders();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<PartsConsumedByOrderDTO>>(workPlans, status);
    }
    //

    // Calculate production time
    @Operation(summary = "Get orders with time", responses = {
            @ApiResponse(responseCode = "200", description = "Orders with time satisfactoriamente obtenidos", content = @Content(schema = @Schema(implementation = OrderDTO.class))),
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    }, description = "Calcula el tiempo de producción de una orden, retorna un una lista OrderDTO con los tiempos de producción")
    @GetMapping("/orders/time")
    public ResponseEntity<List<OrderDTO>> getAllOrdersTime() {
        List<OrderDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = teacherService.getOrdersWithTime();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<OrderDTO>>(workPlans, status);
    }

    // View indicators
    @Operation(summary = "Get all indicators", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de indicadores obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = IndicatorAux.class))),
            @ApiResponse(responseCode = "204", description = "No content", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    }, description = "Retorna una lista con todos los indicadores (IndicatorAux) en el sistema, obtiene los indicadores de producción")
    @GetMapping("/indicators")
    public ResponseEntity<List<IndicatorAux>> getAllIndicators() {
        List<IndicatorAux> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = teacherService.getProductionIndicators();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<IndicatorAux>>(workPlans, status);
    }
    //
}
