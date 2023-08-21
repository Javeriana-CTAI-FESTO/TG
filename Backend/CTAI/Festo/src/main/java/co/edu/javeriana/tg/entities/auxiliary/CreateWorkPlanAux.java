package co.edu.javeriana.tg.entities.auxiliary;

import java.util.Arrays;

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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((workPlanNumber == null) ? 0 : workPlanNumber.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((workPlanType == null) ? 0 : workPlanType.hashCode());
        result = prime * result + ((shortDescription == null) ? 0 : shortDescription.hashCode());
        result = prime * result + ((pictureNumber == null) ? 0 : pictureNumber.hashCode());
        result = prime * result + ((partNumber == null) ? 0 : partNumber.hashCode());
        result = prime * result + Arrays.hashCode(operations);
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CreateWorkPlanAux other = (CreateWorkPlanAux) obj;
        if (workPlanNumber == null) {
            if (other.workPlanNumber != null)
                return false;
        } else if (!workPlanNumber.equals(other.workPlanNumber))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (workPlanType == null) {
            if (other.workPlanType != null)
                return false;
        } else if (!workPlanType.equals(other.workPlanType))
            return false;
        if (shortDescription == null) {
            if (other.shortDescription != null)
                return false;
        } else if (!shortDescription.equals(other.shortDescription))
            return false;
        if (pictureNumber == null) {
            if (other.pictureNumber != null)
                return false;
        } else if (!pictureNumber.equals(other.pictureNumber))
            return false;
        if (partNumber == null) {
            if (other.partNumber != null)
                return false;
        } else if (!partNumber.equals(other.partNumber))
            return false;
        if (!Arrays.equals(operations, other.operations))
            return false;
        return true;
    }
    
}
