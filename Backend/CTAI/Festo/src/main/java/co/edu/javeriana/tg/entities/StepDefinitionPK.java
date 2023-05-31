package co.edu.javeriana.tg.entities;

import java.io.Serializable;

public class StepDefinitionPK implements Serializable{
    public StepDefinitionPK() {
    }
    private Long workPlan;
    private Long stepNumber;
    public StepDefinitionPK(Long wPNo, Long stepNo) {
        workPlan = wPNo;
        stepNumber = stepNo;
    }
    public Long getWorkPlan() {
        return workPlan;
    }
    public void setWorkPlan(Long wPNo) {
        workPlan = wPNo;
    }
    public Long getStepNumber() {
        return stepNumber;
    }
    public void setStepNumber(Long stepNo) {
        stepNumber = stepNo;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((workPlan == null) ? 0 : workPlan.hashCode());
        result = prime * result + ((stepNumber == null) ? 0 : stepNumber.hashCode());
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
        StepDefinitionPK other = (StepDefinitionPK) obj;
        if (workPlan == null) {
            if (other.workPlan != null)
                return false;
        } else if (!workPlan.equals(other.workPlan))
            return false;
        if (stepNumber == null) {
            if (other.stepNumber != null)
                return false;
        } else if (!stepNumber.equals(other.stepNumber))
            return false;
        return true;
    }
}
