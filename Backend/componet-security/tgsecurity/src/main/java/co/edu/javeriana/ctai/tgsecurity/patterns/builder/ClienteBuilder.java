package co.edu.javeriana.ctai.tgsecurity.patterns.builder;

import co.edu.javeriana.ctai.tgsecurity.patterns.model.Cliente;
import co.edu.javeriana.ctai.tgsecurity.patterns.model.User;

public class ClienteBuilder implements IBuilder <Cliente>{

    private User usuario;
    private String nombre;
    private String apellido;
    private Long identificacion;
    private Long celular;
    private String correoElectronico;

    private boolean admin;
    private boolean estudiante;
    private boolean profesor;

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

    public ClienteBuilder setAdmin(boolean admin) {
        this.admin = admin;
        return this;
    }

    public ClienteBuilder setEstudiante(boolean estudiante) {
        this.estudiante = estudiante;
        return this;
    }

    public ClienteBuilder setProfesor(boolean profesor) {
        this.profesor = profesor;
        return this;
    }


    @Override
    public Cliente build() {
        return new Cliente(usuario, nombre, apellido, identificacion, celular, correoElectronico, admin, estudiante, profesor);
    }
}
