package co.edu.javeriana.tg.entities.managed;

import java.io.Serializable;

public class OperationParameterPK implements Serializable {
    private Long operationNumber;
    private Long parameterNumber;

    public OperationParameterPK() {
    }

    public OperationParameterPK(Long operationNumber, Long parameterNumber) {
        this.operationNumber = operationNumber;
        this.parameterNumber = parameterNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((operationNumber == null) ? 0 : operationNumber.hashCode());
        result = prime * result + ((parameterNumber == null) ? 0 : parameterNumber.hashCode());
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
        OperationParameterPK other = (OperationParameterPK) obj;
        if (operationNumber == null) {
            if (other.operationNumber != null)
                return false;
        } else if (!operationNumber.equals(other.operationNumber))
            return false;
        if (parameterNumber == null) {
            if (other.parameterNumber != null)
                return false;
        } else if (!parameterNumber.equals(other.parameterNumber))
            return false;
        return true;
    }

    public Long getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(Long operationNumber) {
        this.operationNumber = operationNumber;
    }

    public Long getParameterNumber() {
        return parameterNumber;
    }

    public void setParameterNumber(Long parameterNumber) {
        this.parameterNumber = parameterNumber;
    }
}
