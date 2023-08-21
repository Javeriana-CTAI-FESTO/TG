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
import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanWithStepsDTO;
import co.edu.javeriana.tg.entities.managed.Client;
import co.edu.javeriana.tg.services.users.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // View machines
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

    // Create clients
    @PostMapping("/clients")
    public ResponseEntity<ClientDTO> createClient(@RequestBody Client client) {
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

    // View defined steps
    @PutMapping("/orders/{orderNumber}")
    public ResponseEntity<OrderDTO> enableOrder(@PathVariable Long orderNumber) {
        OrderDTO workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.enableOrder(orderNumber);
            if (workPlans == null)
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<OrderDTO>(workPlans, status);
    }

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

    @GetMapping("/orders/ends")
    public ResponseEntity<List<Date>> getAllPlannedEnds() {
        List<Date> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        try {
            workPlans = adminService.getAllOrdersPlannedEnds();
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<List<Date>>(workPlans, status);
    }

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

    // View indicators
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
