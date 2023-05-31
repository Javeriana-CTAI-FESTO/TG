package co.edu.javeriana.tg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.tg.entities.dtos.MachineReportDTO;
import co.edu.javeriana.tg.services.MachineReportService;

@RestController
@RequestMapping("/api/report")
public class MachineReportController {
    @Autowired
    private MachineReportService reportService;

    @GetMapping
    public ResponseEntity<List<MachineReportDTO>> getAll() {
        List<MachineReportDTO> reports = reportService.getAll();
        HttpStatus status = HttpStatus.OK;
        if (reports.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<MachineReportDTO>>(reports, status);
    }
}
