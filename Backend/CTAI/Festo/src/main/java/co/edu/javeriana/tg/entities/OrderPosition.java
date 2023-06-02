package co.edu.javeriana.tg.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tblOrderPos")
@IdClass(OrderPositionPK.class)
public class OrderPosition {
    @Id
    @ManyToOne
    @JoinColumn(name = "ONo")
    private Order order;
    @Id
    @Column(name = "OPos")
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
    @ManyToOne
    @JoinColumn(name = "PNo")
    private Part part;
    @Column(name = "subOrderBlocked")
    private Boolean subOrderBlocked;
    @Column(name = "Error")
    private Boolean error;
    @Column(name = "OrderPNo")
    private Long orderPartNumber;
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order orderNumber) {
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
    public Part getPart() {
        return part;
    }
    public void setPart(Part partNumber) {
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
}
