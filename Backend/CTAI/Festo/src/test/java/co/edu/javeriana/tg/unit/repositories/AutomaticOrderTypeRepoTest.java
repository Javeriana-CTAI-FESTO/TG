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

import co.edu.javeriana.tg.entities.managed.AutomaticOrderType;
import co.edu.javeriana.tg.repositories.interfaces.AutomaticOrderTypeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Profile("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AutomaticOrderTypeRepoTest {
    @Autowired
    AutomaticOrderTypeRepository automaticOrderTypeRepository;

    private Long testID = 1L;

    private List<Long> idsList = List.of(2L, 3L, 4L, 5L, 6L);

    @Test
    public void testEmptyCRUD() {
        AutomaticOrderType emptyOrder = new AutomaticOrderType();
        List<AutomaticOrderType> createdList = idsList.stream().map(id -> new AutomaticOrderType())
                .collect(Collectors.toList());
        // Create
        assertThrows(JpaSystemException.class, () -> automaticOrderTypeRepository.save(emptyOrder));
        createdList.forEach(createdElement -> assertThrows(JpaSystemException.class,
                () -> automaticOrderTypeRepository.save(createdElement)));
        // Read
        assertEquals(automaticOrderTypeRepository.count(), 0);
        assertEquals(Optional.empty(), automaticOrderTypeRepository.findById(testID));
        automaticOrderTypeRepository.findAllById(idsList).forEach(element -> assertNull(element));
        // Update
        emptyOrder.setDescription(String.valueOf(testID));
        assertThrows(JpaSystemException.class, () -> automaticOrderTypeRepository.save(emptyOrder));
        createdList.forEach(createdElement -> {
            createdElement.setDescription(String.valueOf(testID));
            assertThrows(JpaSystemException.class, () -> automaticOrderTypeRepository.save(createdElement));
        });
        // Delete
        assertDoesNotThrow(() -> automaticOrderTypeRepository.delete(emptyOrder));
        automaticOrderTypeRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> automaticOrderTypeRepository.delete(element)));
        assertEquals(automaticOrderTypeRepository.count(), 0);
    }

    @Test
    public void testNonEmptyCRUD() {
        AutomaticOrderType order = new AutomaticOrderType(testID);
        List<AutomaticOrderType> createdList = idsList.stream().map(id -> new AutomaticOrderType(id))
                .collect(Collectors.toList());
        // Create
        assertDoesNotThrow(() -> automaticOrderTypeRepository.save(order));
        assertEquals(order, automaticOrderTypeRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> automaticOrderTypeRepository.save(createdElement)));
        assertEquals(order, automaticOrderTypeRepository.findById(testID).get());
        // Read
        assertEquals(automaticOrderTypeRepository.count(), 6);
        assertEquals(order, automaticOrderTypeRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertEquals(createdElement, automaticOrderTypeRepository.findById(createdElement.getId()).get()));
        // Update
        order.setDescription(String.valueOf(testID));
        assertDoesNotThrow(() -> automaticOrderTypeRepository.save(order));
        assertEquals(order, automaticOrderTypeRepository.findById(testID).get());
        createdList.forEach(createdElement -> {
            createdElement.setDescription(String.valueOf(testID));
            assertDoesNotThrow(() -> automaticOrderTypeRepository.save(createdElement));
            assertEquals(createdElement, automaticOrderTypeRepository.findById(createdElement.getId()).get());
        });
        // Delete
        assertDoesNotThrow(() -> automaticOrderTypeRepository.delete(order));
        automaticOrderTypeRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> automaticOrderTypeRepository.delete(element)));
        assertEquals(automaticOrderTypeRepository.count(), 0);
    }
}
