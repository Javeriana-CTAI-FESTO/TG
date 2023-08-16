package co.edu.javeriana.tg.services.components;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import co.edu.javeriana.tg.entities.dtos.MachineReportDTO;
import co.edu.javeriana.tg.entities.dtos.ReportDTO;
import co.edu.javeriana.tg.repositories.interfaces.MachineReportRepository;

@Component
public class MachineReportService {
    
    private final MachineReportRepository reportRepository;

    private final ResourceService resourceService;

    public MachineReportService(MachineReportRepository reportRepository, ResourceService resourceService) {
        this.reportRepository = reportRepository;
        this.resourceService=resourceService;
    }

    public List<MachineReportDTO> getAllMachineReports() {
        return reportRepository.findAll().stream().map(machineReport -> new MachineReportDTO(machineReport, resourceService.getResourceById(machineReport.getResource()))).collect(Collectors.toList());
    }

    public List<ReportDTO> getMachineReportsForMachine(Long resourceId) {
        return reportRepository.findByResource(resourceId).stream().map(ReportDTO::new).collect(Collectors.toList());
    }

    public List<MachineReportDTO> getAllMachineFails() {
        return reportRepository.findFails().stream().map(machineReport -> new MachineReportDTO(machineReport, resourceService.getResourceById(machineReport.getResource()))).collect(Collectors.toList());
    }

    public List<ReportDTO> getAllMachineFailsForMachine(Long resourceId) {
        return reportRepository.findFailsByResource(resourceId).stream().map(ReportDTO::new).collect(Collectors.toList());
    }
}
