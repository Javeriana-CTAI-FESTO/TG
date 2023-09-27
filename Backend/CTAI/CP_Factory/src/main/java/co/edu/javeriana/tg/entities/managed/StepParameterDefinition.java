package co.edu.javeriana.tg.entities.managed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
@Entity
@Table(name = "tblStepParameterDef")
@IdClass(StepParameterDefinitionPK.class)
// Parametros de la lista de operaciones de los planes de trabajo
public class StepParameterDefinition {
    @Id
    @Column(name = "WPNo", nullable = false)
    private Long relatedWorkPlan;
    @Id
    @Column(name = "StepNo", nullable = false)
    private Long step;
    @Id
    @Column(name = "ParameterNo", nullable = false)
    private Long parameterNumber;
    @Column(name = "Parameter", nullable = true)
    private String parameter;
    @Column(name = "ParameterType", nullable = true)
    private Long parameterType;
    @Column(name = "QueryChooseParameter", nullable = true)
    private String chooseParameter;
    public StepParameterDefinition() {
    }
    public StepParameterDefinition(StepParameterDefinitionPK testID) {
        this.relatedWorkPlan = testID.getRelatedWorkPlan();
        this.step = testID.getStep();
        this.parameterNumber = testID.getParameterNumber();
    }
    public Long getRelatedWorkPlan() {
        return relatedWorkPlan;
    }
    public void setRelatedWorkPlan(Long relatedWorkPlan) {
        this.relatedWorkPlan = relatedWorkPlan;
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
    public String getParameter() {
        return parameter;
    }
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
    public Long getParameterType() {
        return parameterType;
    }
    public void setParameterType(Long parameterType) {
        this.parameterType = parameterType;
    }
    public String getChooseParameter() {
        return chooseParameter;
    }
    public void setChooseParameter(String chooseParameter) {
        this.chooseParameter = chooseParameter;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((relatedWorkPlan == null) ? 0 : relatedWorkPlan.hashCode());
        result = prime * result + ((step == null) ? 0 : step.hashCode());
        result = prime * result + ((parameterNumber == null) ? 0 : parameterNumber.hashCode());
        result = prime * result + ((parameter == null) ? 0 : parameter.hashCode());
        result = prime * result + ((parameterType == null) ? 0 : parameterType.hashCode());
        result = prime * result + ((chooseParameter == null) ? 0 : chooseParameter.hashCode());
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
        StepParameterDefinition other = (StepParameterDefinition) obj;
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
        if (parameter == null) {
            if (other.parameter != null)
                return false;
        } else if (!parameter.equals(other.parameter))
            return false;
        if (parameterType == null) {
            if (other.parameterType != null)
                return false;
        } else if (!parameterType.equals(other.parameterType))
            return false;
        if (chooseParameter == null) {
            if (other.chooseParameter != null)
                return false;
        } else if (!chooseParameter.equals(other.chooseParameter))
            return false;
        return true;
    }
}