package co.edu.javeriana.ctai.tgsecurity.services.interfaces.users;

import co.edu.javeriana.ctai.tgsecurity.entities.users.Cliente;
import co.edu.javeriana.ctai.tgsecurity.entities.users.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IClientService {
    List<Cliente> findAll();

    Cliente save(Cliente cliente);

    Boolean existsByCedula(Long cedula);

    Boolean existsByCorreoElectronico(String correo);

    Boolean existsByCelular(Long celular);

    Cliente findByCedula(Long cedula);

    Cliente findByUsuario(User usuario);
}