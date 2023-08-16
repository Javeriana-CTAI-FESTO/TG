package co.edu.javeriana.tg.controllers.web;

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

    // View machines status
    @GetMapping("/reports")
    public ResponseEntity<List<MachineReportDTO>> getAllReports() {
        List<MachineReportDTO> reports = adminService.getAllReports();
        HttpStatus status = HttpStatus.OK;
        if (reports.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<MachineReportDTO>>(reports, status);
    }

    @GetMapping("/reports/{resourceId}")
    public ResponseEntity<List<ReportDTO>> getReportsForMachine(@PathVariable Long resourceId) {
        List<ReportDTO> reports = adminService.getReportsForMachine(resourceId);
        HttpStatus status = HttpStatus.OK;
        if (reports.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<ReportDTO>>(reports, status);
    }
    //

    // View machines fails
    @GetMapping("/reports/fails")
    public ResponseEntity<List<MachineReportDTO>> getAllFailsReports() {
        List<MachineReportDTO> reports = adminService.getAllFails();
        HttpStatus status = HttpStatus.OK;
        if (reports.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<MachineReportDTO>>(reports, status);
    }

    @GetMapping("/reports/fails/{resourceId}")
    public ResponseEntity<List<ReportDTO>> getFailReportsForMachine(@PathVariable Long resourceId) {
        List<ReportDTO> reports = adminService.getAllFailsForMachine(resourceId);
        HttpStatus status = HttpStatus.OK;
        if (reports.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<ReportDTO>>(reports, status);
    }
    //

    // Create clients
    @PostMapping("/clients")
    public ResponseEntity<ClientDTO> createClient(@RequestBody Client client) {
        ClientDTO clientDTO = null;
        HttpStatus status = HttpStatus.NOT_FOUND;
        try {
            clientDTO = adminService.createClient(client);
            status = HttpStatus.OK;
            if (clientDTO == null)
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<ClientDTO>(clientDTO, status);
    }
    //

    // View Stock Parts in the system

    @GetMapping("/parts/available")
    public ResponseEntity<List<PartDTO>> getAllPartsAvailable() {
        List<PartDTO> resources = adminService.getAllPartsAvailable();
        HttpStatus status = HttpStatus.OK;
        if (resources.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<PartDTO>>(resources, status);
    }

    @GetMapping("/parts/unavailable")
    public ResponseEntity<List<PartDTO>> getAllPartsUnavailable() {
        List<PartDTO> resources = adminService.getAllPartsUnavailable();
        HttpStatus status = HttpStatus.OK;
        if (resources.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<PartDTO>>(resources, status);
    }

    @GetMapping("/parts")
    public ResponseEntity<List<PartDTO>> getAllParts() {
        List<PartDTO> resources = adminService.getAllParts();
        HttpStatus status = HttpStatus.OK;
        if (resources.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<PartDTO>>(resources, status);
    }

    // Crear nueva parte
    @PostMapping("/parts")
    public ResponseEntity<PartDTO> createPart(@RequestBody CreatePartAux aux) {
        PartDTO resources = adminService.createPart(aux);
        HttpStatus status = HttpStatus.OK;
        if (resources == null)
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<PartDTO>(resources, status);
    }
    
    @GetMapping("/parts/type")
    public ResponseEntity<Map<Long, String>> getAllPartsType() {
        Map<Long, String> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        workPlans = adminService.getAllPartsTypes();
        if (workPlans.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<Map<Long, String>>(workPlans, status);
    }

    @GetMapping("/parts/type/{typeId}")
    public ResponseEntity<List<PartDTO>> getAllPartsByType(@PathVariable Long typeId) {
        List<PartDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        workPlans = adminService.getAllPartsByType(typeId);
        if (workPlans.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<PartDTO>>(workPlans, status);
    }

    @GetMapping("/parts/production")
    public ResponseEntity<List<PartDTO>> getProduceableParts() {
        List<PartDTO> resources = adminService.getPartsThatCanBeProduced();
        HttpStatus status = HttpStatus.OK;
        if (resources.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<PartDTO>>(resources, status);
    }
    //

    // View product plans
    @GetMapping("/work-plans")
    public ResponseEntity<List<WorkPlanDTO>> getAllWorkPlans() {
        List<WorkPlanDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        workPlans = adminService.getAllWorkPlans();
        if (workPlans.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<WorkPlanDTO>>(workPlans, status);
    }

    @GetMapping("/work-plans/{id}")
    public ResponseEntity<WorkPlanWithStepsDTO> getWorkPlansById(@PathVariable Long id) {
        WorkPlanWithStepsDTO workPlans = null;
        HttpStatus status = HttpStatus.OK;
        workPlans = adminService.getWorkPlanById(id);
        if (workPlans == null)
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<WorkPlanWithStepsDTO>(workPlans, status);
    }

    @GetMapping("/work-plans/type")
    public ResponseEntity<Map<Long, String>> getAllWorkPlanTypes() {
        Map<Long, String> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        workPlans = adminService.getAllWorkPlansTypes();
        if (workPlans.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<Map<Long, String>>(workPlans, status);
    }

    @GetMapping("/work-plans/type/{typeId}")
    public ResponseEntity<List<WorkPlanDTO>> getAllWorkPlansByType(@PathVariable Long typeId) {
        List<WorkPlanDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        workPlans = adminService.getWorkPlansByType(typeId);
        if (workPlans.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<WorkPlanDTO>>(workPlans, status);
    }
    //

    // View defined steps
    @GetMapping("/steps")
    public ResponseEntity<List<StepDefinitionDTO>> getAllDefinedSteps() {
        List<StepDefinitionDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        workPlans = adminService.getAllStepsDefined();
        if (workPlans.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<StepDefinitionDTO>>(workPlans, status);
    }

    // View defined operations
    @GetMapping("/operations")
    public ResponseEntity<List<OperationDTO>> getAllOperations() {
        List<OperationDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        workPlans = adminService.getAllOperations();
        if (workPlans.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<OperationDTO>>(workPlans, status);
    }

    // Create product plans
    @PostMapping("/work-plans")
    public ResponseEntity<WorkPlanDTO> createWorkPlan(@RequestBody CreateWorkPlanAux createRequest) {
        WorkPlanDTO workPlans = null;
        HttpStatus status = HttpStatus.OK;
        workPlans = adminService.createWorkPlan(createRequest);
        if (workPlans == null)
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<WorkPlanDTO>(workPlans, status);
    }
    //

    // View product orders
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllWorkPlanStatus() {
        List<OrderDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        workPlans = adminService.getOrdersWithStatus();
        if (workPlans.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<OrderDTO>>(workPlans, status);
    }

    @GetMapping("/orders/ends")
    public ResponseEntity<List<Date>> getAllPlannedEnds() {
        List<Date> workPlans = adminService.getAllOrdersPlannedEnds();
        HttpStatus status = HttpStatus.OK;
        if (workPlans.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<Date>>(workPlans, status);
    }

    @GetMapping("/orders/status")
    public ResponseEntity<Map<Long, String>> getStatus() {
        Map<Long, String> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        workPlans = adminService.getOrdersPossibleStatus();
        if (workPlans.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<Map<Long, String>>(workPlans, status);
    }

    @GetMapping("/orders/status/{orderStatus}")
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@PathVariable Long orderStatus) {
        List<OrderDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        workPlans = adminService.filterOrdersByStatus(orderStatus);
        if (workPlans.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<OrderDTO>>(workPlans, status);
    }

    @GetMapping("/orders/parts")
    public ResponseEntity<List<PartsConsumedByOrderDTO>> getAllPartsConsumedByOrders() {
        List<PartsConsumedByOrderDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        workPlans = adminService.getAllPartsConsumedByOrders();
        if (workPlans.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<PartsConsumedByOrderDTO>>(workPlans, status);
    }
    //

    // Calculate production time
    @GetMapping("/orders/time")
    public ResponseEntity<List<OrderDTO>> getAllOrdersTime() {
        List<OrderDTO> workPlans = null;
        HttpStatus status = HttpStatus.OK;
        workPlans = adminService.getOrdersWithTime();
        if (workPlans.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<OrderDTO>>(workPlans, status);
    }

    // View indicators
    @GetMapping("/indicators")
    public ResponseEntity<List<IndicatorAux>> getAllIndicators() {
        List<IndicatorAux> workPlans = null;
        HttpStatus status = HttpStatus.NOT_FOUND;
        try {
            workPlans = adminService.getProductionIndicators();
            status = HttpStatus.OK;
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<List<IndicatorAux>>(workPlans, status);
    }
    //
}
