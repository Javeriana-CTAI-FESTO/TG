package co.edu.javeriana.tg.controllers.teacher;

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

import co.edu.javeriana.tg.entities.auxiliary.CreateWorkPlanAux;
import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.dtos.OrderDTO;
import co.edu.javeriana.tg.entities.dtos.PartDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.interfaces.TeacherInterface;
import co.edu.javeriana.tg.services.OrderService;
import co.edu.javeriana.tg.services.PartService;
import co.edu.javeriana.tg.services.WorkPlanService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController implements TeacherInterface {

    private final WorkPlanService workPlanService;

    private final OrderService orderService;

    private final PartService partService;

    public TeacherController(WorkPlanService workPlanService, OrderService orderService, PartService partService) {
        this.workPlanService = workPlanService;
        this.orderService = orderService;
        this.partService = partService;
    }

    // View Stock
    @GetMapping("/parts")
    public ResponseEntity<List<PartDTO>> getAllParts() {
        List<PartDTO> resources = partService.getAll();
        HttpStatus status = HttpStatus.OK;
        if (resources.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<PartDTO>>(resources, status);
    }

    @GetMapping("/parts/type")
    public ResponseEntity<Map<Long, String>> getAllPartsType() {
        Map<Long, String> workPlans = null;
        HttpStatus status = HttpStatus.NOT_FOUND;
        try {
            workPlans = partService.getAllTypes();
            status = HttpStatus.OK;
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<Map<Long, String>>(workPlans, status);
    }

    @GetMapping("/parts/type/{typeId}")
    public ResponseEntity<List<PartDTO>> getAllPartsByType(@PathVariable Long typeId) {
        List<PartDTO> workPlans = null;
        HttpStatus status = HttpStatus.NOT_FOUND;
        try {
            workPlans = partService.getAllByType(typeId);
            status = HttpStatus.OK;
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<List<PartDTO>>(workPlans, status);
    }
    //

    //View product plans
    @GetMapping("/products")
    public ResponseEntity<List<WorkPlanDTO>> getAllWorkPlans() {
        List<WorkPlanDTO> workPlans = null;
        HttpStatus status = HttpStatus.NOT_FOUND;
        try {
            workPlans = workPlanService.getAll();
            status = HttpStatus.OK;
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<List<WorkPlanDTO>>(workPlans, status);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<WorkPlanDTO> getWorkPlansById(@PathVariable Long id) {
        WorkPlanDTO workPlans = null;
        HttpStatus status = HttpStatus.NOT_FOUND;
        try {
            workPlans = workPlanService.getById(id);
            status = HttpStatus.OK;
            if (workPlans == null)
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<WorkPlanDTO>(workPlans, status);
    }

    @GetMapping("/products/type")
    public ResponseEntity<Map<Long, String>> getAllWorkPlanTypes() {
        Map<Long, String> workPlans = null;
        HttpStatus status = HttpStatus.NOT_FOUND;
        try {
            workPlans = workPlanService.getTypes();
            status = HttpStatus.OK;
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<Map<Long, String>>(workPlans, status);
    }

    @GetMapping("/products/type/{typeId}")
    public ResponseEntity<List<WorkPlanDTO>> getAllWorkPlansByType(@PathVariable Long typeId) {
        List<WorkPlanDTO> workPlans = null;
        HttpStatus status = HttpStatus.NOT_FOUND;
        try {
            workPlans = workPlanService.getWorkPlansByType(typeId);
            status = HttpStatus.OK;
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<List<WorkPlanDTO>>(workPlans, status);
    }
    //

    // Create product plans
    @PostMapping("/products")
    public ResponseEntity<WorkPlanDTO> createWorkPlan(@RequestBody CreateWorkPlanAux createRequest) {
        WorkPlanDTO workPlans = null;
        HttpStatus status = HttpStatus.NOT_FOUND;
        try {
            workPlans = workPlanService.createWorkPlan(createRequest);
            status = HttpStatus.OK;
            if (workPlans==null)
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<WorkPlanDTO>(workPlans, status);
    }
    //

    // View product orders
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllWorkPlanStatus() {
        List<OrderDTO> workPlans = null;
        HttpStatus status = HttpStatus.NOT_FOUND;
        try {
            workPlans = orderService.getOrdersWithStatus();
            status = HttpStatus.OK;
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<List<OrderDTO>>(workPlans, status);
    }

    @GetMapping("/orders/ends")
    public ResponseEntity<List<Date>> getAllPlannedEnds(){
        List<Date> workPlans = orderService.getAllPlannedEnds();
        HttpStatus status = HttpStatus.OK;
        if (workPlans.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<Date>>(workPlans, status);
    }

    @GetMapping("/orders/status")
    public ResponseEntity<Map<Long, String>> getStatus() {
        Map<Long, String> workPlans = null;
        HttpStatus status = HttpStatus.NOT_FOUND;
        try {
            workPlans = orderService.getPossibleStatus();
            status = HttpStatus.OK;
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<Map<Long, String>>(workPlans, status);
    }

    @GetMapping("/orders/status/{orderStatus}")
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@RequestParam Long orderStatus) {
        List<OrderDTO> workPlans = null;
        HttpStatus status = HttpStatus.NOT_FOUND;
        try {
            workPlans = orderService.filterByStatus(orderStatus);
            status = HttpStatus.OK;
            if (workPlans.isEmpty())
                status = HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<List<OrderDTO>>(workPlans, status);
    }
    //

    // View indicators
    @GetMapping("/indicators")
    public ResponseEntity<List<IndicatorAux>> getAllIndicators() {
        List<IndicatorAux> workPlans = null;
        HttpStatus status = HttpStatus.NOT_FOUND;
        try {
            workPlans = orderService.getIndicators();
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
