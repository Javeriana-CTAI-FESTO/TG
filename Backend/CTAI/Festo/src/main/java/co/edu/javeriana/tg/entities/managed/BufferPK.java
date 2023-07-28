package co.edu.javeriana.tg.entities.managed;

import java.io.Serializable;

public class BufferPK implements Serializable {
    private Long resource;
    private Long bufferNumber;

    public BufferPK(Long resource, Long bufferNumber) {
        this.resource = resource;
        this.bufferNumber = bufferNumber;
    }

    public BufferPK() {
    }

    public Long getResource() {
        return resource;
    }

    public void setResource(Long resource) {
        this.resource = resource;
    }

    public Long getBufferNumber() {
        return bufferNumber;
    }

    public void setBufferNumber(Long bufferNumber) {
        this.bufferNumber = bufferNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((resource == null) ? 0 : resource.hashCode());
        result = prime * result + ((bufferNumber == null) ? 0 : bufferNumber.hashCode());
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
        BufferPK other = (BufferPK) obj;
        if (resource == null) {
            if (other.resource != null)
                return false;
        } else if (!resource.equals(other.resource))
            return false;
        if (bufferNumber == null) {
            if (other.bufferNumber != null)
                return false;
        } else if (!bufferNumber.equals(other.bufferNumber))
            return false;
        return true;
    }

}
