package co.edu.javeriana.tg.services;

import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.dtos.ClientDTO;
import co.edu.javeriana.tg.repositories.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDTO getClient(Long clientNumber) {
        return new ClientDTO(clientRepository.findByClientNumber(clientNumber));
    }
}
