package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.managed.MachineReport;
import io.swagger.v3.oas.annotations.media.Schema;

public class MachineReportDTO {
    @Schema(name = "resource", example = "10", description = "ResourceDTO, recurso utilizado por la maquina", required = true)
    private ResourceDTO resource;
    @Schema(name = "report", example = "1", description = "ReportDTO, reporte utilizado por la maquina", required = true)
    private ReportDTO report;

    public MachineReportDTO() {
    }

    public MachineReportDTO(MachineReport report, ResourceDTO resource) {
        this.resource = resource;
        this.report = new ReportDTO(report);
    }

    public ResourceDTO getResource() {
        return resource;
    }

    public void setResource(ResourceDTO resource) {
        this.resource = resource;
    }

    public ReportDTO getReport() {
        return report;
    }

    public void setReport(ReportDTO report) {
        this.report = report;
    }
}
