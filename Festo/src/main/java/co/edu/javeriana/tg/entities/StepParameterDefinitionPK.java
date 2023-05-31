package co.edu.javeriana.tg.entities;

import java.io.Serializable;

public class StepParameterDefinitionPK implements Serializable{
    private Long relatedWorkPlan;
    private Long step;
    private Long parameterNumber;
    public StepParameterDefinitionPK(Long workPlan, Long step, Long parameterNumber) {
        this.relatedWorkPlan = workPlan;
        this.step = step;
        this.parameterNumber = parameterNumber;
    }
    public StepParameterDefinitionPK() {
    }
    public Long getRelatedWorkPlan() {
        return relatedWorkPlan;
    }
    public void setRelatedWorkPlan(Long workPlan) {
        this.relatedWorkPlan = workPlan;
    }
    public Long getStep() {
        return step;
    }
    public void setStep(Long step) {
        this.step = step;
    }
    public Long getParameterNumber() {
        return parameterNumber;
    }
    public void setParameterNumber(Long parameterNumber) {
        this.parameterNumber = parameterNumber;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((relatedWorkPlan == null) ? 0 : relatedWorkPlan.hashCode());
        result = prime * result + ((step == null) ? 0 : step.hashCode());
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
        StepParameterDefinitionPK other = (StepParameterDefinitionPK) obj;
        if (relatedWorkPlan == null) {
            if (other.relatedWorkPlan != null)
                return false;
        } else if (!relatedWorkPlan.equals(other.relatedWorkPlan))
            return false;
        if (step == null) {
            if (other.step != null)
                return false;
        } else if (!step.equals(other.step))
            return false;
        if (parameterNumber == null) {
            if (other.parameterNumber != null)
                return false;
        } else if (!parameterNumber.equals(other.parameterNumber))
            return false;
        return true;
    }
    
}