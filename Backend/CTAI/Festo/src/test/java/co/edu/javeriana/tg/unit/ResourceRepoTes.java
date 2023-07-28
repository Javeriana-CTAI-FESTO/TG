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

import co.edu.javeriana.tg.entities.managed.StepDefinition;
import co.edu.javeriana.tg.entities.managed.StepDefinitionPK;
import co.edu.javeriana.tg.repositories.interfaces.StepDefinitionRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("unittest")
@Profile("unittest")
public class ResourceRepoTes {
    @Autowired
    StepDefinitionRepository stepDefinitionRepository;

    /*@Autowired
    StepParameterDefinitionRepository stepParameterDefinitionRepository;

    @Autowired
    TopologyRepository topologyRepository;

    @Autowired
    WorkPlanDefinitionRepository workPlanDefinitionRepository;

    @Autowired
    WorkPlanTypeRepository workPlanTypeRepository; */

    private StepDefinitionPK testID = getWithUniqueID(1L);

    private List<StepDefinitionPK> idsList = List.of(getWithUniqueID(2L), getWithUniqueID(3L), getWithUniqueID(4L), getWithUniqueID(5L), getWithUniqueID(6L));

    private StepDefinitionPK getWithUniqueID(Long id){
        return new StepDefinitionPK(id, id);
    }

    @Test
    public void testEmptyCRUD() {
        StepDefinition emptyOrder = new StepDefinition();
        List<StepDefinition> createdList = idsList.stream().map(id -> new StepDefinition())
                .collect(Collectors.toList());
        // Create
        assertThrows(JpaSystemException.class, () -> stepDefinitionRepository.save(emptyOrder));
        createdList.forEach(createdElement -> assertThrows(JpaSystemException.class,
                () -> stepDefinitionRepository.save(createdElement)));
        // Read
        assertEquals(stepDefinitionRepository.count(), 0);
        assertEquals(Optional.empty(), stepDefinitionRepository.findById(testID));
        stepDefinitionRepository.findAllById(idsList).forEach(element -> assertEquals(Optional.empty(), element));
        // Update
        emptyOrder.setDescription("Name");
        assertThrows(JpaSystemException.class, () -> stepDefinitionRepository.save(emptyOrder));
        createdList.forEach(createdElement -> {
            createdElement.setDescription("Name");
            assertThrows(JpaSystemException.class, () -> stepDefinitionRepository.save(createdElement));
        });
        // Delete
        assertDoesNotThrow(() -> stepDefinitionRepository.delete(emptyOrder));
        stepDefinitionRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> stepDefinitionRepository.delete(element)));
        assertEquals(stepDefinitionRepository.count(), 0);
    }

    @Test
    public void testNonEmptyCRUD() {
        StepDefinition order = new StepDefinition(testID);
        List<StepDefinition> createdList = idsList.stream().map(id -> new StepDefinition(id))
                .collect(Collectors.toList());
        // Create
        assertDoesNotThrow(() -> stepDefinitionRepository.save(order));
        assertEquals(order, stepDefinitionRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertDoesNotThrow(() -> stepDefinitionRepository.save(createdElement)));
        assertEquals(order, stepDefinitionRepository.findById(testID).get());
        // Read
        assertEquals(stepDefinitionRepository.count(), 6);
        assertEquals(order, stepDefinitionRepository.findById(testID).get());
        createdList.forEach(createdElement -> assertEquals(createdElement, stepDefinitionRepository.findById(getWithUniqueID(createdElement.getWorkPlan())).get()));
        // Update
        order.setDescription("Name");
        assertDoesNotThrow(() -> stepDefinitionRepository.save(order));
        assertEquals(order, stepDefinitionRepository.findById(testID).get());
        createdList.forEach(createdElement -> {
            createdElement.setDescription("Name");
            assertDoesNotThrow(() -> stepDefinitionRepository.save(createdElement));
            assertEquals(createdElement, stepDefinitionRepository.findById(getWithUniqueID(createdElement.getStepNumber())).get());
        });
        // Delete
        assertDoesNotThrow(() -> stepDefinitionRepository.delete(order));
        stepDefinitionRepository.findAllById(idsList)
                .forEach(element -> assertDoesNotThrow(() -> stepDefinitionRepository.delete(element)));
        assertEquals(stepDefinitionRepository.count(), 0);
    }
}
