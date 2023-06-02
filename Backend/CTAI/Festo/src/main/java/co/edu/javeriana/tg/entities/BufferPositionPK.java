package co.edu.javeriana.tg.entities;

import java.io.Serializable;

public class BufferPositionPK implements Serializable {
    private Long resourceNumber;
    private Long bufferNumber;
    private Long bufferPosition;
    public BufferPositionPK(Long resource, Long bufferNumber, Long bufferPosition) {
        this.resourceNumber = resource;
        this.bufferNumber = bufferNumber;
        this.bufferPosition = bufferPosition;
    }
    public BufferPositionPK() {
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
    public void setBufferNumber(Long bufferNumber) {
        this.bufferNumber = bufferNumber;
    }
    public Long getBufferPosition() {
        return bufferPosition;
    }
    public void setBufferPosition(Long bufferPosition) {
        this.bufferPosition = bufferPosition;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((resourceNumber == null) ? 0 : resourceNumber.hashCode());
        result = prime * result + ((bufferNumber == null) ? 0 : bufferNumber.hashCode());
        result = prime * result + ((bufferPosition == null) ? 0 : bufferPosition.hashCode());
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
        BufferPositionPK other = (BufferPositionPK) obj;
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
        return true;
    }
}
