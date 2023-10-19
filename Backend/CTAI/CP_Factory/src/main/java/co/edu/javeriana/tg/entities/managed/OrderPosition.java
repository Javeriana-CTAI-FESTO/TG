package co.edu.javeriana.tg.entities.managed;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "tblOrderPos")
@IdClass(OrderPositionPK.class)
// Posiciones de las ordenes pendientes por ser procesadas
public class OrderPosition {
    @Id
    @Column(name = "ONo", nullable = false)
    private Long order;
    @Id
    @Column(name = "OPos", nullable = false)
    private Long orderPosition;
    @Column(name = "PlanedStart")
    private Date plannedStart;
    @Column(name = "PlanedEnd")
    private Date plannedEnd;
    @Column(name = "Start")
    private Date realStart;
    @Column(name = "End")
    private Date realEnd;
    @Column(name = "WPNo")
    private Long workPlanNumber;
    @Column(name = "StepNo")
    private Long stepNumber;
    @Column(name = "MainOPos")
    private Long mainOrderPosition;
    @Column(name = "State")
    private Long state;
    @Column(name = "ResourceID")
    private Long resourceNumber;
    @Column(name = "OpNo")
    private Long operationNumber;
    @Column(name = "WONo")
    private Long workOrderNumber;
    @Column(name = "PNo")
    private Long part;
    @Column(name = "subOrderBlocked")
    private Boolean subOrderBlocked;
    @Column(name = "Error")
    private Boolean error;
    @Column(name = "OrderPNo")
    private Long orderPartNumber;
    public OrderPosition() {
    }
    public OrderPosition(OrderPositionPK testID) {
        this.order = testID.getOrder();
        this.orderPosition = testID.getOrderPosition();
    }
    public Long getOrder() {
        return order;
    }
    public void setOrder(Long orderNumber) {
        this.order = orderNumber;
    }
    public Long getOrderPosition() {
        return orderPosition;
    }
    public void setOrderPosition(Long orderPosition) {
        this.orderPosition = orderPosition;
    }
    public Date getPlannedStart() {
        return plannedStart;
    }
    public void setPlannedStart(Date plannedStart) {
        this.plannedStart = plannedStart;
    }
    public Date getPlannedEnd() {
        return plannedEnd;
    }
    public void setPlannedEnd(Date plannedEnd) {
        this.plannedEnd = plannedEnd;
    }
    public Date getRealStart() {
        return realStart;
    }
    public void setRealStart(Date realStart) {
        this.realStart = realStart;
    }
    public Date getRealEnd() {
        return realEnd;
    }
    public void setRealEnd(Date realEnd) {
        this.realEnd = realEnd;
    }
    public Long getWorkPlanNumber() {
        return workPlanNumber;
    }
    public void setWorkPlanNumber(Long workPlanNumber) {
        this.workPlanNumber = workPlanNumber;
    }
    public Long getStepNumber() {
        return stepNumber;
    }
    public void setStepNumber(Long stepNumber) {
        this.stepNumber = stepNumber;
    }
    public Long getMainOrderPosition() {
        return mainOrderPosition;
    }
    public void setMainOrderPosition(Long mainOrderPosition) {
        this.mainOrderPosition = mainOrderPosition;
    }
    public Long getState() {
        return state;
    }
    public void setState(Long state) {
        this.state = state;
    }
    public Long getResourceNumber() {
        return resourceNumber;
    }
    public void setResourceNumber(Long resourceNumber) {
        this.resourceNumber = resourceNumber;
    }
    public Long getOperationNumber() {
        return operationNumber;
    }
    public void setOperationNumber(Long operationNumber) {
        this.operationNumber = operationNumber;
    }
    public Long getWorkOrderNumber() {
        return workOrderNumber;
    }
    public void setWorkOrderNumber(Long workOrderNumber) {
        this.workOrderNumber = workOrderNumber;
    }
    public Long getPart() {
        return part;
    }
    public void setPart(Long partNumber) {
        this.part = partNumber;
    }
    public Boolean getSubOrderBlocked() {
        return subOrderBlocked;
    }
    public void setSubOrderBlocked(Boolean subOrderBlocked) {
        this.subOrderBlocked = subOrderBlocked;
    }
    public Boolean getError() {
        return error;
    }
    public void setError(Boolean error) {
        this.error = error;
    }
    public Long getOrderPartNumber() {
        return orderPartNumber;
    }
    public void setOrderPartNumber(Long orderPartNumber) {
        this.orderPartNumber = orderPartNumber;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        result = prime * result + ((orderPosition == null) ? 0 : orderPosition.hashCode());
        result = prime * result + ((plannedStart == null) ? 0 : plannedStart.hashCode());
        result = prime * result + ((plannedEnd == null) ? 0 : plannedEnd.hashCode());
        result = prime * result + ((realStart == null) ? 0 : realStart.hashCode());
        result = prime * result + ((realEnd == null) ? 0 : realEnd.hashCode());
        result = prime * result + ((workPlanNumber == null) ? 0 : workPlanNumber.hashCode());
        result = prime * result + ((stepNumber == null) ? 0 : stepNumber.hashCode());
        result = prime * result + ((mainOrderPosition == null) ? 0 : mainOrderPosition.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((resourceNumber == null) ? 0 : resourceNumber.hashCode());
        result = prime * result + ((operationNumber == null) ? 0 : operationNumber.hashCode());
        result = prime * result + ((workOrderNumber == null) ? 0 : workOrderNumber.hashCode());
        result = prime * result + ((part == null) ? 0 : part.hashCode());
        result = prime * result + ((subOrderBlocked == null) ? 0 : subOrderBlocked.hashCode());
        result = prime * result + ((error == null) ? 0 : error.hashCode());
        result = prime * result + ((orderPartNumber == null) ? 0 : orderPartNumber.hashCode());
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
        OrderPosition other = (OrderPosition) obj;
        if (order == null) {
            if (other.order != null)
                return false;
        } else if (!order.equals(other.order))
            return false;
        if (orderPosition == null) {
            if (other.orderPosition != null)
                return false;
        } else if (!orderPosition.equals(other.orderPosition))
            return false;
        if (plannedStart == null) {
            if (other.plannedStart != null)
                return false;
        } else if (!plannedStart.equals(other.plannedStart))
            return false;
        if (plannedEnd == null) {
            if (other.plannedEnd != null)
                return false;
        } else if (!plannedEnd.equals(other.plannedEnd))
            return false;
        if (realStart == null) {
            if (other.realStart != null)
                return false;
        } else if (!realStart.equals(other.realStart))
            return false;
        if (realEnd == null) {
            if (other.realEnd != null)
                return false;
        } else if (!realEnd.equals(other.realEnd))
            return false;
        if (workPlanNumber == null) {
            if (other.workPlanNumber != null)
                return false;
        } else if (!workPlanNumber.equals(other.workPlanNumber))
            return false;
        if (stepNumber == null) {
            if (other.stepNumber != null)
                return false;
        } else if (!stepNumber.equals(other.stepNumber))
            return false;
        if (mainOrderPosition == null) {
            if (other.mainOrderPosition != null)
                return false;
        } else if (!mainOrderPosition.equals(other.mainOrderPosition))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (resourceNumber == null) {
            if (other.resourceNumber != null)
                return false;
        } else if (!resourceNumber.equals(other.resourceNumber))
            return false;
        if (operationNumber == null) {
            if (other.operationNumber != null)
                return false;
        } else if (!operationNumber.equals(other.operationNumber))
            return false;
        if (workOrderNumber == null) {
            if (other.workOrderNumber != null)
                return false;
        } else if (!workOrderNumber.equals(other.workOrderNumber))
            return false;
        if (part == null) {
            if (other.part != null)
                return false;
        } else if (!part.equals(other.part))
            return false;
        if (subOrderBlocked == null) {
            if (other.subOrderBlocked != null)
                return false;
        } else if (!subOrderBlocked.equals(other.subOrderBlocked))
            return false;
        if (error == null) {
            if (other.error != null)
                return false;
        } else if (!error.equals(other.error))
            return false;
        if (orderPartNumber == null) {
            if (other.orderPartNumber != null)
                return false;
        } else if (!orderPartNumber.equals(other.orderPartNumber))
            return false;
        return true;
    }
}
