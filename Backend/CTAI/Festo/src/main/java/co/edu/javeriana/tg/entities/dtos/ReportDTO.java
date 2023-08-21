package co.edu.javeriana.tg.entities.dtos;

import java.util.Date;

import co.edu.javeriana.tg.entities.managed.MachineReport;
import io.swagger.v3.oas.annotations.media.Schema;

public class ReportDTO {
    @Schema(name = "timestamp", example = "2021-11-25", description = "Fecha y hora del reporte", required = true)
    private Date timestamp;
    @Schema(name = "id", example = "1", description = "Identificador del reporte", required = true)
    private Long id;
    @Schema(name = "automaticMode", example = "false", description = "Indica si el reporte es automático", required = true)
    private Boolean automaticMode;
    @Schema(name = "manualMode", example = "false", description = "Indica si el reporte es manual", required = true)
    private Boolean manualMode;
    @Schema(name = "busy", example = "false", description = "Indica si el reporte esta ocupado", required = true)
    private Boolean busy;
    @Schema(name = "reset", example = "false", description = "Indica si el reporte se reinicia", required = true)
    private Boolean reset;
    @Schema(name = "errorL0", example = "false", description = "Indica si el reporte tiene un error L0", required = true)
    private Boolean errorL0;
    @Schema(name = "errorL1", example = "false", description = "Indica si el reporte tiene un error L1", required = true)
    private Boolean errorL1;
    @Schema(name = "errorL2", example = "false", description = "Indica si el reporte tiene un error L2", required = true)
    private Boolean errorL2;

    public ReportDTO(MachineReport report) {
        this.timestamp = report.getTimestamp();
        this.id = report.getId();
        this.automaticMode = report.getAutomaticMode();
        this.manualMode = report.getManualMode();
        this.busy = report.getBusy();
        this.reset = report.getReset();
        this.errorL0 = report.getErrorL0();
        this.errorL1 = report.getErrorL1();
        this.errorL2 = report.getErrorL2();
    }

    public ReportDTO() {
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAutomaticMode() {
        return automaticMode;
    }

    public void setAutomaticMode(Boolean automaticMode) {
        this.automaticMode = automaticMode;
    }

    public Boolean getManualMode() {
        return manualMode;
    }

    public void setManualMode(Boolean manualMode) {
        this.manualMode = manualMode;
    }

    public Boolean getBusy() {
        return busy;
    }

    public void setBusy(Boolean busy) {
        this.busy = busy;
    }

    public Boolean getReset() {
        return reset;
    }

    public void setReset(Boolean reset) {
        this.reset = reset;
    }

    public Boolean getErrorL0() {
        return errorL0;
    }

    public void setErrorL0(Boolean errorL0) {
        this.errorL0 = errorL0;
    }

    public Boolean getErrorL1() {
        return errorL1;
    }

    public void setErrorL1(Boolean errorL1) {
        this.errorL1 = errorL1;
    }

    public Boolean getErrorL2() {
        return errorL2;
    }

    public void setErrorL2(Boolean errorL2) {
        this.errorL2 = errorL2;
    }
}
