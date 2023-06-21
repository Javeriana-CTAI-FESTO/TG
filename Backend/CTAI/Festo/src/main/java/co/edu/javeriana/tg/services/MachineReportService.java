package co.edu.javeriana.tg.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.dtos.MachineReportDTO;
import co.edu.javeriana.tg.entities.dtos.ReportDTO;
import co.edu.javeriana.tg.repositories.MachineReportRepository;

@Service
public class MachineReportService {
    
    private final MachineReportRepository reportRepository;

    public MachineReportService(MachineReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<MachineReportDTO> getAll() {
        return reportRepository.findAll().stream().map(MachineReportDTO::new).collect(Collectors.toList());
    }

    public List<ReportDTO> getForMachine(Long resourceId) {
        return reportRepository.findByResourceId(resourceId).stream().map(ReportDTO::new).collect(Collectors.toList());
    }
}
