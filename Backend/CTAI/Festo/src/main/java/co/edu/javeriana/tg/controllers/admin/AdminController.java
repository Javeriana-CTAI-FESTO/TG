package co.edu.javeriana.tg.controllers.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.dtos.MachineReportDTO;
import co.edu.javeriana.tg.entities.dtos.ReportDTO;
import co.edu.javeriana.tg.entities.dtos.WorkPlanDTO;
import co.edu.javeriana.tg.services.MachineReportService;
import co.edu.javeriana.tg.services.OrderService;
import co.edu.javeriana.tg.services.WorkPlanService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private WorkPlanService workPlanService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MachineReportService reportService;

    @GetMapping("/reports")
    public ResponseEntity<List<MachineReportDTO>> getAll() {
        List<MachineReportDTO> reports = reportService.getAll();
        HttpStatus status = HttpStatus.OK;
        if (reports.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<MachineReportDTO>>(reports, status);
    }

    @GetMapping("/reports/{resourceId}")
    public ResponseEntity<List<ReportDTO>> getForMachine(@PathVariable Long resourceId) {
        List<ReportDTO> reports = reportService.getForMachine(resourceId);
        HttpStatus status = HttpStatus.OK;
        if (reports.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<ReportDTO>>(reports, status);
    }

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
}
