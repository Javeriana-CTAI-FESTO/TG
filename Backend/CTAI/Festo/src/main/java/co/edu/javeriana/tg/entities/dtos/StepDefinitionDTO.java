package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.managed.StepDefinition;

public class StepDefinitionDTO {

    private Long stepNumber;
    private String description;
    private WorkPlanDTO workPlan;
    private OperationDTO operation;
    private Long nextStepNumber;
    private Boolean firstStep;
    private Long nextWhenError;
    private Long newPartNumber;
    private Long operationNumberType;
    private Long resource;
    private Long transportTime;
    private Boolean error;
    private String sqlToWrite;
    private Long calculatedElectricEnergy;
    private Long calculatedCompressedAir;
    private Long calculatedWorkingTime;
    private String freeText;

    public StepDefinitionDTO() {
    }

    public StepDefinitionDTO(StepDefinition step, WorkPlanDTO workPlan, OperationDTO operation){
        this.stepNumber = step.getStepNumber();
        this.description = step.getDescription();
        this.workPlan = workPlan;
        this.operation = operation;
        this.nextStepNumber = step.getNextStepNumber();
        this.firstStep = step.getFirstStep();
        this.nextWhenError = step.getNextWhenError();
        this.newPartNumber = step.getNewPartNumber();
        this.operationNumberType = step.getOperationNumberType();
        this.resource = step.getResource();
        this.transportTime = step.getTransportTime();
        this.error = step.getError();
        this.sqlToWrite = step.getSqlToWrite();
        this.calculatedElectricEnergy = step.getCalculatedElectricEnergy();
        this.calculatedCompressedAir = step.getCalculatedCompressedAir();
        this.calculatedWorkingTime = step.getCalculatedWorkingTime();
        this.freeText = step.getFreeText();
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

    public WorkPlanDTO getWorkPlan() {
        return workPlan;
    }

    public void setWorkPlan(WorkPlanDTO workPlan) {
        this.workPlan = workPlan;
    }

    public OperationDTO getOperation() {
        return operation;
    }

    public void setOperation(OperationDTO operationNumber) {
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

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
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
