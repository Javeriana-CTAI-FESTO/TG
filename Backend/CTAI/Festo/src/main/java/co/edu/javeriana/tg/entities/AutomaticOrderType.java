package co.edu.javeriana.tg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblAutoOrderType")
public class AutomaticOrderType {
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "Description")
    private String description;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
