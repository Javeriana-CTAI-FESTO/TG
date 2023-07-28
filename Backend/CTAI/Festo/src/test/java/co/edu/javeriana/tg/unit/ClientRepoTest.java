package co.edu.javeriana.tg.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.javeriana.tg.entities.managed.Client;
import co.edu.javeriana.tg.repositories.interfaces.ClientRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("unittest")
@Profile("unittest")
public class ClientRepoTest {
    @Autowired
    ClientRepository clientRepository;

    /*@Autowired
    MachineReportRepository machineReportRepository;

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    OrderPositionRepository orderPositionRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PartRepository partRepository;

    @Autowired
    ResourceForOperationRepository resourceForOperationRepository;

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    StepDefinitionRepository stepDefinitionRepository;

    @Autowired
    StepParameterDefinitionRepository stepParameterDefinitionRepository;

    @Autowired
    TopologyRepository topologyRepository;

    @Autowired
    WorkPlanDefinitionRepository workPlanDefinitionRepository;

    @Autowired
    WorkPlanTypeRepository workPlanTypeRepository; */

    private Long testID = getWithUniqueID(1L);

    private List<Long> idsList = List.of(getWithUniqueID(2L), getWithUniqueID(3L), getWithUniqueID(4L), getWithUniqueID(5L), getWithUniqueID(6L));

    private Long getWithUniqueID(Long id){
        return id;
    }

    @Test
    public void testEmptyCRUD() {
        Client emptyOrder = new Client();
        List<Client> createdList = idsList.stream().map(id -> new Client())
                .collect(Collectors.toList());
        // Create
        assertThrows(JpaSystemException.class, () -> clientRepository.save(emptyOrder));
        createdList.forEach(createdElement -> assertThrows(JpaSystemException.class,
                () -> clientRepository.save(createdElement)));
        // Read
        assertEquals(clientRepository.count(), 0);
        assertEquals(Optional.empty(), clientRepository.findById(testID));
        clientRepository.findAllById(idsList).forEach(element -> assertEquals(Optional.empty(), element));
        // Update
        emptyOrder.setPhone(String.valueOf(testID));
        assertThrows(JpaSystemException.class, () -> clientRepository.save(emptyOrder));
        createdList.forEach(createdElement -> {
            createdElement.setPhone(String.valueOf(testID));
            assertThrows(JpaSystemException.class, () -> clientRepository.save(createdElement));
        });
        // Delete
        assertDoesNotThrow(() -> clientRepository.delete(emptyOrder));
        clientRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> clientRepository.delete(element)));
        assertEquals(clientRepository.count(), 0);
    }

    @Test
    public void testNonEmptyCRUD() {
        Client order = new Client(testID);
        List<Client> createdList = idsList.stream().map(id -> new Client(id))
                .collect(Collectors.toList());
        // Create
        assertDoesNotThrow(() -> clientRepository.save(order));
        assertEquals(order, clientRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> clientRepository.save(createdElement)));
        assertEquals(order, clientRepository.findById(testID).get());
        // Read
        assertEquals(clientRepository.count(), 6);
        assertEquals(order, clientRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertEquals(createdElement, clientRepository.findById(getWithUniqueID(createdElement.getClientNumber())).get()));
        // Update
        order.setPhone(String.valueOf(testID));
        assertDoesNotThrow(() -> clientRepository.save(order));
        assertEquals(order, clientRepository.findById(testID).get());
        createdList.forEach(createdElement -> {
            createdElement.setPhone(String.valueOf(testID));
            assertDoesNotThrow(() -> clientRepository.save(createdElement));
            assertEquals(createdElement, clientRepository.findById(getWithUniqueID(createdElement.getClientNumber())).get());
        });
        // Delete
        assertDoesNotThrow(() -> clientRepository.delete(order));
        clientRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> clientRepository.delete(element)));
        assertEquals(clientRepository.count(), 0);
    }
}
