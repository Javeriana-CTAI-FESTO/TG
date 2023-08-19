package co.edu.javeriana.tg.entities.managed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "tblStepParameter")
@IdClass(StepParameterPK.class)
// Parametros de la lista de operaciones de los planes de trabajo
public class StepParameter {
  @Column(name = "WPNo")
  private Long workPlanNumber;
  @Id
  @Column(name = "StepNo")
  private Long stepNumber;
  @Id
  @Column(name = "ONo")
  private Long orderNumber;
  @Id
  @Column(name = "OPos")
  private Long orderPosition;
  @Id
  @Column(name = "ParameterNo")
  private Long parameterNumber;
  @Column(name = "Parameter")
  private String parameterValue;
  public StepParameter(Long workPlanNumber, Long stepNumber, Long orderNumber, Long orderPosition, Long parameterNumber,
      String parameterValue) {
    this.workPlanNumber = workPlanNumber;
    this.stepNumber = stepNumber;
    this.orderNumber = orderNumber;
    this.orderPosition = orderPosition;
    this.parameterNumber = parameterNumber;
    this.parameterValue = parameterValue;
  }
  public StepParameter() {
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
  public Long getParameterNumber() {
    return parameterNumber;
  }
  public void setParameterNumber(Long parameterNumber) {
    this.parameterNumber = parameterNumber;
  }
  public String getParameterValue() {
    return parameterValue;
  }
  public void setParameterValue(String parameterValue) {
    this.parameterValue = parameterValue;
  }

}
