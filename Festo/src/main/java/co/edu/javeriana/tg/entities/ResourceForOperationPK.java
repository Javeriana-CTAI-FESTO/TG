package co.edu.javeriana.tg.entities;
import java.io.Serializable;

public class ResourceForOperationPK implements Serializable{
    private Long resource;
    private Long operation;
    public ResourceForOperationPK(Long resource, Long operation) {
        this.resource = resource;
        this.operation = operation;
    }
    public ResourceForOperationPK() {
    }
    public Long getResource() {
        return resource;
    }
    public void setResource(Long resource) {
        this.resource = resource;
    }
    public Long getOperation() {
        return operation;
    }
    public void setOperation(Long operation) {
        this.operation = operation;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((resource == null) ? 0 : resource.hashCode());
        result = prime * result + ((operation == null) ? 0 : operation.hashCode());
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
        ResourceForOperationPK other = (ResourceForOperationPK) obj;
        if (resource == null) {
            if (other.resource != null)
                return false;
        } else if (!resource.equals(other.resource))
            return false;
        if (operation == null) {
            if (other.operation != null)
                return false;
        } else if (!operation.equals(other.operation))
            return false;
        return true;
    }
}
