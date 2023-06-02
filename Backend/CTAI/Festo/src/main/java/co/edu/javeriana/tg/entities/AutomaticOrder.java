package co.edu.javeriana.tg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tblAutomaticOrder")
public class AutomaticOrder {
    @Column(name = "ResourceId")
    private Long resourceId;
    @Column(name = "PNo")
    private Long partNumber;
    @Column(name = "LowLimit")
    private Long lowLimit;
    @Column(name = "HighLimit")
    private Long highLimit;
    @Column(name = "MClass")
    private Long mClass;
    @Column(name = "MNo")
    private Long mNumber;
    @Column(name = "WPNo")
    private Long workPlanNumber;
    @Column(name = "BufNo")
    private Long bufferNumber;
    @Column(name = "Active")
    private Boolean active;
    @Column(name = "Description")
    private String description;
    @Column(name = "NoONo")
    private Boolean noOrderNumber;
    @ManyToOne
    @JoinColumn(name = "AutoOrderType")
    private AutomaticOrderType autoOrderType;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "ResourceIdSend")
    private Long resourceThatSendMessageId;
    @Column(name = "PNoChk")
    private Long partNumberToCheck;
    @Column(name = "Dispo")
    private Boolean dispo;
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
    public AutomaticOrderType getAutoOrderType() {
        return autoOrderType;
    }
    public void setAutoOrderType(AutomaticOrderType autoOrderType) {
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
