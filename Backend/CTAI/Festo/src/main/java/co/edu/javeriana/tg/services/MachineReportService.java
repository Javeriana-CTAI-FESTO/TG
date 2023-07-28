package co.edu.javeriana.tg.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.dtos.MachineReportDTO;
import co.edu.javeriana.tg.entities.dtos.ReportDTO;
import co.edu.javeriana.tg.repositories.interfaces.MachineReportRepository;

@Service
public class MachineReportService {
    
    private final MachineReportRepository reportRepository;

    private final ResourceService resourceService;

    public MachineReportService(MachineReportRepository reportRepository, ResourceService resourceService) {
        this.reportRepository = reportRepository;
        this.resourceService=resourceService;
    }

    public List<MachineReportDTO> getAll() {
        return reportRepository.findAll().stream().map(machineReport -> new MachineReportDTO(machineReport, resourceService.getById(machineReport.getResource()))).collect(Collectors.toList());
    }

    public List<ReportDTO> getForMachine(Long resourceId) {
        return reportRepository.findByResource(resourceId).stream().map(ReportDTO::new).collect(Collectors.toList());
    }
}
