package co.edu.javeriana.ctai.tgsecurity.entities.builder;

import co.edu.javeriana.ctai.tgsecurity.entities.Cliente;
import co.edu.javeriana.ctai.tgsecurity.entities.Order;
import co.edu.javeriana.ctai.tgsecurity.entities.User;

import java.util.ArrayList;
import java.util.List;

public class ClienteBuilder {

    private User usuario;
    private String nombre;
    private String apellido;
    private Long identificacion;
    private Long celular;
    private String correoElectronico;
    private boolean admin;
    private boolean estudiante;
    private boolean profesor;
    private boolean comprador;
    private List<Order> orders = new ArrayList<>(); // Inicializa la lista de órdenes como vacía por defecto

    public ClienteBuilder setUsuario(User usuario) {
        this.usuario = usuario;
        return this;
    }

    public ClienteBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ClienteBuilder setApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public ClienteBuilder setIdentificacion(Long identificacion) {
        this.identificacion = identificacion;
        return this;
    }

    public ClienteBuilder setCelular(Long celular) {
        this.celular = celular;
        return this;
    }

    public ClienteBuilder setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
        return this;
    }

    public ClienteBuilder setAdmin(Boolean admin) {
        this.admin = admin;
        return this;
    }

    public ClienteBuilder setEstudiante(Boolean estudiante) {
        this.estudiante = estudiante;
        return this;
    }

    public ClienteBuilder setProfesor(Boolean profesor) {
        this.profesor = profesor;
        return this;
    }

    public ClienteBuilder setComprador(Boolean comprador){
        this.comprador = comprador;
        return this;
    }

    public ClienteBuilder addOrder(Order order) {
        this.orders.add(order);
        return this;
    }

    public Cliente build() {
        Cliente cliente = new Cliente(usuario, nombre, apellido, identificacion, celular, correoElectronico, admin, estudiante, profesor, comprador);
        cliente.setOrders(orders);
        return cliente;
    }
}