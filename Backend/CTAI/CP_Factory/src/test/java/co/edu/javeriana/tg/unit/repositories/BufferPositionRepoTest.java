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

import co.edu.javeriana.tg.entities.managed.BufferPosition;
import co.edu.javeriana.tg.entities.managed.BufferPositionPK;
import co.edu.javeriana.tg.repositories.interfaces.BufferPositionRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Profile("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BufferPositionRepoTest {
    @Autowired
    BufferPositionRepository bufferPositionRepository;

    private BufferPositionPK testID = getWithUniqueID(1L);

    private List<BufferPositionPK> idsList = List.of(getWithUniqueID(2L), getWithUniqueID(3L), getWithUniqueID(4L), getWithUniqueID(5L), getWithUniqueID(6L));

    private BufferPositionPK getWithUniqueID(Long id){
        return new BufferPositionPK(id, id, id);
    }

    @Test
    public void testNonEmptyCRUD() {
        BufferPosition order = new BufferPosition(testID);
        List<BufferPosition> createdList = idsList.stream().map(id -> new BufferPosition(id))
                .collect(Collectors.toList());
        // Create
        assertDoesNotThrow(() -> bufferPositionRepository.save(order));
        assertEquals(order, bufferPositionRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> bufferPositionRepository.save(createdElement)));
        assertEquals(order, bufferPositionRepository.findById(testID).get());
        // Read
        assertEquals(bufferPositionRepository.count(), 6);
        assertEquals(order, bufferPositionRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertEquals(createdElement, bufferPositionRepository.findById(getWithUniqueID(createdElement.getBufferNumber())).get()));
        // Update
        order.setPallet(testID.getBufferNumber());
        assertDoesNotThrow(() -> bufferPositionRepository.save(order));
        assertEquals(order, bufferPositionRepository.findById(testID).get());
        createdList.forEach(createdElement -> {
            createdElement.setPallet(testID.getBufferPosition());
            assertDoesNotThrow(() -> bufferPositionRepository.save(createdElement));
            assertEquals(createdElement, bufferPositionRepository.findById(getWithUniqueID(createdElement.getBufferNumber())).get());
        });
        // Delete
        assertDoesNotThrow(() -> bufferPositionRepository.delete(order));
        bufferPositionRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> bufferPositionRepository.delete(element)));
        assertEquals(bufferPositionRepository.count(), 0);
    }
}
