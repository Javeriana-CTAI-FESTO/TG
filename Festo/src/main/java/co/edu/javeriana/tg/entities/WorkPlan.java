package co.edu.javeriana.tg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tblWorkPlanDef")
@Data
public class WorkPlan {
    @Id
    @Column(name = "WPNo", nullable = false)
    private Long work_plan_number;
    @Column(name = "Description", nullable = true)
    private String description;
    @Column(name = "Type", nullable = true)
    private String work_plan_type;
    @Column(name = "Short", nullable = true)
    private String short_description;
    @Column(name = "PictureNo", nullable = true)
    private Long status;
    @Column(name="PNo", nullable = true)
    private Long part_number;
}
