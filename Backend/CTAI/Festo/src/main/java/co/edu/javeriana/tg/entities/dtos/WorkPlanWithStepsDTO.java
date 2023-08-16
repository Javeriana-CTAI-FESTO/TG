package co.edu.javeriana.tg.entities.dtos;

import java.util.List;

public class WorkPlanWithStepsDTO {
  private WorkPlanDTO workPlan;
  private List<StepDefinitionDTO> steps;
  public WorkPlanWithStepsDTO(WorkPlanDTO workPlan, List<StepDefinitionDTO> steps) {
    this.workPlan = workPlan;
    this.steps = steps;
  }
  public WorkPlanWithStepsDTO() {
  }
  public WorkPlanDTO getWorkPlan() {
    return workPlan;
  }
  public void setWorkPlan(WorkPlanDTO workPlan) {
    this.workPlan = workPlan;
  }
  public List<StepDefinitionDTO> getSteps() {
    return steps;
  }
  public void setSteps(List<StepDefinitionDTO> steps) {
    this.steps = steps;
  }
}
