package co.edu.javeriana.tg.entities.managed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblParts")
// Partes disponibles en la planta
public class Part {
    @Id
    @Column(name = "PNo", nullable = false)
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
    public Part() {
    }
    public Part(Long testID) {
        this.partNumber = testID;
    }
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((partNumber == null) ? 0 : partNumber.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((workPlanNumber == null) ? 0 : workPlanNumber.hashCode());
        result = prime * result + ((picture == null) ? 0 : picture.hashCode());
        result = prime * result + ((basePallet == null) ? 0 : basePallet.hashCode());
        result = prime * result + ((mrpType == null) ? 0 : mrpType.hashCode());
        result = prime * result + ((safetyStock == null) ? 0 : safetyStock.hashCode());
        result = prime * result + ((lotSize == null) ? 0 : lotSize.hashCode());
        result = prime * result + ((defaultResourceId == null) ? 0 : defaultResourceId.hashCode());
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
        Part other = (Part) obj;
        if (partNumber == null) {
            if (other.partNumber != null)
                return false;
        } else if (!partNumber.equals(other.partNumber))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (workPlanNumber == null) {
            if (other.workPlanNumber != null)
                return false;
        } else if (!workPlanNumber.equals(other.workPlanNumber))
            return false;
        if (picture == null) {
            if (other.picture != null)
                return false;
        } else if (!picture.equals(other.picture))
            return false;
        if (basePallet == null) {
            if (other.basePallet != null)
                return false;
        } else if (!basePallet.equals(other.basePallet))
            return false;
        if (mrpType == null) {
            if (other.mrpType != null)
                return false;
        } else if (!mrpType.equals(other.mrpType))
            return false;
        if (safetyStock == null) {
            if (other.safetyStock != null)
                return false;
        } else if (!safetyStock.equals(other.safetyStock))
            return false;
        if (lotSize == null) {
            if (other.lotSize != null)
                return false;
        } else if (!lotSize.equals(other.lotSize))
            return false;
        if (defaultResourceId == null) {
            if (other.defaultResourceId != null)
                return false;
        } else if (!defaultResourceId.equals(other.defaultResourceId))
            return false;
        return true;
    }
}
