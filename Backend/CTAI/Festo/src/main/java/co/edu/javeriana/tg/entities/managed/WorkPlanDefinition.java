package co.edu.javeriana.tg.entities.managed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblWorkPlanDef")
// Planes de trabajo (Posibles productos) que pueden hacerse en la planta
public class WorkPlanDefinition {
    @Id
    @Column(name = "WPNo", nullable = false)
    private Long workPlanNumber;
    @Column(name = "Description", nullable = true)
    private String description;
    @Column(name = "Type", nullable = true)
    private Long workPlanType;
    @Column(name = "Short", nullable = true)
    private String shortDescription;
    @Column(name = "PictureNo", nullable = true)
    private Long pictureNumber;
    @Column(name="PNo", nullable = true)
    private Long partNumber;

    public WorkPlanDefinition() {
    }

    public WorkPlanDefinition(Long testID) {
        this.workPlanNumber = testID;
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
        WorkPlanDefinition other = (WorkPlanDefinition) obj;
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
        return true;
    }
}
