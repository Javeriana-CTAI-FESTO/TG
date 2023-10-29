package co.edu.javeriana.ctai.tgsecurity.services;

import co.edu.javeriana.ctai.tgsecurity.entities.Cliente;
import co.edu.javeriana.ctai.tgsecurity.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IClientService {
    List <Cliente> findAll();
    Cliente findById(Long id);
    Cliente save(Cliente cliente);
    void deleteById(Long id);
    Boolean existsByCedula(Long cedula);
    Boolean existsByCorreoElectronico(String correo);
    Boolean existsByCelular(Long celular);
    Cliente findByCedula(Long cedula);
    Cliente findByUsuario(User usuario);


}
