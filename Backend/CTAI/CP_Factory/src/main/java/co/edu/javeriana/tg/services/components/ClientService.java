package co.edu.javeriana.tg.services.components;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import co.edu.javeriana.tg.entities.dtos.ClientDTO;
import co.edu.javeriana.tg.entities.managed.Client;
import co.edu.javeriana.tg.repositories.interfaces.ClientRepository;

@Component
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    public ClientDTO getClientByClientNumber(Long clientNumber) {
        try {
            return new ClientDTO(clientRepository.findByClientNumber(clientNumber));
        } catch (Exception e) {
        }
        return null;
    }

    public ClientDTO createClient(ClientDTO client) {
        try {
            Client c = new Client();
            c.setClientNumber(client.getClientNumber());
            c.setFirstName(client.getFirstName());
            c.setLastName(client.getLastName());
            c.setAddress(client.getAddress());
            c.setCompany(client.getCompany());
            c.setEmail(client.getEmail());
            c.setPhone(client.getPhone());
            clientRepository.save(c);
            return new ClientDTO(c);
        } catch (Exception e) {
        }
        return null;
    }
}
