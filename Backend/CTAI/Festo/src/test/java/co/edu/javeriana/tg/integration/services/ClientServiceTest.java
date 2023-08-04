package co.edu.javeriana.tg.integration.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.javeriana.tg.entities.managed.Client;
import co.edu.javeriana.tg.repositories.interfaces.ClientRepository;
import co.edu.javeriana.tg.services.ClientService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @Test
    public void testNonEmptyGetClient(){
        when(clientRepository.findByClientNumber(1l)).thenReturn(new Client(1l));
        assertNotNull(clientService.getClient(1l));
    }

    @Test
    public void testEmptyGetClient() {
        assertNull(clientService.getClient(1l));
    }
}
