package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.AutomaticOrder;

public class AutomaticOrderDTO {
    private Long resourceId;
    private Long partNumber;
    private Long lowLimit;
    private Long highLimit;
    private Long mClass;
    private Long mNumber;
    private Long workPlanNumber;
    private Long bufferNumber;
    private Boolean active;
    private String description;
    private Boolean noOrderNumber;
    private String autoOrderType;
    private Long id;
    private Long resourceThatSendMessageId;
    private Long partNumberToCheck;
    private Boolean dispo;
    public AutomaticOrderDTO() {
    }
    public AutomaticOrderDTO(AutomaticOrder order) {
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
        this.autoOrderType = order.getAutoOrderType().getDescription();
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
