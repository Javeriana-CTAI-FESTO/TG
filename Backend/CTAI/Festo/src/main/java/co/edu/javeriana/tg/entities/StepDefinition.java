package co.edu.javeriana.tg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(StepDefinitionPK.class)
@Table(name = "tblStepDef")
// Lista de operaciones que componen cada plan de trabajo
public class StepDefinition {
    @Id
    @ManyToOne
    @JoinColumn(name = "WPNo", referencedColumnName = "WPNo")
    private WorkPlanDefinition workPlan;
    @Id
    @Column(name = "StepNo", nullable = false)
    private Long stepNumber;
    @Column(name = "Description", nullable = true)
    private String description;
    @ManyToOne(optional = true)
    @JoinColumn(name = "OpNo", nullable = true)
    private Operation operation;
    @Column(name = "NextStepNo", nullable = true)
    private Long nextStepNumber;
    @Column(name = "FirstStep", nullable = true)
    private Boolean firstStep;
    @Column(name="ErrorStepNo", nullable = true)
    private Long nextWhenError;
    @Column(name="NewPNo", nullable = true)
    private Long newPartNumber;
    @Column(name="OpNoType", nullable = true)
    private Long operationNumberType;
    @Column(name="ResourceID", nullable = true)
    private Long resource;
    @Column(name="TransportTime", nullable = true)
    private Long transportTime;
    @Column(name="ErrorStep", nullable = true)
    private Long error;
    @Column(name="SQLWrite", nullable = true)
    private String sqlToWrite;
    @Column(name="ElectricEnergyCalc", nullable = true)
    private Long calculatedElectricEnergy;
    @Column(name="CompressedAirCalc", nullable = true)
    private Long calculatedCompressedAir;
    @Column(name="WorkingTimeCalc", nullable = true)
    private Long calculatedWorkingTime;
    @Column(name="FreeString", nullable = true)
    private String freeText;
    
    public WorkPlanDefinition getWorkPlan() {
        return workPlan;
    }
    public void setWorkPlan(WorkPlanDefinition workPlan) {
        this.workPlan = workPlan;
    }
    public Long getStepNumber() {
        return stepNumber;
    }
    public void setStepNumber(Long stepNumber) {
        this.stepNumber = stepNumber;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Operation getOperation() {
        return operation;
    }
    public void setOperationNumber(Operation operationNumber) {
        this.operation = operationNumber;
    }
    public Long getNextStepNumber() {
        return nextStepNumber;
    }
    public void setNextStepNumber(Long nextStepNumber) {
        this.nextStepNumber = nextStepNumber;
    }
    public Boolean getFirstStep() {
        return firstStep;
    }
    public void setFirstStep(Boolean firstStep) {
        this.firstStep = firstStep;
    }
    public Long getNextWhenError() {
        return nextWhenError;
    }
    public void setNextWhenError(Long nextWhenError) {
        this.nextWhenError = nextWhenError;
    }
    public Long getNewPartNumber() {
        return newPartNumber;
    }
    public void setNewPartNumber(Long newPartNumber) {
        this.newPartNumber = newPartNumber;
    }
    public Long getOperationNumberType() {
        return operationNumberType;
    }
    public void setOperationNumberType(Long operationNumberType) {
        this.operationNumberType = operationNumberType;
    }
    public Long getResource() {
        return resource;
    }
    public void setResource(Long resource) {
        this.resource = resource;
    }
    public Long getTransportTime() {
        return transportTime;
    }
    public void setTransportTime(Long transportTime) {
        this.transportTime = transportTime;
    }
    public Long getError() {
        return error;
    }
    public void setError(Long error) {
        this.error = error;
    }
    public String getSqlToWrite() {
        return sqlToWrite;
    }
    public void setSqlToWrite(String sqlToWrite) {
        this.sqlToWrite = sqlToWrite;
    }
    public Long getCalculatedElectricEnergy() {
        return calculatedElectricEnergy;
    }
    public void setCalculatedElectricEnergy(Long calculatedElectricEnergy) {
        this.calculatedElectricEnergy = calculatedElectricEnergy;
    }
    public Long getCalculatedCompressedAir() {
        return calculatedCompressedAir;
    }
    public void setCalculatedCompressedAir(Long calculatedCompressedAir) {
        this.calculatedCompressedAir = calculatedCompressedAir;
    }
    public Long getCalculatedWorkingTime() {
        return calculatedWorkingTime;
    }
    public void setCalculatedWorkingTime(Long calculatedWorkingTime) {
        this.calculatedWorkingTime = calculatedWorkingTime;
    }
    public String getFreeText() {
        return freeText;
    }
    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }
}
