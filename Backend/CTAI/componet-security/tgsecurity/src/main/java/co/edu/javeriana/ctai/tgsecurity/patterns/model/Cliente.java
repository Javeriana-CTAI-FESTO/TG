package co.edu.javeriana.ctai.tgsecurity.patterns.model;


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

    @Column(name = "admin")
    private Boolean admin;

    @Column(name = "estudiante")
    private Boolean estudiante;

    @Column(name = "profesor")
    private Boolean profesor;

    @Column(name = "comprador")
    private Boolean comprador;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Cliente() {
    }

    public Cliente(User usuario, String nombre, String apellido, Long identificacion, Long celular, String correoElectronico, boolean admin, boolean estudiante, boolean profesor, boolean comprador) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.celular = celular;
        this.correoElectronico = correoElectronico;
        this.admin = admin;
        this.estudiante = estudiante;
        this.profesor = profesor;
        this.comprador = comprador;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", usuario=" + usuario + ", nombre=" + nombre + ", apellido=" + apellido
                + ", identificacion=" + identificacion + ", celular=" + celular + ", correoElectronico="
                + correoElectronico + "]";
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

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Boolean estudiante) {
        this.estudiante = estudiante;
    }

    public Boolean getProfesor() {
        return profesor;
    }

    public void setProfesor(Boolean profesor) {
        this.profesor = profesor;
    }

    public Boolean getComprador() {
        return comprador;
    }

    public void setComprador(Boolean comprador) {
        this.comprador = comprador;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isProfesor() {
        return profesor;
    }

    public boolean isEstudiante() {
        return estudiante;
    }

    public boolean isComprador() {
        return comprador;
    }
}
