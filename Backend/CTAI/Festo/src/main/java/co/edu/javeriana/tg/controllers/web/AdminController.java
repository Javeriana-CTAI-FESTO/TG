package co.edu.javeriana.tg.controllers.web;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.tg.entities.auxiliary.CreatePartAux;
import co.edu.javeriana.tg.entities.auxiliary.CreateWorkPlanAux;
import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.dtos.ClientDTO;
import co.edu.javeriana.tg.entities.dtos.MachineReportDTO;
import co.edu.javeriana.tg.entities.dtos.OperationDTO;
import co.edu.javeriana.tg.entities.dtos.OrderDTO;
import co.edu.javeriana.tg.entities.dtos.PartDTO;
import co.edu.javeriana.tg.entities.dtos.PartsConsumedByOrderDTO;
import co.edu.javeriana.tg.entities.dtos.ReportDTO;
import co.edu.javeriana.tg.entities.dtos.ResourceDTO;
import co.edu.javeriana.tg.entities.dtos.ResourceForOperationDTO;
import co.edu.javeriana.tg.entities.dtos.StepDefinitionDTO;
import co.edu.javeriana.tg.entities.dtos.StepTimeDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanWithStepsDTO;
import co.edu.javeriana.tg.services.users.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // View machines
    @Operation(summary = "Get all Resources", responses = {
            @ApiResponse(responseCode = "200", description = "Recursos obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = ResourceDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    }, description = "Retorna una lista con todos los recursos (resourceDTO) en el sistema")
    @GetMapping("/resources")
    public ResponseEntity<List<ResourceDTO>> getAllResources() {
        HttpStatus status = HttpStatus.OK;
        List<ResourceDTO> reports = null;
        try {
            reports = adminService.getAllResources();
            if (reports.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<ResourceDTO>>(reports, status);
    }

    // View machines status
    @Operation(summary = "Get all Reports", responses = {
            @ApiResponse(responseCode = "200", description = "Reportes obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = MachineReportDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
    }, description = "Retorna una lista de todos los reportes de maquinas (MachineReportDTO)")
    @GetMapping("/reports")
    public ResponseEntity<List<MachineReportDTO>> getAllReports() {
        HttpStatus status = HttpStatus.OK;
        List<MachineReportDTO> reports = null;
        try {
            reports = adminService.getAllReports();
            if (reports.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<MachineReportDTO>>(reports, status);
    }

    @Operation(summary = "Get reports by machine", responses = {
            @ApiResponse(responseCode = "200", description = "Reportes obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = ReportDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Este metodo dado un resourceID (Id de recurso), retorna una lista de reportes (ReportDTO) correspondiente al id como parametro")
    @Parameter(name = "resourceId", description = "Id del recurso")
    @GetMapping("/reports/{resourceId}")
    public ResponseEntity<List<ReportDTO>> getReportsForMachine(@PathVariable Long resourceId) {
        List<ReportDTO> reports = null;
        HttpStatus status = HttpStatus.OK;
        try {
            reports = adminService.getReportsForMachine(resourceId);
            if (reports.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<ReportDTO>>(reports, status);
    }
    //

    // View machines fails
    @Operation(summary = "Get all fails", responses = {
            @ApiResponse(responseCode = "200", description = "Reportes obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = MachineReportDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
    }, description = "Retorna una lista de todos los reportes de maquinas (MachineReportDTO) que han fallado")
    @GetMapping("/reports/fails")
    public ResponseEntity<List<MachineReportDTO>> getAllFailsReports() {
        List<MachineReportDTO> reports = null;
        HttpStatus status = HttpStatus.OK;
        try {
            reports = adminService.getAllFails();
            if (reports.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<MachineReportDTO>>(reports, status);
    }

    @Operation(summary = "Get fails by machine", responses = {
            @ApiResponse(responseCode = "200", description = "Reportes obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = ReportDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Retorna una lista de reportes de fallos (ReportDTO) correspondientes al id de la maquina(resourceId) como parametro")
    @GetMapping("/reports/fails/{resourceId}")
    public ResponseEntity<List<ReportDTO>> getFailReportsForMachine(@PathVariable Long resourceId) {
        List<ReportDTO> reports = null;
        HttpStatus status = HttpStatus.OK;
        try {
            reports = adminService.getAllFailsForMachine(resourceId);
            if (reports.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<ReportDTO>>(reports, status);
    }
    //

    @Operation(summary = "Get clients", responses = {
            @ApiResponse(responseCode = "200", description = "Clientes obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Retorna una lista de todos los clientes (ClientDTO)")
    @GetMapping("/clients")
    public ResponseEntity<List<ClientDTO>> getClients() {
        List<ClientDTO> reports = null;
        HttpStatus status = HttpStatus.OK;
        try {
            reports = adminService.getAllClients();
            if (reports.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<ClientDTO>>(reports, status);
    }

    // Create clients
    @Operation(summary = "Create client", responses = {
            @ApiResponse(responseCode = "200", description = "Cliente creado satisfactoriamente", content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Metodo POST, dado un cliente (Client) como parametro, crea el cliente en el sistema")
    @PostMapping("/clients")
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO client) {
        ClientDTO clientDTO = null;
        HttpStatus status = HttpStatus.OK;
        try {
            clientDTO = adminService.createClient(client);
            if (clientDTO == null)
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<ClientDTO>(clientDTO, status);
    }
    //

    // View Stock Parts in the system
    @Operation(summary = "Get all parts available", responses = {
            @ApiResponse(responseCode = "200", description = "Partes obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = PartDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Retorna una lista de tipo PartDTO, donde se encuentran todas las partes disponibles en el sistema para la producción")
    @GetMapping("/parts/available")
    public ResponseEntity<List<PartDTO>> getAllPartsAvailable() {
        List<PartDTO> reports = null;
        HttpStatus status = HttpStatus.OK;
        try {
            reports = adminService.getAllPartsAvailable();
            if (reports.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<PartDTO>>(reports, status);
    }

    @Operation(summary = "Get all parts unavailable", responses = {
            @ApiResponse(responseCode = "200", description = "Partes obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = PartDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Retorna una lista de tipo PartDTO, donde se encuentran todas las partes NO disponibles en el sistema para la producción")
    @GetMapping("/parts/unavailable")
    public ResponseEntity<List<PartDTO>> getAllPartsUnavailable() {
        List<PartDTO> reports = null;
        HttpStatus status = HttpStatus.OK;
        try {
            reports = adminService.getAllPartsUnavailable();
            if (reports.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<PartDTO>>(reports, status);
    }

    @Operation(summary = "Get all parts", responses = {
            @ApiResponse(responseCode = "200", description = "Partes obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = PartDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Retorna una lista de tipo PartDTO, donde se encuentran todas las partes del sistema")
    @GetMapping("/parts")
    public ResponseEntity<List<PartDTO>> getAllParts() {
        List<PartDTO> reports = null;
        HttpStatus status = HttpStatus.OK;
        try {
            reports = adminService.getAllParts();
            if (reports.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<PartDTO>>(reports, status);
    }

    // Crear nueva parte
    @Operation(summary = "Create part", responses = {
            @ApiResponse(responseCode = "200", description = "Parte creado satisfactoriamente", content = @Content(schema = @Schema(implementation = PartDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Metodo POST, dado una parte (CreatePartAux) como parametro, crea la parte en el sistema")
    @PostMapping("/parts")
    public ResponseEntity<PartDTO> createPart(@RequestBody CreatePartAux aux) {
        PartDTO resources = null;
        HttpStatus status = HttpStatus.OK;
        try {
            resources = adminService.createPart(aux);
            if (resources == null)
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<PartDTO>(resources, status);
    }

    @Operation(summary = "Get all parts type", responses = {
            @ApiResponse(responseCode = "200", description = "Partes obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Retorna una lista (Map<Long, String>) con todos los tipos de partes existentes en el sistema")
    @GetMapping("/parts/type")
    public ResponseEntity<Map<Long, String>> getAllPartsType() {
        Map<Long, String> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.getAllPartsTypes();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<Long, String>>(workPlans, status);
    }

    @Operation(summary = "Get all parts by type", responses = {
            @ApiResponse(responseCode = "200", description = "Partes obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = PartDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Retorna una lista de tipo PartDTO, donde se encuentran todas las partes del sistema de un determinado tipo dado por parametro")
    @Parameter(name = "typeId", description = "Id del tipo de parte")
    @GetMapping("/parts/type/{typeId}")
    public ResponseEntity<List<PartDTO>> getAllPartsByType(@PathVariable Long typeId) {
        List<PartDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.getAllPartsByType(typeId);
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<PartDTO>>(workPlans, status);
    }

    @Operation(summary = "Get all parts that can be produced", responses = {
            @ApiResponse(responseCode = "200", description = "Partes obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = PartDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Retorna una lista de tipo PartDTO, donde se encuentran todas las partes del sistema que pueden ser producidas")
    @GetMapping("/parts/production")
    public ResponseEntity<List<PartDTO>> getProduceableParts() {
        List<PartDTO> resources = null;
        HttpStatus status = HttpStatus.OK;
        try {
            resources = adminService.getPartsThatCanBeProduced();
            if (resources.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<PartDTO>>(resources, status);
    }
    //

    // View product plans
    @Operation(summary = "Get all work plans", responses = {
            @ApiResponse(responseCode = "200", description = "WorkPlans obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = WorkPlanDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Retorna una lista de tipo WorkPlanDTO, donde se encuentran todos los WorkPlans del sistema")
    @GetMapping("/work-plans")
    public ResponseEntity<List<WorkPlanDTO>> getAllWorkPlans() {
        List<WorkPlanDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.getAllWorkPlans();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<WorkPlanDTO>>(workPlans, status);
    }

    @Operation(summary = "Get work plans by id", responses = {
            @ApiResponse(responseCode = "200", description = "WorkPlans obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = WorkPlanWithStepsDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Retorna un workPlan con sus pasos (WorkPlanWithStepsDTO), dado un id como parametro")
    @Parameter(name = "id", description = "Id del WorkPlan")
    @GetMapping("/work-plans/{id}")
    public ResponseEntity<WorkPlanWithStepsDTO> getWorkPlansById(@PathVariable Long id) {
        WorkPlanWithStepsDTO workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.getWorkPlanById(id);
            if (workPlans == null)
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<WorkPlanWithStepsDTO>(workPlans, status);
    }

    @Operation(summary = "Get all work plan types", responses = {
            @ApiResponse(responseCode = "200", description = "WorkPlans obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Retorna una lista (Map<Long, String>) con todos los tipos de WorkPlans existentes en el sistema")
    @GetMapping("/work-plans/type")
    public ResponseEntity<Map<Long, String>> getAllWorkPlanTypes() {
        Map<Long, String> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.getAllWorkPlansTypes();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<Long, String>>(workPlans, status);
    }

    @Operation(summary = "Get all work plans by type", responses = {
            @ApiResponse(responseCode = "200", description = "WorkPlans obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = WorkPlanDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Retorna una lista de tipo WorkPlanDTO(WorkPlanDTO), donde se encuentran todos los WorkPlans del sistema de un determinado tipo (typeId) dado por parametro")
    @Parameter(name = "typeId", description = "Id del tipo de WorkPlan")
    @GetMapping("/work-plans/type/{typeId}")
    public ResponseEntity<List<WorkPlanDTO>> getAllWorkPlansByType(@PathVariable Long typeId) {
        List<WorkPlanDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.getWorkPlansByType(typeId);
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<WorkPlanDTO>>(workPlans, status);
    }
    //

    @Operation(summary = "Get all defined steps", responses = {
            @ApiResponse(responseCode = "200", description = "Steps obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = StepDefinitionDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Retorna una lista de tipo StepDefinitionDTO, donde se encuentran todas las definiciones de pasos del sistema")
    @GetMapping("/steps")
    public ResponseEntity<List<StepDefinitionDTO>> getAllDefinedSteps() {
        List<StepDefinitionDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.getAllStepsDefined();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<StepDefinitionDTO>>(workPlans, status);
    }

    // View defined operations
    @Operation(summary = "Get all defined operations", responses = {
            @ApiResponse(responseCode = "200", description = "Operations obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = OperationDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Retorna una lista de tipo OperationDTO, donde se encuentran todas las operaciones del sistema")
    @GetMapping("/operations")
    public ResponseEntity<List<OperationDTO>> getAllOperations() {
        List<OperationDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.getAllOperations();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<OperationDTO>>(workPlans, status);
    }

    @Operation(summary = "Get operations by resource", responses = {
            @ApiResponse(responseCode = "200", description = "Operations obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = ResourceForOperationDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Retorna una lista de tipo ResourceForOperationDTO, donde se encuentran todas las operaciones del sistema de un determinado recurso/maquina (resourceId) dado por parametro")
    @Parameter(name = "resource", description = "Id del recurso")
    @GetMapping("/operations/{resource}")
    public ResponseEntity<List<ResourceForOperationDTO>> getOperationsByResource(@PathVariable Long resource) {
        List<ResourceForOperationDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.getOperationsForResource(resource);
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<ResourceForOperationDTO>>(workPlans, status);
    }

    // Create product plans
    @Operation(summary = "Create work plan", responses = {
            @ApiResponse(responseCode = "200", description = "WorkPlan creado satisfactoriamente", content = @Content(schema = @Schema(implementation = WorkPlanDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Crea un nuevo WorkPlan, retorna un WorkPlanDTO en caso de que fue creado satisfactoriamente, dado un WorkPlanAux como parametro")
    @Parameter(name = "createRequest", description = "WorkPlanAux para crear WorkPlan")
    @PostMapping("/work-plans")
    public ResponseEntity<WorkPlanDTO> createWorkPlan(@RequestBody CreateWorkPlanAux createRequest) {
        WorkPlanDTO workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.createWorkPlan(createRequest);
            if (workPlans == null)
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<WorkPlanDTO>(workPlans, status);
    }
    //

    // View product orders
    @Operation(summary = "Get all orders", responses = {
            @ApiResponse(responseCode = "200", description = "Orders obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = OrderDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Obtiene todas las ordenes con status, retorna una lista de ordenes (OrderDTO) en caso de que fue obtenido satisfactoriamente")
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllWorkPlanStatus() {
        List<OrderDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.getOrdersWithStatus();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<OrderDTO>>(workPlans, status);
    }

    @Operation(summary = "Get all planned ends", responses = {
            @ApiResponse(responseCode = "200", description = "Orders obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = Date.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Obtiene todas las ordenes finalizadas, retorna una lista de Date en caso de que fue obtenido satisfactoriamente")
    @GetMapping("/orders/ends")
    public ResponseEntity<List<Map<Long,ZonedDateTime>>> getAllPlannedEnds() {
        List<Map<Long,ZonedDateTime>> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.getAllOrdersPlannedEnds();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<Map<Long,ZonedDateTime>>>(workPlans, status);
    }

    @Operation(summary = "Get all orders possible status", responses = {
            @ApiResponse(responseCode = "200", description = "Orders obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Obtiene todas las ordenes con status, retorna un Map<Long, String> en caso de que fue obtenido satisfactoriamente")
    @GetMapping("/orders/status")
    public ResponseEntity<Map<Long, String>> getStatus() {
        Map<Long, String> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.getOrdersPossibleStatus();
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
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Filtra todas las ordenes dado un status (orderStatus) como parametro")
    @GetMapping("/orders/status/{orderStatus}")
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@PathVariable Long orderStatus) {
        List<OrderDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.filterOrdersByStatus(orderStatus);
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<OrderDTO>>(workPlans, status);
    }

    @Operation(summary = "Get all parts consumed by orders", responses = {
            @ApiResponse(responseCode = "200", description = "Parts obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = PartsConsumedByOrderDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Obtiene todas las partes consumidas por ordenes, retorna una lista de PartsConsumedByOrderDTO en caso de que fue obtenido satisfactoriamente")
    @GetMapping("/orders/parts")
    public ResponseEntity<List<PartsConsumedByOrderDTO>> getAllPartsConsumedByOrders() {
        List<PartsConsumedByOrderDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.getAllPartsConsumedByOrders();
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
            @ApiResponse(responseCode = "200", description = "Orders obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = OrderDTO.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Obtiene todas las ordenes con su tiempo, retorna una lista de ordenes (OrderDTO) en caso de que fue obtenido satisfactoriamente, funciona para calcular el tiempo de producción")
    @GetMapping("/orders/time")
    public ResponseEntity<List<OrderDTO>> getAllOrdersTime() {
        List<OrderDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.getOrdersWithTime();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<OrderDTO>>(workPlans, status);
    }

    // View order steps with time
    @GetMapping("/orders/{id}/status")
    public ResponseEntity<List<StepTimeDTO>> getOrderWithStepsAndTime(@PathVariable(name = "id") Long order) {
        List<StepTimeDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.getStepsWithTimeByOrder(order);
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<StepTimeDTO>>(workPlans, status);
    }
    //

    // View indicators
    @Operation(summary = "Get all indicators", responses = {
            @ApiResponse(responseCode = "200", description = "Indicators obtenidos satisfactoriamente", content = @Content(schema = @Schema(implementation = IndicatorAux.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido para mostrar", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    }, description = "Obtiene todos los indicadores, retorna una lista de IndicatorAux en caso de que fue obtenido satisfactoriamente")
    @GetMapping("/indicators")
    public ResponseEntity<List<IndicatorAux>> getAllIndicators() {
        List<IndicatorAux> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.getProductionIndicators();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<IndicatorAux>>(workPlans, status);
    }
    //
}
