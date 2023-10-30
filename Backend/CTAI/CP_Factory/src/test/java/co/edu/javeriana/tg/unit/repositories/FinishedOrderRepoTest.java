package co.edu.javeriana.tg.unit.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.javeriana.tg.entities.managed.FinishedOrder;
import co.edu.javeriana.tg.repositories.interfaces.FinishedOrderRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Profile("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FinishedOrderRepoTest {
    @Autowired
    FinishedOrderRepository orderRepository;

    private Long testID = getWithUniqueID(1L);

    private List<Long> idsList = List.of(getWithUniqueID(2L), getWithUniqueID(3L), getWithUniqueID(4L), getWithUniqueID(5L), getWithUniqueID(6L));

    private Long getWithUniqueID(Long id){
        return id;
    }

    @Test
    public void testEmptyCRUD() {
        FinishedOrder emptyOrder = new FinishedOrder();
        List<FinishedOrder> createdList = idsList.stream().map(id -> new FinishedOrder())
                .collect(Collectors.toList());
        // Create
        assertThrows(JpaSystemException.class, () -> orderRepository.save(emptyOrder));
        createdList.forEach(createdElement -> assertThrows(JpaSystemException.class,
                () -> orderRepository.save(createdElement)));
        // Read
        assertEquals(orderRepository.count(), 0);
        assertEquals(Optional.empty(), orderRepository.findById(testID));
        orderRepository.findAllById(idsList).forEach(element -> assertNull(element));
        // Update
        emptyOrder.setEnabled(true);
        assertThrows(JpaSystemException.class, () -> orderRepository.save(emptyOrder));
        createdList.forEach(createdElement -> {
            createdElement.setEnabled(true);
            assertThrows(JpaSystemException.class, () -> orderRepository.save(createdElement));
        });
        // Delete
        assertDoesNotThrow(() -> orderRepository.delete(emptyOrder));
        orderRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> orderRepository.delete(element)));
        assertEquals(orderRepository.count(), 0);
    }

    @Test
    public void testNonEmptyCRUD() {
        FinishedOrder order = new FinishedOrder(testID);
        List<FinishedOrder> createdList = idsList.stream().map(id -> new FinishedOrder(id))
                .collect(Collectors.toList());
        // Create
        assertDoesNotThrow(() -> orderRepository.save(order));
        assertEquals(order, orderRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> orderRepository.save(createdElement)));
        assertEquals(order, orderRepository.findById(testID).get());
        // Read
        assertEquals(orderRepository.count(), 6);
        assertEquals(order, orderRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertEquals(createdElement, orderRepository.findById(getWithUniqueID(createdElement.getOrderNumber())).get()));
        // Update
        order.setEnabled(true);
        assertDoesNotThrow(() -> orderRepository.save(order));
        assertEquals(order, orderRepository.findById(testID).get());
        createdList.forEach(createdElement -> {
            createdElement.setEnabled(true);
            assertDoesNotThrow(() -> orderRepository.save(createdElement));
            assertEquals(createdElement, orderRepository.findById(getWithUniqueID(createdElement.getOrderNumber())).get());
        });
        // Delete
        assertDoesNotThrow(() -> orderRepository.delete(order));
        orderRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> orderRepository.delete(element)));
        assertEquals(orderRepository.count(), 0);
    }
}
