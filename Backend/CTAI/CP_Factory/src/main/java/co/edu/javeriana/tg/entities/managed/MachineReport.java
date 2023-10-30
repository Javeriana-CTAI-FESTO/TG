package co.edu.javeriana.tg.entities.managed;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "tblMachineReport")
@IdClass(MachineReportPK.class)
// Reporte de los recursos en un momento determinado
public class MachineReport {
    @Id
    @Column(name = "ResourceID", nullable = false)
    private Long resource;
    @Id
    @Column(name = "TimeStamp", nullable = false)
    private Date timestamp;
    @Id
    @Column(name = "ID", nullable = false)
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
    public MachineReport() {
    }
    public MachineReport(MachineReportPK testID) {
        this.id = testID.getId();
        this.resource = testID.getResource();
        this.timestamp = testID.getTimestamp();
    }
    public Long getResource() {
        return resource;
    }
    public void setResource(Long resource) {
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((resource == null) ? 0 : resource.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((automaticMode == null) ? 0 : automaticMode.hashCode());
        result = prime * result + ((manualMode == null) ? 0 : manualMode.hashCode());
        result = prime * result + ((busy == null) ? 0 : busy.hashCode());
        result = prime * result + ((reset == null) ? 0 : reset.hashCode());
        result = prime * result + ((errorL0 == null) ? 0 : errorL0.hashCode());
        result = prime * result + ((errorL1 == null) ? 0 : errorL1.hashCode());
        result = prime * result + ((errorL2 == null) ? 0 : errorL2.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MachineReport other = (MachineReport) obj;
        if (resource == null) {
            if (other.resource != null)
                return false;
        } else if (!resource.equals(other.resource))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (automaticMode == null) {
            if (other.automaticMode != null)
                return false;
        } else if (!automaticMode.equals(other.automaticMode))
            return false;
        if (manualMode == null) {
            if (other.manualMode != null)
                return false;
        } else if (!manualMode.equals(other.manualMode))
            return false;
        if (busy == null) {
            if (other.busy != null)
                return false;
        } else if (!busy.equals(other.busy))
            return false;
        if (reset == null) {
            if (other.reset != null)
                return false;
        } else if (!reset.equals(other.reset))
            return false;
        if (errorL0 == null) {
            if (other.errorL0 != null)
                return false;
        } else if (!errorL0.equals(other.errorL0))
            return false;
        if (errorL1 == null) {
            if (other.errorL1 != null)
                return false;
        } else if (!errorL1.equals(other.errorL1))
            return false;
        if (errorL2 == null) {
            if (other.errorL2 != null)
                return false;
        } else if (!errorL2.equals(other.errorL2))
            return false;
        return true;
    }
}
