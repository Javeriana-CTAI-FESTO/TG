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

import co.edu.javeriana.tg.entities.managed.ResourceForOperation;
import co.edu.javeriana.tg.entities.managed.ResourceForOperationPK;
import co.edu.javeriana.tg.repositories.interfaces.ResourceForOperationRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("unittest")
@Profile("unittest")
public class ResourceForOperationRepoTest {
    @Autowired
    ResourceForOperationRepository resourceForOperationRepository;

    /*@Autowired
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

    private ResourceForOperationPK testID = getWithUniqueID(1L);

    private List<ResourceForOperationPK> idsList = List.of(getWithUniqueID(2L), getWithUniqueID(3L), getWithUniqueID(4L), getWithUniqueID(5L), getWithUniqueID(6L));

    private ResourceForOperationPK getWithUniqueID(Long id){
        return new ResourceForOperationPK(id, id);
    }

    @Test
    public void testEmptyCRUD() {
        ResourceForOperation emptyOrder = new ResourceForOperation();
        List<ResourceForOperation> createdList = idsList.stream().map(id -> new ResourceForOperation())
                .collect(Collectors.toList());
        // Create
        assertThrows(JpaSystemException.class, () -> resourceForOperationRepository.save(emptyOrder));
        createdList.forEach(createdElement -> assertThrows(JpaSystemException.class,
                () -> resourceForOperationRepository.save(createdElement)));
        // Read
        assertEquals(resourceForOperationRepository.count(), 0);
        assertEquals(Optional.empty(), resourceForOperationRepository.findById(testID));
        resourceForOperationRepository.findAllById(idsList).forEach(element -> assertEquals(Optional.empty(), element));
        // Update
        emptyOrder.setCompressedAir(testID.getOperation());
        assertThrows(JpaSystemException.class, () -> resourceForOperationRepository.save(emptyOrder));
        createdList.forEach(createdElement -> {
            createdElement.setCompressedAir(testID.getOperation());
            assertThrows(JpaSystemException.class, () -> resourceForOperationRepository.save(createdElement));
        });
        // Delete
        assertDoesNotThrow(() -> resourceForOperationRepository.delete(emptyOrder));
        resourceForOperationRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> resourceForOperationRepository.delete(element)));
        assertEquals(resourceForOperationRepository.count(), 0);
    }

    @Test
    public void testNonEmptyCRUD() {
        ResourceForOperation order = new ResourceForOperation(testID);
        List<ResourceForOperation> createdList = idsList.stream().map(id -> new ResourceForOperation(id))
                .collect(Collectors.toList());
        // Create
        assertDoesNotThrow(() -> resourceForOperationRepository.save(order));
        assertEquals(order, resourceForOperationRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> resourceForOperationRepository.save(createdElement)));
        assertEquals(order, resourceForOperationRepository.findById(testID).get());
        // Read
        assertEquals(resourceForOperationRepository.count(), 6);
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
        assertDoesNotThrow(() -> resourceForOperationRepository.delete(order));
        resourceForOperationRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> resourceForOperationRepository.delete(element)));
        assertEquals(resourceForOperationRepository.count(), 0);
    }
}
