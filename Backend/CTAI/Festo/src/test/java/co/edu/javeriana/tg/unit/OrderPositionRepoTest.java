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

import co.edu.javeriana.tg.entities.managed.OrderPosition;
import co.edu.javeriana.tg.entities.managed.OrderPositionPK;
import co.edu.javeriana.tg.repositories.interfaces.OrderPositionRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("unittest")
@Profile("unittest")
public class OrderPositionRepoTest {
    @Autowired
    OrderPositionRepository orderPositionRepository;

    /*@Autowired
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

    private OrderPositionPK testID = getWithUniqueID(1L);

    private List<OrderPositionPK> idsList = List.of(getWithUniqueID(2L), getWithUniqueID(3L), getWithUniqueID(4L), getWithUniqueID(5L), getWithUniqueID(6L));

    private OrderPositionPK getWithUniqueID(Long id){
        return new OrderPositionPK(id, id);
    }

    @Test
    public void testEmptyCRUD() {
        OrderPosition emptyOrder = new OrderPosition();
        List<OrderPosition> createdList = idsList.stream().map(id -> new OrderPosition())
                .collect(Collectors.toList());
        // Create
        assertThrows(JpaSystemException.class, () -> orderPositionRepository.save(emptyOrder));
        createdList.forEach(createdElement -> assertThrows(JpaSystemException.class,
                () -> orderPositionRepository.save(createdElement)));
        // Read
        assertEquals(orderPositionRepository.count(), 0);
        assertEquals(Optional.empty(), orderPositionRepository.findById(testID));
        orderPositionRepository.findAllById(idsList).forEach(element -> assertEquals(Optional.empty(), element));
        // Update
        emptyOrder.setError(true);
        assertThrows(JpaSystemException.class, () -> orderPositionRepository.save(emptyOrder));
        createdList.forEach(createdElement -> {
            createdElement.setError(true);
            assertThrows(JpaSystemException.class, () -> orderPositionRepository.save(createdElement));
        });
        // Delete
        assertDoesNotThrow(() -> orderPositionRepository.delete(emptyOrder));
        orderPositionRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> orderPositionRepository.delete(element)));
        assertEquals(orderPositionRepository.count(), 0);
    }

    @Test
    public void testNonEmptyCRUD() {
        OrderPosition order = new OrderPosition(testID);
        List<OrderPosition> createdList = idsList.stream().map(id -> new OrderPosition(id))
                .collect(Collectors.toList());
        // Create
        assertDoesNotThrow(() -> orderPositionRepository.save(order));
        assertEquals(order, orderPositionRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> orderPositionRepository.save(createdElement)));
        assertEquals(order, orderPositionRepository.findById(testID).get());
        // Read
        assertEquals(orderPositionRepository.count(), 6);
        assertEquals(order, orderPositionRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertEquals(createdElement, orderPositionRepository.findById(getWithUniqueID(createdElement.getOperationNumber())).get()));
        // Update
        order.setError(true);
        assertDoesNotThrow(() -> orderPositionRepository.save(order));
        assertEquals(order, orderPositionRepository.findById(testID).get());
        createdList.forEach(createdElement -> {
            createdElement.setError(true);
            assertDoesNotThrow(() -> orderPositionRepository.save(createdElement));
            assertEquals(createdElement, orderPositionRepository.findById(getWithUniqueID(createdElement.getOperationNumber())).get());
        });
        // Delete
        assertDoesNotThrow(() -> orderPositionRepository.delete(order));
        orderPositionRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> orderPositionRepository.delete(element)));
        assertEquals(orderPositionRepository.count(), 0);
    }
}
