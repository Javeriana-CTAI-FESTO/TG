package co.edu.javeriana.tg.entities.dtos;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import co.edu.javeriana.tg.entities.managed.FinishedOrder;
import co.edu.javeriana.tg.entities.managed.Order;
import co.edu.javeriana.tg.entities.managed.OrderPosition;
import io.swagger.v3.oas.annotations.media.Schema;

public class OrderDTO {
    @Schema(name = "orderNumber", example = "4668", description = "Numero que representa una orden en el sistema", required = true)
    private Long orderNumber;
    @Schema(name = "cliente", example = "20271125", description = "Numero de cliente que realiza la orden", required = true)
    private ClientDTO client;
    @Schema(name = "orderState", example = "1", description = "Indica el estado de la orden", required = true)
    private Long orderState;
    @Schema(name = "enabled", example = "true", description = "Indica si la orden esta habilitada a producción", required = true)
    private Boolean enabled;
    @Schema(name = "release", example = "2021-11-25", description = "Fecha de liberacion de la orden", required = true)
    private ZonedDateTime release;
    @Schema(name = "orderPosition", example = "1", description = "Numero de posicion de la orden", required = true)
    private Long orderPosition;
    @Schema(name = "plannedStart", example = "2021-11-25", description = "Fecha de inicio de la orden", required = true)
    private ZonedDateTime plannedStart;
    @Schema(name = "plannedEnd", example = "2021-11-25", description = "Fecha de fin de la orden", required = true)
    private ZonedDateTime plannedEnd;
    @Schema(name = "realStart", example = "2021-11-25", description = "Fecha de inicio real de la orden", required = true)
    private ZonedDateTime realStart;
    @Schema(name = "realEnd", example = "2021-11-25", description = "Fecha de fin real de la orden", required = true)
    private ZonedDateTime realEnd;
    @Schema(name = "workPlanNumber", example = "1", description = "Numero de plan de trabajo", required = true)
    private Long workPlanNumber;
    @Schema(name = "stepNumber", example = "1", description = "Numero de paso de la orden", required = true)
    private Long stepNumber;
    @Schema(name = "mainOrderPosition", example = "1", description = "Numero de posicion principal de la orden", required = true)
    private Long mainOrderPosition;
    @Schema(name = "state", example = "1", description = "Indica el estado de la orden", required = true)
    private Long state;
    @Schema(name = "resourceNumber", example = "1", description = "Numero de recurso de la orden", required = true)
    private Long resourceNumber;
    @Schema(name = "operationNumber", example = "1", description = "Numero de operacion de la orden", required = true)
    private Long operationNumber;
    @Schema(name = "workOrderNumber", example = "1", description = "Numero de orden de trabajo", required = true)
    private Long workOrderNumber;
    @Schema(name = "part", example = "1", description = "¨PartDTO usada en la orden", required = true)
    private PartDTO part;
    @Schema(name = "subOrderBlocked", example = "true", description = "Indica si la orden esta bloqueada", required = true)
    private Boolean subOrderBlocked;
    @Schema(name = "error", example = "true", description = "Indica si hay error en la orden", required = true)
    private Boolean error;
    @Schema(name = "status", example = "1", description = "Indica el estado de la orden", required = true)
    private String status;
    @Schema(name = "orderPartNumber", example = "1", description = "Numero de parte de la orden", required = true)
    private Long orderPartNumber;
    @Schema(name = "timeNeeded", example = "1", description = "Tiempo requerido para la orden", required = true)
    private String timeNeeded;

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

    public OrderDTO(OrderPosition order, ClientDTO client, Long orderState, Boolean orderEnabled, Date orderRelease,
            PartDTO part) {
        this.orderNumber = order.getOrder();
        this.orderState = orderState;
        this.enabled = orderEnabled;
        this.release = ZonedDateTime.ofInstant(orderRelease.toInstant(), ZoneId.of("America/Bogota"));
        this.orderPosition = order.getOrderPosition();
        this.plannedStart = ZonedDateTime.ofInstant(order.getPlannedStart().toInstant(), ZoneId.of("America/Bogota"));
        this.plannedEnd = ZonedDateTime.ofInstant(order.getPlannedEnd().toInstant(), ZoneId.of("America/Bogota"));
        this.realStart = ZonedDateTime.ofInstant(order.getRealStart().toInstant(), ZoneId.of("America/Bogota"));
        ;
        this.realEnd = ZonedDateTime.ofInstant(order.getRealEnd().toInstant(), ZoneId.of("America/Bogota"));
        ;
        this.workPlanNumber = order.getWorkPlanNumber();
        this.stepNumber = order.getStepNumber();
        this.mainOrderPosition = order.getMainOrderPosition();
        this.state = order.getState();
        this.client = client;
        this.resourceNumber = order.getResourceNumber();
        this.operationNumber = order.getOperationNumber();
        this.workOrderNumber = order.getWorkOrderNumber();
        this.part = part;
        this.subOrderBlocked = order.getSubOrderBlocked();
        this.error = order.getError();
        this.orderPartNumber = order.getOrderPartNumber();
    }

