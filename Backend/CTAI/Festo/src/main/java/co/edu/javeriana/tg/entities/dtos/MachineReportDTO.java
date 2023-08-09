package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.managed.MachineReport;

public class MachineReportDTO {
    private ResourceDTO resource;
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