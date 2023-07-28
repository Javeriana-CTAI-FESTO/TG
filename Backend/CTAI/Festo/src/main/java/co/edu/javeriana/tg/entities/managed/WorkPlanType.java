package co.edu.javeriana.tg.entities.managed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblWorkPlanType")
// Tipo de plan de trabajo
public class WorkPlanType {
    @Id
    @Column(name = "Type", nullable = false)
    private Long typeNumber;
    @Column(name = "Description", nullable = true)
    private String description;
    public Long getTypeNumber() {
        return typeNumber;
    }
    public void setTypeNumber(Long typeNumber) {
        this.typeNumber = typeNumber;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
