package co.edu.javeriana.tg.entities.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class WorkPlanWithStepsDTO {
  @Schema(name = "workPlan", example = "110", description = "WorkPlanDTO seleccionado", required = true)
  private WorkPlanDTO workPlan;
  @Schema(name = "steps", example = "10", description = "Lista de pasos asignados al workPlan", required = true)
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
