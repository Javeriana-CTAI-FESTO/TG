package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.WorkPlan;
import lombok.Getter;

@Getter
public class WorkPlanDTO {
    private Long work_plan_number;
    private String description;
    private String work_plan_type;
    private String short_description;
    private Long status;
    private Long part_number;

    public WorkPlanDTO(WorkPlan work_plan){
        this.work_plan_number = work_plan.getWork_plan_number();
        this.description = work_plan.getDescription();
        this.work_plan_type = work_plan.getWork_plan_type();
        this.short_description = work_plan.getShort_description();
        this.status = work_plan.getStatus();
        this.part_number = work_plan.getPart_number();
    }
}
