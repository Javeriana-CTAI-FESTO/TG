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

import co.edu.javeriana.tg.entities.managed.Part;
import co.edu.javeriana.tg.repositories.interfaces.PartRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Profile("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PartRepoTest {
    @Autowired
    PartRepository partRepository;

    private Long testID = getWithUniqueID(1L);

    private List<Long> idsList = List.of(getWithUniqueID(2L), getWithUniqueID(3L), getWithUniqueID(4L), getWithUniqueID(5L), getWithUniqueID(6L));

    private Long getWithUniqueID(Long id){
        return id;
    }

    @Test
    public void testEmptyCRUD() {
        Part emptyOrder = new Part();
        List<Part> createdList = idsList.stream().map(id -> new Part())
                .collect(Collectors.toList());
        // Create
        assertThrows(JpaSystemException.class, () -> partRepository.save(emptyOrder));
        createdList.forEach(createdElement -> assertThrows(JpaSystemException.class,
                () -> partRepository.save(createdElement)));
        // Read
        assertEquals(partRepository.count(), 0);
        assertEquals(Optional.empty(), partRepository.findById(testID));
        partRepository.findAllById(idsList).forEach(element -> assertEquals(Optional.empty(), element));
        // Update
        emptyOrder.setDescription("Description");
        assertThrows(JpaSystemException.class, () -> partRepository.save(emptyOrder));
        createdList.forEach(createdElement -> {
            createdElement.setDescription("Description");
            assertThrows(JpaSystemException.class, () -> partRepository.save(createdElement));
        });
        // Delete
        assertDoesNotThrow(() -> partRepository.delete(emptyOrder));
        partRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> partRepository.delete(element)));
        assertEquals(partRepository.count(), 0);
    }

    @Test
    public void testNonEmptyCRUD() {
        Part order = new Part(testID);
        List<Part> createdList = idsList.stream().map(id -> new Part(id))
                .collect(Collectors.toList());
        // Create
        assertDoesNotThrow(() -> partRepository.save(order));
        assertEquals(order, partRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> partRepository.save(createdElement)));
        assertEquals(order, partRepository.findById(testID).get());
        // Read
        assertEquals(partRepository.count(), 6);
        assertEquals(order, partRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertEquals(createdElement, partRepository.findById(getWithUniqueID(createdElement.getPartNumber())).get()));
        // Update
        order.setDescription("Description");
        assertDoesNotThrow(() -> partRepository.save(order));
        assertEquals(order, partRepository.findById(testID).get());
        createdList.forEach(createdElement -> {
            createdElement.setDescription("Description");
            assertDoesNotThrow(() -> partRepository.save(createdElement));
            assertEquals(createdElement, partRepository.findById(getWithUniqueID(createdElement.getPartNumber())).get());
        });
        // Delete
        assertDoesNotThrow(() -> partRepository.delete(order));
        partRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> partRepository.delete(element)));
        assertEquals(partRepository.count(), 0);
    }
}
