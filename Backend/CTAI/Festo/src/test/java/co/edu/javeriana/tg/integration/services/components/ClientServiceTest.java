package co.edu.javeriana.tg.integration.services.components;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.javeriana.tg.entities.managed.Client;
import co.edu.javeriana.tg.repositories.interfaces.ClientRepository;
import co.edu.javeriana.tg.services.components.ClientService;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Profile("test")
@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @Test
    public void testNonEmptyGetAllClients(){
        when(clientRepository.findAll()).thenReturn(List.of(new Client(1l)));
        assertNotNull(clientService.getAllClients());
    }

    @Test
    public void testNonEmptyGetClient(){
        when(clientRepository.findByClientNumber(1l)).thenReturn(new Client(1l));
        assertNotNull(clientService.getClientByClientNumber(1l));
    }

    @Test
    public void testEmptyGetAll() {
        assertNotNull(clientService.getAllClients());
    }

    @Test
    public void testEmptyGetClient() {
        assertNull(clientService.getClientByClientNumber(1l));
    }

    @Test
    public void testEmptyCreateClient() {
        Client c = new Client();
        when(clientRepository.save(c)).thenThrow(RuntimeException.class);
        assertNull(clientService.createClient(c));
    }

     @Test
    public void testCreateClient() {
        assertNotNull(clientService.createClient(new Client(1l)));
    }
}