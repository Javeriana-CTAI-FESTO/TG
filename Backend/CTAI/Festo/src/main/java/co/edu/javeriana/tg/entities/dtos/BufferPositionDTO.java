package co.edu.javeriana.tg.entities.dtos;

import java.util.Date;

import co.edu.javeriana.tg.entities.BufferPosition;

public class BufferPositionDTO {
    private Long resource;
    private Long buffer;
    private Long position;
    private Long partNumber;
    private Long orderNumber;
    private Long orderPosition;
    private Long type;
    private Long zone;
    private Long quantity;
    private Long quantityMax;
    private Date timestamp;
    private Long pallet;
    private Long box;
    private Long partNumberGroup;
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
        this.timestamp = bufferPosition.getTimestamp();
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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
}
