package co.edu.javeriana.tg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblAgvDocked")
public class Agv {
    @Id
    @Column(name="AgvId")
    private Long id;
    @Column(name="ResourceId", nullable = true)
    private Long resourceId;
}
