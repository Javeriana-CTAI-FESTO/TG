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

import co.edu.javeriana.tg.entities.managed.AutomaticOrder;
import co.edu.javeriana.tg.repositories.interfaces.AutomaticOrderRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Profile("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AutomaticOrderRepoTest {

    @Autowired
    AutomaticOrderRepository automaticOrderRepository;

    private Long testID = 1L;

    private List<Long> idsList = List.of(2L, 3L, 4L, 5L, 6L);

    @Test
    public void testEmptyCRUD() {
        AutomaticOrder emptyOrder = new AutomaticOrder();
        List<AutomaticOrder> createdList = idsList.stream().map(id -> new AutomaticOrder())
                .collect(Collectors.toList());
        // Create
        assertThrows(JpaSystemException.class, () -> automaticOrderRepository.save(emptyOrder));
        createdList.forEach(createdElement -> assertThrows(JpaSystemException.class,
                () -> automaticOrderRepository.save(createdElement)));
        // Read
        assertEquals(automaticOrderRepository.count(), 0);
        assertEquals(Optional.empty(), automaticOrderRepository.findById(testID));
        automaticOrderRepository.findAllById(idsList).forEach(element -> assertNull(element));
        // Update
        emptyOrder.setResourceThatSendMessageId(testID);
        assertThrows(JpaSystemException.class, () -> automaticOrderRepository.save(emptyOrder));
        createdList.forEach(createdElement -> {
            createdElement.setResourceThatSendMessageId(testID);
            assertThrows(JpaSystemException.class, () -> automaticOrderRepository.save(createdElement));
        });
        // Delete
        assertDoesNotThrow(() -> automaticOrderRepository.delete(emptyOrder));
        automaticOrderRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> automaticOrderRepository.delete(element)));
        assertEquals(automaticOrderRepository.count(), 0);
    }

    @Test
    public void testNonEmptyCRUD() {
        AutomaticOrder order = new AutomaticOrder(testID);
        List<AutomaticOrder> createdList = idsList.stream().map(id -> new AutomaticOrder(id))
                .collect(Collectors.toList());
        // Create
        assertDoesNotThrow(() -> automaticOrderRepository.save(order));
        assertEquals(order, automaticOrderRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> automaticOrderRepository.save(createdElement)));
        assertEquals(order, automaticOrderRepository.findById(testID).get());
        // Read
        assertEquals(automaticOrderRepository.count(), 6);
        assertEquals(order, automaticOrderRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertEquals(createdElement, automaticOrderRepository.findById(createdElement.getId()).get()));
        // Update
        order.setResourceThatSendMessageId(testID);
        assertDoesNotThrow(() -> automaticOrderRepository.save(order));
        assertEquals(order, automaticOrderRepository.findById(testID).get());
        createdList.forEach(createdElement -> {
            createdElement.setResourceThatSendMessageId(testID);
            assertDoesNotThrow(() -> automaticOrderRepository.save(createdElement));
            assertEquals(createdElement, automaticOrderRepository.findById(createdElement.getId()).get());
        });
        // Delete
        assertDoesNotThrow(() -> automaticOrderRepository.delete(order));
        automaticOrderRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> automaticOrderRepository.delete(element)));
        assertEquals(automaticOrderRepository.count(), 0);
    }

}
