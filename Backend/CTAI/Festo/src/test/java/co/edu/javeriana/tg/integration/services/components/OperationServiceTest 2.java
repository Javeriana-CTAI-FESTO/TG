package co.edu.javeriana.tg.integration.services.components;

import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.javeriana.tg.entities.managed.Operation;
import co.edu.javeriana.tg.repositories.interfaces.OperationRepository;
import co.edu.javeriana.tg.services.components.OperationService;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Profile("test")
@SpringBootTest
public class OperationServiceTest {
    @Autowired
    private OperationService operationService;

    @MockBean
    private OperationRepository operationRepository;

    @Test
    public void testEmptyGetAll() {
        assertEquals(0, operationService.getAllOperations().size());
    }

    @Test
    public void testNonEmptyGetAll() {
        when(operationRepository.findAll()).thenReturn(List.of(new Operation(1L)));
        assertEquals(1, operationService.getAllOperations().size());
    }

    @Test
    public void testEmptyGetForMachine() {
        Long operationNumber = 1l;
        assertNull(operationService.get(operationNumber));
    }

    @Test
    public void testNonEmptyGet() {
        Long operationNumber = 1l;
        when(operationRepository.findById(operationNumber)).thenReturn(Optional.of(new Operation(operationNumber)));
        assertNotNull(operationService.get(operationNumber));
    }
}
