package co.edu.javeriana.ctai.tgsecurity.entities.builder;

import co.edu.javeriana.ctai.tgsecurity.entities.Cliente;
import co.edu.javeriana.ctai.tgsecurity.entities.Order;
import co.edu.javeriana.ctai.tgsecurity.entities.User;
import co.edu.javeriana.ctai.tgsecurity.entities.auxillary.Rol;

import java.util.ArrayList;
import java.util.List;

public class ClienteBuilder implements IBuilder<Cliente> {

    private User usuario;
    private String nombre;
    private String apellido;
    private Long identificacion;
    private Long celular;
    private String correoElectronico;
    private Rol rol; // Enum para el rol
    private List<Order> orders = new ArrayList<>();

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

    public ClienteBuilder setRol(Rol rol) { // Método para establecer el rol
        this.rol = rol;
        return this;
    }

    public ClienteBuilder addOrder(Order order) {
        this.orders.add(order);
        return this;
    }

    @Override
    public Cliente build() {
        Cliente cliente = new Cliente(usuario, nombre, apellido, identificacion, celular, correoElectronico, rol);
        cliente.setOrders(orders);
        return cliente;
    }
}
