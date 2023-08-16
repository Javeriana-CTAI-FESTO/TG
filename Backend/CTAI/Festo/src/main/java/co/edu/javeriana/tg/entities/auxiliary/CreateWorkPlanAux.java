package co.edu.javeriana.tg.entities.auxiliary;

public class CreateWorkPlanAux {
    private Long workPlanNumber;
    private String description;
    private Long workPlanType;
    private String shortDescription;
    private Long pictureNumber;
    private Long partNumber;
    private OperationToPerformInWorkplanAux[] operations;
    public CreateWorkPlanAux(Long workPlanNumber, String description, Long workPlanType, String shortDescription,
            Long pictureNumber, Long partNumber, OperationToPerformInWorkplanAux[] operations) {
        this.workPlanNumber = workPlanNumber;
        this.description = description;
        this.workPlanType = workPlanType;
        this.shortDescription = shortDescription;
        this.pictureNumber = pictureNumber;
        this.partNumber = partNumber;
        this.operations = operations;
    }
    public CreateWorkPlanAux() {
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
    public Long getWorkPlanType() {
        return workPlanType;
    }
    public void setWorkPlanType(Long workPlanType) {
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
    public void setPictureNumber(Long pictureNumber) {
        this.pictureNumber = pictureNumber;
    }
    public Long getPartNumber() {
        return partNumber;
    }
    public void setPartNumber(Long partNumber) {
        this.partNumber = partNumber;
    }
    public OperationToPerformInWorkplanAux[] getOperations() {
        return operations;
    }
    public void setOperations(OperationToPerformInWorkplanAux[] operations) {
        this.operations = operations;
    }
    
}
