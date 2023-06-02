package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.Part;

public class PartDTO {
    private Long partNumber;
    private String description;
    private Long type;
    private Long workPlanNumber;
    private String picture;
    private Long basePallet;
    private Long mrpType;
    private Long safetyStock;
    private Long lotSize;
    private Long defaultResourceId;
    
    public PartDTO() {
    }

    public PartDTO(Part part){
        this.partNumber = part.getPartNumber();
        this.description = part.getDescription();
        this.type = part.getType();
        this.workPlanNumber = part.getWorkPlanNumber();
        this.picture = part.getPicture();
        this.basePallet = part.getBasePallet();
        this.mrpType = part.getMrpType();
        this.safetyStock = part.getSafetyStock();
        this.lotSize = part.getLotSize();
        this.defaultResourceId = part.getDefaultResourceId();
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
}
