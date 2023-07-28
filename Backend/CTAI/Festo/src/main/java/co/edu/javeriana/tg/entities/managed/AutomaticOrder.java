package co.edu.javeriana.tg.entities.managed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    @Column(name = "AutoOrderType")
    private Long autoOrderType;
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "ResourceIdSend")
    private Long resourceThatSendMessageId;
    @Column(name = "PNoChk")
    private Long partNumberToCheck;
    @Column(name = "Dispo")
    private Boolean dispo;

    public AutomaticOrder() {
    }

    public AutomaticOrder(Long id) {
        this.id = id;
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

    public Long getAutoOrderType() {
        return autoOrderType;
    }

    public void setAutoOrderType(Long autoOrderType) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((resourceId == null) ? 0 : resourceId.hashCode());
        result = prime * result + ((partNumber == null) ? 0 : partNumber.hashCode());
        result = prime * result + ((lowLimit == null) ? 0 : lowLimit.hashCode());
        result = prime * result + ((highLimit == null) ? 0 : highLimit.hashCode());
        result = prime * result + ((mClass == null) ? 0 : mClass.hashCode());
        result = prime * result + ((mNumber == null) ? 0 : mNumber.hashCode());
        result = prime * result + ((workPlanNumber == null) ? 0 : workPlanNumber.hashCode());
        result = prime * result + ((bufferNumber == null) ? 0 : bufferNumber.hashCode());
        result = prime * result + ((active == null) ? 0 : active.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((noOrderNumber == null) ? 0 : noOrderNumber.hashCode());
        result = prime * result + ((autoOrderType == null) ? 0 : autoOrderType.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((resourceThatSendMessageId == null) ? 0 : resourceThatSendMessageId.hashCode());
        result = prime * result + ((partNumberToCheck == null) ? 0 : partNumberToCheck.hashCode());
        result = prime * result + ((dispo == null) ? 0 : dispo.hashCode());
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
        AutomaticOrder other = (AutomaticOrder) obj;
        if (resourceId == null) {
            if (other.resourceId != null)
                return false;
        } else if (!resourceId.equals(other.resourceId))
            return false;
        if (partNumber == null) {
            if (other.partNumber != null)
                return false;
        } else if (!partNumber.equals(other.partNumber))
            return false;
        if (lowLimit == null) {
            if (other.lowLimit != null)
                return false;
        } else if (!lowLimit.equals(other.lowLimit))
            return false;
        if (highLimit == null) {
            if (other.highLimit != null)
                return false;
        } else if (!highLimit.equals(other.highLimit))
            return false;
        if (mClass == null) {
            if (other.mClass != null)
                return false;
        } else if (!mClass.equals(other.mClass))
            return false;
        if (mNumber == null) {
            if (other.mNumber != null)
                return false;
        } else if (!mNumber.equals(other.mNumber))
            return false;
        if (workPlanNumber == null) {
            if (other.workPlanNumber != null)
                return false;
        } else if (!workPlanNumber.equals(other.workPlanNumber))
            return false;
        if (bufferNumber == null) {
            if (other.bufferNumber != null)
                return false;
        } else if (!bufferNumber.equals(other.bufferNumber))
            return false;
        if (active == null) {
            if (other.active != null)
                return false;
        } else if (!active.equals(other.active))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (noOrderNumber == null) {
            if (other.noOrderNumber != null)
                return false;
        } else if (!noOrderNumber.equals(other.noOrderNumber))
            return false;
        if (autoOrderType == null) {
            if (other.autoOrderType != null)
                return false;
        } else if (!autoOrderType.equals(other.autoOrderType))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (resourceThatSendMessageId == null) {
            if (other.resourceThatSendMessageId != null)
                return false;
        } else if (!resourceThatSendMessageId.equals(other.resourceThatSendMessageId))
            return false;
        if (partNumberToCheck == null) {
            if (other.partNumberToCheck != null)
                return false;
        } else if (!partNumberToCheck.equals(other.partNumberToCheck))
            return false;
        if (dispo == null) {
            if (other.dispo != null)
                return false;
        } else if (!dispo.equals(other.dispo))
            return false;
        return true;
    }
}