    public OrderDTO(OrderPosition order, ClientDTO client, String status, Long orderState, Boolean orderEnabled,
            Date orderRelease, PartDTO part) {
        this.orderNumber = order.getOrder();
        this.orderState = orderState;
        this.enabled = orderEnabled;
        this.release = ZonedDateTime.ofInstant(orderRelease.toInstant(), ZoneId.of("America/Bogota"));
        this.orderPosition = order.getOrderPosition();
        this.status = status;
        this.workPlanNumber = order.getWorkPlanNumber();
        this.stepNumber = order.getStepNumber();
        this.mainOrderPosition = order.getMainOrderPosition();
        this.state = order.getState();
        this.client = client;
        this.resourceNumber = order.getResourceNumber();
        this.operationNumber = order.getOperationNumber();
        this.workOrderNumber = order.getWorkOrderNumber();
        this.part = part;
        this.subOrderBlocked = order.getSubOrderBlocked();
        this.error = order.getError();
        this.orderPartNumber = order.getOrderPartNumber();
    }

    public OrderDTO(OrderPosition order, ClientDTO client, Long time, Long orderState, Boolean orderEnabled,
            Date orderRelease, PartDTO part) {
        this.orderNumber = order.getOrder();
        this.orderState = orderState;
        this.enabled = orderEnabled;
        this.release = ZonedDateTime.ofInstant(orderRelease.toInstant(), ZoneId.of("America/Bogota"));
        this.orderPosition = order.getOrderPosition();
        this.timeNeeded = time + "s";
        this.workPlanNumber = order.getWorkPlanNumber();
        this.stepNumber = order.getStepNumber();
        this.mainOrderPosition = order.getMainOrderPosition();
        this.state = order.getState();
        this.client = client;
        this.resourceNumber = order.getResourceNumber();
        this.operationNumber = order.getOperationNumber();
        this.workOrderNumber = order.getWorkOrderNumber();
        this.part = part;
        this.subOrderBlocked = order.getSubOrderBlocked();
        this.error = order.getError();
        this.orderPartNumber = order.getOrderPartNumber();
    }

    public OrderDTO(Order order, ClientDTO client) {
        this.orderNumber = order.getOrderNumber();
        this.orderState = order.getState();
        this.enabled = order.getEnabled();
        this.release = ZonedDateTime.ofInstant(order.getRelease().toInstant(), ZoneId.of("America/Bogota"));
        this.client = client;
    }

    public OrderDTO(FinishedOrder order, ClientDTO client) {
        this.orderNumber = order.getOrderNumber();
        this.orderState = order.getState();
        this.enabled = order.getEnabled();
        this.release = ZonedDateTime.ofInstant(order.getRelease().toInstant(), ZoneId.of("America/Bogota"));
        this.client = client;
    }

    public OrderDTO(Order order, ClientDTO client, String status) {
        this.orderNumber = order.getOrderNumber();
        this.orderState = order.getState();
        this.enabled = order.getEnabled();
        this.release = ZonedDateTime.ofInstant(order.getRelease().toInstant(), ZoneId.of("America/Bogota"));
        this.client = client;
        this.status = status;
    }

    public OrderDTO(FinishedOrder order, ClientDTO client, String status) {
        this.orderNumber = order.getOrderNumber();
        this.orderState = order.getState();
        this.enabled = order.getEnabled();
        this.release = ZonedDateTime.ofInstant(order.getRelease().toInstant(), ZoneId.of("America/Bogota"));
        this.client = client;
        this.status = status;
    }

    public OrderDTO(Order order, ClientDTO client, Long time) {
        this.orderNumber = order.getOrderNumber();
        this.orderState = order.getState();
        this.enabled = order.getEnabled();
        this.release = ZonedDateTime.ofInstant(order.getRelease().toInstant(), ZoneId.of("America/Bogota"));
        this.client = client;
        this.timeNeeded = time + "s";
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public void setPart(PartDTO part) {
        this.part = part;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeNeeded() {
        return timeNeeded;
    }

    public void setTimeNeeded(String timeNeeded) {
        this.timeNeeded = timeNeeded;
    }

    public ZonedDateTime getRelease() {
        return release;
    }

    public void setRelease(ZonedDateTime release) {
        this.release = release;
    }

    public ZonedDateTime getPlannedStart() {
        return plannedStart;
    }

    public void setPlannedStart(ZonedDateTime plannedStart) {
        this.plannedStart = plannedStart;
    }

    public ZonedDateTime getPlannedEnd() {
        return plannedEnd;
    }

    public void setPlannedEnd(ZonedDateTime plannedEnd) {
        this.plannedEnd = plannedEnd;
    }

    public ZonedDateTime getRealStart() {
        return realStart;
    }

    public void setRealStart(ZonedDateTime realStart) {
        this.realStart = realStart;
    }

    public ZonedDateTime getRealEnd() {
        return realEnd;
    }

    public void setRealEnd(ZonedDateTime realEnd) {
        this.realEnd = realEnd;
    }
}
