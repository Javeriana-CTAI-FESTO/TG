package co.edu.javeriana.tg.entities.managed;

import java.io.Serializable;

public class StepParameterPK implements Serializable{
    private Long stepNumber;
    private Long orderNumber;
    private Long orderPosition;
    private Long parameterNumber;
    public StepParameterPK(Long stepNumber, Long orderNumber, Long orderPosition, Long parameterNumber) {
        this.stepNumber = stepNumber;
        this.orderNumber = orderNumber;
        this.orderPosition = orderPosition;
        this.parameterNumber = parameterNumber;
    }
    public StepParameterPK() {
    }
    public Long getStepNumber() {
        return stepNumber;
    }
    public void setStepNumber(Long stepNumber) {
        this.stepNumber = stepNumber;
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((stepNumber == null) ? 0 : stepNumber.hashCode());
        result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
        result = prime * result + ((orderPosition == null) ? 0 : orderPosition.hashCode());
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
        StepParameterPK other = (StepParameterPK) obj;
        if (stepNumber == null) {
            if (other.stepNumber != null)
                return false;
        } else if (!stepNumber.equals(other.stepNumber))
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
        return true;
    }
    public Long getParameterNumber() {
        return parameterNumber;
    }
    public void setParameterNumber(Long parameterNumber) {
        this.parameterNumber = parameterNumber;
    }
    
}