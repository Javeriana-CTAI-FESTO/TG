package co.edu.javeriana.tg.entities.auxiliary;

public class OperationToPerformByWorkPlanAux {
    private Long workPlanNumber;
    private Long resourceId;
    private Long operationNumber;
    private String description;
    private Long nextStepNumber;
    private Long errorStepNumber;
    private Boolean firstStep;
    private Long newPartNumber;
    private Long operationNumberType;
    private Long transportTime;
    private Boolean errorStep;
    private String sqlToWrite;
    private Long electricEnergyCalc;
    private Long compressedAirCalc;
    private Long workingTimeCalc;
    private String freeText;

    public OperationToPerformByWorkPlanAux(Long workPlanNumber, Long resourceId, Long operationNumber,
            String description, Long nextStepNumber, Long errorStepNumber, Boolean firstStep, Long newPartNumber,
            Long operationNumberType, Long transportTime, Boolean errorStep, String sqlToWrite, Long electricEnergyCalc,
            Long compressedAirCalc, Long workingTimeCalc, String freeText) {
        this.workPlanNumber = workPlanNumber;
        this.resourceId = resourceId;
        this.operationNumber = operationNumber;
        this.description = description;
        this.nextStepNumber = nextStepNumber;
        this.errorStepNumber = errorStepNumber;
        this.firstStep = firstStep;
        this.newPartNumber = newPartNumber;
        this.operationNumberType = operationNumberType;
        this.transportTime = transportTime;
        this.errorStep = errorStep;
        this.sqlToWrite = sqlToWrite;
        this.electricEnergyCalc = electricEnergyCalc;
        this.compressedAirCalc = compressedAirCalc;
        this.workingTimeCalc = workingTimeCalc;
        this.freeText = freeText;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public Long getOperationNumber() {
        return operationNumber;
    }

    public Long getWorkPlanNumber() {
        return workPlanNumber;
    }

    public String getDescription() {
        return description;
    }

    public Long getNextStepNumber() {
        return nextStepNumber;
    }

    public Long getErrorStepNumber() {
        return errorStepNumber;
    }

    public Boolean getFirstStep() {
        return firstStep;
    }

    public Long getNewPartNumber() {
        return newPartNumber;
    }

    public Long getOperationNumberType() {
        return operationNumberType;
    }

    public Long getTransportTime() {
        return transportTime;
    }

    public Boolean getErrorStep() {
        return errorStep;
    }

    public String getSqlToWrite() {
        return sqlToWrite;
    }

    public Long getElectricEnergyCalc() {
        return electricEnergyCalc;
    }

    public Long getCompressedAirCalc() {
        return compressedAirCalc;
    }

    public Long getWorkingTimeCalc() {
        return workingTimeCalc;
    }

    public String getFreeText() {
        return freeText;
    }
}
