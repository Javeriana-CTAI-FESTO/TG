package co.edu.javeriana.tg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tblWorkPlanDef")
public class WorkPlanDefinition {
    @Id
    @Column(name = "WPNo", nullable = false)
    private Long workPlanNumber;
    @Column(name = "Description", nullable = true)
    private String description;
    @ManyToOne(optional = true)
    @JoinColumn(name = "Type", nullable = true)
    private WorkPlanType workPlanType;
    @Column(name = "Short", nullable = true)
    private String shortDescription;
    @Column(name = "PictureNo", nullable = true)
    private Long pictureNumber;
    @Column(name="PNo", nullable = true)
    private Long partNumber;

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
    public WorkPlanType getWorkPlanType() {
        return workPlanType;
    }
    public void setWorkPlanType(WorkPlanType workPlanType) {
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
}
