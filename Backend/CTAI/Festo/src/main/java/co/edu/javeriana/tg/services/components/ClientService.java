package co.edu.javeriana.tg.services.components;

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
