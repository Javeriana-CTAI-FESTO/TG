package co.edu.javeriana.tg.entities.managed;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(StepPK.class)
@Table(name = "tblStep")
// Pasos por cada plan de trabajo ejecutado en una orden
public class Step {
  @Column(name = "WPNo", nullable = true)
  private Long workPlanNumber;
  @Id
  @Column(name = "StepNo", nullable = false)
  private Long stepNumber;
  @Id
  @Column(name = "ONo", nullable = false)
  private Long orderNumber;
  @Id
  @Column(name = "OPos", nullable = false)
  private Long orderPosition;
  @Column(name = "Description", nullable = true)
  private String description;
  @Column(name = "OpNo", nullable = true)
  private Long operation;
  @Column(name = "NextStepNo", nullable = true)
  private Long nextStepNumber;
  @Column(name = "FirstStep", nullable = true)
  private Boolean firstStep;
  @Column(name = "ErrorStepNo", nullable = true)
  private Long nextWhenError;
  @Column(name = "NewPNo", nullable = true)
  private Long newPartNumber;
  @Column(name = "PlanedStart")
  private Date plannedStart;
  @Column(name = "PlanedEnd")
  private Date plannedEnd;
  @Column(name = "Start")
  private Date realStart;
  @Column(name = "End")
  private Date realEnd;
  @Column(name = "OpNoType", nullable = true)
  private Long operationNumberType;
  @Column(name = "ResourceID", nullable = true)
  private Long resource;
  @Column(name = "TransportTime", nullable = true)
  private Long transportTime;
  @Column(name = "ErrorStep", nullable = true)
  private Boolean error;
  @Column(name = "ElectricEnergyCalc", nullable = true)
  private Long calculatedElectricEnergy;
  @Column(name = "ElectricEnergyReal", nullable = true)
  private Long realElectricEnergy;
  @Column(name = "CompressedAirCalc", nullable = true)
  private Long calculatedCompressedAir;
  @Column(name = "CompressedAirReal", nullable = true)
  private Long realCompressedAir;
  @Column(name = "FreeString", nullable = true)
  private String freeText;
  @Column(name = "StaffId", nullable = true)
  private Long staffId;
  @Column(name = "ErrorRetVal", nullable = true)
  private Long errorReturnValue;

  public Step() {
  }

  public Step(FinishedStep step) {
    this.workPlanNumber = step.getWorkPlanNumber();
    this.stepNumber = step.getStepNumber();
    this.orderNumber = step.getOrderNumber();
    this.orderPosition = step.getOrderPosition();
    this.description = step.getDescription();
    this.operation = step.getOperation();
    this.nextStepNumber = step.getNextStepNumber();
    this.firstStep = step.getFirstStep();
    this.nextWhenError = step.getNextWhenError();
    this.newPartNumber = step.getNewPartNumber();
    this.plannedStart = step.getPlannedStart();
    this.plannedEnd = step.getPlannedEnd();
    this.realStart = step.getRealStart();
    this.realEnd = step.getRealEnd();
    this.operationNumberType = step.getOperationNumberType();
    this.resource = step.getResource();
    this.transportTime = step.getTransportTime();
    this.error = step.getError();
    this.calculatedElectricEnergy = step.getCalculatedElectricEnergy();
    this.realElectricEnergy = step.getRealElectricEnergy();
    this.calculatedCompressedAir = step.getCalculatedCompressedAir();
    this.realCompressedAir = step.getRealCompressedAir();
    this.freeText = step.getFreeText();
    this.staffId = step.getStaffId();
    this.errorReturnValue = step.getErrorReturnValue();
  }

  public Long getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(Long orderNumber) {
    this.orderNumber = orderNumber;
  }

  public Long getOrderPosition() {
    return orderPosition;
  }

  public void setOrderPosition(Long orderPosition) {
    this.orderPosition = orderPosition;
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

  public void setOperation(Long operation) {
    this.operation = operation;
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

  public Date getPlannedStart() {
    return plannedStart;
  }

  public void setPlannedStart(Date plannedStart) {
    this.plannedStart = plannedStart;
  }

  public Date getPlannedEnd() {
    return plannedEnd;
  }

  public void setPlannedEnd(Date plannedEnd) {
    this.plannedEnd = plannedEnd;
  }

  public Date getRealStart() {
    return realStart;
  }

  public void setRealStart(Date realStart) {
    this.realStart = realStart;
  }

  public Date getRealEnd() {
    return realEnd;
  }

  public void setRealEnd(Date realEnd) {
    this.realEnd = realEnd;
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

  public Long getCalculatedElectricEnergy() {
    return calculatedElectricEnergy;
  }

  public void setCalculatedElectricEnergy(Long calculatedElectricEnergy) {
    this.calculatedElectricEnergy = calculatedElectricEnergy;
  }

  public Long getRealElectricEnergy() {
    return realElectricEnergy;
  }

  public void setRealElectricEnergy(Long realElectricEnergy) {
    this.realElectricEnergy = realElectricEnergy;
  }

  public Long getCalculatedCompressedAir() {
    return calculatedCompressedAir;
  }

  public void setCalculatedCompressedAir(Long calculatedCompressedAir) {
    this.calculatedCompressedAir = calculatedCompressedAir;
  }

  public Long getRealCompressedAir() {
    return realCompressedAir;
  }

  public void setRealCompressedAir(Long realCompressedAir) {
    this.realCompressedAir = realCompressedAir;
  }

  public String getFreeText() {
    return freeText;
  }

  public void setFreeText(String freeText) {
    this.freeText = freeText;
  }

  public Long getStaffId() {
    return staffId;
  }

  public void setStaffId(Long staffId) {
    this.staffId = staffId;
  }

  public Long getErrorReturnValue() {
    return errorReturnValue;
  }

  public void setErrorReturnValue(Long errorReturnValue) {
    this.errorReturnValue = errorReturnValue;
  }

  public Long getWorkPlanNumber() {
    return workPlanNumber;
  }

  public void setWorkPlanNumber(Long workPlanNumber) {
    this.workPlanNumber = workPlanNumber;
  }

  public Long getStepNumber() {
    return stepNumber;
  }

  public void setStepNumber(Long stepNumber) {
    this.stepNumber = stepNumber;
  }
}
