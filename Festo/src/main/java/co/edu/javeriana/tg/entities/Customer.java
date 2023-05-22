package co.edu.javeriana.tg.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="customer")
@Data
public class Customer {
    @Id
    @Column(name = "Id", nullable = false)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String first_name;
    @Column(name = "last_name", nullable = false)
    private String last_name;
}
