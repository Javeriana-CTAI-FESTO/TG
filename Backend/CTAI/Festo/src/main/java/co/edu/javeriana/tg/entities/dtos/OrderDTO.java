package co.edu.javeriana.tg.entities.dtos;

import java.util.Date;

import co.edu.javeriana.tg.entities.OrderPosition;

public class OrderDTO {
    private Long orderNumber;
    private Long orderState;
    private Boolean enabled;
    private Date release;
    private Long orderPosition;
    private Date plannedStart;
    private Date plannedEnd;
    private Date realStart;
    private Date realEnd;
    private Long workPlanNumber;
    private Long stepNumber;
    private Long mainOrderPosition;
    private Long state;
    private Long resourceNumber;
    private Long operationNumber;
    private Long workOrderNumber;
    private PartDTO part;
    private Boolean subOrderBlocked;
    private Boolean error;
    private Long orderPartNumber;

    public OrderDTO() {
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
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

    public PartDTO getPart() {
        return part;
    }

    public void setPartNumber(PartDTO partNumber) {
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

    public Long getOrderState() {
        return orderState;
    }

    public void setOrderState(Long orderState) {
        this.orderState = orderState;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public OrderDTO(OrderPosition order) {
        this.orderNumber = order.getOrder().getOrderNumber();
        this.orderState = order.getOrder().getState();
        this.enabled = order.getOrder().getEnabled();
        this.release = order.getOrder().getRelease();
        this.orderPosition = order.getOrderPosition();
        this.plannedStart = order.getPlannedStart();
        this.plannedEnd = order.getPlannedEnd();
        this.realStart = order.getRealStart();
        this.realEnd = order.getRealEnd();
        this.workPlanNumber = order.getWorkPlanNumber();
        this.stepNumber = order.getStepNumber();
        this.mainOrderPosition = order.getMainOrderPosition();
        this.state = order.getState();
        this.resourceNumber = order.getResourceNumber();
        this.operationNumber = order.getOperationNumber();
        this.workOrderNumber = order.getWorkOrderNumber();
        this.part = new PartDTO(order.getPart());
        this.subOrderBlocked = order.getSubOrderBlocked();
        this.error = order.getError();
        this.orderPartNumber = order.getOrderPartNumber();
    }
}