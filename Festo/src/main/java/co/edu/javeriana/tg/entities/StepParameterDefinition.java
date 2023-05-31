package co.edu.javeriana.tg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
@Entity
@Table(name = "tblStepParameterDef")
@IdClass(StepParameterDefinitionPK.class)
public class StepParameterDefinition {
    @Id
    @Column(name = "WPNo")
    private Long relatedWorkPlan;
    @Id
    @Column(name = "StepNo" )
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
}