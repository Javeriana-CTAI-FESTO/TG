package co.edu.javeriana.ctai.tgsecurity.services.imp;

import co.edu.javeriana.ctai.tgsecurity.services.IClientService;
import co.edu.javeriana.ctai.tgsecurity.entities.Cliente;
import co.edu.javeriana.ctai.tgsecurity.entities.User;
import co.edu.javeriana.ctai.tgsecurity.repository.interfaces.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImp implements IClientService {

    @Autowired
    private IClientRepository clientRepository;
    @Override
    public List<Cliente> findAll() {
        return clientRepository.findAll();
    }
    @Override
    public Cliente findById(Long id) {
        return clientRepository.findById(id).get();
    }
    @Override
    public Cliente save(Cliente cliente) {
        return clientRepository.save(cliente);
    }
    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
    @Override
    public Boolean existsByCedula(Long cedula) {
        return clientRepository.existsByIdentificacion(cedula);
    }
    @Override
    public Boolean existsByCorreoElectronico(String correo) {
        return clientRepository.existsByCorreoElectronico(correo);
    }
    @Override
    public Boolean existsByCelular(Long celular) {
        return clientRepository.existsByCelular(celular);
    }
    @Override
    public Cliente findByCedula(Long cedula) {
        return clientRepository.findByIdentificacion(cedula);
    }
    @Override
    public Cliente findByUsuario(User usuario) {
        return clientRepository.findByUsuario(usuario);
    }

}
