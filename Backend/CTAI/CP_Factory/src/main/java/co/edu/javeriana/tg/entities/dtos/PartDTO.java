package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.managed.Part;
import io.swagger.v3.oas.annotations.media.Schema;

public class PartDTO {
    @Schema(name = "partNumber", example = "114", description = "Numero que representa un parte en el sistema", required = true)
    private Long partNumber;
    @Schema(name = "description", example = "Front Cover Box", description = "Descripción de parte, nombre", required = true)
    private String description;
    @Schema(name = "type", example = "1", description = "Indica el tipo de parte", required = true)
    private Long type;
    @Schema(name = "workPlanNumber", example = "1", description = "Numero de plan de trabajo", required = true)
    private Long workPlanNumber;
    @Schema(name = "picture", example = "imagen.png", description = "Imagen de la parte", required = false)
    private String picture;
    @Schema(name = "basePallet", example = "25", description = "Numero de base de pallet", required = true)
    private Long basePallet;
    @Schema(name = "mrpType", example = "1", description = "Indica el tipo de mrp", required = false)
    private Long mrpType;
    @Schema(name = "safetyStock", example = "2", description = "Cantidad de stock de seguridad", required = false)
    private Long safetyStock;
    @Schema(name = "lotSize", example = "1", description = "Cantidad de lotes", required = false)
    private Long lotSize;
    @Schema(name = "defaultResourceId", example = "11", description = "Indica el ID del recurso por defecto", required = true)
    private Long defaultResourceId;

    public PartDTO() {
    }

    public PartDTO(Part part) {
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
