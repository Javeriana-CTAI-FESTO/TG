package co.edu.javeriana.tg.entities.managed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "tblResourceOperation")
// Operaciones que los recursos pueden hacer
@IdClass(ResourceForOperationPK.class)
public class ResourceForOperation {
    @Id
    @Column(name = "ResourceID", nullable = false)
    private Long resource;
    @Id
    @Column(name = "OpNo", nullable = false)
    private Long operation;
    @Column(name = "workingTime")
    private Long workingTime;
    @Column(name="OffsetTime")
    private Long offsetTime;
    @Column(name = "ElectricEnergy")
    private Long electricEnergy;
    @Column(name = "CompressedAir")
    private Long compressedAir;
    public ResourceForOperation() {
    }
    public ResourceForOperation(ResourceForOperationPK testID) {
        this.resource = testID.getResource();
        this.operation = testID.getOperation();
    }
    public Long getResource() {
        return resource;
    }
    public void setResource(Long resource) {
        this.resource = resource;
    }
    public Long getOperation() {
        return operation;
    }
    public void setOperation(Long operation) {
        this.operation = operation;
    }
    public Long getWorkingTime() {
        return workingTime;
    }
    public void setWorkingTime(Long workingTime) {
        this.workingTime = workingTime;
    }
    public Long getOffsetTime() {
        return offsetTime;
    }
    public void setOffsetTime(Long offsetTime) {
        this.offsetTime = offsetTime;
    }
    public Long getElectricEnergy() {
        return electricEnergy;
    }
    public void setElectricEnergy(Long electricEnergy) {
        this.electricEnergy = electricEnergy;
    }
    public Long getCompressedAir() {
        return compressedAir;
    }
    public void setCompressedAir(Long compressedAir) {
        this.compressedAir = compressedAir;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((resource == null) ? 0 : resource.hashCode());
        result = prime * result + ((operation == null) ? 0 : operation.hashCode());
        result = prime * result + ((workingTime == null) ? 0 : workingTime.hashCode());
        result = prime * result + ((offsetTime == null) ? 0 : offsetTime.hashCode());
        result = prime * result + ((electricEnergy == null) ? 0 : electricEnergy.hashCode());
        result = prime * result + ((compressedAir == null) ? 0 : compressedAir.hashCode());
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
        ResourceForOperation other = (ResourceForOperation) obj;
        if (resource == null) {
            if (other.resource != null)
                return false;
        } else if (!resource.equals(other.resource))
            return false;
        if (operation == null) {
            if (other.operation != null)
                return false;
        } else if (!operation.equals(other.operation))
            return false;
        if (workingTime == null) {
            if (other.workingTime != null)
                return false;
        } else if (!workingTime.equals(other.workingTime))
            return false;
        if (offsetTime == null) {
            if (other.offsetTime != null)
                return false;
        } else if (!offsetTime.equals(other.offsetTime))
            return false;
        if (electricEnergy == null) {
            if (other.electricEnergy != null)
                return false;
        } else if (!electricEnergy.equals(other.electricEnergy))
            return false;
        if (compressedAir == null) {
            if (other.compressedAir != null)
                return false;
        } else if (!compressedAir.equals(other.compressedAir))
            return false;
        return true;
    }
}
