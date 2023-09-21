package co.edu.javeriana.tg.unit.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.javeriana.tg.entities.managed.Order;
import co.edu.javeriana.tg.entities.managed.OrderPosition;
import co.edu.javeriana.tg.entities.managed.OrderPositionPK;
import co.edu.javeriana.tg.repositories.interfaces.OrderPositionRepository;
import co.edu.javeriana.tg.repositories.interfaces.OrderRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Profile("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderPositionRepoTest {
    @Autowired
    OrderPositionRepository orderPositionRepository;

    @Autowired
    OrderRepository orderRepository;

    private List<Long> idsListR = List.of(1L, 2L, 3L, 4L, 5L,6L);

    private OrderPositionPK testID = getWithUniqueID(1L);

    private List<OrderPositionPK> idsList = List.of(getWithUniqueID(2L), getWithUniqueID(3L), getWithUniqueID(4L), getWithUniqueID(5L), getWithUniqueID(6L));

    private OrderPositionPK getWithUniqueID(Long id){
        return new OrderPositionPK(id, id);
    }

    @Test
    public void testNonEmptyCRUD() {
        OrderPosition order = new OrderPosition(testID);
        List<OrderPosition> createdList = idsList.stream().map(id -> new OrderPosition(id))
                .collect(Collectors.toList());
        List<Order> createdResourcesList = idsListR.stream().map(id -> new Order(id))
                .collect(Collectors.toList());
        // Create
        createdResourcesList.forEach(createdElement -> assertDoesNotThrow(() -> orderRepository.save(createdElement)));
        assertDoesNotThrow(() -> orderPositionRepository.save(order));
        assertEquals(order, orderPositionRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> orderPositionRepository.save(createdElement)));
        assertEquals(order, orderPositionRepository.findById(testID).get());
        // Read
        assertEquals(orderPositionRepository.findAll().size(), 6);
        assertEquals(order, orderPositionRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertEquals(createdElement, orderPositionRepository.findById(getWithUniqueID(createdElement.getOrder())).get()));
        // Update
        order.setError(true);
        assertDoesNotThrow(() -> orderPositionRepository.save(order));
        assertEquals(order, orderPositionRepository.findById(testID).get());
        createdList.forEach(createdElement -> {
            createdElement.setError(true);
            assertDoesNotThrow(() -> orderPositionRepository.save(createdElement));
            assertEquals(createdElement, orderPositionRepository.findById(getWithUniqueID(createdElement.getOrder())).get());
        });
        // Delete
        createdResourcesList.forEach(createdElement -> assertDoesNotThrow(() -> orderRepository.delete(createdElement)));
        assertDoesNotThrow(() -> orderPositionRepository.delete(order));
        orderPositionRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> orderPositionRepository.delete(element)));
    }
}
