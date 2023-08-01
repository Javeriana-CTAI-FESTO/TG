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
    public WorkPlanType() {
    }
    public WorkPlanType(Long id) {
        this.typeNumber = id;
    }
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((typeNumber == null) ? 0 : typeNumber.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
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
        WorkPlanType other = (WorkPlanType) obj;
        if (typeNumber == null) {
            if (other.typeNumber != null)
                return false;
        } else if (!typeNumber.equals(other.typeNumber))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        return true;
    }
}
