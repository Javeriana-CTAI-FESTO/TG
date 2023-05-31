package co.edu.javeriana.tg.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tblMachineReport")
@IdClass(MachineReportPK.class)
public class MachineReport {
    @Id
    @ManyToOne
    @JoinColumn(name = "ResourceID")
    private Resource resource;
    @Id
    @Column(name = "TimeStamp")
    private Date timestamp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "AutomaticMode")
    private Boolean automaticMode;
    @Column(name = "ManualMode")
    private Boolean manualMode;
    @Column(name = "Busy")
    private Boolean busy;
    @Column(name = "Reset")
    private Boolean reset;
    @Column(name = "ErrorL0")
    private Boolean errorL0;
    @Column(name = "ErrorL1")
    private Boolean errorL1;
    @Column(name = "ErrorL2")
    private Boolean errorL2;
    public Resource getResource() {
        return resource;
    }
    public void setResource(Resource resource) {
        this.resource = resource;
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
