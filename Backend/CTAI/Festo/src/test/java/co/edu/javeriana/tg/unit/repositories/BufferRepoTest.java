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

import co.edu.javeriana.tg.entities.managed.Buffer;
import co.edu.javeriana.tg.entities.managed.BufferPK;
import co.edu.javeriana.tg.repositories.interfaces.BufferRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Profile("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BufferRepoTest {
    @Autowired
    BufferRepository bufferRepository;

    private BufferPK testID = getWithUniqueID(1L);

    private List<BufferPK> idsList = List.of(getWithUniqueID(2L), getWithUniqueID(3L), getWithUniqueID(4L), getWithUniqueID(5L), getWithUniqueID(6L));

    private BufferPK getWithUniqueID(Long id){
        return new BufferPK(id, id);
    }

    @Test
    public void testNonEmptyCRUD() {
        Buffer order = new Buffer(testID);
        List<Buffer> createdList = idsList.stream().map(id -> new Buffer(id))
                .collect(Collectors.toList());
        // Create
        assertDoesNotThrow(() -> bufferRepository.save(order));
        assertEquals(order, bufferRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> bufferRepository.save(createdElement)));
        assertEquals(order, bufferRepository.findById(testID).get());
        // Read
        assertEquals(bufferRepository.count(), 6);
        assertEquals(order, bufferRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertEquals(createdElement, bufferRepository.findById(getWithUniqueID(createdElement.getBufferNumber())).get()));
        // Update
        order.setBeltNumber(testID.getBufferNumber());
        assertDoesNotThrow(() -> bufferRepository.save(order));
        assertEquals(order, bufferRepository.findById(testID).get());
        createdList.forEach(createdElement -> {
            createdElement.setBeltNumber(testID.getBufferNumber());
            assertDoesNotThrow(() -> bufferRepository.save(createdElement));
            assertEquals(createdElement, bufferRepository.findById(getWithUniqueID(createdElement.getBufferNumber())).get());
        });
        // Delete
        assertDoesNotThrow(() -> bufferRepository.delete(order));
        bufferRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> bufferRepository.delete(element)));
        assertEquals(bufferRepository.count(), 0);
    }
}
