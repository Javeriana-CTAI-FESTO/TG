package co.edu.javeriana.tg.controllers.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.dtos.ClientDTO;
import co.edu.javeriana.tg.entities.dtos.MachineReportDTO;
import co.edu.javeriana.tg.entities.dtos.ReportDTO;
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
