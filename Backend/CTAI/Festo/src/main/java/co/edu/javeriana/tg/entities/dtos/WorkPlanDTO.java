package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.managed.WorkPlanDefinition;
import io.swagger.v3.oas.annotations.media.Schema;

public class WorkPlanDTO {
    @Schema(name = "workPlanNumber", example = "110", description = "Numero de plan de trabajo", required = true)
    private Long workPlanNumber;
    @Schema(name = "description", example = "create PNo 110", description = "Descripción del workplan, que produce, que involucra", required = true)
    private String description;
    @Schema(name = "workPlanType", example = "1", description = "Indica el tipo de plan de trabajo", required = true)
    private String workPlanType;
    @Schema(name = "shortDescription", example = "Front Cover Box", description = "Descripción corta del workplan", required = true)
    private String shortDescription;
    @Schema(name = "pictureNumber", example = "1", description = "Numero de imagen", required = false)
    private Long pictureNumber;
    @Schema(name = "partNumber", example = "1", description = "Numero de parte", required = false)
    private Long partNumber;

    public WorkPlanDTO() {
    }

    public WorkPlanDTO(WorkPlanDefinition work_plan, String description) {
        this.workPlanNumber = work_plan.getWorkPlanNumber();
        this.description = work_plan.getDescription();
        this.workPlanType = description;
        this.shortDescription = work_plan.getShortDescription();
        this.pictureNumber = work_plan.getPictureNumber();
        this.partNumber = work_plan.getPartNumber();
    }

    public Long getWorkPlanNumber() {
        return workPlanNumber;
    }

    public void setWorkPlanNumber(Long workPlanNumber) {
        this.workPlanNumber = workPlanNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkPlanType() {
        return workPlanType;
    }

    public void setWorkPlanType(String workPlanType) {
        this.workPlanType = workPlanType;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Long getPictureNumber() {
        return pictureNumber;
    }

    public void setPictureNumber(Long status) {
        this.pictureNumber = status;
    }

    public Long getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(Long partNumber) {
        this.partNumber = partNumber;
    }

}
