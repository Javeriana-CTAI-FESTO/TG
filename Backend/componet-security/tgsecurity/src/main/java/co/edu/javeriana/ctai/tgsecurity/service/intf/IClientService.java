package co.edu.javeriana.ctai.tgsecurity.service.intf;

import co.edu.javeriana.ctai.tgsecurity.patterns.model.Cliente;
import co.edu.javeriana.ctai.tgsecurity.patterns.model.User;
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
