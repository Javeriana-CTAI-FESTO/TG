package co.edu.javeriana.tg.entities.managed;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "tblBufferPos")
@IdClass(BufferPositionPK.class)
// Posiciones (Cantidad de elementos) en un almacenamiento
public class BufferPosition {
    @Id
    @Column(name = "ResourceId", nullable = false)
    private Long resourceNumber;
    @Id
    @Column(name = "BufNo", nullable = false)
    private Long bufferNumber;
    @Id
    @Column(name = "BufPos", nullable = false)
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
    public BufferPosition(BufferPositionPK id) {
        this.resourceNumber = id.getResourceNumber();
        this.bufferNumber = id.getBufferNumber();
        this.bufferPosition = id.getBufferPosition();
    }
    public BufferPosition() {
    }
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((resourceNumber == null) ? 0 : resourceNumber.hashCode());
        result = prime * result + ((bufferNumber == null) ? 0 : bufferNumber.hashCode());
        result = prime * result + ((bufferPosition == null) ? 0 : bufferPosition.hashCode());
        result = prime * result + ((partNumber == null) ? 0 : partNumber.hashCode());
        result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
        result = prime * result + ((orderPosition == null) ? 0 : orderPosition.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((zone == null) ? 0 : zone.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((quantityMax == null) ? 0 : quantityMax.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((pallet == null) ? 0 : pallet.hashCode());
        result = prime * result + ((box == null) ? 0 : box.hashCode());
        result = prime * result + ((partNumberGroup == null) ? 0 : partNumberGroup.hashCode());
        result = prime * result + ((booked == null) ? 0 : booked.hashCode());
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
        BufferPosition other = (BufferPosition) obj;
        if (resourceNumber == null) {
            if (other.resourceNumber != null)
                return false;
        } else if (!resourceNumber.equals(other.resourceNumber))
            return false;
        if (bufferNumber == null) {
            if (other.bufferNumber != null)
                return false;
        } else if (!bufferNumber.equals(other.bufferNumber))
            return false;
        if (bufferPosition == null) {
            if (other.bufferPosition != null)
                return false;
        } else if (!bufferPosition.equals(other.bufferPosition))
            return false;
        if (partNumber == null) {
            if (other.partNumber != null)
                return false;
        } else if (!partNumber.equals(other.partNumber))
            return false;
        if (orderNumber == null) {
            if (other.orderNumber != null)
                return false;
        } else if (!orderNumber.equals(other.orderNumber))
            return false;
        if (orderPosition == null) {
            if (other.orderPosition != null)
                return false;
        } else if (!orderPosition.equals(other.orderPosition))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (zone == null) {
            if (other.zone != null)
                return false;
        } else if (!zone.equals(other.zone))
            return false;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        if (quantityMax == null) {
            if (other.quantityMax != null)
                return false;
        } else if (!quantityMax.equals(other.quantityMax))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        if (pallet == null) {
            if (other.pallet != null)
                return false;
        } else if (!pallet.equals(other.pallet))
            return false;
        if (box == null) {
            if (other.box != null)
                return false;
        } else if (!box.equals(other.box))
            return false;
        if (partNumberGroup == null) {
            if (other.partNumberGroup != null)
                return false;
        } else if (!partNumberGroup.equals(other.partNumberGroup))
            return false;
        if (booked == null) {
            if (other.booked != null)
                return false;
        } else if (!booked.equals(other.booked))
            return false;
        return true;
    }
    
}
