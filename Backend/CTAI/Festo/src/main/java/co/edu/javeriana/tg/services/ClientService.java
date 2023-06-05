package co.edu.javeriana.tg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.dtos.ClientDTO;
import co.edu.javeriana.tg.repositories.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientDTO getClient(Long clientNumber) {
        return new ClientDTO(clientRepository.findByClientNumber(clientNumber));
    }
}
