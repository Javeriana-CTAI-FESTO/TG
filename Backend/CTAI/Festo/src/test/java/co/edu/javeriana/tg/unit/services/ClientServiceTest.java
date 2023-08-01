package co.edu.javeriana.tg.unit.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.javeriana.tg.entities.dtos.ClientDTO;
import co.edu.javeriana.tg.entities.managed.Client;
import co.edu.javeriana.tg.services.ClientService;

@ExtendWith(SpringExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientService clientService;

    private final Long testID=1l;
    private final Long notPresentID = 2l;

    @Test
    public void testGetClient(){
        Client client = new Client(testID);
        when(clientService.getClient(testID)).thenReturn(new ClientDTO(client));
        assertEquals(client.getClientNumber(), clientService.getClient(testID).getClientNumber());
        assertNull(clientService.getClient(notPresentID));
    }
}
