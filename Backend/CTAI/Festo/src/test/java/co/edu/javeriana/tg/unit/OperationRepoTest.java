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

import co.edu.javeriana.tg.entities.managed.Operation;
import co.edu.javeriana.tg.repositories.interfaces.OperationRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("unittest")
@Profile("unittest")
public class OperationRepoTest {
    @Autowired
    OperationRepository operationRepository;

    /*@Autowired
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
        Operation emptyOrder = new Operation();
        List<Operation> createdList = idsList.stream().map(id -> new Operation())
                .collect(Collectors.toList());
        // Create
        assertThrows(JpaSystemException.class, () -> operationRepository.save(emptyOrder));
        createdList.forEach(createdElement -> assertThrows(JpaSystemException.class,
                () -> operationRepository.save(createdElement)));
        // Read
        assertEquals(operationRepository.count(), 0);
        assertEquals(Optional.empty(), operationRepository.findById(testID));
        operationRepository.findAllById(idsList).forEach(element -> assertEquals(Optional.empty(), element));
        // Update
        emptyOrder.setShortDescription("Short");
        assertThrows(JpaSystemException.class, () -> operationRepository.save(emptyOrder));
        createdList.forEach(createdElement -> {
            createdElement.setShortDescription("Short");
            assertThrows(JpaSystemException.class, () -> operationRepository.save(createdElement));
        });
        // Delete
        assertDoesNotThrow(() -> operationRepository.delete(emptyOrder));
        operationRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> operationRepository.delete(element)));
        assertEquals(operationRepository.count(), 0);
    }

    @Test
    public void testNonEmptyCRUD() {
        Operation order = new Operation(testID);
        List<Operation> createdList = idsList.stream().map(id -> new Operation(id))
                .collect(Collectors.toList());
        // Create
        assertDoesNotThrow(() -> operationRepository.save(order));
        assertEquals(order, operationRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> operationRepository.save(createdElement)));
        assertEquals(order, operationRepository.findById(testID).get());
        // Read
        assertEquals(operationRepository.count(), 6);
        assertEquals(order, operationRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertEquals(createdElement, operationRepository.findById(getWithUniqueID(createdElement.getId())).get()));
        // Update
        order.setShortDescription("Short");
        assertDoesNotThrow(() -> operationRepository.save(order));
        assertEquals(order, operationRepository.findById(testID).get());
        createdList.forEach(createdElement -> {
            createdElement.setShortDescription("Short");
            assertDoesNotThrow(() -> operationRepository.save(createdElement));
            assertEquals(createdElement, operationRepository.findById(getWithUniqueID(createdElement.getId())).get());
        });
        // Delete
        assertDoesNotThrow(() -> operationRepository.delete(order));
        operationRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> operationRepository.delete(element)));
        assertEquals(operationRepository.count(), 0);
    }
}
