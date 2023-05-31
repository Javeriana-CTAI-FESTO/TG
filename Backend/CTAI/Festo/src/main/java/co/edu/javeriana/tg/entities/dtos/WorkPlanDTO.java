package co.edu.javeriana.tg.entities.dtos;


import co.edu.javeriana.tg.entities.WorkPlanDefinition;

public class WorkPlanDTO {
    private Long workPlanNumber;
    private String description;
    private String workPlanType;
    private String shortDescription;
    private Long pictureNumber;
    private Long partNumber;

    public WorkPlanDTO() {
    }

    public WorkPlanDTO(WorkPlanDefinition work_plan){
        this.workPlanNumber = work_plan.getWorkPlanNumber();
        this.description = work_plan.getDescription();
        this.workPlanType = work_plan.getWorkPlanType().getDescription();
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
