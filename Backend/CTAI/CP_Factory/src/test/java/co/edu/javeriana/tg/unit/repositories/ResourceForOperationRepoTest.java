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

import co.edu.javeriana.tg.entities.managed.Resource;
import co.edu.javeriana.tg.entities.managed.ResourceForOperation;
import co.edu.javeriana.tg.entities.managed.ResourceForOperationPK;
import co.edu.javeriana.tg.repositories.interfaces.ResourceForOperationRepository;
import co.edu.javeriana.tg.repositories.interfaces.ResourceRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Profile("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ResourceForOperationRepoTest {
    @Autowired
    ResourceForOperationRepository resourceForOperationRepository;

    @Autowired
    ResourceRepository resourceRepository;

    private List<Long> idsListR = List.of(1L, 2L, 3L, 4L, 5L,6L);

    private ResourceForOperationPK testID = getWithUniqueID(1L);

    private List<ResourceForOperationPK> idsList = List.of(getWithUniqueID(2L), getWithUniqueID(3L), getWithUniqueID(4L), getWithUniqueID(5L), getWithUniqueID(6L));

    private ResourceForOperationPK getWithUniqueID(Long id){
        return new ResourceForOperationPK(id, id);
    }

    @Test
    public void testNonEmptyCRUD() {
        ResourceForOperation order = new ResourceForOperation(testID);
        List<ResourceForOperation> createdList = idsList.stream().map(id -> new ResourceForOperation(id))
                .collect(Collectors.toList());
        List<Resource> createdResourcesList = idsListR.stream().map(id -> new Resource(id))
                .collect(Collectors.toList());
        // Create
        createdResourcesList.forEach(createdElement -> assertDoesNotThrow(() -> resourceRepository.save(createdElement)));
        assertDoesNotThrow(() -> resourceForOperationRepository.save(order));
        assertEquals(order, resourceForOperationRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> resourceForOperationRepository.save(createdElement)));
        assertEquals(order, resourceForOperationRepository.findById(testID).get());
        // Read
        assertEquals(resourceForOperationRepository.findAll().size(), 6);
        assertEquals(order, resourceForOperationRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertEquals(createdElement, resourceForOperationRepository.findById(getWithUniqueID(createdElement.getOperation())).get()));
        // Update
        order.setCompressedAir(testID.getOperation());
        assertDoesNotThrow(() -> resourceForOperationRepository.save(order));
        assertEquals(order, resourceForOperationRepository.findById(testID).get());
        createdList.forEach(createdElement -> {
            createdElement.setCompressedAir(testID.getOperation());
            assertDoesNotThrow(() -> resourceForOperationRepository.save(createdElement));
            assertEquals(createdElement, resourceForOperationRepository.findById(getWithUniqueID(createdElement.getResource())).get());
        });
        // Delete
        createdResourcesList.forEach(createdElement -> assertDoesNotThrow(() -> resourceRepository.delete(createdElement)));
        assertDoesNotThrow(() -> resourceForOperationRepository.delete(order));
        resourceForOperationRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> resourceForOperationRepository.delete(element)));
    }
}
