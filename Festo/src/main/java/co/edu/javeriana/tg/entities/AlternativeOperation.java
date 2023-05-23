package co.edu.javeriana.tg.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="tblAltOperation")
@IdClass(AltOperationId.class)
public class AlternativeOperation {
    @Id 
    private Long OpNo;
    @Id 
    private Long AltOpNo;
}
