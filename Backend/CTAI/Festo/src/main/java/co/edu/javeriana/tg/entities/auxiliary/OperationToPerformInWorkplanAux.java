package co.edu.javeriana.tg.entities.auxiliary;

public class OperationToPerformInWorkplanAux {
  private Long nextWhenError;
  private Long newPartNumber;
  private Long transportTime;
  private Long operationNumber;
  private Long electricEnergy;
  private Long compressedAir;
  private Long resource;
  private Long workingTime;

  public OperationToPerformInWorkplanAux(Long nextWhenError, Long newPartNumber, Long transportTime,
      Long operationNumber, Long electricEnergy, Long compressedAir, Long resource, Long workingTime) {
    this.nextWhenError = nextWhenError;
    this.newPartNumber = newPartNumber;
    this.transportTime = transportTime;
    this.operationNumber = operationNumber;
    this.electricEnergy = electricEnergy;
    this.compressedAir = compressedAir;
    this.resource = resource;
    this.workingTime = workingTime;
  }

  public OperationToPerformInWorkplanAux() {
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

  public Long getTransportTime() {
    return transportTime;
  }

  public void setTransportTime(Long transportTime) {
    this.transportTime = transportTime;
  }

  public Long getOperationNumber() {
    return operationNumber;
  }

  public void setOperationNumber(Long operationNumber) {
    this.operationNumber = operationNumber;
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

  public Long getResource() {
    return resource;
  }

  public void setResource(Long resource) {
    this.resource = resource;
  }

  public Long getWorkingTime() {
    return workingTime;
  }

  public void setWorkingTime(Long workingTime) {
    this.workingTime = workingTime;
  }
}
