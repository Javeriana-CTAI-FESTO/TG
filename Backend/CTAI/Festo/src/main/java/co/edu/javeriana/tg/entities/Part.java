package co.edu.javeriana.tg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblParts")
// Partes disponibles en la planta
public class Part {
    @Id
    @Column(name = "PNo")
    private Long partNumber;
    @Column(name = "Description")
    private String description;
    @Column(name = "Type")
    private Long type;
    @Column(name = "WPNo")
    private Long workPlanNumber;
    @Column(name = "Picture")
    private String picture;
    @Column(name = "BasePallet")
    private Long basePallet;
    @Column(name = "MrpType")
    private Long mrpType;
    @Column(name = "SafetyStock")
    private Long safetyStock;
    @Column(name = "LotSize")
    private Long lotSize;
    @Column(name = "DefResourceId")
    private Long defaultResourceId;
    public Long getPartNumber() {
        return partNumber;
    }
    public void setPartNumber(Long partNumber) {
        this.partNumber = partNumber;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Long getType() {
        return type;
    }
    public void setType(Long type) {
        this.type = type;
    }
    public Long getWorkPlanNumber() {
        return workPlanNumber;
    }
    public void setWorkPlanNumber(Long workPlanNumber) {
        this.workPlanNumber = workPlanNumber;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public Long getBasePallet() {
        return basePallet;
    }
    public void setBasePallet(Long basePallet) {
        this.basePallet = basePallet;
    }
    public Long getMrpType() {
        return mrpType;
    }
    public void setMrpType(Long mrpType) {
        this.mrpType = mrpType;
    }
    public Long getSafetyStock() {
        return safetyStock;
    }
    public void setSafetyStock(Long safetyStock) {
        this.safetyStock = safetyStock;
    }
    public Long getLotSize() {
        return lotSize;
    }
    public void setLotSize(Long lotSize) {
        this.lotSize = lotSize;
    }
    public Long getDefaultResourceId() {
        return defaultResourceId;
    }
    public void setDefaultResourceId(Long defaultResourceId) {
        this.defaultResourceId = defaultResourceId;
    }
}
