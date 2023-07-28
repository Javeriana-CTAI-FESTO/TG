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

import co.edu.javeriana.tg.entities.managed.Resource;
import co.edu.javeriana.tg.repositories.interfaces.ResourceRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("unittest")
@Profile("unittest")
public class ResourceRepoTest {
    @Autowired
    ResourceRepository resourceRepository;

    /*@Autowired
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
        Resource emptyOrder = new Resource();
        List<Resource> createdList = idsList.stream().map(id -> new Resource())
                .collect(Collectors.toList());
        // Create
        assertThrows(JpaSystemException.class, () -> resourceRepository.save(emptyOrder));
        createdList.forEach(createdElement -> assertThrows(JpaSystemException.class,
                () -> resourceRepository.save(createdElement)));
        // Read
        assertEquals(resourceRepository.count(), 0);
        assertEquals(Optional.empty(), resourceRepository.findById(testID));
        resourceRepository.findAllById(idsList).forEach(element -> assertEquals(Optional.empty(), element));
        // Update
        emptyOrder.setName("Name");
        assertThrows(JpaSystemException.class, () -> resourceRepository.save(emptyOrder));
        createdList.forEach(createdElement -> {
            createdElement.setName("Name");
            assertThrows(JpaSystemException.class, () -> resourceRepository.save(createdElement));
        });
        // Delete
        assertDoesNotThrow(() -> resourceRepository.delete(emptyOrder));
        resourceRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> resourceRepository.delete(element)));
        assertEquals(resourceRepository.count(), 0);
    }

    @Test
    public void testNonEmptyCRUD() {
        Resource order = new Resource(testID);
        List<Resource> createdList = idsList.stream().map(id -> new Resource(id))
                .collect(Collectors.toList());
        // Create
        assertDoesNotThrow(() -> resourceRepository.save(order));
        assertEquals(order, resourceRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> resourceRepository.save(createdElement)));
        assertEquals(order, resourceRepository.findById(testID).get());
        // Read
        assertEquals(resourceRepository.count(), 6);
        assertEquals(order, resourceRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertEquals(createdElement, resourceRepository.findById(getWithUniqueID(createdElement.getId())).get()));
        // Update
        order.setName("Name");
        assertDoesNotThrow(() -> resourceRepository.save(order));
        assertEquals(order, resourceRepository.findById(testID).get());
        createdList.forEach(createdElement -> {
            createdElement.setName("Name");
            assertDoesNotThrow(() -> resourceRepository.save(createdElement));
            assertEquals(createdElement, resourceRepository.findById(getWithUniqueID(createdElement.getId())).get());
        });
        // Delete
        assertDoesNotThrow(() -> resourceRepository.delete(order));
        resourceRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> resourceRepository.delete(element)));
        assertEquals(resourceRepository.count(), 0);
    }
}
