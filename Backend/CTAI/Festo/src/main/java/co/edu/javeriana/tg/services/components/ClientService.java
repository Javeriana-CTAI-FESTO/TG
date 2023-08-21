package co.edu.javeriana.tg.services.components;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import co.edu.javeriana.tg.entities.dtos.ClientDTO;
import co.edu.javeriana.tg.entities.managed.Client;
import co.edu.javeriana.tg.repositories.interfaces.ClientRepository;

@Component
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> getAllClients(){
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    public ClientDTO getClientByClientNumber(Long clientNumber) {
        try {
            return new ClientDTO(clientRepository.findByClientNumber(clientNumber));
        } catch (Exception e) {
        }
        return null;
    }

    public ClientDTO createClient(Client client){
        try {
            clientRepository.save(client);
            return new ClientDTO(client);
        } catch (Exception e) {
        }
        return null;
    }
}
