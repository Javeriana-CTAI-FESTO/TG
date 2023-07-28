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
}