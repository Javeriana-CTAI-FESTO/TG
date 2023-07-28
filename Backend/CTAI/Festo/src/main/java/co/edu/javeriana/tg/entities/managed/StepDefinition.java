package co.edu.javeriana.tg.entities.managed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(StepDefinitionPK.class)
@Table(name = "tblStepDef")
// Lista de operaciones que componen cada plan de trabajo
public class StepDefinition {
    @Id
    @Column(name = "WPNo", nullable = false)
    private Long workPlan;
    @Id
    @Column(name = "StepNo", nullable = false)
    private Long stepNumber;
    @Column(name = "Description", nullable = true)
    private String description;
    @Column(name = "OpNo", nullable = true)
    private Long operation;
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
    
    public StepDefinition() {
    }
    public StepDefinition(StepDefinitionPK testID) {
        this.workPlan = testID.getWorkPlan();
        this.stepNumber=testID.getStepNumber();
    }
    public Long getWorkPlan() {
        return workPlan;
    }
    public void setWorkPlan(Long workPlan) {
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
    public Long getOperation() {
        return operation;
    }
    public void setOperationNumber(Long operationNumber) {
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((workPlan == null) ? 0 : workPlan.hashCode());
        result = prime * result + ((stepNumber == null) ? 0 : stepNumber.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((operation == null) ? 0 : operation.hashCode());
        result = prime * result + ((nextStepNumber == null) ? 0 : nextStepNumber.hashCode());
        result = prime * result + ((firstStep == null) ? 0 : firstStep.hashCode());
        result = prime * result + ((nextWhenError == null) ? 0 : nextWhenError.hashCode());
        result = prime * result + ((newPartNumber == null) ? 0 : newPartNumber.hashCode());
        result = prime * result + ((operationNumberType == null) ? 0 : operationNumberType.hashCode());
        result = prime * result + ((resource == null) ? 0 : resource.hashCode());
        result = prime * result + ((transportTime == null) ? 0 : transportTime.hashCode());
        result = prime * result + ((error == null) ? 0 : error.hashCode());
        result = prime * result + ((sqlToWrite == null) ? 0 : sqlToWrite.hashCode());
        result = prime * result + ((calculatedElectricEnergy == null) ? 0 : calculatedElectricEnergy.hashCode());
        result = prime * result + ((calculatedCompressedAir == null) ? 0 : calculatedCompressedAir.hashCode());
        result = prime * result + ((calculatedWorkingTime == null) ? 0 : calculatedWorkingTime.hashCode());
        result = prime * result + ((freeText == null) ? 0 : freeText.hashCode());
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
        StepDefinition other = (StepDefinition) obj;
        if (workPlan == null) {
            if (other.workPlan != null)
                return false;
        } else if (!workPlan.equals(other.workPlan))
            return false;
        if (stepNumber == null) {
            if (other.stepNumber != null)
                return false;
        } else if (!stepNumber.equals(other.stepNumber))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (operation == null) {
            if (other.operation != null)
                return false;
        } else if (!operation.equals(other.operation))
            return false;
        if (nextStepNumber == null) {
            if (other.nextStepNumber != null)
                return false;
        } else if (!nextStepNumber.equals(other.nextStepNumber))
            return false;
        if (firstStep == null) {
            if (other.firstStep != null)
                return false;
        } else if (!firstStep.equals(other.firstStep))
            return false;
        if (nextWhenError == null) {
            if (other.nextWhenError != null)
                return false;
        } else if (!nextWhenError.equals(other.nextWhenError))
            return false;
        if (newPartNumber == null) {
            if (other.newPartNumber != null)
                return false;
        } else if (!newPartNumber.equals(other.newPartNumber))
            return false;
        if (operationNumberType == null) {
            if (other.operationNumberType != null)
                return false;
        } else if (!operationNumberType.equals(other.operationNumberType))
            return false;
        if (resource == null) {
            if (other.resource != null)
                return false;
        } else if (!resource.equals(other.resource))
            return false;
        if (transportTime == null) {
            if (other.transportTime != null)
                return false;
        } else if (!transportTime.equals(other.transportTime))
            return false;
        if (error == null) {
            if (other.error != null)
                return false;
        } else if (!error.equals(other.error))
            return false;
        if (sqlToWrite == null) {
            if (other.sqlToWrite != null)
                return false;
        } else if (!sqlToWrite.equals(other.sqlToWrite))
            return false;
        if (calculatedElectricEnergy == null) {
            if (other.calculatedElectricEnergy != null)
                return false;
        } else if (!calculatedElectricEnergy.equals(other.calculatedElectricEnergy))
            return false;
        if (calculatedCompressedAir == null) {
            if (other.calculatedCompressedAir != null)
                return false;
        } else if (!calculatedCompressedAir.equals(other.calculatedCompressedAir))
            return false;
        if (calculatedWorkingTime == null) {
            if (other.calculatedWorkingTime != null)
                return false;
        } else if (!calculatedWorkingTime.equals(other.calculatedWorkingTime))
            return false;
        if (freeText == null) {
            if (other.freeText != null)
                return false;
        } else if (!freeText.equals(other.freeText))
            return false;
        return true;
    }
}
