package co.edu.javeriana.tg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tblResourceOperation")
// Operaciones que los recursos pueden hacer
@IdClass(ResourceForOperationPK.class)
public class ResourceForOperation {
    @Id
    @ManyToOne
    @JoinColumn(name = "ResourceID")
    private Resource resource;
    @Id
    @ManyToOne
    @JoinColumn(name = "OpNo")
    private Operation operation;
    @Column(name = "workingTime")
    private Long workingTime;
    @Column(name="OffsetTime")
    private Long offsetTime;
    @Column(name = "ElectricEnergy")
    private Long electricEnergy;
    @Column(name = "CompressedAir")
    private Long compressedAir;
    public Resource getResource() {
        return resource;
    }
    public void setResource(Resource resource) {
        this.resource = resource;
    }
    public Operation getOperation() {
        return operation;
    }
    public void setOperation(Operation operation) {
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
}
