package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.managed.AutomaticOrder;
import io.swagger.v3.oas.annotations.media.Schema;

public class AutomaticOrderDTO {
    @Schema(name = "resource", example = "10", description = "Numero Long que identifica el id de un recurso", required = true)
    private Long resourceId;
    @Schema(name = "partNumber", example = "1", description = "Numero Long que identifica el id de una parte", required = true)
    private Long partNumber;
    @Schema(name = "lowLimit", example = "1", description = "Numero Long que identifica el limite inferior", required = true)
    private Long lowLimit;
    @Schema(name = "highLimit", example = "1", description = "Numero Long que identifica el limite superior", required = true)
    private Long highLimit;
    @Schema(name = "mClass", example = "1", description = "Numero Long que identifica el mClass", required = true)
    private Long mClass;
    @Schema(name = "mNumber", example = "1", description = "Numero Long que identifica el mNumber", required = true)
    private Long mNumber;
    @Schema(name = "workPlanNumber", example = "1", description = "Numero Long que identifica el numero de workPlan", required = true)
    private Long workPlanNumber;
    @Schema(name = "bufferNumber", example = "1", description = "Numero Long que identifica el numero de buffer", required = true)
    private Long bufferNumber;
    @Schema(name = "active", example = "1", description = "Indica si la orden esta activa", required = true)
    private Boolean active;
    @Schema(name = "description", example = "1", description = "Descripcion de la orden", required = true)
    private String description;
    @Schema(name = "noOrderNumber", example = "1", description = "Indica si la orden no tiene numero de orden", required = true)
    private Boolean noOrderNumber;
    @Schema(name = "autoOrderType", example = "1", description = "Indica el tipo de orden", required = true)
    private String autoOrderType;
    @Schema(name = "id", example = "1", description = "Identificador de la orden", required = true)
    private Long id;
    @Schema(name = "resourceThatSendMessageId", example = "1", description = "Identificador del recurso que envia el mensaje", required = true)
    private Long resourceThatSendMessageId;
    @Schema(name = "partNumberToCheck", example = "1", description = "Numero Long que identifica el id de una parte", required = true)
    private Long partNumberToCheck;
    @Schema(name = "dispo", example = "1", description = "Indica si la orden esta disponible", required = true)
    private Boolean dispo;

    public AutomaticOrderDTO() {
    }

    public AutomaticOrderDTO(AutomaticOrder order, String description) {
        this.resourceId = order.getResourceId();
        this.partNumber = order.getPartNumber();
        this.lowLimit = order.getLowLimit();
        this.highLimit = order.getHighLimit();
        this.mClass = order.getmClass();
        this.mNumber = order.getmNumber();
        this.workPlanNumber = order.getWorkPlanNumber();
        this.bufferNumber = order.getBufferNumber();
        this.active = order.getActive();
        this.description = order.getDescription();
        this.noOrderNumber = order.getNoOrderNumber();
        this.autoOrderType = description;
        this.id = order.getId();
        this.resourceThatSendMessageId = order.getResourceThatSendMessageId();
        this.partNumberToCheck = order.getPartNumberToCheck();
        this.dispo = order.getDispo();
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(Long partNumber) {
        this.partNumber = partNumber;
    }

    public Long getLowLimit() {
        return lowLimit;
    }

    public void setLowLimit(Long lowLimit) {
        this.lowLimit = lowLimit;
    }

    public Long getHighLimit() {
        return highLimit;
    }

    public void setHighLimit(Long highLimit) {
        this.highLimit = highLimit;
    }

    public Long getmClass() {
        return mClass;
    }

    public void setmClass(Long mClass) {
        this.mClass = mClass;
    }

    public Long getmNumber() {
        return mNumber;
    }

    public void setmNumber(Long mNumber) {
        this.mNumber = mNumber;
    }

    public Long getWorkPlanNumber() {
        return workPlanNumber;
    }

    public void setWorkPlanNumber(Long workPlanNumber) {
        this.workPlanNumber = workPlanNumber;
    }

    public Long getBufferNumber() {
        return bufferNumber;
    }

    public void setBufferNumber(Long bufferNumber) {
        this.bufferNumber = bufferNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getNoOrderNumber() {
        return noOrderNumber;
    }

    public void setNoOrderNumber(Boolean noOrderNumber) {
        this.noOrderNumber = noOrderNumber;
    }

    public String getAutoOrderType() {
        return autoOrderType;
    }

    public void setAutoOrderType(String autoOrderType) {
        this.autoOrderType = autoOrderType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceThatSendMessageId() {
        return resourceThatSendMessageId;
    }

    public void setResourceThatSendMessageId(Long resourceThatSendMessageId) {
        this.resourceThatSendMessageId = resourceThatSendMessageId;
    }

    public Long getPartNumberToCheck() {
        return partNumberToCheck;
    }

    public void setPartNumberToCheck(Long partNumberToCheck) {
        this.partNumberToCheck = partNumberToCheck;
    }

    public Boolean getDispo() {
        return dispo;
    }

    public void setDispo(Boolean dispo) {
        this.dispo = dispo;
    }
}
