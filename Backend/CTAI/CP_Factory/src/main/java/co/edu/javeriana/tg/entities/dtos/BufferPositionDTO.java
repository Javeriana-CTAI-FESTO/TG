package co.edu.javeriana.tg.entities.dtos;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import co.edu.javeriana.tg.entities.managed.BufferPosition;
import io.swagger.v3.oas.annotations.media.Schema;

public class BufferPositionDTO {
    @Schema(name = "resource", example = "10", description = "Long, recurso utilizado por el buffer", required = true)
    private Long resource;
    @Schema(name = "buffer", example = "1", description = "Numero Long que identifica al buffer", required = true)
    private Long buffer;
    @Schema(name = "position", example = "1", description = "Indica la posicion del buffer", required = true)
    private Long position;
    @Schema(name = "partNumber", example = "1", description = "Indica el numero de parte", required = false)
    private Long partNumber;
    @Schema(name = "orderNumber", example = "1", description = "Indica el numero de orden", required = false)
    private Long orderNumber;
    @Schema(name = "orderPosition", example = "1", description = "Indica la posicion de la orden", required = false)
    private Long orderPosition;
    @Schema(name = "type", example = "1", description = "Indica el tipo de buffer", required = false)
    private Long type;
    @Schema(name = "zone", example = "1", description = "Indica la zona", required = false)
    private Long zone;
    @Schema(name = "quantity", example = "1", description = "Indica la cantidad", required = false)
    private Long quantity;
    @Schema(name = "quantityMax", example = "1", description = "Indica la cantidad maxima", required = false)
    private Long quantityMax;
    @Schema(name = "timestamp", example = "1", description = "Indica la marca de tiempo", required = false)
    private ZonedDateTime timestamp;
    @Schema(name = "pallet", example = "1", description = "Indica el pallet", required = false)
    private Long pallet;
    @Schema(name = "box", example = "1", description = "Indica la caja", required = false)
    private Long box;
    @Schema(name = "partNumberGroup", example = "1", description = "Indica el grupo de parte", required = false)
    private Long partNumberGroup;
    @Schema(name = "booked", example = "1", description = "Indica si esta reservado", required = false)
    private Boolean booked;

    public BufferPositionDTO() {
    }

    public BufferPositionDTO(BufferPosition bufferPosition) {
        this.resource = bufferPosition.getResourceNumber();
        this.buffer = bufferPosition.getBufferNumber();
        this.position = bufferPosition.getBufferPosition();
        this.partNumber = bufferPosition.getPartNumber();
        this.orderNumber = bufferPosition.getOrderNumber();
        this.orderPosition = bufferPosition.getOrderPosition();
        this.type = bufferPosition.getType();
        this.zone = bufferPosition.getZone();
        this.quantity = bufferPosition.getQuantity();
        this.quantityMax = bufferPosition.getQuantityMax();
        this.timestamp = ZonedDateTime.ofInstant(bufferPosition.getTimestamp().toInstant(), ZoneId.of("America/Bogota"));
        this.pallet = bufferPosition.getPallet();
        this.box = bufferPosition.getBox();
        this.partNumberGroup = bufferPosition.getPartNumberGroup();
        this.booked = bufferPosition.getBooked();
    }

    public Long getResource() {
        return resource;
    }

    public void setResource(Long resource) {
        this.resource = resource;
    }

    public Long getBuffer() {
        return buffer;
    }

    public void setBuffer(Long buffer) {
        this.buffer = buffer;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Long getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(Long partNumber) {
        this.partNumber = partNumber;
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

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getZone() {
        return zone;
    }

    public void setZone(Long zone) {
        this.zone = zone;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getQuantityMax() {
        return quantityMax;
    }

    public void setQuantityMax(Long quantityMax) {
        this.quantityMax = quantityMax;
    }
    public Long getPallet() {
        return pallet;
    }

    public void setPallet(Long pallet) {
        this.pallet = pallet;
    }

    public Long getBox() {
        return box;
    }

    public void setBox(Long box) {
        this.box = box;
    }

    public Long getPartNumberGroup() {
        return partNumberGroup;
    }

    public void setPartNumberGroup(Long partNumberGroup) {
        this.partNumberGroup = partNumberGroup;
    }

    public Boolean getBooked() {
        return booked;
    }

    public void setBooked(Boolean booked) {
        this.booked = booked;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
