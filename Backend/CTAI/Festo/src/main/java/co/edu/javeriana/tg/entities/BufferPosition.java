package co.edu.javeriana.tg.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "tblBufferPos")
@IdClass(BufferPositionPK.class)
public class BufferPosition {
    @Id
    @Column(name = "ResourceId")
    private Long resourceNumber;
    @Id
    @Column(name = "BufNo")
    private Long bufferNumber;
    @Id
    @Column(name = "BufPos")
    private Long bufferPosition;
    @Column(name = "PNo")
    private Long partNumber;
    @Column(name = "ONo")
    private Long orderNumber;
    @Column(name = "OPos")
    private Long orderPosition;
    @Column(name = "Type")
    private Long type;
    @Column(name = "Zone")
    private Long zone;
    @Column(name = "Quantity")
    private Long quantity;
    @Column(name = "QuantityMax")
    private Long quantityMax;
    @Column(name = "TimeStamp")
    private Date timestamp;
    @Column(name = "PalletID")
    private Long pallet;
    @Column(name = "BoxID")
    private Long box;
    @Column(name = "PNoGroup")
    private Long partNumberGroup;
    @Column(name = "Booked")
    private Boolean booked;
    public Long getResourceNumber() {
        return resourceNumber;
    }
    public void setResourceNumber(Long resource) {
        this.resourceNumber = resource;
    }
    public Long getBufferNumber() {
        return bufferNumber;
    }
    public void setBufferNumber(Long buffer) {
        this.bufferNumber = buffer;
    }
    public Long getBufferPosition() {
        return bufferPosition;
    }
    public void setBufferPosition(Long position) {
        this.bufferPosition = position;
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
