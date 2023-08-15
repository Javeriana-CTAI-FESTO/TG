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

@RestController
@RequestMapping("/api/students")
public class StudentController{

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // This is what the user can produce
        @GetMapping("/parts")
        public ResponseEntity<List<PartDTO>> getAllParts() {
            List<PartDTO> resources = studentService.getAllParts();
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
                workPlans = studentService.getAllPartsTypes();
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
                workPlans = studentService.getAllPartsByType(typeId);
                status = HttpStatus.OK;
                if (workPlans.isEmpty())
                    status = HttpStatus.NO_CONTENT;
            } catch (Exception e) {
                status = HttpStatus.NOT_FOUND;
            }
            return new ResponseEntity<List<PartDTO>>(workPlans, status);
        }
    // The user can generate a new order based on the availability
        @PostMapping("/parts/new-order")
        public ResponseEntity<OrderDTO> newOrder(@RequestParam Long partNumber, @RequestParam Long clientNumber, @RequestParam Long positions) {
            OrderDTO workPlans = null;
            HttpStatus status = HttpStatus.NOT_FOUND;
            try {
                workPlans = studentService.generateNewOrder(partNumber, clientNumber, positions);
                status = HttpStatus.OK;
                if (workPlans == null)
                    status = HttpStatus.NO_CONTENT;
            } catch (Exception e) {
                status = HttpStatus.NOT_FOUND;
            }
            return new ResponseEntity<OrderDTO>(workPlans, status);
        }
    //

    // The user can view what is being produced
        @GetMapping("/orders")
        public ResponseEntity<List<OrderDTO>> getAllWorkPlanStatus() {
            List<OrderDTO> workPlans = null;
            HttpStatus status = HttpStatus.NOT_FOUND;
            try {
                workPlans = studentService.getOrdersWithStatus();
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
            List<Date> workPlans = studentService.getAllOrdersPlannedEnds();
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
                workPlans = studentService.getOrdersPossibleStatus();
                status = HttpStatus.OK;
                if (workPlans.isEmpty())
                    status = HttpStatus.NO_CONTENT;
            } catch (Exception e) {
                status = HttpStatus.NOT_FOUND;
            }
            return new ResponseEntity<Map<Long, String>>(workPlans, status);
        }

        @GetMapping("/orders/status/{orderStatus}")
        public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@PathVariable Long orderStatus) {
            List<OrderDTO> workPlans = null;
            HttpStatus status = HttpStatus.NOT_FOUND;
            try {
                workPlans = studentService.filterOrdersByStatus(orderStatus);
                status = HttpStatus.OK;
                if (workPlans.isEmpty())
                    status = HttpStatus.NO_CONTENT;
            } catch (Exception e) {
                status = HttpStatus.NOT_FOUND;
            }
            return new ResponseEntity<List<OrderDTO>>(workPlans, status);
        }
    //
    // Calculate production time
        @GetMapping("/orders/time")
        public ResponseEntity<List<OrderDTO>> getAllOrdersTime() {
            List<OrderDTO> workPlans = null;
            HttpStatus status = HttpStatus.NOT_FOUND;
            try {
                workPlans = studentService.getOrdersWithTime();
                status = HttpStatus.OK;
                if (workPlans.isEmpty())
                    status = HttpStatus.NO_CONTENT;
            } catch (Exception e) {
                status = HttpStatus.NOT_FOUND;
            }
            return new ResponseEntity<List<OrderDTO>>(workPlans, status);
        }
    //

    // View perfomance indicators
        @GetMapping("/indicators")
        public ResponseEntity<List<IndicatorAux>> getAllIndicators() {
            List<IndicatorAux> workPlans = null;
            HttpStatus status = HttpStatus.NOT_FOUND;
            try {
                workPlans = studentService.getProductionIndicators();
                status = HttpStatus.OK;
                if (workPlans.isEmpty())
                    status = HttpStatus.NO_CONTENT;
            } catch (Exception e) {
                status = HttpStatus.NOT_FOUND;
            }
            return new ResponseEntity<List<IndicatorAux>>(workPlans, status);
        }
    //
/* 
    //View work plans
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
    // */

    

    

    
}
