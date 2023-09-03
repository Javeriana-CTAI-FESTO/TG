package co.edu.javeriana.ctai.tgsecurity.patterns.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario_id")  // Cambié "Usuarioid" a "Usuario_id" para seguir las convenciones de nomenclatura.
    private User usuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "identificacion")
    private Long identificacion;

    @Column(name = "celular")
    private Long celular;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @Column(name = "admin")
    private boolean admin;

    @Column(name = "estudiante")
    private boolean estudiante;

    @Column(name = "profesor")
    private boolean profesor;


    public Cliente(User usuario, String nombre, String apellido, Long identificacion, Long celular, String correoElectronico, boolean admin, boolean estudiante, boolean profesor) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.celular = celular;
        this.correoElectronico = correoElectronico;
        this.admin = admin;
        this.estudiante = estudiante;
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", usuario=" + usuario + ", nombre=" + nombre + ", apellido=" + apellido
                + ", identificacion=" + identificacion + ", celular=" + celular + ", correoElectronico="
                + correoElectronico + "]";
    }

    // getters y setters


}