package co.edu.javeriana.tg.entities.auxiliary;

public class StepToPerformInWorkplanAux {
  private Long definedStepWorkPlanNumber;
  private Long definedStepNumber;
  private Long thisStepNumber;
  private Long nextStepNumber;
  private Boolean firstStep;
  private Boolean errorStep;
  private Long errorStepNumber;

  public StepToPerformInWorkplanAux(Long definedStepWorkPlanNumber, Long definedStepNumber, Long thisStepNumber,
      Long nextStepNumber, Boolean firstStep, Boolean errorStep, Long errorStepNumber) {
    this.definedStepWorkPlanNumber = definedStepWorkPlanNumber;
    this.definedStepNumber = definedStepNumber;
    this.thisStepNumber = thisStepNumber;
    this.nextStepNumber = nextStepNumber;
    this.firstStep = firstStep;
    this.errorStep = errorStep;
    this.errorStepNumber = errorStepNumber;
  }

  public Long getDefinedStepWorkPlanNumber() {
    return definedStepWorkPlanNumber;
  }

  public void setDefinedStepWorkPlanNumber(Long definedStepWorkPlanNumber) {
    this.definedStepWorkPlanNumber = definedStepWorkPlanNumber;
  }

  public Long getDefinedStepNumber() {
    return definedStepNumber;
  }

  public void setDefinedStepNumber(Long definedStepNumber) {
    this.definedStepNumber = definedStepNumber;
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

  public Boolean getErrorStep() {
    return errorStep;
  }

  public void setErrorStep(Boolean errorStep) {
    this.errorStep = errorStep;
  }

  public Long getErrorStepNumber() {
    return errorStepNumber;
  }

  public void setErrorStepNumber(Long errorStepNumber) {
    this.errorStepNumber = errorStepNumber;
  }

  public Long getThisStepNumber() {
    return thisStepNumber;
  }

  public void setThisStepNumber(Long thisStepNumber) {
    this.thisStepNumber = thisStepNumber;
  }
}
