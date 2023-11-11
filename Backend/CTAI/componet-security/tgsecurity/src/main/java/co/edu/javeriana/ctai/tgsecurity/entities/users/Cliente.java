package co.edu.javeriana.ctai.tgsecurity.entities.users;

import co.edu.javeriana.ctai.tgsecurity.entities.users.cp_factory.Order;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario_id")
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

    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    private Rol rol; // Enumeración para el rol

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Cliente() {
    }

    public Cliente(User usuario, String nombre, String apellido, Long identificacion, Long celular, String correoElectronico, Rol rol) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.celular = celular;
        this.correoElectronico = correoElectronico;
        this.rol = rol; // Configura el rol
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Long identificacion) {
        this.identificacion = identificacion;
    }

    public Long getCelular() {
        return celular;
    }

    public void setCelular(Long celular) {
        this.celular = celular;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
